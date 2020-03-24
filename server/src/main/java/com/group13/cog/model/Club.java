package com.group13.cog.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Yiran on 2020/3/21.
 */

@Document("club")
public class Club {
    @Id
    private String id;

    private String clubName;

    private String city;

    private String announcement;

    private ObjectId founderId;

    public Club(String clubName, String city, String announcement, ObjectId founderId) {
        this.clubName = clubName;
        this.city = city;
        this.announcement = announcement;
        this.founderId = founderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public ObjectId getFounderId() {
        return founderId;
    }

    public void setFounderId(ObjectId founderId) {
        this.founderId = founderId;
    }
}
