package com.gondorchic.app.service.mapper;

import com.gondorchic.app.domain.Produit;
import com.gondorchic.app.service.dto.ProduitDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-10T04:48:53+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class ProduitMapperImpl implements ProduitMapper {

    @Override
    public Produit toEntity(ProduitDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Produit produit = new Produit();

        produit.setId( dto.getId() );
        produit.setLibelleProduit( dto.getLibelleProduit() );
        produit.setPrixProduit( dto.getPrixProduit() );
        produit.setQuantiteProduit( dto.getQuantiteProduit() );
        produit.setImageProduit( dto.getImageProduit() );

        return produit;
    }

    @Override
    public ProduitDTO toDto(Produit entity) {
        if ( entity == null ) {
            return null;
        }

        ProduitDTO produitDTO = new ProduitDTO();

        produitDTO.setId( entity.getId() );
        produitDTO.setLibelleProduit( entity.getLibelleProduit() );
        produitDTO.setPrixProduit( entity.getPrixProduit() );
        produitDTO.setQuantiteProduit( entity.getQuantiteProduit() );
        produitDTO.setImageProduit( entity.getImageProduit() );

        return produitDTO;
    }

    @Override
    public List<Produit> toEntity(List<ProduitDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Produit> list = new ArrayList<Produit>( dtoList.size() );
        for ( ProduitDTO produitDTO : dtoList ) {
            list.add( toEntity( produitDTO ) );
        }

        return list;
    }

    @Override
    public List<ProduitDTO> toDto(List<Produit> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProduitDTO> list = new ArrayList<ProduitDTO>( entityList.size() );
        for ( Produit produit : entityList ) {
            list.add( toDto( produit ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Produit entity, ProduitDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getLibelleProduit() != null ) {
            entity.setLibelleProduit( dto.getLibelleProduit() );
        }
        if ( dto.getPrixProduit() != null ) {
            entity.setPrixProduit( dto.getPrixProduit() );
        }
        if ( dto.getQuantiteProduit() != null ) {
            entity.setQuantiteProduit( dto.getQuantiteProduit() );
        }
        if ( dto.getImageProduit() != null ) {
            entity.setImageProduit( dto.getImageProduit() );
        }
    }
}
