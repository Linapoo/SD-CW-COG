package com.group13.cog.service;

import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.*;
import org.springframework.http.ResponseEntity;

/**
 * Created by Yiran on 2020/3/24.
 */
public interface ForumService {
    /**
     * Get the information of a forum.
     *
     * @param gameId The relevant gameId
     * @return The forum model
     */
    ResponseEntity<Forum> getForumInfo(String gameId);

    /**
     * Get the posts of a forum.
     *
     * @return A List<ForumPost> with pagination
     */
    ResponseEntity<Page<ForumPost>> getForumPosts(String forumId, Integer pageNo, Integer pageSize);

    /**
     * Create a post.
     * Only the forum administrator can create a sticky post.
     *
     * @param forumPost The post model. The forumId and authorId are essential
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the forum or user does not exist.
     */
    int createNewPost(ForumPost forumPost);

    /**
     * Delete a post.
     * Only the post author and forum administrator can delete a post.
     * After deleting the post, all its replies will be deleted as well.
     *
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the post does not exist.
     * Throw {@link IllegalArgumentException} when the user isn't the post author or forum administrator.
     */
    int deletePost(String postId, String userId);

    /**
     * Get replies of a post.
     * The return list is ordered.
     *
     * @return A List<ForumReply> with pagination. Throw {@link DataNotFoundException} when this post does not exist.
     */
    ResponseEntity<Page<ForumReply>> getReplies(String postId, Integer pageNo, Integer pageSize);

    /**
     * Add a reply to a post.
     * Each time a reply added, the relevant post will reset the numReply and finalReplyTime.
     *
     * @param forumReply The reply model. The postId and authorId are essential.
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the post does not exist.
     */
    int addReply(ForumReply forumReply);

    /**
     * Delete a reply.
     * Only the reply author or forum administrator can delete a reply.
     * Each time a reply deleted, the relevant post will reset the numReply and finalReplyTime.
     *
     * @return 1 success, throw {@link DataNotFoundException} when the reply does not exist.
     * Throw {@link IllegalArgumentException} if the user isn't the reply author or the forum administrator.
     */
    int deleteReply(String replyId, String userId);

    /**
     * Stick a post.
     * Only the forum administrator can stick a post.
     *
     * @param stick true stick, false non-stick
     * @return 1 success, throw {@link DataNotFoundException} when the post does not exist.
     * Throw {@link IllegalArgumentException} if the user isn't the forum administrator.
     */
    int stickPost(String postId, String userId, boolean stick);

    /**
     * A user joins a forum.
     *
     * @param userForum The user-forum model. The forumId and userId are essential.
     * @return 1 success 0 undo. Throw {@link DataNotFoundException} when the forum or user does not exist.
     */
    int joinForum(UserForum userForum);

    /**
     * A user quits a forum.
     * The administrator cannot quit the forum.
     *
     * @param userForum The user-forum model. The forumId and userId are essential.
     * @return 1 success 0 undo. Throw {@link DataNotFoundException} if the user isn't the forum member.
     * Throw {@link IllegalArgumentException} when the administrator tries to quit the forum.
     */
    int quitForum(UserForum userForum);

    /**
     * Check if the user is the forum member.
     *
     * @return true is a member, otherwise not.
     */
    boolean isForumMember(String forumId, String userId);
}
