package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    private Trade trade;

    @BeforeEach
    void setUp() {
        trade = new Trade();
        trade.setTradeId(1);
        trade.setAccount("Test Account");
        trade.setType("Type 1");
        trade.setBuyQuantity(100.0);
        trade.setSellQuantity(50.0);
        trade.setBuyPrice(10.0);
        trade.setSellPrice(12.0);
    }

    @Test
    void testGetAllTrades() {
        when(tradeRepository.findAll()).thenReturn(Arrays.asList(trade));

        List<Trade> trades = tradeService.getAllTrades();

        assertNotNull(trades);
        assertEquals(1, trades.size());
        assertEquals("Test Account", trades.get(0).getAccount());

        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void testGetTradeById_TradeFound() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Trade foundTrade = tradeService.getTradeById(1);

        assertNotNull(foundTrade);
        assertEquals("Test Account", foundTrade.getAccount());

        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void testGetTradeById_TradeNotFound() {
        when(tradeRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> tradeService.getTradeById(2));

        assertEquals("Trade non trouvé", exception.getMessage());
        verify(tradeRepository, times(1)).findById(2);
    }

    @Test
    void testSaveTrade() {
        when(tradeRepository.save(any(Trade.class))).thenReturn(trade);

        Trade savedTrade = tradeService.saveTrade(trade);

        assertNotNull(savedTrade);
        assertEquals("Test Account", savedTrade.getAccount());

        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void testUpdateTrade() {
        Trade updatedTradeDetails = new Trade();
        updatedTradeDetails.setAccount("Updated Account");
        updatedTradeDetails.setType("Updated Type");
        updatedTradeDetails.setBuyQuantity(200.0);
        updatedTradeDetails.setSellQuantity(150.0);
        updatedTradeDetails.setBuyPrice(20.0);
        updatedTradeDetails.setSellPrice(22.0);

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeRepository.save(any(Trade.class))).thenReturn(updatedTradeDetails);

        Trade updatedTrade = tradeService.updateTrade(1, updatedTradeDetails);

        assertNotNull(updatedTrade);
        assertEquals("Updated Account", updatedTrade.getAccount());
        assertEquals(200.0, updatedTrade.getBuyQuantity());

        verify(tradeRepository, times(1)).findById(1);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void testDeleteTrade() {
        doNothing().when(tradeRepository).deleteById(1);

        tradeService.deleteTrade(1);

        verify(tradeRepository, times(1)).deleteById(1);
    }
}
