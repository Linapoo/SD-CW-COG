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

@Document("forum_post")
public class ForumPost {
    @Id
    private String id;

    private String title;

    @DBRef
    private Forum forum;

    @DBRef
    private User author;

    private String content;

    private LocalDateTime createTime;

    private boolean sticky;

    private Integer numReply;

    private LocalDateTime finalReplyTime;

    @Transient
    private String forumId;

    @Transient
    private String authorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Only use for specific APIs, not for common use
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getForumId() {
        return forumId;
    }

    public void setForumId(String forumId) {
        this.forumId = forumId;
    }

    @JsonIgnore
    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public boolean isSticky() {
        return sticky;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public Integer getNumReply() {
        return numReply;
    }

    public void setNumReply(Integer numReply) {
        this.numReply = numReply;
    }

    public LocalDateTime getFinalReplyTime() {
        return finalReplyTime;
    }

    public void setFinalReplyTime(LocalDateTime finalReplyTime) {
        this.finalReplyTime = finalReplyTime;
    }
}
