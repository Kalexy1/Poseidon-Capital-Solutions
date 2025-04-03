package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BidListService bidListService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BidListController bidListController;

    private BidList bidList;

    @BeforeEach
    void setUp() {
        bidList = new BidList();
        bidList.setBidListId(1);
        bidList.setAccount("Test Account");
        bidList.setType("Type 1");
        bidList.setBidQuantity(100.0);

        mockMvc = MockMvcBuilders.standaloneSetup(bidListController).build();
    }

    @Test
    void testHome_ShouldReturnBidListPage() throws Exception {
        List<BidList> bids = Arrays.asList(bidList);
        when(bidListService.getAllBidLists()).thenReturn(bids);

        String viewName = bidListController.home(model);

        assertEquals("bidList/list", viewName);
        verify(bidListService, times(1)).getAllBidLists();
        verify(model, times(1)).addAttribute("bidLists", bids); // Fix model attribute
    }

    @Test
    void testAddBidForm_ShouldReturnAddPage() {
        String viewName = bidListController.addBidForm(model);

        assertEquals("bidList/add", viewName);
        verify(model, times(1)).addAttribute(eq("bidList"), any(BidList.class)); // Fix model attribute
    }

    @Test
    void testValidate_ShouldSaveBidAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = bidListController.validate(bidList, bindingResult, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).saveBidList(bidList);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = bidListController.validate(bidList, bindingResult, model);

        assertEquals("bidList/add", viewName);
        verify(bidListService, times(0)).saveBidList(any(BidList.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(bidListService.getBidListById(1)).thenReturn(bidList);

        String viewName = bidListController.showUpdateForm(1, model);

        assertEquals("bidList/update", viewName);
        verify(model, times(1)).addAttribute("bidList", bidList); // Fix model attribute
    }

    @Test
    void testUpdateBid_ShouldUpdateBidAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = bidListController.updateBid(1, bidList, bindingResult, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).updateBidList(1, bidList);
    }

    @Test
    void testUpdateBid_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = bidListController.updateBid(1, bidList, bindingResult, model);

        assertEquals("bidList/update", viewName);
        verify(bidListService, times(0)).updateBidList(anyInt(), any(BidList.class));
    }

    @Test
    void testDeleteBid_ShouldDeleteBidAndRedirect() {
        String viewName = bidListController.deleteBid(1, model);

        assertEquals("redirect:/bidList/list", viewName);
        verify(bidListService, times(1)).deleteBidList(1);
    }
}
