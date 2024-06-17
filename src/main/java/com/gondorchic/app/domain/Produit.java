package com.gondorchic.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "libelle_produit", length = 200, nullable = false)
    private String libelleProduit;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "99999999999999999999.99")
    @Column(name = "prix_produit", precision = 21, scale = 2, nullable = false)
    private BigDecimal prixProduit;

    @NotNull
    @Min(value = 0)
    @Column(name = "quantite_produit", nullable = false)
    private Integer quantiteProduit;

    @Lob
    @Column(name = "image_produit")
    private byte[] imageProduit;

    @Column(name = "image_produit_content_type")
    private String imageProduitContentType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "produit" }, allowSetters = true)
    private Set<ProduitJour> produitJours = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Produit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelleProduit() {
        return this.libelleProduit;
    }

    public Produit libelleProduit(String libelleProduit) {
        this.setLibelleProduit(libelleProduit);
        return this;
    }

    public void setLibelleProduit(String libelleProduit) {
        this.libelleProduit = libelleProduit;
    }

    public BigDecimal getPrixProduit() {
        return this.prixProduit;
    }

    public Produit prixProduit(BigDecimal prixProduit) {
        this.setPrixProduit(prixProduit);
        return this;
    }

    public void setPrixProduit(BigDecimal prixProduit) {
        this.prixProduit = prixProduit;
    }

    public Integer getQuantiteProduit() {
        return this.quantiteProduit;
    }

    public Produit quantiteProduit(Integer quantiteProduit) {
        this.setQuantiteProduit(quantiteProduit);
        return this;
    }

    public void setQuantiteProduit(Integer quantiteProduit) {
        this.quantiteProduit = quantiteProduit;
    }

    public byte[] getImageProduit() {
        return this.imageProduit;
    }

    public Produit imageProduit(byte[] imageProduit) {
        this.setImageProduit(imageProduit);
        return this;
    }

    public void setImageProduit(byte[] imageProduit) {
        this.imageProduit = imageProduit;
    }

    public String getImageProduitContentType() {
        return this.imageProduitContentType;
    }

    public Produit imageProduitContentType(String imageProduitContentType) {
        this.imageProduitContentType = imageProduitContentType;
        return this;
    }

    public void setImageProduitContentType(String imageProduitContentType) {
        this.imageProduitContentType = imageProduitContentType;
    }

    public Set<ProduitJour> getProduitJours() {
        return this.produitJours;
    }

    public void setProduitJours(Set<ProduitJour> produitJours) {
        if (this.produitJours != null) {
            this.produitJours.forEach(i -> i.setProduit(null));
        }
        if (produitJours != null) {
            produitJours.forEach(i -> i.setProduit(this));
        }
        this.produitJours = produitJours;
    }

    public Produit produitJours(Set<ProduitJour> produitJours) {
        this.setProduitJours(produitJours);
        return this;
    }

    public Produit addProduitJour(ProduitJour produitJour) {
        this.produitJours.add(produitJour);
        produitJour.setProduit(this);
        return this;
    }

    public Produit removeProduitJour(ProduitJour produitJour) {
        this.produitJours.remove(produitJour);
        produitJour.setProduit(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return getId() != null && getId().equals(((Produit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", libelleProduit='" + getLibelleProduit() + "'" +
            ", prixProduit=" + getPrixProduit() +
            ", quantiteProduit=" + getQuantiteProduit() +
            ", imageProduit='" + getImageProduit() + "'" +
            ", imageProduitContentType='" + getImageProduitContentType() + "'" +
            "}";
    }
}
