package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating;

    @BeforeEach
    void setUp() {
        rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("AAA");
        rating.setSandPRating("AA");
        rating.setFitchRating("A+");
        rating.setOrderNumber(1);
    }

    @Test
    void testGetAllRatings() {
        when(ratingRepository.findAll()).thenReturn(Arrays.asList(rating));

        List<Rating> ratings = ratingService.getAllRatings();

        assertNotNull(ratings);
        assertEquals(1, ratings.size());
        assertEquals("AAA", ratings.get(0).getMoodysRating());

        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void testGetRatingById_RatingFound() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Rating foundRating = ratingService.getRatingById(1);

        assertNotNull(foundRating);
        assertEquals("AAA", foundRating.getMoodysRating());

        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    void testGetRatingById_RatingNotFound() {
        when(ratingRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ratingService.getRatingById(2));

        assertEquals("Rating non trouvé", exception.getMessage());
        verify(ratingRepository, times(1)).findById(2);
    }

    @Test
    void testSaveRating() {
        when(ratingRepository.save(any(Rating.class))).thenReturn(rating);

        Rating savedRating = ratingService.saveRating(rating);

        assertNotNull(savedRating);
        assertEquals("AAA", savedRating.getMoodysRating());

        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void testUpdateRating() {
        Rating updatedRatingDetails = new Rating();
        updatedRatingDetails.setMoodysRating("BBB");
        updatedRatingDetails.setSandPRating("BB");
        updatedRatingDetails.setFitchRating("B+");
        updatedRatingDetails.setOrderNumber(2);

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        when(ratingRepository.save(any(Rating.class))).thenReturn(updatedRatingDetails);

        Rating updatedRating = ratingService.updateRating(1, updatedRatingDetails);

        assertNotNull(updatedRating);
        assertEquals("BBB", updatedRating.getMoodysRating());
        assertEquals("BB", updatedRating.getSandPRating());

        verify(ratingRepository, times(1)).findById(1);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void testDeleteRating() {
        doNothing().when(ratingRepository).deleteById(1);

        ratingService.deleteRating(1);

        verify(ratingRepository, times(1)).deleteById(1);
    }
}
