package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RatingController ratingController;

    private Rating rating;

    @BeforeEach
    void setUp() {
        rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("AAA");
        rating.setSandPRating("AA");
        rating.setFitchRating("A+");
        rating.setOrderNumber(1);

        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
    }

    @Test
    void testHome_ShouldReturnRatingListPage() throws Exception {
        List<Rating> ratings = Arrays.asList(rating);
        when(ratingService.getAllRatings()).thenReturn(ratings);

        String viewName = ratingController.home(model);

        assertEquals("rating/list", viewName);
        verify(ratingService, times(1)).getAllRatings();
        verify(model, times(1)).addAttribute("ratings", ratings);
    }

    @Test
    void testAddRatingForm_ShouldReturnAddPage() {
        String viewName = ratingController.addRatingForm(model);

        assertEquals("rating/add", viewName);
        verify(model, times(1)).addAttribute(eq("rating"), any(Rating.class));
    }

    @Test
    void testValidate_ShouldSaveRatingAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ratingController.validate(rating, bindingResult, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).saveRating(rating);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ratingController.validate(rating, bindingResult, model);

        assertEquals("rating/add", viewName);
        verify(ratingService, times(0)).saveRating(any(Rating.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(ratingService.getRatingById(1)).thenReturn(rating);

        String viewName = ratingController.showUpdateForm(1, model);

        assertEquals("rating/update", viewName);
        verify(model, times(1)).addAttribute("rating", rating);
    }

    @Test
    void testUpdateRating_ShouldUpdateRatingAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ratingController.updateRating(1, rating, bindingResult, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).updateRating(1, rating);
    }

    @Test
    void testUpdateRating_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ratingController.updateRating(1, rating, bindingResult, model);

        assertEquals("rating/update", viewName);
        verify(ratingService, times(0)).updateRating(anyInt(), any(Rating.class));
    }

    @Test
    void testDeleteRating_ShouldDeleteRatingAndRedirect() {
        String viewName = ratingController.deleteRating(1, model);

        assertEquals("redirect:/rating/list", viewName);
        verify(ratingService, times(1)).deleteRating(1);
    }
}
