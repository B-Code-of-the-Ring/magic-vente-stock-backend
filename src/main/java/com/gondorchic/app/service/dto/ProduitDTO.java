package com.gondorchic.app.service.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.gondorchic.app.domain.Produit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProduitDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 200)
    private String libelleProduit;

    private BigDecimal prixProduit;

    private Integer quantiteProduit;

    @Lob
    private String imageProduit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleProduit() {
        return libelleProduit;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public BigDecimal getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(BigDecimal prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Integer getQuantiteProduit() {
        return quantiteProduit;
    }

    public void setQuantiteProduit(Integer quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, produitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", libelleProduit='" + getLibelleProduit() + "'" +
            ", prixProduit=" + getPrixProduit() +
            ", quantiteProduit=" + getQuantiteProduit() +
            ", imageProduit='" + getImageProduit() + "'" +
            "}";
    }
}
