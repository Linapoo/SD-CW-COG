package com.group13.cog.controller;

import com.group13.cog.model.Club;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.model.response.UserClubResp;
import com.group13.cog.service.ClubService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    public int createClub(@NotBlank @RequestParam(value = "clubName") String clubName,
                          @NotBlank @RequestParam(value = "city") String city,
                          @NotBlank @RequestParam(value = "founderId") String founderId,
                          @RequestParam(value = "announcement", required = false) String announcement) {
        Club club = new Club(clubName, city, announcement, new ObjectId(founderId));
        return clubService.createClub(club);
    }

    @DeleteMapping("delete")
    public int deleteClub(@NotBlank @RequestParam(value = "clubId") String clubId) {
        return clubService.deleteClub(clubId);
    }

    @PostMapping("userJoinClub")
    public int userJoinClub(@NotBlank @RequestParam(value = "userId") String userId,
                            @NotBlank @RequestParam(value = "clubId") String clubId) {
        return clubService.userJoinClub(userId, clubId);
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
