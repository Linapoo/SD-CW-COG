package com.group13.cog.service.impl;

import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.repository.GameRepository;
import com.group13.cog.service.GameService;
import com.mongodb.BasicDBObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
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
    public ResponseEntity<Page<Game>> viewUserGameColloct(String userId, Integer PageSize, Integer PageNo) {
        return new ResponseEntity<>(gameRepository.viewUserGame(userId, PageSize, PageNo), HttpStatus.OK);
    }

    @Override
    public int DeleteGame(String gameId) {
        return gameRepository.deleteGame(gameId);
    }

    @Override
    public ResponseEntity<Page<Game>> searchGame(Integer pageSize, Integer pageNo, String gameName) {
        return new ResponseEntity<>(gameRepository.findByName(pageSize, pageNo, gameName), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Game> update(Game game) {
        return new ResponseEntity<>(gameRepository.update(game), HttpStatus.OK);
    }

    @Override
    public int checkOwn(String userId, String gameId) {
        return gameRepository.checkOwn(userId, gameId);
    }

    @Override
    public ResponseEntity<Page<Game>> searchGameType(Integer pageSize, Integer pageNo,
            String type) {
        return new ResponseEntity<>(gameRepository.searchGameType(pageSize, pageNo, type), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<BasicDBObject>> sortScore(Integer pageSize, Integer pageNo) {
        return new ResponseEntity<>(gameRepository.sortScore(pageSize, pageNo), HttpStatus.OK);
    }


}
