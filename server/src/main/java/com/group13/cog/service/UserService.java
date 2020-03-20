package com.group13.cog.service;

import com.group13.cog.model.User;
import org.springframework.http.ResponseEntity;

/**
 * Created by Yiran on 2020/3/1.
 */
public interface UserService {

    /**
     * User register
     *
     * @param user The user model
     * @return Return a user model to the client if success, 
     * otherwise throw {@link com.group13.cog.exception.DataDuplicateException}
     */
    User signUp(User user);

    /**
     * User login
     *
     * @param userName The username
     * @param pwd      The password
     * @return Return a user model to the client if success, otherwise return null,
     * status both are {@link org.springframework.http.HttpStatus.OK}
     */
    ResponseEntity<User> signIn(String userName, String pwd);

    /**
     * Update user information
     *
     * @param user The user model
     * @return Return a user model to the client if success, otherwise return null,
     * status both are {@link org.springframework.http.HttpStatus.OK}
     */
    ResponseEntity<User> updateUserInfo(User user);

    /**
     * Reset user password
     *
     * @param uid    The user id
     * @param oldPwd The old password
     * @param newPwd The new password
     * @return 1 success, 0 failed or undo, or throw {@link com.group13.cog.exception.DataNotFoundException}
     * if uid and oldPwd don't match
     */
    int resetPwd(String uid, String oldPwd, String newPwd);

    /**
     * Update user avatar
     *
     * @param uid      The user id
     * @param filename The filename, e.g., avatar_${uid}.png
     * @return Return avatar filename if success, otherwise return null
     */
    String updateAvatar(String uid, String filename);

    /**
     * find a user by Id
     * @param uid
     * @return Return a user model to the client if success, or throw {@link com.group13.cog.exception.DataNotFoundException}
     */
    ResponseEntity<User> findbyId(String uid);
}
