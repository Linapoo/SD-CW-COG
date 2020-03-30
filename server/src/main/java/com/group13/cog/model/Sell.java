package com.group13.cog.model;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sell")
public class Sell {
    @Id
    private String id;

    @DBRef
    private User seller;

    @DBRef
    private Game game;

    private String contact;

    private Double price;

    private String description;

    private LocalDateTime postTime;

    public Sell(String contact, Double price, String description) {
        this.setContact(contact);
        this.setPrice(price);
        this.setDescription(description);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getSeller() {
        return seller;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDesciption(){
        return description;
    }

    public void setPostTime(LocalDateTime postTime){
        this.postTime = postTime;
    }
    public LocalDateTime getPostTime(){
        return postTime;
    }
}