package com.gondorchic.app.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ProduitJourAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProduitJourAllPropertiesEquals(ProduitJour expected, ProduitJour actual) {
        assertProduitJourAutoGeneratedPropertiesEquals(expected, actual);
        assertProduitJourAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProduitJourAllUpdatablePropertiesEquals(ProduitJour expected, ProduitJour actual) {
        assertProduitJourUpdatableFieldsEquals(expected, actual);
        assertProduitJourUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProduitJourAutoGeneratedPropertiesEquals(ProduitJour expected, ProduitJour actual) {
        assertThat(expected)
            .as("Verify ProduitJour auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProduitJourUpdatableFieldsEquals(ProduitJour expected, ProduitJour actual) {
        assertThat(expected)
            .as("Verify ProduitJour relevant properties")
            .satisfies(e -> assertThat(e.getDateDebut()).as("check dateDebut").isEqualTo(actual.getDateDebut()))
            .satisfies(e -> assertThat(e.getDateFin()).as("check dateFin").isEqualTo(actual.getDateFin()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertProduitJourUpdatableRelationshipsEquals(ProduitJour expected, ProduitJour actual) {
        assertThat(expected)
            .as("Verify ProduitJour relationships")
            .satisfies(e -> assertThat(e.getProduit()).as("check produit").isEqualTo(actual.getProduit()));
    }
}
