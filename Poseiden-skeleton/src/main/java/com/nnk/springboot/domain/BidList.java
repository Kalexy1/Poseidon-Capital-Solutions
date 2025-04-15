package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

/**
 * Représente une enchère (BidList) dans le système.
 */
@Entity
@Table(name = "bidlist")
public class BidList {

    /** Identifiant unique de l'enchère */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Integer bidListId;

    /** Compte associé à l'enchère */
    @NotBlank(message = "Le compte est requis")
    @Column(name = "account")
    private String account;

    /** Type d'enchère */
    @NotBlank(message = "Le type est requis")
    @Column(name = "type")
    private String type;

    /** Quantité offerte */
    @NotNull(message = "La quantité est requise")
    @Positive(message = "Bid quantity must be greater than 0")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    /** Quantité demandée */
    @Column(name = "askQuantity")
    private Double askQuantity;

    /** Prix d'achat */
    @Column(name = "bid")
    private Double bid;

    /** Prix de vente */
    @Column(name = "ask")
    private Double ask;

    /** Benchmark de comparaison */
    @Column(name = "benchmark")
    private String benchmark;

    /** Date de l'enchère */
    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    /** Commentaire associé */
    @Column(name = "commentary")
    private String commentary;

    /** Sécurité financière concernée */
    @Column(name = "security")
    private String security;

    /** Statut de l'enchère */
    @Column(name = "status")
    private String status;

    /** Nom du trader */
    @Column(name = "trader")
    private String trader;

    /** Livre de transactions */
    @Column(name = "book")
    private String book;

    /** Nom du créateur */
    @Column(name = "creationName")
    private String creationName;

    /** Date de création */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    /** Nom du réviseur */
    @Column(name = "revisionName")
    private String revisionName;

    /** Date de révision */
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    /** Nom de l'opération */
    @Column(name = "dealName")
    private String dealName;

    /** Type d'opération */
    @Column(name = "dealType")
    private String dealType;

    /** Identifiant de la source */
    @Column(name = "sourceListId")
    private String sourceListId;

    /** Côté de la transaction (achat ou vente) */
    @Column(name = "side")
    private String side;

    /** Constructeur par défaut */
    public BidList() {}

    /**
     * Constructeur avec les champs essentiels.
     * @param account compte
     * @param type type d'enchère
     * @param bidQuantity quantité offerte
     * @param askQuantity quantité demandée
     * @param bid prix d'achat
     * @param ask prix de vente
     */
    public BidList(String account, String type, Double bidQuantity, Double askQuantity, Double bid, Double ask) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
    }

    public Integer getBidListId() { return bidListId; }
    public void setBidListId(Integer bidListId) { this.bidListId = bidListId; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBidQuantity() { return bidQuantity; }
    public void setBidQuantity(Double bidQuantity) { this.bidQuantity = bidQuantity; }

    public Double getAskQuantity() { return askQuantity; }
    public void setAskQuantity(Double askQuantity) { this.askQuantity = askQuantity; }

    public Double getBid() { return bid; }
    public void setBid(Double bid) { this.bid = bid; }

    public Double getAsk() { return ask; }
    public void setAsk(Double ask) { this.ask = ask; }

    public String getBenchmark() { return benchmark; }
    public void setBenchmark(String benchmark) { this.benchmark = benchmark; }

    public Timestamp getBidListDate() { return bidListDate; }
    public void setBidListDate(Timestamp bidListDate) { this.bidListDate = bidListDate; }

    public String getCommentary() { return commentary; }
    public void setCommentary(String commentary) { this.commentary = commentary; }

    public String getSecurity() { return security; }
    public void setSecurity(String security) { this.security = security; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTrader() { return trader; }
    public void setTrader(String trader) { this.trader = trader; }

    public String getBook() { return book; }
    public void setBook(String book) { this.book = book; }

    public String getCreationName() { return creationName; }
    public void setCreationName(String creationName) { this.creationName = creationName; }

    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }

    public String getRevisionName() { return revisionName; }
    public void setRevisionName(String revisionName) { this.revisionName = revisionName; }

    public Timestamp getRevisionDate() { return revisionDate; }
    public void setRevisionDate(Timestamp revisionDate) { this.revisionDate = revisionDate; }

    public String getDealName() { return dealName; }
    public void setDealName(String dealName) { this.dealName = dealName; }

    public String getDealType() { return dealType; }
    public void setDealType(String dealType) { this.dealType = dealType; }

    public String getSourceListId() { return sourceListId; }
    public void setSourceListId(String sourceListId) { this.sourceListId = sourceListId; }

    public String getSide() { return side; }
    public void setSide(String side) { this.side = side; }
}
