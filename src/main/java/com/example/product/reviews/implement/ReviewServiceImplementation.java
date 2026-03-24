package com.example.product.reviews.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.reviews.Review;
import com.example.product.reviews.ReviewRepository;
import com.example.product.reviews.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
    
    ReviewRepository reviewRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    
}
