package com.group13.cog.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.*;

import com.group13.cog.model.response.UserClubResp;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ClubRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add a new club to club collection.
     *
     * @param club The club model
     * @return 1 success, throw {@link DataDuplicateException} if the clubName exists, throw
     * {@link DataNotFoundException} if the founder does not exists.
     */
    public int createClub(Club club) {
        Club clubRes = findByName(club.getClubName());
        if (clubRes == null) {
            clubRes = mongoTemplate.insert(club);
            return userJoinClub(club.getFounderId().toHexString(), clubRes.getId());
        } else {
            throw new DataDuplicateException(String.format("The club <%s> exits.", club.getClubName()));
        }
    }

    /**
     * Delete a club from club collection
     *
     * @param clubId The clubId
     * @return 1 success 0 undo
     */
    public int deleteClub(String clubId) {
        mongoTemplate.remove(new Query(Criteria.where("clubId").is(new ObjectId(clubId))), UserClub.class);

        DeleteResult result = mongoTemplate.remove(new Query(Criteria.where("id").is(clubId)), Club.class);
        return result.getDeletedCount() > 0 ? 1 : 0;
    }

    /**
     * Add a club to user's club collection
     *
     * @return 1 success 0 undo, throw {@link DataNotFoundException} if the clubId or userId is invalid
     */
    public int userJoinClub(String userId, String clubId) {
        User user = userRepository.findById(userId);
        if (user == null)
            throw new DataNotFoundException(String.format("The user <%s> does not exist.", userId));

        Club club = findById(clubId);
        if (club == null)
            throw new DataNotFoundException(String.format("The club <%s> does not exist.", clubId));

        UserClub userClubQuery = findUserClub(user, club);
        if (userClubQuery == null) {
            UserClub userClub = new UserClub();
            userClub.setClubId(new ObjectId(clubId));
            userClub.setJoinTime(LocalDateTime.now());
            userClub.setUserId(new ObjectId(userId));

            mongoTemplate.insert(userClub);
            return 1;
        } else {
            System.out.println("The user has been a member of the club.");
            return 0;
        }
    }

    /**
     * Delete a club from user's club collection
     *
     * @return 1 success 0 undo
     */
    public int userQuitClub(String userId, String clubId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("clubId").is(clubId));
        query.addCriteria(Criteria.where("userId").is(userId));

        DeleteResult result = mongoTemplate.remove(query, UserClub.class);
        return result.getDeletedCount() > 0 ? 1 : 0;
    }

    /**
     * View a user's club collection
     *
     * @return A List<UserClubResp> with pagination for the user
     */
    public Page<UserClubResp> viewUserClub(String userId, Integer pageNo, Integer pageSize) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("club")
                .localField("clubId")
                .foreignField("_id")
                .as("club");

        AggregationOperation localMatch = Aggregation.match(
                Criteria.where("userId").is(new ObjectId(userId)));

        Aggregation aggregation = Aggregation.newAggregation(localMatch, lookupOperation,
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "joinTime")),
                Aggregation.project("club._id", "club.clubName", "club.city", "club.announcement",
                        "club.founderId", "joinTime"));

        List<UserClubResp> clubs = mongoTemplate.aggregate(aggregation, "user_club", UserClubResp.class)
                .getMappedResults();

        return new Page<>(clubs, pageSize, pageNo);
    }

    /**
     * Find a club by club id in club collection.
     *
     * @param clubId The club id
     * @return A club model
     */
    public Club findById(String clubId) {
        return mongoTemplate.findById(new ObjectId(clubId), Club.class);
    }

    /**
     * Find a club by club name in club collection.
     *
     * @return A club model
     */
    public Club findByName(String clubName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("clubName").is(clubName));
        return mongoTemplate.findOne(query, Club.class);
    }

    /**
     * Find a membership between a club and a user
     *
     * @return A UserClub model
     */
    public UserClub findUserClub(User user, Club club) {
        Query query = new Query();
        query.addCriteria(Criteria.where("club").is(club));
        query.addCriteria(Criteria.where("user").is(user));
        return mongoTemplate.findOne(query, UserClub.class);
    }

    /**
     * Search clubs by name and city name.
     *
     * @return A List<Club> with pagination
     */
    public Page<Club> searchByNameAndCity(String clubName, String city, Integer pageNo, Integer pageSize) {
        Query query = new Query();
        if (!StringUtils.isEmpty(clubName))
            query.addCriteria(Criteria.where("clubName").regex(clubName));
        if (!StringUtils.isEmpty(city))
            query.addCriteria(Criteria.where("city").regex(city));

        int totalPage = (int) Math.ceil((double) mongoTemplate.count(query, Club.class) / pageSize);
        query.with(Sort.by(Sort.Direction.ASC, "clubName"));
        query.skip(pageSize * (pageNo - 1)).limit(pageSize);
        List<Club> results = mongoTemplate.find(query, Club.class);
        return new Page<>(results, pageSize, pageNo, totalPage);
    }

    /**
     * Get the members of a club
     *
     * @return A List<User> with pagination
     */
    public Page<User> getMembers(ObjectId clubId, Integer pageNo, Integer pageSize) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("user_club")
                .localField("_id")
                .foreignField("userId")
                .as("userClub");

        AggregationOperation foreignMatch = Aggregation.match(
                Criteria.where("userClub.clubId").is(clubId));

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, foreignMatch,
                Aggregation.sort(Sort.by(Sort.Direction.DESC, "userClub.joinTime")));

        List<User> members = mongoTemplate.aggregate(aggregation, "user", User.class)
                .getMappedResults();
        return new Page<>(members, pageSize, pageNo);
    }
}