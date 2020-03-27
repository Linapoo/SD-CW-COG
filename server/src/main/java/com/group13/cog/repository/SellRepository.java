package com.group13.cog.repository;

import java.util.List;

import com.group13.cog.exception.DataNotFoundException;
import com.group13.cog.model.Page;
import com.group13.cog.model.Sell;

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

@Component
public class SellRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * save a new sell
     * 
     * @param Sell The sell
     * @return Sell if success
     */
    public Sell saveSell(Sell sell) {
        Sell sellRes = mongoTemplate.insert(sell);
        return sellRes;
    }

    /**
     * delete a Sell
     * 
     * @param sellId
     * @return 1 if success otherwise throw dataNotFoundException
     */
    public int deleteSell(String sellId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(sellId));
        Sell sellRes = mongoTemplate.findAndRemove(query, Sell.class);
        if ( sellRes != null){
            return 1;
        }else{
            throw new DataNotFoundException(String.format("the sellId <%s> not exists", sellId));
        }
    }

    /**
     * update a Sell
     * 
     * @param Sell
     * @return Sell if success
     */
    public Sell updateSell(Sell sell){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(sell.getId()));

        Update update = new Update();
        if (!StringUtils.isEmpty(sell.getContact()))
            update.set("contact", sell.getContact());
        if (!StringUtils.isEmpty(sell.getDesciption()))
            update.set("description", sell.getDesciption());
        if (sell.getPrice() != null)
            update.set("price", sell.getPrice());    
        mongoTemplate.updateFirst(query, update, Sell.class);
        return findById(sell.getId());
    }

    /**
     * Find a Sell by sellId
     * 
     * @param gameId 
     * @return Sell if success
     * otherwise throw DataNotFoundException
     */
    public Sell findById(String sellId) {
        Sell sell = mongoTemplate.findById(new ObjectId(sellId), Sell.class);
        if (sell != null){
            return sell;
        }else{
            throw new DataNotFoundException(String.format("the sellId <%s> not exists", sellId));
        }
    }

    /**
     * Find all Sell for a game
     * 
     * @param gameId
     * @return Page<Sell> if success
     */
    public Page<Sell> viewGameSell(String gameId, Integer pageSize, Integer pageNo){
        Query query = new Query();
        query.addCriteria(Criteria.where("game.$id").is(new ObjectId(gameId)));
        query.with(Sort.by(Direction.DESC, "price"));
        long total = mongoTemplate.count(query, Sell.class);
        query.skip(pageSize*(pageNo-1)).limit(pageSize);
        List<Sell> data = mongoTemplate.find(query, Sell.class);
        int totalPage = (int) Math.ceil((double) total/pageSize);
        Page<Sell> page = new Page<Sell>(data, pageSize, pageNo, totalPage);
        return page;
    }

     /**
     * Find all Sell for a User
     * 
     * @param userId
     * @return Page<Sell> if success
     */
    public Page<Sell> viewUserSell(String userId, Integer pageSize, Integer pageNo){
        Query query = new Query();
        query.addCriteria(Criteria.where("seller.$id").is(new ObjectId(userId)));
        //query.with(Sort.by(Direction.ASC, "gameName"));
        long total = mongoTemplate.count(query, Sell.class);
        query.skip(pageSize*(pageNo-1)).limit(pageSize);
        List<Sell> data = mongoTemplate.find(query, Sell.class);
        int totalPage = (int) Math.ceil((double) total/pageSize);
        Page<Sell> page = new Page<Sell>(data, pageSize, pageNo, totalPage);
        return page;
    }

}