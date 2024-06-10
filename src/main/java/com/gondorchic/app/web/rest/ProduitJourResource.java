package com.gondorchic.app.web.rest;

import com.gondorchic.app.repository.ProduitJourRepository;
import com.gondorchic.app.service.ProduitJourService;
import com.gondorchic.app.service.dto.ProduitJourDTO;
import com.gondorchic.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.gondorchic.app.domain.ProduitJour}.
 */
@RestController
@RequestMapping("/api/produit-jours")
public class ProduitJourResource {

    private final Logger log = LoggerFactory.getLogger(ProduitJourResource.class);

    private static final String ENTITY_NAME = "produitJour";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProduitJourService produitJourService;

    private final ProduitJourRepository produitJourRepository;

    public ProduitJourResource(ProduitJourService produitJourService, ProduitJourRepository produitJourRepository) {
        this.produitJourService = produitJourService;
        this.produitJourRepository = produitJourRepository;
    }

    /**
     * {@code POST  /produit-jours} : Create a new produitJour.
     *
     * @param produitJourDTO the produitJourDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produitJourDTO, or with status {@code 400 (Bad Request)} if the produitJour has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProduitJourDTO> createProduitJour(@RequestBody ProduitJourDTO produitJourDTO) throws URISyntaxException {
        log.debug("REST request to save ProduitJour : {}", produitJourDTO);
        if (produitJourDTO.getId() != null) {
            throw new BadRequestAlertException("A new produitJour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        produitJourDTO = produitJourService.save(produitJourDTO);
        return ResponseEntity.created(new URI("/api/produit-jours/" + produitJourDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, produitJourDTO.getId().toString()))
            .body(produitJourDTO);
    }

    /**
     * {@code PUT  /produit-jours/:id} : Updates an existing produitJour.
     *
     * @param id the id of the produitJourDTO to save.
     * @param produitJourDTO the produitJourDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produitJourDTO,
     * or with status {@code 400 (Bad Request)} if the produitJourDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produitJourDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProduitJourDTO> updateProduitJour(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProduitJourDTO produitJourDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProduitJour : {}, {}", id, produitJourDTO);
        if (produitJourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produitJourDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produitJourRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        produitJourDTO = produitJourService.update(produitJourDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, produitJourDTO.getId().toString()))
            .body(produitJourDTO);
    }

    /**
     * {@code PATCH  /produit-jours/:id} : Partial updates given fields of an existing produitJour, field will ignore if it is null
     *
     * @param id the id of the produitJourDTO to save.
     * @param produitJourDTO the produitJourDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produitJourDTO,
     * or with status {@code 400 (Bad Request)} if the produitJourDTO is not valid,
     * or with status {@code 404 (Not Found)} if the produitJourDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the produitJourDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProduitJourDTO> partialUpdateProduitJour(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ProduitJourDTO produitJourDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProduitJour partially : {}, {}", id, produitJourDTO);
        if (produitJourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, produitJourDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!produitJourRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProduitJourDTO> result = produitJourService.partialUpdate(produitJourDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, produitJourDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /produit-jours} : get all the produitJours.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produitJours in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ProduitJourDTO>> getAllProduitJours(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ProduitJours");
        Page<ProduitJourDTO> page = produitJourService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /produit-jours/:id} : get the "id" produitJour.
     *
     * @param id the id of the produitJourDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produitJourDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProduitJourDTO> getProduitJour(@PathVariable("id") Long id) {
        log.debug("REST request to get ProduitJour : {}", id);
        Optional<ProduitJourDTO> produitJourDTO = produitJourService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produitJourDTO);
    }

    /**
     * {@code DELETE  /produit-jours/:id} : delete the "id" produitJour.
     *
     * @param id the id of the produitJourDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduitJour(@PathVariable("id") Long id) {
        log.debug("REST request to delete ProduitJour : {}", id);
        produitJourService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
