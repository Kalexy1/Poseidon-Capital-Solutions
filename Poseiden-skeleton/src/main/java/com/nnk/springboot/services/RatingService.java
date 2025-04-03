package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.domain.Rating;
import java.util.List;

/**
 * Service pour la gestion des entités {@link Rating}.
 * <p>
 * Fournit les opérations CRUD liées aux notations financières (ratings).
 * </p>
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Récupère tous les enregistrements de type {@link Rating}.
     *
     * @return une liste de ratings
     */
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    /**
     * Récupère un {@link Rating} par son identifiant.
     *
     * @param id identifiant du rating
     * @return le rating trouvé
     * @throws RuntimeException si aucun rating correspondant n'est trouvé
     */
    public Rating getRatingById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating non trouvé"));
    }

    /**
     * Enregistre un nouveau {@link Rating} dans la base de données.
     *
     * @param rating l'objet rating à sauvegarder
     * @return le rating sauvegardé
     */
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Met à jour un {@link Rating} existant avec de nouvelles valeurs.
     *
     * @param id identifiant du rating à modifier
     * @param ratingDetails nouvelles données à appliquer
     * @return le rating mis à jour
     */
    public Rating updateRating(Integer id, Rating ratingDetails) {
        Rating rating = getRatingById(id);
        rating.setMoodysRating(ratingDetails.getMoodysRating());
        rating.setSandPRating(ratingDetails.getSandPRating());
        rating.setFitchRating(ratingDetails.getFitchRating());
        rating.setOrderNumber(ratingDetails.getOrderNumber());
        return ratingRepository.save(rating);
    }

    /**
     * Supprime un {@link Rating} en fonction de son identifiant.
     *
     * @param id identifiant du rating à supprimer
     */
    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }
}
