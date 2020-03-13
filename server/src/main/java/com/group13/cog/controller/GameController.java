package com.group13.cog.controller;

import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.service.StorageService;
import com.group13.cog.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by Qizhen on 2020/3/10.
 */

@RequestMapping("/api/game/")
@RestController
@Validated
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    StorageService storageService;

    @PostMapping("publish")
    @ResponseStatus(HttpStatus.CREATED)
    public int gamePublish(@NotBlank @RequestParam(value = "gameName") String gameName,
                      @NotBlank @RequestParam(value = "publisher") String publisher,
                      @RequestParam(value = "description", required = false) String description,
                      @RequestParam(value = "artist", required = false) String artist,
                      @RequestParam(value = "designer", required = false) String designer,
                      @RequestParam(value = "timePerRound", required = false) Integer timePerRound,
                      @RequestParam(value = "year", required = false) Integer year,
                      @RequestParam(value = "playerAge", required = false) Integer playerAge){
        Game game = new Game(gameName, publisher, artist, designer, description, timePerRound, year, playerAge);
        return gameService.gamePublish(game);
    }

    @GetMapping("view")
    public ResponseEntity<Game> viewGame(@NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.viewGame(gameId);
    }
    
    @GetMapping("viewAll")
    public ResponseEntity<Page<Game>> viewAllGame(@NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo){
        return gameService.viewAllGame(pageSize, pageNo);
    }

    @DeleteMapping("delete")
    public int DeleteGame(@NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.DeleteGame(gameId);
    }

    @PostMapping("addToUser")
    public int gameAddToUser(@NotBlank @RequestParam(value = "userId") String userId,
                            @NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.gameAddToUser(userId, gameId);
    }

    @DeleteMapping("delToUser")
    public int gameDelToUser(@NotBlank @RequestParam(value = "userId") String userId,
                            @NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.gameDelToUser(userId, gameId);
    }

    @GetMapping("viewUserGame")
    public ResponseEntity<List<Game>> viewUserGame(@NotBlank @RequestParam(value = "userId") String userId){
        return gameService.viewUserGameColloct(userId);
    }




}
