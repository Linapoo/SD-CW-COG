package com.group13.cog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by Yiran on 2020/3/25.
 */
@Document("user_forum")
public class UserForum {
    @Id
    private String id;

    @DBRef
    private Forum forum;

    @DBRef
    private User user;

    private LocalDateTime joinTime;

    @Transient
    private String forumId;

    @Transient
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
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

    /**
     * Only use for specific APIs, not for common use
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
