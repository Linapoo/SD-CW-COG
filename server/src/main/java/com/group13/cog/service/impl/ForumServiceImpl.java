package com.group13.cog.service.impl;

import com.group13.cog.model.*;
import com.group13.cog.repository.ForumRepository;
import com.group13.cog.service.ForumService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Yiran on 2020/3/24.
 */
@Service
public class ForumServiceImpl implements ForumService {

    @Autowired
    private ForumRepository forumRepository;

    @Override
    public ResponseEntity<Forum> getForumInfo(String gameId) {
        return new ResponseEntity<>(forumRepository.findForumByGameId(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<ForumPost>> getForumPosts(String forumId, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(forumRepository.findPostsByForumId(new ObjectId(forumId), pageNo, pageSize),
                HttpStatus.OK);
    }

    @Override
    public int createNewPost(ForumPost forumPost) {
        return forumRepository.createPost(forumPost);
    }

    @Override
    public int deletePost(String postId, String userId) {
        return forumRepository.deletePost(new ObjectId(postId), userId);
    }

    @Override
    public ResponseEntity<Page<ForumReply>> getReplies(String postId, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(forumRepository.getReplies(new ObjectId(postId), pageNo, pageSize), HttpStatus.OK);
    }

    @Override
    public int addReply(ForumReply forumReply) {
        return forumRepository.addReply(forumReply);
    }

    @Override
    public int deleteReply(String replyId, String userId) {
        return forumRepository.deleteReply(replyId, userId);
    }

    @Override
    public int stickPost(String postId, String userId, boolean stick) {
        return forumRepository.stickPost(postId, userId, stick);
    }

    @Override
    public int joinForum(UserForum userForum) {
        return forumRepository.joinForum(userForum);
    }

    @Override
    public int quitForum(UserForum userForum) {
        return forumRepository.quitForum(userForum);
    }

    @Override
    public boolean isForumMember(String forumId, String userId) {
        return forumRepository.isForumMember(userId, forumId);
    }
}
