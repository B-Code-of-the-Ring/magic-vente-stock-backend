package com.gondorchic.app.service.mapper;

import com.gondorchic.app.domain.Produit;
import com.gondorchic.app.domain.ProduitJour;
import com.gondorchic.app.service.dto.ProduitDTO;
import com.gondorchic.app.service.dto.ProduitJourDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProduitJour} and its DTO {@link ProduitJourDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProduitJourMapper extends EntityMapper<ProduitJourDTO, ProduitJour> {
    @Mapping(target = "produit", source = "produit", qualifiedByName = "produitId")
    ProduitJourDTO toDto(ProduitJour s);

    @Named("produitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProduitDTO toDtoProduitId(Produit produit);
}
