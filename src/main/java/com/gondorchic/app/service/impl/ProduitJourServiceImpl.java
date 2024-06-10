package com.gondorchic.app.service.impl;

import com.gondorchic.app.domain.ProduitJour;
import com.gondorchic.app.repository.ProduitJourRepository;
import com.gondorchic.app.service.ProduitJourService;
import com.gondorchic.app.service.dto.ProduitJourDTO;
import com.gondorchic.app.service.mapper.ProduitJourMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.gondorchic.app.domain.ProduitJour}.
 */
@Service
@Transactional
public class ProduitJourServiceImpl implements ProduitJourService {

    private final Logger log = LoggerFactory.getLogger(ProduitJourServiceImpl.class);

    private final ProduitJourRepository produitJourRepository;

    private final ProduitJourMapper produitJourMapper;

    public ProduitJourServiceImpl(ProduitJourRepository produitJourRepository, ProduitJourMapper produitJourMapper) {
        this.produitJourRepository = produitJourRepository;
        this.produitJourMapper = produitJourMapper;
    }

    @Override
    public ProduitJourDTO save(ProduitJourDTO produitJourDTO) {
        log.debug("Request to save ProduitJour : {}", produitJourDTO);
        ProduitJour produitJour = produitJourMapper.toEntity(produitJourDTO);
        produitJour = produitJourRepository.save(produitJour);
        return produitJourMapper.toDto(produitJour);
    }

    @Override
    public ProduitJourDTO update(ProduitJourDTO produitJourDTO) {
        log.debug("Request to update ProduitJour : {}", produitJourDTO);
        ProduitJour produitJour = produitJourMapper.toEntity(produitJourDTO);
        produitJour = produitJourRepository.save(produitJour);
        return produitJourMapper.toDto(produitJour);
    }

    @Override
    public Optional<ProduitJourDTO> partialUpdate(ProduitJourDTO produitJourDTO) {
        log.debug("Request to partially update ProduitJour : {}", produitJourDTO);

        return produitJourRepository
            .findById(produitJourDTO.getId())
            .map(existingProduitJour -> {
                produitJourMapper.partialUpdate(existingProduitJour, produitJourDTO);

                return existingProduitJour;
            })
            .map(produitJourRepository::save)
            .map(produitJourMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProduitJourDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProduitJours");
        return produitJourRepository.findAll(pageable).map(produitJourMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitJourDTO> findOne(Long id) {
        log.debug("Request to get ProduitJour : {}", id);
        return produitJourRepository.findById(id).map(produitJourMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProduitJour : {}", id);
        produitJourRepository.deleteById(id);
    }
}
