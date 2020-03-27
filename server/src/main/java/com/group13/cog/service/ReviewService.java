package com.group13.cog.service;

import com.group13.cog.model.Page;
import com.group13.cog.model.Review;

import org.springframework.http.ResponseEntity;

public interface ReviewService {
    
    /**
     * Post new review for a game
     *  
     * @param gameId 
     * @param userId 
     * @return Review if success,
     * otherwise throw DataDuplicateException
     */
    ResponseEntity<Review> postReview(String userId, String gameId, Review review);

    /**
     * 
     * @param userId
     * @param gameId
     * @return  1 if review exist
     */
    int checkReviewExist(String userId, String gameId);

    /**
     * Delete a review for a game
     * 
     * @param SellId 
     * @return 1 if success,
     * otherwise throw DataNotFoundException
     */
    int deleteReview(String reviewId);

    /**
     * update a review
     * 
     * @param Review The review model
     * @return Review if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Review> updateReview(Review review);

    /**
     * view all review to a game
     * 
     * @param gameId
     * @param pageSize
     * @param pageNo
     * @return Page<Review> if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Page<Review>> viewGameReview(String gameId, Integer PageSize, Integer PageNo);

    /**
     * view all review to a User
     * 
     * @param userId
     * @param pageSize
     * @param pageNo
     * @return Page<Review> if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Page<Review>> viewUserReview(String userId, Integer PageSize, Integer PageNo);

    /**
     * view a review by Id
     * 
     * @param reviewId
     * @return Review if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Review> viewReviewById(String reviewId);
}