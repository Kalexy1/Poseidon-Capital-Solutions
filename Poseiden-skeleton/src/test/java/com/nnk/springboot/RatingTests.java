package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RatingTests {

    @Autowired
    private RatingRepository ratingRepository;

    private Rating rating;

    @BeforeEach
    public void setup() {
        rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        rating = ratingRepository.save(rating);
    }

    @AfterEach
    public void cleanup() {
        ratingRepository.deleteAll();
    }

    @Test
    public void testSaveRating() {
        assertNotNull(rating.getId());
        assertEquals(10, rating.getOrderNumber());
    }

    @Test
    public void testUpdateRating() {
        rating.setOrderNumber(20);
        rating = ratingRepository.save(rating);
        assertEquals(20, rating.getOrderNumber());
    }

    @Test
    public void testFindAllRatings() {
        List<Rating> listResult = ratingRepository.findAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void testDeleteRating() {
        Integer id = rating.getId();
        ratingRepository.delete(rating);
        Optional<Rating> ratingList = ratingRepository.findById(id);
        assertFalse(ratingList.isPresent());
    }
}
