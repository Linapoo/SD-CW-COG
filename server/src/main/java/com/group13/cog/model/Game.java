package com.group13.cog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private String type;

    private String link;

    private Double price;

    private String artist;

    private String designer;

    private String publisher;

    private Integer timePerRound;

    private Integer year;

    private Integer playerAge;

    private String image;

    public Game(String gameName, String publisher, String artist, String designer, String description,
            Integer timePerRound, Integer year, Integer playerAge, String type, String link, Double price) {
        this.setGameName(gameName);
        this.setDescription(description);
        this.setArtist(artist);
        this.setDesigner(designer);
        this.setPublisher(publisher);
        this.setTimePerRound(timePerRound);
        this.setYear(year);
        this.setPlayerAge(playerAge);
        this.setType(type);
        this.setlink(link);
        this.setPrice(price);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getlink() {
        return link;
    }

    public void setlink(String selllink) {
        this.link = selllink;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @JsonIgnore
    public String getImage(){
        return image;
    }

    public void setId(String id){
        this.id = id;
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