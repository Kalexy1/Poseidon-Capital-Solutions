package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TradeService tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TradeController tradeController;

    private Trade trade;

    @BeforeEach
    void setUp() {
        trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Test Account");
        trade.setType("Type 1");
        trade.setBuyQuantity(100.0);

        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }

    @Test
    void testHome_ShouldReturnTradeListPage() throws Exception {
        List<Trade> trades = Arrays.asList(trade);
        when(tradeService.getAllTrades()).thenReturn(trades);

        String viewName = tradeController.home(model);

        assertEquals("trade/list", viewName);
        verify(tradeService, times(1)).getAllTrades();
        verify(model, times(1)).addAttribute("trades", trades);
    }

    @Test
    void testAddTradeForm_ShouldReturnAddPage() {
        String viewName = tradeController.addTradeForm(model);

        assertEquals("trade/add", viewName);
        verify(model, times(1)).addAttribute(eq("trade"), any(Trade.class));
    }

    @Test
    void testValidate_ShouldSaveTradeAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = tradeController.validate(trade, bindingResult, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).saveTrade(trade);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = tradeController.validate(trade, bindingResult, model);

        assertEquals("trade/add", viewName);
        verify(tradeService, times(0)).saveTrade(any(Trade.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(tradeService.getTradeById(1)).thenReturn(trade);

        String viewName = tradeController.showUpdateForm(1, model);

        assertEquals("trade/update", viewName);
        verify(model, times(1)).addAttribute("trade", trade);
    }

    @Test
    void testUpdateTrade_ShouldUpdateTradeAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = tradeController.updateTrade(1, trade, bindingResult, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).updateTrade(1, trade);
    }

    @Test
    void testUpdateTrade_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = tradeController.updateTrade(1, trade, bindingResult, model);

        assertEquals("trade/update", viewName);
        verify(tradeService, times(0)).updateTrade(anyInt(), any(Trade.class));
    }

    @Test
    void testDeleteTrade_ShouldDeleteTradeAndRedirect() {
        String viewName = tradeController.deleteTrade(1, model);

        assertEquals("redirect:/trade/list", viewName);
        verify(tradeService, times(1)).deleteTrade(1);
    }
}
