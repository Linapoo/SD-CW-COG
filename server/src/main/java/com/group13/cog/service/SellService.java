package com.group13.cog.service;

import com.group13.cog.model.Page;
import com.group13.cog.model.Sell;

import org.springframework.http.ResponseEntity;

public interface SellService {
    
    /**
     * Post new sell for a game
     *  
     * @param gameId 
     * @param userId 
     * @return Sell if success,
     * otherwise throw DataDuplicateException
     */
    ResponseEntity<Sell> postSell(String userId, String gameId, Sell sell);

    /**
     * Delete a sell for a game
     * 
     * @param SellId 
     * @return 1 if success,
     * otherwise throw DataNotFoundException
     */
    int deleteSell(String sellId);

    /**
     * update a sell
     * 
     * @param Sell The sell model
     * @return Sell if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Sell> updateSell(Sell sell);

    /**
     * view all sell to a game
     * 
     * @param gameId
     * @param pageSize
     * @param pageNo
     * @return Page<Sell> if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Page<Sell>> viewGameSell(String gameId, Integer pageSize, Integer pageNo);

    /**
     * view all sell to a User
     * 
     * @param userId
     * @param pageSize
     * @param pageNo
     * @return Page<Sell> if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Page<Sell>> viewUserSell(String userId, Integer pageSize, Integer pageNo);

    /**
     * view a sell by Id
     * 
     * @param sellId
     * @return Sell if success,
     * otherwise throw DataNotFoundException
     */
    ResponseEntity<Sell> viewSellById(String sellId);
}