package com.group13.cog.service;

import com.group13.cog.model.Club;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.model.response.UserClubResp;
import com.group13.cog.exception.*;
import org.springframework.http.ResponseEntity;

/**
 * Created by Yiran on 2020/3/21.
 */
public interface ClubService {
    /**
     * Create a club
     *
     * @return 1 success, throw {@link DataDuplicateException} if the clubName exists, throw
     * {@link DataNotFoundException} if the founder does not exists.
     */
    int createClub(Club club);

    /**
     * Delete a club
     *
     * @return 1 success 0 undo
     */
    int deleteClub(String clubId);

    /**
     * A user joins a club
     *
     * @return 1 success 0 undo, throw {@link DataNotFoundException} if the clubId or userId is invalid
     */
    int userJoinClub(String userId, String clubId);

    /**
     * A user quits a club
     *
     * @return 1 success 0 undo
     */
    int userQuitClub(String userId, String clubId);

    /**
     * Get a user's clubs
     *
     * @return A List<UserClubResp> with pagination for the user
     */
    ResponseEntity<Page<UserClubResp>> getUserClubs(String userId, Integer pageNo, Integer pageSize);

    /**
     * Search clubs by clubName and city name.
     * The clubName and city cannot be null at the same time.
     *
     * @return A List<Club> with pagination
     */
    ResponseEntity<Page<Club>> searchClub(String clubName, String city, Integer pageNo, Integer pageSize);

    /**
     * Get members of a club
     *
     * @return A List<User> with pagination
     */
    ResponseEntity<Page<User>> getClubMembers(String clubId, Integer pageNo, Integer pageSize);
}
