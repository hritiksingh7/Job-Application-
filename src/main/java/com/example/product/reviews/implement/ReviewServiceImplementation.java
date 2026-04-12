package com.example.product.reviews.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.product.company.Company;
import com.example.product.company.CompanyService;
import com.example.product.reviews.Review;
import com.example.product.reviews.ReviewRepository;
import com.example.product.reviews.ReviewService;

@Service
public class ReviewServiceImplementation implements ReviewService {
    
    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    public ReviewServiceImplementation(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review){
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId){

        // works well but does not verify if the review belongs to that specific company or not
        //return reviewRepository.findById(reviewId).orElse(null);
        
        // best method - but we need to keep return type as Optional<Review> and not Review
        // Review review = reviewRepository.findByIdAndCompanyId(reviewId, companyId).orElse(null);

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().
               filter(review -> review.getId().equals(reviewId))
               .findFirst()
               .orElse(null);
    }



    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review review) {
        Company company = companyService.getCompanyById(companyId);

        if(company != null){
            review.setCompany(company);
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }



    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);

        if(company != null){

            // MY SOLUTION
            // Review review = reviewRepository
            //                 .findByCompanyId(companyId)
            //                 .stream()
            //                 .filter(rev -> rev.getId().equals(reviewId))
            //                 .findFirst()
            //                 .orElse(null);
            
            // if(review != null){
            //     reviewRepository.delete(review);
            //     return true;
            // }
            


            // BEST APPROACH BY GPT
            // Review review = reviewRepository
            //                 .findByIdAndCompanyId(reviewId, companyId)
            //                 .orElse(null);

            // if (review != null) {
            //     reviewRepository.delete(review);
            //     return true;
            // }
            // return false;



            // FAISAL SOLUTION
            if(reviewRepository.existsById(reviewId)){
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company comp = review.getCompany();
                comp.getReviews().remove(review);  // for in-memory consistency, not DB
                companyService.updateCompany(comp, companyId);
                reviewRepository.deleteById(reviewId);
                return true;
            }
        }
        return false;
    }

    
}
