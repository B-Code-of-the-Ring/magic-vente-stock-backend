package com.gondorchic.app.domain;

import static com.gondorchic.app.domain.ProduitJourTestSamples.*;
import static com.gondorchic.app.domain.ProduitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gondorchic.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProduitJourTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitJour.class);
        ProduitJour produitJour1 = getProduitJourSample1();
        ProduitJour produitJour2 = new ProduitJour();
        assertThat(produitJour1).isNotEqualTo(produitJour2);

        produitJour2.setId(produitJour1.getId());
        assertThat(produitJour1).isEqualTo(produitJour2);

        produitJour2 = getProduitJourSample2();
        assertThat(produitJour1).isNotEqualTo(produitJour2);
    }

    @Test
    void produitTest() {
        ProduitJour produitJour = getProduitJourRandomSampleGenerator();
        Produit produitBack = getProduitRandomSampleGenerator();

        produitJour.setProduit(produitBack);
        assertThat(produitJour.getProduit()).isEqualTo(produitBack);

        produitJour.produit(null);
        assertThat(produitJour.getProduit()).isNull();
    }
}
