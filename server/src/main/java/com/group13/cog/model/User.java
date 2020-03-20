package com.group13.cog.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Yiran on 2020/2/26.
 */

@Document("user")
public class User {
    @Id
    private String id;

    private String userName;

    private String pwd;

    private String email;

    private String avatar;

    private String city;

    /* 0 male 1 female */
    private Integer gender;

    private Integer age;
    
    @DBRef
    private List<Game> games;
    
    public User(String userName, String email, String avatar, String city, Integer gender, Integer age) {
        this.userName = userName;
        this.email = email;
        this.avatar = avatar;
        this.city = city;
        this.gender = gender;
        this.age = age;
    }

    @JsonIgnore
    public List<Game> getGames(){
        return games;
    }
    public void setGames(List<Game> games){
        this.games = games;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @JsonIgnore
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
