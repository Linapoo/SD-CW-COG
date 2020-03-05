package com.group13.cog.service.impl;

import com.group13.cog.model.User;
import com.group13.cog.repository.UserRepository;
import com.group13.cog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Yiran on 2020/3/1.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int signUp(User user) {
        return userRepository.addNewUser(user);
    }

    @Override
    public ResponseEntity<User> signIn(String userName, String pwd) {
        return new ResponseEntity<>(userRepository.login(userName, pwd), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUserInfo(User user) {
        return new ResponseEntity<>(userRepository.updateUserInfo(user), HttpStatus.OK);
    }

    @Override
    public int resetPwd(String uid, String oldPwd, String newPwd) {
        return userRepository.resetPwd(uid, oldPwd, newPwd);
    }

    @Override
    public String updateAvatar(String uid, String filename) {
        User user = new User(null, null, filename, null, null, null);
        user.setId(uid);

        User userRes = userRepository.updateUserInfo(user);
        return userRes == null ? null : userRes.getAvatar();
    }
}
