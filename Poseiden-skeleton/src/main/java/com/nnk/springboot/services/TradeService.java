package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.domain.Trade;
import java.util.List;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade non trouvé"));
    }

    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public Trade updateTrade(Integer id, Trade tradeDetails) {
        Trade trade = getTradeById(id);
        trade.setAccount(tradeDetails.getAccount());
        trade.setType(tradeDetails.getType());
        trade.setBuyQuantity(tradeDetails.getBuyQuantity());
        trade.setSellQuantity(tradeDetails.getSellQuantity());
        trade.setBuyPrice(tradeDetails.getBuyPrice());
        trade.setSellPrice(tradeDetails.getSellPrice());
        return tradeRepository.save(trade);
    }

    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
