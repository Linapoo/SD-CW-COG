package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.Page;
import com.group13.cog.model.Review;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ReviewRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
   
    /**
     * save a new review
     * 
     * @param Review The review
     * @return Review if success
     */
    public Review saveReview(Review review) {
        Review ReviewRes = mongoTemplate.insert(review);
        return ReviewRes;
    }

    /**
     * check if user posted a review for a game
     * 
     * @param gameId
     * @param userId
     * @return Review if it exists or Null
     */
    public int checkReviewExist(String gameId, String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("reviewer.$id").is(new ObjectId(userId)));
        query.addCriteria(Criteria.where("game.$id").is(new ObjectId(gameId)));
        Review ReviewRes = mongoTemplate.findOne(query, Review.class);
        if (ReviewRes != null){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * delete a Review
     * 
     * @param reviewId
     * @return 1 if success otherwise throw dataNotFoundException
     */
    public int deleteReview(String reviewId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(reviewId));
        Review reviewRes = mongoTemplate.findAndRemove(query, Review.class);
        if ( reviewRes != null){
            return 1;
        }else{
            throw new DataNotFoundException(String.format("the reviewId <%s> not exists", reviewId));
        }
    }

    /**
     * update a Review
     * 
     * @param Review
     * @return Review if success
     */
    public Review updateReview(Review review){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(review.getId()));

        Update update = new Update();
        if (!StringUtils.isEmpty(review.getContent()))
            update.set("content", review.getContent());
        if (review.getScore() != null)
            update.set("score", review.getScore()); 
        if (review.getAnonymous() != null)
            update.set("anonymous", review.getAnonymous());
        mongoTemplate.updateFirst(query, update, Review.class);
        return findById(review.getId());
    }

    /**
     * Find a Review by reviewId
     * 
     * @param gameId 
     * @return Review if success
     * otherwise throw DataNotFoundException
     */
    public Review findById(String reviewId) {
        Review review = mongoTemplate.findById(new ObjectId(reviewId), Review.class);
        if (review != null){
            return review;
        }else{
            throw new DataNotFoundException(String.format("The reviewId <%s> not exists", reviewId));
        }
    }

    /**
     * Find all Review for a game
     * 
     * @param gameId
     * @return Page<Review> if success
     */
    public Page<Review> viewGameReview(String gameId, Integer pageSize, Integer pageNo){
        Query query = new Query();
        query.addCriteria(Criteria.where("game.$id").is(new ObjectId(gameId)));
        query.with(Sort.by(Direction.DESC, "postTime"));
        long total = mongoTemplate.count(query, Review.class);
        query.skip(pageSize*(pageNo-1)).limit(pageSize);
        List<Review> data = mongoTemplate.find(query, Review.class);
        int totalPage = (int) Math.ceil((double) total/pageSize);
        Page<Review> page = new Page<Review>(data, pageSize, pageNo, totalPage);
        return page;
    }

     /**
     * Find all Review for a User
     * 
     * @param userId
     * @return Page<Review> if success
     */
    public Page<Review> viewUserReview(String userId, Integer pageSize, Integer pageNo){
        Query query = new Query();
        query.addCriteria(Criteria.where("reviewer.$id").is(new ObjectId(userId)));
        //query.with(Sort.by(Direction.ASC, "gameName"));
        query.with(Sort.by(Direction.DESC, "postTime"));
        long total = mongoTemplate.count(query, Review.class);
        query.skip(pageSize*(pageNo-1)).limit(pageSize);
        List<Review> data = mongoTemplate.find(query, Review.class);
        int totalPage = (int) Math.ceil((double) total/pageSize);
        Page<Review> page = new Page<Review>(data, pageSize, pageNo, totalPage);
        return page;
    }
}