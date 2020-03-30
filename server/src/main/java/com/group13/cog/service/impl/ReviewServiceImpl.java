package com.group13.cog.service.impl;

import java.time.LocalDateTime;

import com.group13.cog.exception.DataDuplicateException;
import com.group13.cog.model.Page;
import com.group13.cog.model.Review;
import com.group13.cog.model.User;
import com.group13.cog.repository.GameRepository;
import com.group13.cog.repository.ReviewRepository;
import com.group13.cog.repository.UserRepository;
import com.group13.cog.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by Qizhen on 2020/3/10.
 */

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Override
    public ResponseEntity<Review> postReview(String userId, String gameId, Review review) {
        User reviewer = userRepository.findById(userId);
        gameRepository.findById(gameId);
        if (reviewRepository.checkReviewExist(gameId, userId)==0){
            review.setReviewer(reviewer);
            review.setGameId(gameId);
            review.setPostTime(LocalDateTime.now());
            return new ResponseEntity<Review>(reviewRepository.saveReview(review), HttpStatus.OK);
        }else{
            throw new DataDuplicateException("The user already write a review for this game");
        }
    }

    @Override
    public int checkReviewExist(String userId, String gameId){
        return reviewRepository.checkReviewExist(gameId, userId);
    }
    
    @Override
    public int deleteReview(String reviewId) {
        return reviewRepository.deleteReview(reviewId);
    }

    @Override
    public ResponseEntity<Review> updateReview(Review review) {
        return new ResponseEntity<Review>(reviewRepository.updateReview(review),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Review>> viewGameReview(String gameId, Integer pageSize, Integer pageNo) {
        return new ResponseEntity<Page<Review>>(reviewRepository.viewGameReview(gameId, pageSize, pageNo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Review>> viewUserReview(String userId, Integer pageSize, Integer pageNo) {
        return new ResponseEntity<Page<Review>>(reviewRepository.viewUserReview(userId, pageSize, pageNo),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Review> viewReviewById(String reviewId) {
        return new ResponseEntity<Review>(reviewRepository.findById(reviewId), HttpStatus.OK);
    }
}