package com.group13.cog.service;

import com.group13.cog.model.Game;
import com.group13.cog.model.Page;

import org.springframework.http.ResponseEntity;

/**
 * Created by Qizhen on 2020/3/10.
 */
public interface GameService {
    
    /**
     * Publish new game
     *  
     * @param game The game model
     * @return Return a game model to the client if success,
     * otherwise throw {@link com.group13.cog.exception.DataDuplicateException}
     */
    int gamePublish(Game game);

    /**
     * View a game info by id
     * 
     * @param gameId The game id 
     * @return Return a game model to the client if success, otherwise return null,
     * status both are {{@link org.springframework.http.HttpStatus.OK}}
     */
    ResponseEntity<Game> viewGame(String gameId);

    /**
     * View all game 
     * 
     * @param pageNum The page number
     * @return Return a list of Game 
     */
    ResponseEntity<Page<Game>> viewAllGame(Integer pagePage, Integer PageNo);

    /**
     * Delete a game 
     * 
     * @param gameId The game id
     * @return Return 1 success, 0 failded or undo, or throw {@link com.group13.cog.exception.DataNotFoundException}
     * if gameId not found
     */
    int DeleteGame(String gameId);

    /**
     * Add a game to a user game collection
     * 
     * @param userId The user id
     * @param gameId The game id
     * @return Return 1 success, 0 failded or undo, or throw {@link com.group13.cog.exception.DataNotFoundException}
     * if userId or gameId not found
     */
    int gameAddToUser(String userId, String gameId);

    /**
     * delete a game from a user game collection
     * 
     * @param userId The user id
     * @param gameId The game id
     * @return Return 1 success, 0 fail or undo, or throw {@link com.group13.cog.exception.DataNotFoundException}
     * if gameid not associated with the user
     */
    int gameDelToUser(String userId, String gameId);
    
    /**
     * view game collection from a user
     * @param userId the user id
     * @param gameId the game id
     * @return Return a Page of Game 
     */
    ResponseEntity<Page<Game>> viewUserGameColloct(String userId, Integer PageSize, Integer PageNo);

    /**
     * search game by game name
     * @param pageSize
     * @param pageNo
     * @param gameName
     * @return Return a Page of Game
     */
    ResponseEntity<Page<Game>> searchGame(Integer pageSize, Integer pageNo, String gameName);

    /**
     * update game info
     * @param game
     * @return game updated 
     */
    ResponseEntity<Game> update(Game game);
}