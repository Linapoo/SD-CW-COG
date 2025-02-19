package com.group13.cog.repository;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.Friendship;
import com.group13.cog.model.User;
import com.group13.cog.model.response.UserInfoResp;
import com.mongodb.client.result.UpdateResult;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by Yiran on 2020/3/4.
 */
@Component
public class UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FriendRepository friendRepository;

    /**
     * Add a new user.
     *
     * @param user The user model
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public User addNewUser(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user.getUserName()));

        User userRes = mongoTemplate.findOne(query, User.class);
        if (userRes == null) {
            mongoTemplate.insert(user);
            return user;
        } else {
            throw new DataDuplicateException(String.format("The userName <%s> exits.", user.getUserName()));
        }
    }

    /**
     * User login
     *
     * @param userName The username
     * @param pwd      The password
     * @return A user model including the user information
     */
    public User login(String userName, String pwd) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("pwd").is(pwd));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null){
            return user;
        }else{
            throw new DataNotFoundException(String.format("The userName or the password not correct"));
        }
    }

    /**
     * Find a user by user id.
     *
     * @param id The user id
     * @return A user model including the user information
     */
    public User findById(String id) {
        User user = mongoTemplate.findById(new ObjectId(id), User.class);
        if (user != null) {
            return user;
        } else {
            throw new DataNotFoundException(String.format("The user <%s> not exists", id));
        }
    }

    /**
     * Update user information.
     *
     * @param user The user model
     * @return Return a user model if update success, otherwise null
     * @see #resetPwd(String, String, String) if reset password
     */
    public User updateUserInfo(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));

        Update update = new Update();
        if (!StringUtils.isEmpty(user.getUserName()))
            update.set("userName", user.getUserName());
        if (!StringUtils.isEmpty(user.getEmail()))
            update.set("email", user.getEmail());
        if (!StringUtils.isEmpty(user.getAvatar()))
            update.set("avatar", user.getAvatar());
        if (!StringUtils.isEmpty(user.getCity()))
            update.set("city", user.getCity());
        if (user.getGender() != null)
            update.set("gender", user.getGender());
        if (user.getAge() != null)
            update.set("age", user.getAge());

        UpdateResult result = mongoTemplate.upsert(query, update, User.class);
        return result.getModifiedCount() > 0 ? findById(user.getId()) : null;
    }

    /**
     * Reset user password.
     *
     * @param uid    The user id
     * @param oldPwd The old password
     * @param newPwd The new password
     * @return 1 success, 0 failed or undo, or throw {@link DataNotFoundException} if uid and oldPwd don't match
     */
    public int resetPwd(String uid, String oldPwd, String newPwd) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(uid));
        query.addCriteria(Criteria.where("pwd").is(oldPwd));

        if (mongoTemplate.findOne(query, User.class) == null)
            throw new DataNotFoundException("The old password is incorrect.");

        Update update = new Update();
        update.set("pwd", newPwd);

        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result.getModifiedCount() > 0 ? 1 : 0;
    }

    /**
     * Get the information of a user
     *
     * @param uid      The user who is getting the information
     * @param targetId The user whose information will be fetched
     * @return Return a {@link UserInfoResp} model if success, throw {@link DataNotFoundException} if the target
     * user does not exist.
     */
    public UserInfoResp getUserInformation(ObjectId uid, ObjectId targetId) {
        UserInfoResp userInfoResp = new UserInfoResp();

        User user = findById(targetId.toHexString());
        if (user == null)
            throw new DataNotFoundException(String.format("The user <%s> does not exist.", targetId.toHexString()));

        Friendship friendship = friendRepository.findFriendById(uid, targetId);

        userInfoResp.setUser(user);
        userInfoResp.setIsFriend(friendship == null ? 0 : 1);
        return userInfoResp;
    }
}
