package com.group13.cog.repository;

import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Yiran on 2020/3/24.
 */

@Component
public class ForumRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    /**
     * Find a forum by its relevant gameId
     *
     * @return The forum model
     */
    public Forum findForumByGameId(String gameId) {
        return mongoTemplate.findOne(new Query(Criteria.where("game.id").is(gameId)), Forum.class);
    }

    /**
     * Find a forum by forumId
     *
     * @return The forum model
     */
    public Forum findForumById(ObjectId forumId) {
        return mongoTemplate.findById(forumId, Forum.class);
    }

    /**
     * Find a post by postId
     *
     * @return The post model
     */
    public ForumPost findPostById(ObjectId postId) {
        return mongoTemplate.findById(postId, ForumPost.class);
    }

    /**
     * Find a reply by replyId
     *
     * @return The reply model
     */
    public ForumReply findReplyById(ObjectId replyId) {
        return mongoTemplate.findById(replyId, ForumReply.class);
    }

    /**
     * Get the number of replies of a post by postId
     *
     * @return The number of replies of the post
     */
    public long getReplyNumByPostId(String postId) {
        return mongoTemplate.count(new Query(Criteria.where("post.$id").is(new ObjectId(postId))), ForumReply.class);
    }

    /**
     * Create a forum
     *
     * @param game The game model
     */
    public void createForum(Game game) {
        User publisher = userRepository.findById(game.getPublisher());
        Forum forum = new Forum();
        forum.setAdmin(publisher);
        forum.setGame(game);
        forum = mongoTemplate.insert(forum);

        UserForum userForum = new UserForum();
        userForum.setForum(forum);
        userForum.setUser(publisher);
        userForum.setJoinTime(LocalDateTime.now());
        mongoTemplate.insert(userForum);
    }

    /**
     * Get the last response time of a post
     *
     * @return The last response time
     */
    public LocalDateTime getFinalReplyTime(ObjectId postId) {
        ForumPost post = findPostById(postId);
        if (post == null)
            throw new DataNotFoundException(String.format("The post <%s> does not exist.", postId));

        Query query = new Query();
        query.addCriteria(Criteria.where("post.$id").is(postId));
        query.with(Sort.by(Sort.Direction.DESC, "replyTime"));
        ForumReply lastReply = mongoTemplate.findOne(query, ForumReply.class);
        return lastReply == null ? post.getCreateTime() : lastReply.getReplyTime();
    }

    /**
     * Get the posts of a forum.
     * The return list is ordered.
     *
     * @return A List<ForumPost> with pagination
     */
    public Page<ForumPost> findPostsByForumId(ObjectId forumId, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("forum.$id").is(forumId));
        query.with(Sort.by(Sort.Direction.DESC, "sticky", "finalReplyTime"));
        query.skip(pageSize * (pageNo - 1)).limit(pageSize);

        List<ForumPost> list = mongoTemplate.find(query, ForumPost.class);

        int totalPage = (int) Math.ceil((double) mongoTemplate.count(new Query(Criteria.where("forum.$id").is(forumId)),
                ForumPost.class) / pageSize);
        return new Page<>(list, pageSize, pageNo, totalPage);
    }

    /**
     * Create a post.
     * Only the forum member can create a post.
     * Only the forum administrator can create a sticky post.
     *
     * @param forumPost The post model. The forumId and authorId are essential
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the forum or user does not exist,
     * and when the user isn't the forum member.
     */
    public int createPost(ForumPost forumPost) {
        Forum forum = findForumById(new ObjectId(forumPost.getForumId()));
        if (forum == null)
            throw new DataNotFoundException(String.format("The forum <%s> does not exist.", forumPost.getForumId()));

        User user = userRepository.findById(forumPost.getAuthorId());
        if (user == null)
            throw new DataNotFoundException(String.format("The user <%s> does not exist.", forumPost.getAuthorId()));

        if (!isForumMember(forumPost.getAuthorId(), forumPost.getForumId()))
            throw new DataNotFoundException(String.format("The user <%s> isn't the forum member.", forumPost.getAuthorId()));

        forumPost.setForum(forum);
        forumPost.setAuthor(user);
        if (forumPost.isSticky() && !user.getId().equals(forum.getAdmin().getId())) {
            System.out.println("Only the forum administrator can create a sticky post.");
            forumPost.setSticky(false);
        }

        mongoTemplate.insert(forumPost);
        return 1;
    }

    /**
     * Delete a post.
     * Only the post author and forum administrator can delete a post.
     * After deleting the post, all its replies will be deleted as well.
     *
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the post does not exist.
     * Throw {@link IllegalArgumentException} when the user isn't the post author or forum administrator.
     */
    public int deletePost(ObjectId postId, String userId) {
        ForumPost post = findPostById(postId);
        if (post == null)
            throw new DataNotFoundException(String.format("The post <%s> does not exist.", postId));

        if (!userId.equals(post.getAuthor().getId()) && !userId.equals(post.getForum().getAdmin().getId()))
            throw new IllegalArgumentException("Only the post author and forum administrator can delete the post.");

        if (mongoTemplate.remove(post).getDeletedCount() <= 0)
            throw new RuntimeException("Failed to delete the post.");

        // Delete all replies of this post
        Query query = new Query();
        query.addCriteria(Criteria.where("post.$id").is(postId));
        mongoTemplate.remove(query, ForumReply.class);
        return 1;
    }

    /**
     * Get replies of a post.
     * The return list is ordered.
     *
     * @return A List<ForumReply> with pagination. Throw {@link DataNotFoundException} when this post does not exist.
     */
    public Page<ForumReply> getReplies(ObjectId postId, Integer pageNo, Integer pageSize) {
        ForumPost post = findPostById(postId);
        if (post == null)
            throw new DataNotFoundException(String.format("The post <%s> does not exist.", postId));

        Query query = new Query();
        query.addCriteria(Criteria.where("post.$id").is(postId));
        query.with(Sort.by(Sort.Direction.ASC, "replyTime"));
        query.skip(pageSize * (pageNo - 1)).limit(pageSize);

        List<ForumReply> replies = mongoTemplate.find(query, ForumReply.class);

        int totalPage = (int) Math.ceil((double) getReplyNumByPostId(postId.toHexString()) / pageSize);
        return new Page<>(replies, pageSize, pageNo, totalPage);
    }

    /**
     * Add a reply to a post.
     * Only the forum member can add a reply.
     * Each time a reply added, the relevant post will reset the numReply and finalReplyTime.
     *
     * @param forumReply The reply model. The postId and authorId are essential.
     * @return 1 success, otherwise throw {@link DataNotFoundException} when the post does not exist or the user isn't
     * the forum member.
     */
    public int addReply(ForumReply forumReply) {
        ForumPost post = findPostById(new ObjectId(forumReply.getPostId()));
        if (post == null)
            throw new DataNotFoundException(String.format("The post <%s> does not exist.", forumReply.getPostId()));
        forumReply.setPost(post);

        if (!isForumMember(forumReply.getAuthorId(), post.getForum().getId()))
            throw new DataNotFoundException(String.format("The user <%s> isn't the forum member.", forumReply.getAuthorId()));

        User author = userRepository.findById(forumReply.getAuthorId());
        forumReply.setAuthor(author);

        if (!StringUtils.isEmpty(forumReply.getTargetReplyId())) {
            ForumReply targetReply = findReplyById(new ObjectId(forumReply.getTargetReplyId()));
            forumReply.setTargetReply(targetReply);
        }
        mongoTemplate.insert(forumReply);

        // Update the post
        Update update = new Update();
        update.set("numReply", getReplyNumByPostId(post.getId()));
        update.set("finalReplyTime", forumReply.getReplyTime());
        if (0 >= mongoTemplate.updateFirst(new Query(Criteria.where("id").is(post.getId())), update, ForumPost.class)
                .getModifiedCount())
            throw new RuntimeException("Failed to attach the reply to the post");
        return 1;
    }

    /**
     * Delete a reply.
     * Only the reply author or forum administrator can delete a reply.
     * Each time a reply deleted, the relevant post will reset the numReply and finalReplyTime.
     *
     * @return 1 success, throw {@link DataNotFoundException} when the reply does not exist.
     * Throw {@link IllegalArgumentException} if the user isn't the reply author or the forum administrator.
     */
    public int deleteReply(String replyId, String userId) {
        ForumReply reply = findReplyById(new ObjectId(replyId));
        if (reply == null)
            throw new DataNotFoundException(String.format("The reply <%s> does not exist.", replyId));

        if (!userId.equals(reply.getAuthor().getId()) && !userId.equals(reply.getPost().getForum().getAdmin().getId()))
            throw new IllegalArgumentException("Only the reply author and forum administrator can delete the reply.");

        if (mongoTemplate.remove(reply).getDeletedCount() <= 0)
            throw new RuntimeException("Failed to delete the reply.");

        // Update the post
        ForumPost post = reply.getPost();
        Update update = new Update();
        update.set("numReply", getReplyNumByPostId(post.getId()));
        update.set("finalReplyTime", getFinalReplyTime(new ObjectId(post.getId())));
        if (0 >= mongoTemplate.updateFirst(new Query(Criteria.where("id").is(post.getId())), update, ForumPost.class)
                .getModifiedCount())
            throw new RuntimeException("Failed to detach the reply to the post");
        return 1;
    }

    /**
     * Stick a post.
     * Only the forum administrator can stick a post.
     *
     * @param stick true stick, false non-stick
     * @return 1 success, throw {@link DataNotFoundException} when the post does not exist.
     * Throw {@link IllegalArgumentException} if the user isn't the forum administrator.
     */
    public int stickPost(String postId, String userId, boolean stick) {
        ForumPost post = findPostById(new ObjectId(postId));
        if (post == null)
            throw new DataNotFoundException(String.format("The post <%s> does not exist.", postId));

        if (!userId.equals(post.getForum().getAdmin().getId()))
            throw new IllegalArgumentException("Only the forum administrator can stick the post.");

        Update update = new Update();
        update.set("sticky", stick);
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(postId)), update, ForumPost.class);
        return 1;
    }

    /**
     * A user joins a forum.
     *
     * @param userForum The user-forum model. The forumId and userId are essential.
     * @return 1 success 0 undo. Throw {@link DataNotFoundException} when the forum or user does not exist.
     */
    public int joinForum(UserForum userForum) {
        Forum forum = findForumById(new ObjectId(userForum.getForumId()));
        if (forum == null)
            throw new DataNotFoundException(String.format("The forum <%s> does not exist.", userForum.getForumId()));

        User user = userRepository.findById(userForum.getUserId());
        if (user == null)
            throw new DataNotFoundException(String.format("The user <%s> does not exist.", userForum.getUserId()));

        if (isForumMember(userForum.getUserId(), userForum.getForumId())) {
            System.out.println("This user is already a forum member.");
            return 0;
        }

        userForum.setForum(forum);
        userForum.setUser(user);
        userForum.setJoinTime(LocalDateTime.now());
        mongoTemplate.insert(userForum);
        return 1;
    }

    /**
     * A user quits a forum.
     * The administrator cannot quit the forum.
     *
     * @param userForum The user-forum model. The forumId and userId are essential.
     * @return 1 success 0 undo. Throw {@link DataNotFoundException} if the user isn't the forum member.
     * Throw {@link IllegalArgumentException} when the administrator tries to quit the forum.
     */
    public int quitForum(UserForum userForum) {
        if (!isForumMember(userForum.getUserId(), userForum.getForumId()))
            throw new DataNotFoundException("This user isn't a forum member.");

        Forum forum = findForumById(new ObjectId(userForum.getForumId()));
        if (forum.getAdmin().getId().equals(userForum.getUserId()))
            throw new IllegalArgumentException("The forum administrator cannot quit the forum.");

        Query query = new Query();
        query.addCriteria(Criteria.where("forum.$id").is(new ObjectId(userForum.getForumId())));
        query.addCriteria(Criteria.where("user.$id").is(new ObjectId(userForum.getUserId())));

        DeleteResult result = mongoTemplate.remove(query, UserForum.class);
        return result.getDeletedCount() > 0 ? 1 : 0;
    }

    /**
     * Check if the user is the forum member.
     *
     * @return true is a member, otherwise not.
     */
    public boolean isForumMember(String userId, String forumId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("forum.$id").is(new ObjectId(forumId)));
        query.addCriteria(Criteria.where("user.$id").is(new ObjectId(userId)));
        return mongoTemplate.findOne(query, UserForum.class) != null;
    }
}
