package com.group13.cog.controller;

import com.group13.cog.model.response.UserInfoResp;
import com.group13.cog.utils.FileStorage;
import com.group13.cog.model.User;
import com.group13.cog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Yiran on 2020/3/1.
 */

@RequestMapping("/api/user/")
@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    FileStorage filestorage = new FileStorage("upload-files/user");

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@NotBlank @RequestParam(value = "userName") String userName,
                      @NotBlank @RequestParam(value = "password") String pwd,
                      @RequestParam(value = "email", required = false) String email,
                      @RequestParam(value = "city", required = false) String city,
                      @RequestParam(value = "gender", required = false) Integer gender,
                      @RequestParam(value = "age", required = false) Integer age) {
        User user = new User(userName, email, null, city, gender, age);
        user.setPwd(pwd);
        return userService.signUp(user);
    }

    @PostMapping("login")
    public ResponseEntity<User> signIn(@NotBlank @RequestParam(value = "userName") String userName,
                                       @NotBlank @RequestParam(value = "password") String pwd) {
        return userService.signIn(userName, pwd);
    }

    @PutMapping("updateUserInfo")
    public ResponseEntity<User> updateUserInfo(@NotBlank @RequestParam(value = "uid") String uid,
                                               @RequestParam(value = "userName", required = false) String userName,
                                               @RequestParam(value = "email", required = false) String email,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "gender", required = false) Integer gender,
                                               @RequestParam(value = "age", required = false) Integer age) {
        if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(email)
                && StringUtils.isEmpty(city) && gender == null && age == null)
            return new ResponseEntity<>(null, HttpStatus.OK);

        User user = new User(userName, email, null, city, gender, age);
        user.setId(uid);
        return userService.updateUserInfo(user);
    }

    @PutMapping("resetPwd")
    public int resetPwd(@NotBlank @RequestParam(value = "uid") String uid,
                        @NotBlank @RequestParam(value = "oldPwd") String oldPwd,
                        @NotBlank @RequestParam(value = "newPwd") String newPwd) {
        return userService.resetPwd(uid, oldPwd, newPwd);
    }

    @PostMapping("updateAvatar")
    public Integer updateAvatar(@NotBlank @RequestParam(value = "uid") String uid,
                                @NotNull @RequestParam(value = "avatar") MultipartFile avatar) {
        String filename = StringUtils.cleanPath(avatar.getOriginalFilename());
        filename = uid + filename.substring(filename.indexOf('.'));
        User user = userService.findbyId(uid).getBody();
        if (!StringUtils.isEmpty(user.getAvatar())) {
            filestorage.delete(user.getAvatar());
        }
        user.setAvatar(filename);
        filestorage.store(avatar, filename);
        return userService.updateUserInfo(user) == null ? null : 1;
    }

    @GetMapping(value = "getAvatar")
    public ResponseEntity<Resource> getAvatar(@NotBlank @RequestParam(value = "uid") String uid) {
        User user = userService.findbyId(uid).getBody();
        Resource resource = filestorage.loadAsResource(user.getAvatar());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @GetMapping("getUserInfo")
    public ResponseEntity<UserInfoResp> getUserInfo(@NotBlank @RequestParam(value = "uid") String uid,
                                                    @NotBlank @RequestParam(value = "targetId") String targetId) {
        return userService.getUserInfo(uid, targetId);
    }
}
