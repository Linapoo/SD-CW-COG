package com.group13.cog.service.impl;

import com.group13.cog.model.Club;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;
import com.group13.cog.model.response.UserClubResp;
import com.group13.cog.repository.ClubRepository;
import com.group13.cog.service.ClubService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Yiran on 2020/3/21.
 */

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public int createClub(Club club) {
        return clubRepository.createClub(club);
    }

    @Override
    public int deleteClub(String clubId) {
        return clubRepository.deleteClub(clubId);
    }

    @Override
    public int userJoinClub(String userId, String clubId) {
        return clubRepository.userJoinClub(userId, clubId);
    }

    @Override
    public int userQuitClub(String userId, String clubId) {
        return clubRepository.userQuitClub(userId, clubId);
    }

    @Override
    public ResponseEntity<Page<UserClubResp>> getUserClubs(String userId, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(clubRepository.viewUserClub(userId, pageNo, pageSize), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Club>> searchClub(String clubName, String city, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(clubRepository.searchByNameAndCity(clubName, city, pageNo, pageSize), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<User>> getClubMembers(String clubId, Integer pageNo, Integer pageSize) {
        return new ResponseEntity<>(clubRepository.getMembers(new ObjectId(clubId), pageNo, pageSize), HttpStatus.OK);
    }
}
