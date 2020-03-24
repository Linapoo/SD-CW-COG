package com.group13.cog.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by Yiran on 2020/3/21.
 */

@Document("user_club")
public class UserClub {
    @Id
    private String id;

    private ObjectId clubId;

    private ObjectId userId;

    private LocalDateTime joinTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectId getClubId() {
        return clubId;
    }

    public void setClubId(ObjectId clubId) {
        this.clubId = clubId;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }
}
