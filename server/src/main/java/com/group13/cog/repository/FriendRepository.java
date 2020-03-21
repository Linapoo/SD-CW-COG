package com.group13.cog.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.response.FriendRequest;
import com.group13.cog.model.Friendship;

import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class FriendRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Add a new friend to user' friend collection.
     * If the receiver has sent a request to the sender before, then the add operation turns to accept operation.
     *
     * @param senderId   The request sender
     * @param receiverId The request receiver
     * @return 1 success, 0 failed to accept the request, throw {@link DataDuplicateException} when the sender has
     * already sent a friend request to the receiver.
     */
    public int addRequest(ObjectId senderId, ObjectId receiverId, String message) {
        Friendship querySender = mongoTemplate.findOne(new Query(
                        Criteria.where("userId").is(senderId).and("friendId").is(receiverId))
                , Friendship.class);
        if (querySender != null)
            throw new DataDuplicateException(String.format("The user:<%s> has sent request before.", senderId));

        // If the receiver has sent request to the sender, then they become friends straight away.
        Friendship queryReceiver = mongoTemplate.findOne(new Query(
                        Criteria.where("userId").is(receiverId).and("friendId").is(senderId))
                , Friendship.class);
        if (queryReceiver != null)
            return acceptRequest(senderId, receiverId);

        Friendship friendship = new Friendship(senderId, receiverId, 0, message, LocalDateTime.now(),
                null);
        mongoTemplate.insert(friendship);
        return 1;
    }

    /**
     * Get the friend requests to this user
     *
     * @param pageNo The first page is 1
     * @return A friend request list with pagination
     */
    public Page<FriendRequest> getFriendRequests(ObjectId userId, Integer pageNo, Integer pageSize) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("user")
                .localField("userId")
                .foreignField("_id")
                .as("user");
        AggregationOperation localMatch = Aggregation.match(Criteria.where("friendId").is(userId).and("status").is(0));
        Aggregation aggregation = Aggregation.newAggregation(localMatch, lookupOperation,
                Aggregation.sort(Sort.by(Direction.DESC, "requestTime")),
                Aggregation.project("user._id", "user.userName", "user.email",
                        "user.avatar", "user.city", "user.gender", "user.age",
                        "requestTime", "requestMsg"));
        List<FriendRequest> list = mongoTemplate.aggregate(aggregation, "friendship", FriendRequest.class)
                .getMappedResults();
        return new Page<>(list, pageSize, pageNo);
    }

    /**
     * Accept a friend request
     *
     * @param userId   The user is going to accept
     * @param friendId The user is going to be accepted(e.g., a request sender)
     * @return 1 success 0 error or undo, throw {@link DataNotFoundException} when the request doesn't exist.
     */
    public int acceptRequest(ObjectId userId, ObjectId friendId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(friendId).and("friendId").is(userId));

        Friendship queryRes = mongoTemplate.findOne(query, Friendship.class);
        if (queryRes != null) {
            if (queryRes.getStatus() == 1) {
                System.out.println("Two users are already friends.");
                return 0;
            }

            LocalDateTime time = LocalDateTime.now();

            Update update = new Update();
            update.set("status", 1);
            update.set("acceptTime", time);

            UpdateResult result = mongoTemplate.updateFirst(query, update, Friendship.class);
            if (result.getModifiedCount() > 0) {
                Friendship friendship = new Friendship(userId, friendId, 1, queryRes.getRequestMsg(),
                        queryRes.getRequestTime(), time);
                mongoTemplate.insert(friendship);
                return 1;
            }
            System.out.println("Failed to accept friend request.");
            return 0;
        } else {
            throw new DataNotFoundException("The request does not exist.");
        }
    }

    /**
     * Delete a friend from user's friend collection
     *
     * @return 1 success 0 undo
     */
    public int deleteFriend(ObjectId userId, ObjectId friendId) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("userId").is(userId).and("friendId").is(friendId),
                Criteria.where("userId").is(friendId).and("friendId").is(userId)
        ).and("status").is(1));
        DeleteResult result = mongoTemplate.remove(query, Friendship.class);
        return result.getDeletedCount() > 0 ? 1 : 0;
    }

    /**
     * View a user's friend collection
     *
     * @param pageNo The first page is 1
     * @return A friend list with pagination
     */
    public Page<User> viewUserFriends(ObjectId userId, Integer pageNo, Integer pageSize) {
        LookupOperation senderLookup = LookupOperation.newLookup()
                .from("friendship")
                .localField("_id")
                .foreignField("friendId")
                .as("friend");

        AggregationOperation localMatch = Aggregation.match(
                Criteria.where("_id").ne(userId));
        AggregationOperation foreignMatch = Aggregation.match(
                Criteria.where("friend.userId").is(userId).and("friend.status").is(1)
        );

        Aggregation aggregation = Aggregation.newAggregation(localMatch, senderLookup, foreignMatch,
                Aggregation.sort(Sort.by(Direction.DESC, "friend.acceptTime")));

        List<User> friends = mongoTemplate.aggregate(aggregation, "user", User.class)
                .getMappedResults();
        return new Page<>(friends, pageSize, pageNo, friends.size());
    }

    /**
     * Find a built friendship of a user.
     *
     * @param userId   The current user
     * @param friendId The friend id
     * @return Return a {@link Friendship} if find, otherwise null
     */
    public Friendship findFriendById(ObjectId userId, ObjectId friendId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("friendId").is(friendId));
        query.addCriteria(Criteria.where("status").is(1));
        return mongoTemplate.findOne(query, Friendship.class);
    }
//
//    /**
//     * Find a friend by name from user's friend collection.
//     *
//     * @param friendName The user name of the friend
//     * @return the friend's user information
//     */
//    public Friend findByNameToUser(String friendName) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("friendName").is(friendName));
//        return mongoTemplate.findone(query, User.class);
//    }
//
//    /**
//     * Find a friend by name from all users.
//     *
//     * @param FriendName The name of the friend
//     * @return the friend's user information
//     */
//    public Friend findByName(String friendName) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is(friendName));
//        User friendRes = mongoTemplate.findone(query, User.class);
//        if (friendRes != null) {
//            return friendRes;
//        } else {
//            throw new DataDuplicateException(String.format("The friend name <%s> does not exits.", friendName));
//        }
//    }
}