package com.example.product.reviews;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
}
