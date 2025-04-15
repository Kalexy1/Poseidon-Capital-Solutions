package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Représente une entité CurvePoint utilisée pour stocker des points de courbe
 * dans le système, généralement utilisés pour les calculs financiers.
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "CurveId est requis")
    @Column(name = "curve_id")
    private Integer curveId;

    @Column(name = "as_of_date")
    private Timestamp asOfDate;

    @NotNull(message = "Le terme est requis")
    @Positive(message = "Bid quantity must be greater than 0")
    private Double term;

    @NotNull(message = "La valeur est requise")
    @Positive(message = "Bid quantity must be greater than 0")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    /**
     * Constructeur par défaut.
     */
    public CurvePoint() {}

    /**
     * Constructeur principal pour les champs requis.
     *
     * @param curveId identifiant de la courbe
     * @param term    terme associé
     * @param value   valeur correspondante
     */
    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
        this.asOfDate = Timestamp.valueOf(LocalDateTime.now());
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * @return l'identifiant unique du point de courbe
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id identifiant du point de courbe
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return l'identifiant de la courbe associée
     */
    public Integer getCurveId() {
        return curveId;
    }

    /**
     * @param curveId identifiant de la courbe
     */
    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    /**
     * @return la date de référence du point de courbe
     */
    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    /**
     * @param asOfDate date de référence
     */
    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    /**
     * @return le terme (échéance) du point de courbe
     */
    public Double getTerm() {
        return term;
    }

    /**
     * @param term terme (échéance)
     */
    public void setTerm(Double term) {
        this.term = term;
    }

    /**
     * @return la valeur associée au terme
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value valeur correspondante
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return la date de création de l'entité
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate date de création
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
