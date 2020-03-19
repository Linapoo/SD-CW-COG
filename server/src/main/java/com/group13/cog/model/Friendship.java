package com.group13.cog.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by Yiran on 2020/3/16.
 */

@Document("friendship")
public class Friendship {
    @Id
    private String id;

    private ObjectId userId;

    private ObjectId friendId;

    /* 0 request 1 friends */
    private Integer status;

    private LocalDateTime requestTime;

    private LocalDateTime acceptTime;

    private String requestMsg;

    public Friendship(ObjectId userId, ObjectId friendId, Integer status, String requestMsg,
                      LocalDateTime requestTime, LocalDateTime acceptTime) {
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
        this.requestMsg = requestMsg;
        this.requestTime = requestTime;
        this.acceptTime = acceptTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getFriendId() {
        return friendId;
    }

    public void setFriendId(ObjectId friendId) {
        this.friendId = friendId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public LocalDateTime getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(LocalDateTime acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }
}
