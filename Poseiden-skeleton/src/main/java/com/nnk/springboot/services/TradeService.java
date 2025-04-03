package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.domain.Trade;
import java.util.List;

/**
 * Service pour la gestion des entités {@link Trade}.
 * <p>
 * Fournit les opérations CRUD liées aux transactions de l'application.
 * </p>
 */
@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Récupère toutes les transactions existantes.
     *
     * @return une liste de {@link Trade}
     */
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /**
     * Récupère une transaction à partir de son identifiant.
     *
     * @param id identifiant de la transaction
     * @return la transaction correspondante
     * @throws RuntimeException si aucune transaction n'est trouvée
     */
    public Trade getTradeById(Integer id) {
        return tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade non trouvé"));
    }

    /**
     * Enregistre une nouvelle transaction.
     *
     * @param trade l'objet {@link Trade} à sauvegarder
     * @return l'objet sauvegardé
     */
    public Trade saveTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    /**
     * Met à jour une transaction existante avec les nouvelles valeurs fournies.
     *
     * @param id identifiant de la transaction à mettre à jour
     * @param tradeDetails les nouvelles valeurs de la transaction
     * @return la transaction mise à jour
     */
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

    /**
     * Supprime une transaction à partir de son identifiant.
     *
     * @param id identifiant de la transaction à supprimer
     */
    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
