package com.group13.cog.service.impl;

import java.time.LocalDateTime;

import com.group13.cog.model.Game;
import com.group13.cog.model.Page;
import com.group13.cog.model.Sell;
import com.group13.cog.model.User;
import com.group13.cog.repository.GameRepository;
import com.group13.cog.repository.SellRepository;
import com.group13.cog.repository.UserRepository;
import com.group13.cog.service.SellService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Qizhen on 2020/3/10.
 */

@Service
public class SellServiceImpl implements SellService {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;
    
    @Override
    public ResponseEntity<Sell> postSell(String userId, String gameId, Sell sell) {
        User seller = userRepository.findById(userId);
        Game game = gameRepository.findById(gameId);
        sell.setSeller(seller);
        sell.setGame(game);
        sell.setPostTime(LocalDateTime.now());
        return new ResponseEntity<Sell>(sellRepository.saveSell(sell),HttpStatus.OK);
    }

    @Override
    public int deleteSell(String sellId) {
        return sellRepository.deleteSell(sellId);
    }

    @Override
    public ResponseEntity<Sell> updateSell(Sell sell) {
        return new ResponseEntity<Sell>(sellRepository.updateSell(sell),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Sell>> viewGameSell(String gameId, Integer pageSize, Integer pageNo) {
        return new ResponseEntity<Page<Sell>>(sellRepository.viewGameSell(gameId, pageSize, pageNo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Sell>> viewUserSell(String userId, Integer pageSize, Integer pageNo) {
        return new ResponseEntity<Page<Sell>>(sellRepository.viewUserSell(userId, pageSize, pageNo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Sell> viewSellById(String sellId) {
        return new ResponseEntity<Sell>(sellRepository.findById(sellId),HttpStatus.OK);
    }
}