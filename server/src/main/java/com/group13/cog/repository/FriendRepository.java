package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
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
public class FriendRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Add a new friend to user' friend collection
     *
     * @param userId
     * @param friendId
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public int addNewFriend(String userId, String friendId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User friendRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(friendId), User.class);
       if (friendRes != null) {
            Update update = new Update().addToSet("friends", friendRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The friend id <%s> not exits.", friendId));
        }
    } 

    /**
     * Delete a friend from user's friend collection
     *
     * @param userId
     * @param friendId
     * @return 1 sucuess, otherwise throw {@link DataDuplicateException}
     */
    public int deleteFriend(String userId, String friendId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User friendRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(friendId)), User.class);
        if (friendRes != null) {
            Update update = new Update().pull("friends", friendRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The friend id <%s> not exits.", friendId));
        }
    }
    
    /**
     * view a user's friend collection
     *
     * @param userId
     * @return a List<Friend> for the user
     */
    public List<Friend> viewUserFriend(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        List<Friend> friends = user.getFriends();
        return friends;
    }
   
    /**
     * Find a friend by id from user's friend collection.
     *
     * @param friendId The user id of the friend
     * @return the friend's user information
     */
    public Friend findByIdToUser(String friendId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("friendId").is(friendId));
        return mongoTemplate.findone(query, User.class);
    }
    
    /**
     * Find a friend by name from user's friend collection.
     *
     * @param friendName The user name of the friend
     * @return the friend's user information
     */
    public Friend findByNameToUser(String friendName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("friendName").is(friendName));
        return mongoTemplate.findone(query, User.class);
    }
    
    /**
     * Find a friend by name from all users.
     *
     * @param FriendName The name of the friend
     * @return the friend's user information
     */
     public Friend findByName(String friendName){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(friendName));
        User friendRes = mongoTemplate.findone(query, User.class);
        if(friendRes != null){
            return friendRes;
        }else{
            throw new DataDuplicateException(String.format("The friend name <%s> does not exits.", friendName));
        } 
     }
     
    /**
     * Find a friend by id from all users.
     *
     * @param friendId The id of the friend
     * @return the friend's user information
     */
     public Friend findById(String friendId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(friendId));
        User friendRes = mongoTemplate.findone(query, User.class);
        if(friendRes != null){
            return friendRes;
        }else{
            throw new DataDuplicateException(String.format("The friend id <%s> does not exits.", friendId));
        } 
     }
}