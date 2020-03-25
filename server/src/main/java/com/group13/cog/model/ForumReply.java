package com.group13.cog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by Yiran on 2020/3/23.
 */

@Document("forum_reply")
public class ForumReply {
    @Id
    private String id;

    @DBRef
    private ForumPost post;

    private String content;

    @DBRef
    private User author;

    private LocalDateTime replyTime;

    @DBRef
    private ForumReply targetReply;

    @Transient
    private String postId;

    @Transient
    private String authorId;

    @Transient
    private String targetReplyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public ForumPost getPost() {
        return post;
    }

    public void setPost(ForumPost post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }

    public ForumReply getTargetReply() {
        return targetReply;
    }

    public void setTargetReply(ForumReply targetReply) {
        this.targetReply = targetReply;
    }

    /**
     * Only use for specific APIs, not for common use
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * Only use for specific APIs, not for common use
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     * Only use for specific APIs, not for common use
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getTargetReplyId() {
        return targetReplyId;
    }

    public void setTargetReplyId(String targetReplyId) {
        this.targetReplyId = targetReplyId;
    }
}
