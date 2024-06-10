package com.gondorchic.app.service;

import com.gondorchic.app.service.dto.ProduitJourDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.gondorchic.app.domain.ProduitJour}.
 */
public interface ProduitJourService {
    /**
     * Save a produitJour.
     *
     * @param produitJourDTO the entity to save.
     * @return the persisted entity.
     */
    ProduitJourDTO save(ProduitJourDTO produitJourDTO);

    /**
     * Updates a produitJour.
     *
     * @param produitJourDTO the entity to update.
     * @return the persisted entity.
     */
    ProduitJourDTO update(ProduitJourDTO produitJourDTO);

    /**
     * Partially updates a produitJour.
     *
     * @param produitJourDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProduitJourDTO> partialUpdate(ProduitJourDTO produitJourDTO);

    /**
     * Get all the produitJours.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProduitJourDTO> findAll(Pageable pageable);

    /**
     * Get the "id" produitJour.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProduitJourDTO> findOne(Long id);

    /**
     * Delete the "id" produitJour.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
