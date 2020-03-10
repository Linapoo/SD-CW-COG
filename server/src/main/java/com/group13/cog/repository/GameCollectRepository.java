package com.group13.cog.repository;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by Ying on 2020/3/4.
 */
@Component
public class GameCollectRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Add a new game to game collection.
     *
     * @param user The user model
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public int addNewGame(GameCollect game) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(game.getGameName()));
        GameCollect gameRes = mongoTemplate.findOne(query, GameCollect.class);
        if (gameRes == null) {
            mongoTemplate.insert(game);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The game <%s> exits.", game.getGameName()));
        }
    }
    
    /**
     * Show all the games'list in game collection.
     *
     * @return the collection of game including the game information
     */
     public GameCollect findAll(){
         return mongoTemplate.findAll(GameCollect.class);
     }
     
    /**
     * Delete a game from game collection
     *
     * @param userName The username
     * @param pwd      The password
     * @return 1 success
     */
    public int deleteGame (GameCollect game) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(game.getGameName()));
        mongoTemplate.remove(query,GameCollect.class);
        return 1;
    }

    /**
     * Find a game by game id.
     *
     * @param id The game id
     * @return the game information
     */
    public GameCollect findById(String id) {
        return mongoTemplate.findById(id, GameCollect.class);
    }

    /**
     * Find a game by game name.
     *
     * @param GameName The name of the game
     * @return A user model including the user information
     */
     public GameCollect findOne(String GameName){
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(game.getGameName()));
        return mongoTemplate.findOne(query, GameCollect.class);
     }
}