package com.group13.cog.controller;

import com.group13.cog.utils.FileStorage;
import com.mongodb.BasicDBObject;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.service.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.core.io.Resource;


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

    FileStorage filestorage = new FileStorage("upload-files/game");

    @PostMapping("publish")
    @ResponseStatus(HttpStatus.CREATED)
    public int gamePublish(@NotBlank @RequestBody String gameInfo) throws JSONException {
        JSONObject gameObject = new JSONObject(gameInfo);
        Game game = new Game(gameObject.getString("gameName"), 
                gameObject.getString("publisher"), 
                gameObject.getString("artist"), 
                gameObject.getString("designer"), 
                gameObject.getString("description"),
                gameObject.optInt("timePerRound"), 
                gameObject.optInt("year"), 
                gameObject.optInt("playerAge"), 
                gameObject.getString("type"),
                gameObject.getString("link"),
                gameObject.getDouble("price"));
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
    public int gameAddToUser(@NotBlank @RequestBody String addToUserInfo) throws JSONException {
        JSONObject addToUserObject = new JSONObject(addToUserInfo);
        return gameService.gameAddToUser(addToUserObject.getString("userId"), addToUserObject.getString("gameId"));
    }

    @DeleteMapping("delToUser")
    public int gameDelToUser(@NotBlank @RequestParam(value = "userId") String userId,
                            @NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.gameDelToUser(userId, gameId);
    }

    @GetMapping("checkOwn")
    public int checkOwn(@NotBlank @RequestParam(value = "userId") String userId,
                        @NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.checkOwn(userId, gameId);
    }
    @GetMapping("viewUserGame")
    public ResponseEntity<Page<Game>> viewUserGame(@NotBlank @RequestParam(value = "userId") String userId,
                                                @NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo){
        return gameService.viewUserGameColloct(userId, pageSize, pageNo);
    }

    @GetMapping("search")
    public ResponseEntity<Page<Game>> searchGame(@NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                @NotBlank @RequestParam(value = "gameName") String gameName){
        return gameService.searchGame(pageSize, pageNo, gameName);                                        
    }

    @GetMapping("searchType")
    public ResponseEntity<Page<Game>> searchGameType(@NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo,
                                                @NotBlank @RequestParam(value = "type") String type){
        return gameService.searchGameType(pageSize, pageNo, type);
    }

    @GetMapping("sortScore")
    public ResponseEntity<Page<BasicDBObject>> sortByScore(@NotNull @RequestParam(value = "pageSize") Integer pageSize,
                                                @NotNull @RequestParam(value = "pageNo") Integer pageNo){
        return gameService.sortScore(pageSize, pageNo);
    }
    
    @GetMapping("getAvgScore")
    public ResponseEntity<BasicDBObject> getAvgScore(@NotBlank @RequestParam(value = "gameId") String gameId){
        return gameService.getAvgScore(gameId);
    }

    @PutMapping("updateGameInfo")
    public ResponseEntity<Game> update(@NotBlank @RequestBody String gameUpdate) throws JSONException {
        JSONObject gameObject = new JSONObject(gameUpdate);
        Game game = new Game(gameObject.getString("gameName"), 
                gameObject.getString("publisher"), 
                gameObject.getString("artist"), 
                gameObject.getString("designer"), 
                gameObject.getString("description"),
                gameObject.optInt("timePerRound"), 
                gameObject.optInt("year"), 
                gameObject.optInt("playerAge"), 
                gameObject.getString("type"),
                gameObject.getString("link"),
                gameObject.getDouble("price"));
        game.setId(gameObject.getString("gameId"));
        return gameService.update(game);                                  
    }

    @PostMapping("updateImage")
    public Integer updateImage(@NotBlank @RequestParam(value = "gameId") String gameId,
                            @NotNull @RequestParam(value = "image") MultipartFile image){
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        filename = gameId + filename.substring(filename.indexOf("."));
        Game game = gameService.viewGame(gameId).getBody();
        if (!StringUtils.isEmpty(game.getImage())){
            filestorage.delete(game.getImage());
        }
        game.setImage(filename);
        filestorage.store(image, filename);
        return gameService.update(game) == null? null: 1;
    }

    @GetMapping("getImage")
    public ResponseEntity<Resource> viewImage(@NotBlank @RequestParam(value = "gameId") String gameId){
        Game game = gameService.viewGame(gameId).getBody();
        Resource resource;
        if (game.getImage() != null){
            resource = filestorage.loadAsResource(game.getImage());
        }
        else {
            resource = filestorage.loadError();
        }
        return ResponseEntity.ok()
        .contentType(MediaType.IMAGE_JPEG)
        .body(resource);
    }


}
