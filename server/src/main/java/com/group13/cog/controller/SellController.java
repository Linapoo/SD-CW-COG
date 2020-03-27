package com.group13.cog.controller;

import com.group13.cog.model.Page;
import com.group13.cog.model.Sell;
import com.group13.cog.service.SellService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RequestMapping("/api/sell")
@RestController
@Validated
public class SellController {

    @Autowired
    SellService sellService;

    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sell> post(@NotBlank @RequestBody String sellInfo) throws JSONException {
        JSONObject sellJson = new JSONObject(sellInfo);
        String sellerId = sellJson.getString("sellerId");
        String gameId = sellJson.getString("gameId");
        Sell sell = new Sell(sellJson.getString("contact"), sellJson.getDouble("price"), sellJson.getString("description"));
        return sellService.postSell(sellerId, gameId, sell);
    }

    @DeleteMapping("delete")
    public int delete(@NotBlank @RequestParam(value = "sellId") String sellId){
        return sellService.deleteSell(sellId);
    }

    @PutMapping("update")
    public ResponseEntity<Sell> update(@NotBlank @RequestParam(value = "sellId") String sellId,
                                    @RequestParam(value = "contact", required = false) String contact,
                                    @RequestParam(value = "price", required = false) Double price,
                                    @RequestParam(value = "description", required = false) String description){
        Sell sell = new Sell(contact, price, description);
        sell.setId(sellId);
        return sellService.updateSell(sell);
    }

    @GetMapping("viewGameSell")
    public ResponseEntity<Page<Sell>> viewGameSell(@NotBlank @RequestParam(value = "gameId") String gameId,
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @RequestParam(value = "pageNo") Integer pageNo){
        return sellService.viewGameSell(gameId, pageSize, pageNo);
    }

    @GetMapping("viewUserSell")
    public ResponseEntity<Page<Sell>> viewUserSell(@NotBlank @RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @RequestParam(value = "pageNo") Integer pageNo){
        return sellService.viewUserSell(userId, pageSize, pageNo);
    }

    @GetMapping("viewSell")
    public ResponseEntity<Sell> viewSell(@NotBlank @RequestParam(value = "sellId") String sellId){
        return sellService.viewSellById(sellId);
    }
}