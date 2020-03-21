package com.group13.cog.model.response;

import com.group13.cog.model.User;

/**
 * Created by Yiran on 2020/3/21.
 */
public class UserInfoResp {
    private User user;

    /* 1 true 0 false */
    private Integer isFriend;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }
}
