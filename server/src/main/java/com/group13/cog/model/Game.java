package com.group13.cog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Qizhen on 2020/3/10.
 */

@Document("game")
public class Game {
    @Id
    private String id;

    private String gameName;

    private String description;

    private String artist;

    private String designer;

    private String publisher;

    private Integer timePerRound;

    private Integer year;

    private Integer playerAge;

    public Game(String gameName, String publisher, String artist, String designer, String description, Integer timePerRound,
            Integer year, Integer playerAge) {
        this.setGameName(gameName);
        this.setDescription(description);
        this.setArtist(artist);
        this.setDesigner(designer);
        this.setPublisher(publisher);
        this.setTimePerRound(timePerRound);
        this.setYear(year);
        this.setPlayerAge(playerAge);
    }

    public String getId(){
        return id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getTimePerRound() {
        return timePerRound;
    }

    public void setTimePerRound(Integer timePerRound) {
        this.timePerRound = timePerRound;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(Integer playerAge) {
        this.playerAge = playerAge;
    }

   
    
}