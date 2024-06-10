package com.gondorchic.app.repository;

import com.gondorchic.app.domain.ProduitJour;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProduitJour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitJourRepository extends JpaRepository<ProduitJour, Long> {}
