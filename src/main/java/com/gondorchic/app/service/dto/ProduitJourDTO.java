package com.gondorchic.app.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.gondorchic.app.domain.ProduitJour} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProduitJourDTO implements Serializable {

    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private ProduitDTO produit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ProduitDTO getProduit() {
        return produit;
    }

    public void setProduit(ProduitDTO produit) {
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitJourDTO)) {
            return false;
        }

        ProduitJourDTO produitJourDTO = (ProduitJourDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, produitJourDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitJourDTO{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", produit=" + getProduit() +
            "}";
    }
}
