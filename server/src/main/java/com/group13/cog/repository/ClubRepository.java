package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.model.Club;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class ClubRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Add a new club to club collection.
     *
     * @param Club The club model
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public int addNewClub(Club club) {
        Query query = new Query();
        query.addCriteria(Criteria.where("clubName").is(club.getClubName()));
        Club clubRes = mongoTemplate.findOne(query, Club.class);
        if (clubRes == null) {
            mongoTemplate.insert(club);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The club <%s> exits.", club.getClubName()));
        }
    }

    /**
     * Delete a club from club collection
     *
     * @param clubId The clubId
     * @return 1 success
     */
    public int deleteClub (String clubId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(clubId));
        mongoTemplate.remove(query, Club.class);
        return 1;
    }

    /**
     * Show all the club'list in club collection.
     * @param pageSize the number of game per page
     * @param pageNo the number of page need return 
     * @return a Page<Club> of club including all the clubs' information
     */
     public Page<Club> findAll(Integer pageSize, Integer pageNo){
         Query query = new Query();
         int totalPage = (int) Math.ceil((double)mongoTemplate.count(query, Club.class)/pageSize);
         query.with(Sort.by(Direction.ASC, "clubName"));
         query.skip(pageSize*(pageNo-1)).limit(pageSize);
         List<Club> data = mongoTemplate.find(query, Club.class);
         Page<Club> page = new Page<Club>(data, pageSize, pageNo, totalPage);
         return page;
     }
    
    /**
     * Add a club to user's club collection
     * @param userId
     * @param clubId
     * @return 1 success
     */
    public int addClubToUser(String userId, String clubId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Club clubRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(clubId)), Club.class);
        if (clubRes != null) {
            Update update = new Update().addToSet("clubs", clubRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The club id <%s> not exits.", clubId));
        }
    } 
    
    /**
     * Delete a club from user's club collection
     * @param userId
     * @param clubId
     * @return 1 sucuess
     */
    public int deleteClubToUser(String userId, String clubId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Club clubRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(clubId)), Club.class);
        if (clubRes != null) {
            Update update = new Update().pull("clubs", clubRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The club id <%s> not exits.", clubId));
        }
    }

    /**
     * view a user's club collection
     *
     * @param userId
     * @return a List<Club> for the user
     */
    public List<Club> viewUserClub(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        List<Club> clubs = user.getClubs();
        return clubs;
    }
   
    /**
     * Find a club by club id in club collection.
     *
     * @param clubId The club id
     * @return the game information
     */
    public Club findById(String clubId) {
        return mongoTemplate.findById(new ObjectId(clubId), Club.class);
    }

    /**
     * Find a club by club name in club collection.
     *
     * @param GameName The name of the game
     * @return A user model including the user information
     */
     public Club findByName(String ClubName){
        Query query = new Query();
        query.addCriteria(Criteria.where("clubName").is(ClubName));
        return mongoTemplate.findOne(query, Club.class);
     }
     
    /**
     * Find a club by club id in user's club collection.
     *
     * @param userId The user's id
     * @param clubId The club's id
     * @return the club information success
     */
     public Club findByIdToUser(String userId, String clubId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Club clubRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(clubId)), Club.class);
        if (clubRes != null) {
            return clubRes;
        } else {
            throw new DataDuplicateException(String.format("The club id <%s> not exits.", clubId));
        }
    }
    
    /**
     * Find a club by club name in user's club collection.
     *
     * @param userId The user's id
     * @param clubName The club's name
     * @return the club information success
     */
     public Club findByNameToUser(String userId, String ClubName){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Club clubRes = mongoTemplate.findOne(Query.query(Criteria.where("clubName").is(ClubName)), Club.class);
        if (clubRes != null) {
            return clubRes;
        } else {
            throw new DataDuplicateException(String.format("The club name <%s> not exits.", clubId));
        }
    }
}
