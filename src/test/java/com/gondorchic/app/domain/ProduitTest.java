package com.gondorchic.app.domain;

import static com.gondorchic.app.domain.ProduitJourTestSamples.*;
import static com.gondorchic.app.domain.ProduitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gondorchic.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProduitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produit.class);
        Produit produit1 = getProduitSample1();
        Produit produit2 = new Produit();
        assertThat(produit1).isNotEqualTo(produit2);

        produit2.setId(produit1.getId());
        assertThat(produit1).isEqualTo(produit2);

        produit2 = getProduitSample2();
        assertThat(produit1).isNotEqualTo(produit2);
    }

    @Test
    void produitJourTest() {
        Produit produit = getProduitRandomSampleGenerator();
        ProduitJour produitJourBack = getProduitJourRandomSampleGenerator();

        produit.addProduitJour(produitJourBack);
        assertThat(produit.getProduitJours()).containsOnly(produitJourBack);
        assertThat(produitJourBack.getProduit()).isEqualTo(produit);

        produit.removeProduitJour(produitJourBack);
        assertThat(produit.getProduitJours()).doesNotContain(produitJourBack);
        assertThat(produitJourBack.getProduit()).isNull();

        produit.produitJours(new HashSet<>(Set.of(produitJourBack)));
        assertThat(produit.getProduitJours()).containsOnly(produitJourBack);
        assertThat(produitJourBack.getProduit()).isEqualTo(produit);

        produit.setProduitJours(new HashSet<>());
        assertThat(produit.getProduitJours()).doesNotContain(produitJourBack);
        assertThat(produitJourBack.getProduit()).isNull();
    }
}
