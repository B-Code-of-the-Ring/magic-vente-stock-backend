package com.gondorchic.app.web.rest;

import static com.gondorchic.app.domain.ProduitJourAsserts.*;
import static com.gondorchic.app.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gondorchic.app.IntegrationTest;
import com.gondorchic.app.domain.ProduitJour;
import com.gondorchic.app.repository.ProduitJourRepository;
import com.gondorchic.app.service.dto.ProduitJourDTO;
import com.gondorchic.app.service.mapper.ProduitJourMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProduitJourResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProduitJourResourceIT {

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/produit-jours";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProduitJourRepository produitJourRepository;

    @Autowired
    private ProduitJourMapper produitJourMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProduitJourMockMvc;

    private ProduitJour produitJour;

    private ProduitJour insertedProduitJour;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitJour createEntity(EntityManager em) {
        ProduitJour produitJour = new ProduitJour().dateDebut(DEFAULT_DATE_DEBUT).dateFin(DEFAULT_DATE_FIN);
        return produitJour;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitJour createUpdatedEntity(EntityManager em) {
        ProduitJour produitJour = new ProduitJour().dateDebut(UPDATED_DATE_DEBUT).dateFin(UPDATED_DATE_FIN);
        return produitJour;
    }

    @BeforeEach
    public void initTest() {
        produitJour = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProduitJour != null) {
            produitJourRepository.delete(insertedProduitJour);
            insertedProduitJour = null;
        }
    }

    @Test
    @Transactional
    void createProduitJour() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);
        var returnedProduitJourDTO = om.readValue(
            restProduitJourMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitJourDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProduitJourDTO.class
        );

        // Validate the ProduitJour in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedProduitJour = produitJourMapper.toEntity(returnedProduitJourDTO);
        assertProduitJourUpdatableFieldsEquals(returnedProduitJour, getPersistedProduitJour(returnedProduitJour));

        insertedProduitJour = returnedProduitJour;
    }

    @Test
    @Transactional
    void createProduitJourWithExistingId() throws Exception {
        // Create the ProduitJour with an existing ID
        produitJour.setId(1L);
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitJourMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitJourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProduitJours() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        // Get all the produitJourList
        restProduitJourMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produitJour.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())));
    }

    @Test
    @Transactional
    void getProduitJour() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        // Get the produitJour
        restProduitJourMockMvc
            .perform(get(ENTITY_API_URL_ID, produitJour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produitJour.getId().intValue()))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProduitJour() throws Exception {
        // Get the produitJour
        restProduitJourMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProduitJour() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produitJour
        ProduitJour updatedProduitJour = produitJourRepository.findById(produitJour.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProduitJour are not directly saved in db
        em.detach(updatedProduitJour);
        updatedProduitJour.dateDebut(UPDATED_DATE_DEBUT).dateFin(UPDATED_DATE_FIN);
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(updatedProduitJour);

        restProduitJourMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produitJourDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(produitJourDTO))
            )
            .andExpect(status().isOk());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProduitJourToMatchAllProperties(updatedProduitJour);
    }

    @Test
    @Transactional
    void putNonExistingProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(
                put(ENTITY_API_URL_ID, produitJourDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(produitJourDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(produitJourDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(produitJourDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProduitJourWithPatch() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produitJour using partial update
        ProduitJour partialUpdatedProduitJour = new ProduitJour();
        partialUpdatedProduitJour.setId(produitJour.getId());

        restProduitJourMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduitJour.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduitJour))
            )
            .andExpect(status().isOk());

        // Validate the ProduitJour in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProduitJourUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProduitJour, produitJour),
            getPersistedProduitJour(produitJour)
        );
    }

    @Test
    @Transactional
    void fullUpdateProduitJourWithPatch() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the produitJour using partial update
        ProduitJour partialUpdatedProduitJour = new ProduitJour();
        partialUpdatedProduitJour.setId(produitJour.getId());

        partialUpdatedProduitJour.dateDebut(UPDATED_DATE_DEBUT).dateFin(UPDATED_DATE_FIN);

        restProduitJourMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProduitJour.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProduitJour))
            )
            .andExpect(status().isOk());

        // Validate the ProduitJour in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProduitJourUpdatableFieldsEquals(partialUpdatedProduitJour, getPersistedProduitJour(partialUpdatedProduitJour));
    }

    @Test
    @Transactional
    void patchNonExistingProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, produitJourDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(produitJourDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(produitJourDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProduitJour() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        produitJour.setId(longCount.incrementAndGet());

        // Create the ProduitJour
        ProduitJourDTO produitJourDTO = produitJourMapper.toDto(produitJour);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProduitJourMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(produitJourDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProduitJour in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProduitJour() throws Exception {
        // Initialize the database
        insertedProduitJour = produitJourRepository.saveAndFlush(produitJour);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the produitJour
        restProduitJourMockMvc
            .perform(delete(ENTITY_API_URL_ID, produitJour.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return produitJourRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ProduitJour getPersistedProduitJour(ProduitJour produitJour) {
        return produitJourRepository.findById(produitJour.getId()).orElseThrow();
    }

    protected void assertPersistedProduitJourToMatchAllProperties(ProduitJour expectedProduitJour) {
        assertProduitJourAllPropertiesEquals(expectedProduitJour, getPersistedProduitJour(expectedProduitJour));
    }

    protected void assertPersistedProduitJourToMatchUpdatableProperties(ProduitJour expectedProduitJour) {
        assertProduitJourAllUpdatablePropertiesEquals(expectedProduitJour, getPersistedProduitJour(expectedProduitJour));
    }
}
