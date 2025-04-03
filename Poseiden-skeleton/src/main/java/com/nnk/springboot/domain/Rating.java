package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entité représentant une notation financière (Rating) dans le système.
 * <p>
 * Chaque instance inclut des notations issues des agences Moody's, S&P et Fitch,
 * ainsi qu'un ordre numérique utilisé pour le tri ou la hiérarchisation.
 * </p>
 */
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // explicite pour éviter les doublons de noms en base
    private Integer id;

    @NotBlank(message = "Moody's rating est requis")
    @Column(name = "moodysRating")
    private String moodysRating;

    @NotBlank(message = "S&P rating est requis")
    @Column(name = "sandPRating")
    private String sandPRating;

    @NotBlank(message = "Fitch rating est requis")
    @Column(name = "fitchRating")
    private String fitchRating;

    @NotNull(message = "L'ordre est obligatoire")
    @Column(name = "orderNumber")
    private Integer orderNumber;

    /**
     * Constructeur sans argument requis par JPA.
     */
    public Rating() {}

    /**
     * Constructeur avec tous les champs obligatoires.
     *
     * @param moodysRating la notation fournie par Moody's
     * @param sandPRating  la notation fournie par S&P
     * @param fitchRating  la notation fournie par Fitch
     * @param orderNumber  l'ordre de classement
     */
    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMoodysRating() { return moodysRating; }
    public void setMoodysRating(String moodysRating) { this.moodysRating = moodysRating; }

    public String getSandPRating() { return sandPRating; }
    public void setSandPRating(String sandPRating) { this.sandPRating = sandPRating; }

    public String getFitchRating() { return fitchRating; }
    public void setFitchRating(String fitchRating) { this.fitchRating = fitchRating; }

    public Integer getOrderNumber() { return orderNumber; }
    public void setOrderNumber(Integer orderNumber) { this.orderNumber = orderNumber; }
}
