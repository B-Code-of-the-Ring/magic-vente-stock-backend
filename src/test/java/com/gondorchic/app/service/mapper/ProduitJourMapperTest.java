package com.gondorchic.app.service.mapper;

import static com.gondorchic.app.domain.ProduitJourAsserts.*;
import static com.gondorchic.app.domain.ProduitJourTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProduitJourMapperTest {

    private ProduitJourMapper produitJourMapper;

    @BeforeEach
    void setUp() {
        produitJourMapper = new ProduitJourMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getProduitJourSample1();
        var actual = produitJourMapper.toEntity(produitJourMapper.toDto(expected));
        assertProduitJourAllPropertiesEquals(expected, actual);
    }
}
