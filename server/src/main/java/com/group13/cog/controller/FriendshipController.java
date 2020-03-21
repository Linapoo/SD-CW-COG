package com.group13.cog.controller;

import com.group13.cog.model.response.FriendRequest;
import com.group13.cog.model.Friendship;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Yiran on 2020/3/16.
 */

@RequestMapping("/api/friendship/")
@RestController
@Validated
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @PostMapping("sendFriendRequest")
    public int requestNewFriend(@NotBlank @RequestParam(value = "senderId") String senderId,
                                @NotBlank @RequestParam(value = "receiverId") String receiverId,
                                @RequestParam(value = "message", required = false) String message) {
        return friendshipService.requestNewFriend(senderId, receiverId, message);
    }

    @GetMapping("getFriendRequests")
    public ResponseEntity<Page<FriendRequest>> getFriendRequests(@NotBlank @RequestParam(value = "userId") String userId,
                                                                 @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                                 @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getFriendRequests(userId, pageNo, pageSize);
    }

    @PostMapping("acceptFriendship")
    public int acceptFriendRequest(@NotBlank @RequestParam(value = "userId") String userId,
                                   @NotBlank @RequestParam(value = "friendId") String friendId) {
        return friendshipService.acceptFriendRequest(userId, friendId);
    }

    @GetMapping("myFriends")
    public ResponseEntity<Page<User>> getUserFriends(@NotBlank @RequestParam(value = "userId") String uid,
                                                     @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                     @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getUserFriends(uid, pageNo, pageSize);
    }

    @DeleteMapping("delete")
    public int deleteFriend(@NotBlank @RequestParam(value = "userId") String uid,
                            @NotBlank @RequestParam(value = "friendId") String friendId) {
        return friendshipService.deleteFriend(uid, friendId);
    }

    @GetMapping("recommend")
    public ResponseEntity<Page<Friendship>> getRecommendFriends(@NotBlank @RequestParam(value = "uid") String uid,
                                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                                @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getRecommendFriends(uid, pageNo, pageSize);
    }
}
