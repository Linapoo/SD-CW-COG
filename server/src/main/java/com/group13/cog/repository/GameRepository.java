package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.model.User;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


/**
 * Created by Ying on 2020/3/4.
 */
@Component
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Add a new game to game collection.
     *
     * @param Game The game model
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public int addNewGame(Game game) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(game.getGameName()));
        Game gameRes = mongoTemplate.findOne(query, Game.class);
        if (gameRes == null) {
            mongoTemplate.insert(game);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The game <%s> exits.", game.getGameName()));
        }
    }

    /**
     * Delete a game from game collection
     *
     * @param gameId The gameId
     * @return 1 success
     */
    public int deleteGame (String gameId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(gameId));
        mongoTemplate.remove(query, Game.class);
        return 1;
    }

    /**
     * Show all the games'list in game collection.
     * @param pageSize the number of game per page
     * @param pageNo the number of page need return 
     * @return a Page<Game> of game including the game information
     */
     public Page<Game> findAll(Integer pageSize, Integer pageNo){
         Query query = new Query();
         int totalPage = (int) (mongoTemplate.count(query, Game.class)/pageSize);
         query.with(Sort.by(Direction.ASC, "gameName"));
         query.skip((pageSize-1)*pageNo).limit(pageSize);
         List<Game> data = mongoTemplate.findAll(Game.class);
         Page<Game> page = new Page<Game>(data, pageSize, pageNo, totalPage);
         return page;
     }
    
    /**
     * Add a game to user game collection
     * @param userId
     * @param gameId
     * @return 1 success
     */
    public int addGameToUser(String userId, String gameId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Game gameRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(gameId)), Game.class);
        if (gameRes != null) {
            Update update = new Update().addToSet("games", gameRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The game id <%s> not exits.", gameId));
        }
    } 
    
    /**
     * Delete a game from user game collection
     * @param userId
     * @param gameId
     * @return 1 sucuess
     */
    public int deleteGameToUser(String userId, String gameId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        Game gameRes = mongoTemplate.findOne(Query.query(Criteria.where("id").is(gameId)), Game.class);
        if (gameRes != null) {
            Update update = new Update().pull("games", gameRes);
            mongoTemplate.updateFirst(query, update, User.class);
            return 1;
        } else {
            throw new DataDuplicateException(String.format("The game id <%s> not exits.", gameId));
        }
    }

    /**
     * view a user game collection
     * @param userId
     * @return a List<Game> for the user
     */
    public List<Game> viewUserGame(String userId){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        List<Game> games = user.getGames();
        return games;
    }
   
    /**
     * Find a game by game id.
     *
     * @param gameId The game id
     * @return the game information
     */
    public Game findById(String gameId) {
        return mongoTemplate.findById(new ObjectId(gameId), Game.class);
    }

    /**
     * Find a game by game name.
     *
     * @param GameName The name of the game
     * @return A user model including the user information
     */
     public Game findByName(String GameName){
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(GameName));
        return mongoTemplate.findOne(query, Game.class);
     }
}