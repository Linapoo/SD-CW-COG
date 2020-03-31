package com.group13.cog.controller;

import javax.validation.constraints.NotBlank;

import com.group13.cog.model.Page;
import com.group13.cog.model.Review;
import com.group13.cog.repository.ReviewRepository;
import com.group13.cog.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/review")
@RestController
@Validated
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> post(@NotBlank @RequestBody String reviewInfo) throws JSONException {
        JSONObject reviewJson = new JSONObject(reviewInfo);
        String reviewerId = reviewJson.getString("reviewerId");
        String gameId = reviewJson.getString("gameId");
        Review review = new Review(reviewJson.getString("content"), reviewJson.optInt("score"), reviewJson.optInt("anonymous"));
        return reviewService.postReview(reviewerId, gameId, review);
    }

    @GetMapping("checkExist")
    public int checkExist(@NotBlank @RequestParam(value = "userId") String userId,
                        @NotBlank @RequestParam(value = "gameId") String gameId){
        return reviewService.checkReviewExist(userId, gameId);               
    }

    @DeleteMapping("delete")
    public int delete(@NotBlank @RequestParam(value = "reviewId") String reviewId){
        return reviewService.deleteReview(reviewId);
    }

    @PutMapping("update")
    public ResponseEntity<Review> update(@NotBlank @RequestBody String reviewUpdate) throws JSONException {
        JSONObject reviewObject = new JSONObject(reviewUpdate);
        Review review = new Review(reviewObject.getString("content"), reviewObject.optInt("score"), reviewObject.optInt("anonymous"));
        review.setId(reviewObject.getString("reviewId"));
        return reviewService.updateReview(review);
    }

    @GetMapping("viewGameReview")
    public ResponseEntity<Page<Review>> viewGameReview(@NotBlank @RequestParam(value = "gameId") String gameId,
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @RequestParam(value = "pageNo") Integer pageNo){
        return reviewService.viewGameReview(gameId, pageSize, pageNo);
    }

    @GetMapping("viewUserReview")
    public ResponseEntity<Page<Review>> viewUserReview(@NotBlank @RequestParam(value = "userId") String userId,
                                    @RequestParam(value = "pageSize") Integer pageSize,
                                    @RequestParam(value = "pageNo") Integer pageNo){
        return reviewService.viewUserReview(userId, pageSize, pageNo);
    }

    @GetMapping("viewReview")
    public ResponseEntity<Review> viewReview(@NotBlank @RequestParam(value = "reviewId") String reviewId){
        return reviewService.viewReviewById(reviewId);
    }
}