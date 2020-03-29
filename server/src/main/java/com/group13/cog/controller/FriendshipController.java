package com.group13.cog.controller;

import com.group13.cog.model.response.FriendRequest;
import com.group13.cog.model.Friendship;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;

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
    public int requestNewFriend(@NotBlank @RequestBody String requestInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(requestInfo);
        if (!jsonObject.has("senderId") || !jsonObject.has("receiverId"))
            throw new InvalidParameterException("senderId and receiverId cannot be null");

        String message = null;
        if (jsonObject.has("message")) message = jsonObject.getString("message");
        return friendshipService.requestNewFriend(jsonObject.getString("senderId"),
                jsonObject.getString("receiverId"), message);
    }

    @GetMapping("getFriendRequests")
    public ResponseEntity<Page<FriendRequest>> getFriendRequests(@NotBlank @RequestParam(value = "userId") String userId,
                                                                 @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                                 @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getFriendRequests(userId, pageNo, pageSize);
    }

    @PostMapping("acceptFriendship")
    public int acceptFriendRequest(@NotBlank @RequestBody String requestInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(requestInfo);
        if (!jsonObject.has("userId") || !jsonObject.has("friendId"))
            throw new InvalidParameterException("userId and friendId cannot be null");

        return friendshipService.acceptFriendRequest(jsonObject.getString("userId"),
                jsonObject.getString("friendId"));
    }

    @GetMapping("myFriends")
    public ResponseEntity<Page<User>> getUserFriends(@NotBlank @RequestParam(value = "userId") String uid,
                                                     @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                     @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getUserFriends(uid, pageNo, pageSize);
    }

    @DeleteMapping("delete")
    public int deleteFriend(@NotBlank @RequestBody String deleteInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(deleteInfo);
        if (!jsonObject.has("userId") || !jsonObject.has("friendId"))
            throw new InvalidParameterException("userId and friendId cannot be null");

        return friendshipService.deleteFriend(jsonObject.getString("userId"),
                jsonObject.getString("friendId"));
    }

    @GetMapping("recommend")
    public ResponseEntity<Page<Friendship>> getRecommendFriends(@NotBlank @RequestParam(value = "uid") String uid,
                                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                                @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return friendshipService.getRecommendFriends(uid, pageNo, pageSize);
    }
}
