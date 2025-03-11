package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    private Double term;

    @NotNull(message = "La valeur est requise")
    private Double value;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    // --- Constructeurs ---
    public CurvePoint() {}

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
        this.asOfDate= Timestamp.valueOf(LocalDateTime.now());
        this.creationDate= Timestamp.valueOf(LocalDateTime.now());
    }
    
    // --- Getters et Setters ---
	public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public Integer getCurveId() {return curveId;}
    public void setCurveId(Integer curveId) {this.curveId = curveId;}

    public Timestamp getAsOfDate() {return asOfDate;}
    public void setAsOfDate(Timestamp asOfDate) {this.asOfDate = asOfDate;}

    public Double getTerm() {return term;}
    public void setTerm(Double term) {this.term = term;}

    public Double getValue() {return value;}
    public void setValue(Double value) {this.value = value;}

    public Timestamp getCreationDate() {return creationDate;}
    public void setCreationDate(Timestamp creationDate) {this.creationDate = creationDate;}
}
