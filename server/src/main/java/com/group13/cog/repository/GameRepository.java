package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.exception.DataNotFoundException;
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
import org.springframework.util.StringUtils;

/**
 * Created by Ying on 2020/3/4.
 */
@Component
public class GameRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ForumRepository forumRepository;

    /**
     * Add a new game to game collection.
     *
     * @param game The game model
     * @return 1 success, otherwise throw {@link DataDuplicateException}
     */
    public int addNewGame(Game game) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").is(game.getGameName()));
        Game gameRes = mongoTemplate.findOne(query, Game.class);
        if (gameRes == null) {
            mongoTemplate.insert(game);

            // Create the forum
            forumRepository.createForum(game);

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
        Game gameRes = mongoTemplate.findAndRemove(query, Game.class);
        if (gameRes != null) {
            return 1;
        } else {
            throw new DataNotFoundException(String.format("The game <%s> not exits.", gameId));
        }
    }

    /**
     * Show all the games'list in game collection.
     * @param pageSize the number of game per page
     * @param pageNo the number of page need return 
     * @return a Page<Game> of game including the game information
     */
     public Page<Game> findAll(Integer pageSize, Integer pageNo){
         Query query = new Query();
         int totalPage = (int) Math.ceil((double)mongoTemplate.count(query, Game.class)/pageSize);
         query.with(Sort.by(Direction.ASC, "gameName"));
         query.skip(pageSize*(pageNo-1)).limit(pageSize);
         List<Game> data = mongoTemplate.find(query, Game.class);
         Page<Game> page = new Page<Game>(data, pageSize, pageNo, totalPage);
         return page;
     }
    
    /**
     * Add a game to user game collection
     * @param userId
     * @param gameId
     * @return 1 success or throw DataNotFoundExcrption if gameId not exits
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
            throw new DataNotFoundException(String.format("The game id <%s> not exits.", gameId));
        }
    } 
    
    /**
     * Delete a game from user game collection
     * @param userId
     * @param gameId
     * @return 1 success or throw DataNotFoundException if the gameId not exits in user collection
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
            throw new DataNotFoundException(String.format("The game id <%s> not exits.", gameId));
        }
    }

    /**
     * view a user game collection
     * @param userId
     * @return a Page<Game> for the user
     */
    public Page<Game> viewUserGame(String userId, Integer pageSize, Integer pageNo){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        List<Game> games = user.getGames();
        Page<Game> page = new Page<Game>(games, pageSize,pageNo);
        return page;
    }
   
    /**
     * Find a game by game id.
     *
     * @param gameId The game id
     * @return the game information
     */
    public Game findById(String gameId) {
        Game game = mongoTemplate.findById(new ObjectId(gameId), Game.class);
        if (game != null){
            return game;
        } else {
            throw new DataNotFoundException(String.format("The game id <%s> not exits", gameId));
        }
    }

    /**
     * Find a game by game name.
     *
     * @param GameName The name of the game
     * @param pageSize The number of element in one page  
     * @param pageNo The number of page
     * @return A user model including the user information
     */
    public Page<Game> findByName(Integer pageSize, Integer pageNo, String GameName){
        Query query = new Query();
        query.addCriteria(Criteria.where("gameName").regex(GameName));
        int totalPage = (int) Math.ceil((double)mongoTemplate.count(query, Game.class)/pageSize);
        query.with(Sort.by(Direction.ASC, "gameName"));
        query.skip(pageSize*(pageNo-1)).limit(pageSize);
        List<Game> data = mongoTemplate.find(query, Game.class);
        if (data != null){
            Page<Game> page = new Page<Game>(data, pageSize, pageNo, totalPage);
            return page;
        } else {
            throw new DataNotFoundException(String.format("No game match the name <%s>", GameName));
        }
    }

    /**
     * update a game info
     * @param game The game model
     * @return Game updated if success or throw DataNotFoundExcepton if the gameId not exits
     */
    public Game update(Game game){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(game.getId()));

        Update update = new Update();
        if (!StringUtils.isEmpty(game.getGameName()))
            update.set("gameName", game.getGameName());
        if (!StringUtils.isEmpty(game.getArtist()))
            update.set("artist", game.getArtist());
        if (!StringUtils.isEmpty(game.getDesigner()))
            update.set("designer", game.getDesigner());
        if (!StringUtils.isEmpty(game.getDescription()))
            update.set("description", game.getDescription());
        if (!StringUtils.isEmpty(game.getImage()))
            update.set("image", game.getImage());    
        if (game.getTimePerRound() != null)
            update.set("timePerRound", game.getTimePerRound());
        if (game.getYear() != null)
            update.set("year", game.getYear());
        if (game.getPlayerAge() != null)
            update.set("playerAge", game.getPlayerAge());

        mongoTemplate.updateFirst(query, update, Game.class);
        return findById(game.getId());
    }
}