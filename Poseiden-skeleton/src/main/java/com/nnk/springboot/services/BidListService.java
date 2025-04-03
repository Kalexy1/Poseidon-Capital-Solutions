package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.domain.BidList;

import java.util.List;

/**
 * Service pour la gestion des entités {@link BidList}.
 * Fournit les opérations CRUD pour manipuler les données de BidList.
 */
@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Récupère toutes les entités {@link BidList} enregistrées.
     *
     * @return liste de toutes les BidList
     */
    public List<BidList> getAllBidLists() {
        return bidListRepository.findAll();
    }

    /**
     * Récupère une entité {@link BidList} par son identifiant.
     *
     * @param id identifiant de la BidList
     * @return la BidList trouvée
     * @throws RuntimeException si non trouvée
     */
    public BidList getBidListById(Integer id) {
        return bidListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BidList non trouvé pour l'id : " + id));
    }

    /**
     * Enregistre une nouvelle entité {@link BidList}.
     *
     * @param bidList la BidList à enregistrer
     * @return la BidList persistée
     */
    public BidList saveBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    /**
     * Met à jour une entité {@link BidList} existante.
     *
     * @param id identifiant de l'entité à mettre à jour
     * @param bidListDetails données à mettre à jour
     * @return la BidList mise à jour
     */
    public BidList updateBidList(Integer id, BidList bidListDetails) {
        BidList bidList = getBidListById(id);

        bidList.setAccount(bidListDetails.getAccount());
        bidList.setType(bidListDetails.getType());
        bidList.setBidQuantity(bidListDetails.getBidQuantity());
        bidList.setAskQuantity(bidListDetails.getAskQuantity());
        bidList.setBid(bidListDetails.getBid());
        bidList.setAsk(bidListDetails.getAsk());
        bidList.setBenchmark(bidListDetails.getBenchmark());
        bidList.setBidListDate(bidListDetails.getBidListDate());
        bidList.setCommentary(bidListDetails.getCommentary());
        bidList.setSecurity(bidListDetails.getSecurity());
        bidList.setStatus(bidListDetails.getStatus());
        bidList.setTrader(bidListDetails.getTrader());
        bidList.setBook(bidListDetails.getBook());
        bidList.setCreationName(bidListDetails.getCreationName());
        bidList.setCreationDate(bidListDetails.getCreationDate());
        bidList.setRevisionName(bidListDetails.getRevisionName());
        bidList.setRevisionDate(bidListDetails.getRevisionDate());
        bidList.setDealName(bidListDetails.getDealName());
        bidList.setDealType(bidListDetails.getDealType());
        bidList.setSourceListId(bidListDetails.getSourceListId());
        bidList.setSide(bidListDetails.getSide());

        return bidListRepository.save(bidList);
    }

    /**
     * Supprime une BidList par son identifiant.
     *
     * @param id identifiant à supprimer
     */
    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }
}
