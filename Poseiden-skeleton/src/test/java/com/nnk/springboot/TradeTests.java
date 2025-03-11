package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeTests {

    @Autowired
    private TradeRepository tradeRepository;

    private Trade trade;

    @BeforeEach
    public void setup() {
        trade = new Trade("Trade Account", "Type", 10d, 5d, 100d, 105d);
        trade = tradeRepository.save(trade);
    }

    @AfterEach
    public void cleanup() {
        tradeRepository.deleteAll();
    }

    @Test
    public void testSaveTrade() {
        assertNotNull(trade.getTradeId());
        assertEquals("Trade Account", trade.getAccount());
    }

    @Test
    public void testUpdateTrade() {
        trade.setAccount("Trade Account Update");
        trade = tradeRepository.save(trade);
        assertEquals("Trade Account Update", trade.getAccount());
    }

    @Test
    public void testFindAllTrades() {
        List<Trade> listResult = tradeRepository.findAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void testDeleteTrade() {
        Integer id = trade.getTradeId();
        tradeRepository.delete(trade);
        Optional<Trade> tradeList = tradeRepository.findById(id);
        assertFalse(tradeList.isPresent());
    }
}
