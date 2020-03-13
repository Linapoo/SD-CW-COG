package com.group13.cog.service.impl;

import java.util.List;

import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.repository.GameRepository;
import com.group13.cog.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Qizhen on 2020/3/10.
 */

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public int gamePublish(Game game) {
        return gameRepository.addNewGame(game);
    }

    @Override
    public ResponseEntity<Game> viewGame(String gameId) {
        return new ResponseEntity<>(gameRepository.findById(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Game>> viewAllGame(Integer pageSize, Integer pageNo) {
        return new ResponseEntity<>(gameRepository.findAll(pageSize, pageNo), HttpStatus.OK);
    }

    @Override
    public int gameAddToUser(String userId, String gameId) {
        return gameRepository.addGameToUser(userId, gameId);
    }

    @Override
    public int gameDelToUser(String userId, String gameId) {
        return gameRepository.deleteGameToUser(userId, gameId);
    }

    @Override
    public ResponseEntity<List<Game>> viewUserGameColloct(String userId) {
        return new ResponseEntity<>(gameRepository.viewUserGame(userId), HttpStatus.OK);
    }

    @Override
    public int DeleteGame(String gameId) {
        return gameRepository.deleteGame(gameId);
    }


}