package com.gondorchic.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.gondorchic.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProduitJourDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitJourDTO.class);
        ProduitJourDTO produitJourDTO1 = new ProduitJourDTO();
        produitJourDTO1.setId(1L);
        ProduitJourDTO produitJourDTO2 = new ProduitJourDTO();
        assertThat(produitJourDTO1).isNotEqualTo(produitJourDTO2);
        produitJourDTO2.setId(produitJourDTO1.getId());
        assertThat(produitJourDTO1).isEqualTo(produitJourDTO2);
        produitJourDTO2.setId(2L);
        assertThat(produitJourDTO1).isNotEqualTo(produitJourDTO2);
        produitJourDTO1.setId(null);
        assertThat(produitJourDTO1).isNotEqualTo(produitJourDTO2);
    }
}
