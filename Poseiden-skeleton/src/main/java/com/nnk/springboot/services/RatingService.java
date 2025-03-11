package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.domain.Rating;
import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating non trouvé"));
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public Rating updateRating(Integer id, Rating ratingDetails) {
        Rating rating = getRatingById(id);
        rating.setMoodysRating(ratingDetails.getMoodysRating());
        rating.setSandPRating(ratingDetails.getSandPRating());
        rating.setFitchRating(ratingDetails.getFitchRating());
        rating.setOrderNumber(ratingDetails.getOrderNumber());
        return ratingRepository.save(rating);
    }

    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
