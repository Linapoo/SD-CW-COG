package com.group13.cog.model;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("review")
public class Review {
    @Id
    private String id;

    @DBRef
    private User reviewer;

    private String gameId;

    private String content;

    private Integer score;

    private LocalDateTime postTime;

    private Integer anonymous;

    public Review(String content, Integer score, Integer anonymous){
        this.setContent(content);
        this.setScore(score);
        this.setAnonymous(anonymous);
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setReviewer(User reviewer){
        this.reviewer = reviewer;
    }

    public User getReviewer(){
        if (anonymous == 0){
            return reviewer;
        }else{
            return null;
        }
    }
    
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setScore(Integer score){
        this.score = score;
    }

    public Integer getScore(){
        return score;
    }

    public void setPostTime(LocalDateTime postTime){
        this.postTime = postTime;
    }

    public LocalDateTime getPostTime(){
        return postTime;
    }

    public void setAnonymous(Integer anonymous){
        this.anonymous = anonymous;
    }

    public Integer getAnonymous(){
        return anonymous;
    }
}