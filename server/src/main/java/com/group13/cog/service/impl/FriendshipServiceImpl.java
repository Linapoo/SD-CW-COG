package com.group13.cog.service.impl;

import com.group13.cog.model.response.FriendRequest;
import com.group13.cog.model.Friendship;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.repository.FriendRepository;
import com.group13.cog.service.FriendshipService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Yiran on 2020/3/16.
 */

@Service
public class FriendshipServiceImpl implements FriendshipService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public int requestNewFriend(String senderId, String receiverId, String message) {
        return friendRepository.addRequest(new ObjectId(senderId), new ObjectId(receiverId), message);
    }

    @Override
    public ResponseEntity<Page<FriendRequest>> getFriendRequests(String userId, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(friendRepository.getFriendRequests(new ObjectId(userId), pageNo, pageSize), HttpStatus.OK);
    }

    @Override
    public int acceptFriendRequest(String userId, String friendId) {
        return friendRepository.acceptRequest(new ObjectId(userId), new ObjectId(friendId));
    }

    @Override
    public ResponseEntity<Page<User>> getUserFriends(String uid, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(friendRepository.viewUserFriends(new ObjectId(uid), pageNo, pageSize), HttpStatus.OK);
    }

    @Override
    public int deleteFriend(String uid, String friendId) {
        return friendRepository.deleteFriend(new ObjectId(uid), new ObjectId(friendId));
    }

    @Override
    public ResponseEntity<Page<Friendship>> getRecommendFriends(String uid, Integer pageNo, Integer pageSize) {
        return null;
    }
}
