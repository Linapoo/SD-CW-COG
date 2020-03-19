package com.group13.cog.service;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.FriendRequest;
import com.group13.cog.model.Friendship;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import org.springframework.http.ResponseEntity;

/**
 * Created by Yiran on 2020/3/16.
 */
public interface FriendshipService {
    /**
     * Request a new friend
     *
     * @param senderId   The request sender
     * @param receiverId The request receiver
     * @param message    Request message
     * @return 1 success, 0 failed to accept the request, throw {@link DataDuplicateException} when the sender has
     * already sent a friend request to the receiver.
     */
    int requestNewFriend(String senderId, String receiverId, String message);

    /**
     * Get a user's friend requests
     *
     * @param pageNo The first page is 1
     * @return A friend request list with pagination
     */
    ResponseEntity<Page<FriendRequest>> getFriendRequests(String userId, Integer pageNo, Integer pageSize);

    /**
     * Accept a friend request
     *
     * @param userId   The user is going to accept
     * @param friendId The user is going to be accepted(e.g., a request sender)
     * @return 1 success 0 error or undo, throw {@link DataNotFoundException} when the request doesn't exist.
     */
    int acceptFriendRequest(String userId, String friendId);

    /**
     * Get a user's friends
     *
     * @param pageNo The first page is 1
     * @return A {@link User} list with pagination
     */
    ResponseEntity<Page<User>> getUserFriends(String uid, Integer pageNo, Integer pageSize);

    /**
     * Delete a friend
     *
     * @return 1 success 0 undo
     */
    int deleteFriend(String uid, String friendId);

    ResponseEntity<Page<Friendship>> getRecommendFriends(String uid, Integer pageNo, Integer pageSize);
}
