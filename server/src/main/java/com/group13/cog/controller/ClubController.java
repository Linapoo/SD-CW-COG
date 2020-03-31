package com.group13.cog.controller;

import com.group13.cog.model.Club;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.model.response.UserClubResp;
import com.group13.cog.service.ClubService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.InvalidParameterException;

/**
 * Created by Yiran on 2020/3/21.
 */

@RequestMapping("/api/club/")
@RestController
@Validated
public class ClubController {

    @Autowired
    private ClubService clubService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public int createClub(@NotBlank @RequestBody String requestInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(requestInfo);
        if (!jsonObject.has("clubName") || !jsonObject.has("city") || !jsonObject.has("founderId"))
            throw new InvalidParameterException("clubName, city and founderId cannot be null");
        String announcement = null;
        if (jsonObject.has("announcement")) announcement = jsonObject.getString("announcement");
        Club club = new Club(jsonObject.getString("clubName"), jsonObject.getString("city"),
                announcement, new ObjectId(jsonObject.getString("founderId")));
        return clubService.createClub(club);
    }

    @DeleteMapping("delete")
    public int deleteClub(@NotBlank @RequestParam(value = "clubId") String clubId) {
        return clubService.deleteClub(clubId);
    }

    @PostMapping("userJoinClub")
    public int userJoinClub(@NotBlank @RequestBody String requestInfo) throws JSONException {
        JSONObject jsonObject = new JSONObject(requestInfo);
        if (!jsonObject.has("userId") || !jsonObject.has("clubId"))
            throw new InvalidParameterException("userId and clubId cannot be null");
        return clubService.userJoinClub(jsonObject.getString("userId"), jsonObject.getString("clubId"));
    }

    @DeleteMapping("userQuitClub")
    public int userQuitClub(@NotBlank @RequestParam(value = "userId") String userId,
                            @NotBlank @RequestParam(value = "clubId") String clubId) {
        return clubService.userQuitClub(userId, clubId);
    }

    @GetMapping("getUserClubs")
    public ResponseEntity<Page<UserClubResp>> getUserClubs(@NotBlank @RequestParam(value = "userId") String userId,
                                                           @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                           @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return clubService.getUserClubs(userId, pageNo, pageSize);
    }

    @GetMapping("search")
    public ResponseEntity<Page<Club>> searchClub(@RequestParam(value = "clubName", required = false) String clubName,
                                                 @RequestParam(value = "city", required = false) String city,
                                                 @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                 @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        if (StringUtils.isEmpty(clubName) && StringUtils.isEmpty(city))
            throw new IllegalArgumentException("Please provide a keyword, clubName or city.");
        return clubService.searchClub(clubName, city, pageNo, pageSize);
    }

    @GetMapping("getClubMembers")
    public ResponseEntity<Page<User>> getClubMembers(@NotBlank @RequestParam(value = "clubId") String clubId,
                                                     @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                     @NotNull @RequestParam(value = "pageSize") Integer pageSize) {
        return clubService.getClubMembers(clubId, pageNo, pageSize);
    }
}
