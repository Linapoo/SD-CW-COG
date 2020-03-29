package com.group13.cog.controller;

import com.group13.cog.model.*;
import com.group13.cog.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

/**
 * Created by Yiran on 2020/3/23.
 */

@RequestMapping("/api/forum/")
@RestController
@Validated
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping("getForumInfo")
    public ResponseEntity<Forum> getForumInfo(@NotBlank @RequestParam(value = "gameId") String gameId) {
        return forumService.getForumInfo(gameId);
    }

    @GetMapping("getPosts")
    public ResponseEntity<Page<ForumPost>> getForumPosts(@NotBlank @RequestParam(value = "forumId") String forumId,
                                                         @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                         @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return forumService.getForumPosts(forumId, pageNo, pageSize);
    }

    @PostMapping("createPost")
    public int createNewPost(@RequestBody ForumPost forumPost) {
        if (StringUtils.isEmpty(forumPost.getTitle()) || StringUtils.isEmpty(forumPost.getForumId())
                || StringUtils.isEmpty(forumPost.getAuthorId()))
            throw new InvalidParameterException("title, forumId and authorId cannot be null");
        LocalDateTime dateTime = LocalDateTime.now();
        forumPost.setCreateTime(dateTime);
        forumPost.setFinalReplyTime(dateTime);
        forumPost.setNumReply(0);
        return forumService.createNewPost(forumPost);
    }

    @DeleteMapping("deletePost")
    public int deletePost(@RequestBody String deleteInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(deleteInfo);
        if (!jsonObject.has("postId") || !jsonObject.has("userId"))
            throw new InvalidParameterException("postId and userId cannot be null");
        return forumService.deletePost(jsonObject.getString("postId"), jsonObject.getString("userId"));
    }

    @GetMapping("getReplies")
    public ResponseEntity<Page<ForumReply>> getReplies(@NotBlank @RequestParam(value = "postId") String postId,
                                                       @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                       @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return forumService.getReplies(postId, pageNo, pageSize);
    }

    @PostMapping("addReply")
    public int addReply(@RequestBody ForumReply forumReply) {
        if (StringUtils.isEmpty(forumReply.getPostId()) || StringUtils.isEmpty(forumReply.getAuthorId())
                || StringUtils.isEmpty(forumReply.getContent()))
            throw new InvalidParameterException("postId, authorId and content cannot be null");
        forumReply.setReplyTime(LocalDateTime.now());
        return forumService.addReply(forumReply);
    }

    @DeleteMapping("deleteReply")
    public int deleteReply(@RequestBody String deleteInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(deleteInfo);
        if (!jsonObject.has("replyId") || !jsonObject.has("userId"))
            throw new InvalidParameterException("replyId and userId cannot be null");
        return forumService.deleteReply(jsonObject.getString("replyId"), jsonObject.getString("userId"));
    }

    @PostMapping("stickPost")
    public int stickPost(@RequestBody String requestInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(requestInfo);
        if (!jsonObject.has("postId") || !jsonObject.has("userId") || !jsonObject.has("stick"))
            throw new InvalidParameterException("postId, userId and stick cannot be null");
        return forumService.stickPost(jsonObject.getString("postId"), jsonObject.getString("userId"),
                jsonObject.getBoolean("stick"));
    }

//    @PostMapping("userJoinForum")
//    public int joinForum(@RequestBody UserForum userForum) {
//        if (StringUtils.isEmpty(userForum.getForumId()) || StringUtils.isEmpty(userForum.getUserId()))
//            throw new InvalidParameterException("forumId and userId cannot be null");
//        return forumService.joinForum(userForum);
//    }
//
//    @DeleteMapping("userQuitForum")
//    public int quitForum(@RequestBody UserForum userForum) {
//        if (StringUtils.isEmpty(userForum.getForumId()) || StringUtils.isEmpty(userForum.getUserId()))
//            throw new InvalidParameterException("forumId and userId cannot be null");
//        return forumService.quitForum(userForum);
//    }
//
//    @GetMapping("isForumMember")
//    public boolean isForumMember(@NotBlank @RequestParam(value = "forumId") String forumId,
//                                 @NotBlank @RequestParam(value = "userId") String userId) {
//        return forumService.isForumMember(forumId, userId);
//    }
}
