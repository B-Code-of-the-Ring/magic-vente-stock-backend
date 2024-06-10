package com.gondorchic.app.service.mapper;

import com.gondorchic.app.domain.Produit;
import com.gondorchic.app.service.dto.ProduitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {}
