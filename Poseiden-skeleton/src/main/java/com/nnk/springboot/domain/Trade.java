package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;

/**
 * Représente une entité de transaction (Trade) utilisée dans l'application.
 * <p>
 * Cette entité contient les informations d'une opération financière telle qu'un achat ou une vente,
 * avec les détails associés comme les quantités, les prix, le compte, le type, etc.
 * </p>
 */
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tradeId")
    private Integer tradeId;

    @NotBlank(message = "Le compte est requis")
    @Column(name = "account")
    private String account;

    @NotBlank(message = "Le type est requis")
    @Column(name = "type")
    private String type;

    @NotNull(message = "La quantité achetée est requise")
    @Positive(message = "Bid quantity must be greater than 0")
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    @NotNull(message = "La quantité vendue est requise")
    @Positive(message = "Bid quantity must be greater than 0")
    @Column(name = "sellQuantity")
    private Double sellQuantity;

    @NotNull(message = "Le prix d'achat est requis")
    @Positive(message = "Bid quantity must be greater than 0")
    @Column(name = "buyPrice")
    private Double buyPrice;

    @NotNull(message = "Le prix de vente est requis")
    @Positive(message = "Bid quantity must be greater than 0")
    @Column(name = "sellPrice")
    private Double sellPrice;

    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    @Column(name = "security")
    private String security;

    @Column(name = "status")
    private String status;

    @Column(name = "trader")
    private String trader;

    @Column(name = "benchmark")
    private String benchmark;

    @Column(name = "book")
    private String book;

    @Column(name = "creationName")
    private String creationName;

    @Column(name = "creationDate")
    private Timestamp creationDate;

    @Column(name = "revisionName")
    private String revisionName;

    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    @Column(name = "dealName")
    private String dealName;

    @Column(name = "dealType")
    private String dealType;

    @Column(name = "sourceListId")
    private String sourceListId;

    @Column(name = "side")
    private String side;

    /**
     * Constructeur par défaut.
     */
    public Trade() {}

    /**
     * Constructeur principal pour les champs obligatoires.
     *
     * @param account      le compte concerné par la transaction
     * @param type         le type de transaction
     * @param buyQuantity  la quantité achetée
     * @param sellQuantity la quantité vendue
     * @param buyPrice     le prix d'achat
     * @param sellPrice    le prix de vente
     */
    public Trade(String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    /** @return l'identifiant de la transaction */
    public Integer getTradeId() { return tradeId; }

    /** @param tradeId l'identifiant à définir */
    public void setTradeId(Integer tradeId) { this.tradeId = tradeId; }

    /** @return le compte concerné */
    public String getAccount() { return account; }

    /** @param account le compte à définir */
    public void setAccount(String account) { this.account = account; }

    /** @return le type de transaction */
    public String getType() { return type; }

    /** @param type le type à définir */
    public void setType(String type) { this.type = type; }

    /** @return la quantité achetée */
    public Double getBuyQuantity() { return buyQuantity; }

    /** @param buyQuantity la quantité achetée à définir */
    public void setBuyQuantity(Double buyQuantity) { this.buyQuantity = buyQuantity; }

    /** @return la quantité vendue */
    public Double getSellQuantity() { return sellQuantity; }

    /** @param sellQuantity la quantité vendue à définir */
    public void setSellQuantity(Double sellQuantity) { this.sellQuantity = sellQuantity; }

    /** @return le prix d'achat */
    public Double getBuyPrice() { return buyPrice; }

    /** @param buyPrice le prix d'achat à définir */
    public void setBuyPrice(Double buyPrice) { this.buyPrice = buyPrice; }

    /** @return le prix de vente */
    public Double getSellPrice() { return sellPrice; }

    /** @param sellPrice le prix de vente à définir */
    public void setSellPrice(Double sellPrice) { this.sellPrice = sellPrice; }

    /** @return la date de la transaction */
    public Timestamp getTradeDate() { return tradeDate; }

    /** @param tradeDate la date de transaction à définir */
    public void setTradeDate(Timestamp tradeDate) { this.tradeDate = tradeDate; }

    /** @return le nom du titre financier */
    public String getSecurity() { return security; }

    /** @param security le titre financier à définir */
    public void setSecurity(String security) { this.security = security; }

    /** @return le statut de la transaction */
    public String getStatus() { return status; }

    /** @param status le statut à définir */
    public void setStatus(String status) { this.status = status; }

    /** @return le nom du trader */
    public String getTrader() { return trader; }

    /** @param trader le trader à définir */
    public void setTrader(String trader) { this.trader = trader; }

    /** @return la référence de benchmark */
    public String getBenchmark() { return benchmark; }

    /** @param benchmark la référence de benchmark à définir */
    public void setBenchmark(String benchmark) { this.benchmark = benchmark; }

    /** @return le nom du book */
    public String getBook() { return book; }

    /** @param book le nom du book à définir */
    public void setBook(String book) { this.book = book; }

    /** @return le nom de création */
    public String getCreationName() { return creationName; }

    /** @param creationName le nom de création à définir */
    public void setCreationName(String creationName) { this.creationName = creationName; }

    /** @return la date de création */
    public Timestamp getCreationDate() { return creationDate; }

    /** @param creationDate la date de création à définir */
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }

    /** @return le nom de la révision */
    public String getRevisionName() { return revisionName; }

    /** @param revisionName le nom de révision à définir */
    public void setRevisionName(String revisionName) { this.revisionName = revisionName; }

    /** @return la date de la révision */
    public Timestamp getRevisionDate() { return revisionDate; }

    /** @param revisionDate la date de révision à définir */
    public void setRevisionDate(Timestamp revisionDate) { this.revisionDate = revisionDate; }

    /** @return le nom du deal */
    public String getDealName() { return dealName; }

    /** @param dealName le nom du deal à définir */
    public void setDealName(String dealName) { this.dealName = dealName; }

    /** @return le type de deal */
    public String getDealType() { return dealType; }

    /** @param dealType le type de deal à définir */
    public void setDealType(String dealType) { this.dealType = dealType; }

    /** @return l'identifiant de la source */
    public String getSourceListId() { return sourceListId; }

    /** @param sourceListId l'identifiant de la source à définir */
    public void setSourceListId(String sourceListId) { this.sourceListId = sourceListId; }

    /** @return la direction de l'opération (achat ou vente) */
    public String getSide() { return side; }

    /** @param side la direction de l'opération à définir */
    public void setSide(String side) { this.side = side; }
}
