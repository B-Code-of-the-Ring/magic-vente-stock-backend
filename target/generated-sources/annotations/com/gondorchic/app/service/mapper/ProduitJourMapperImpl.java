package com.gondorchic.app.service.mapper;

import com.gondorchic.app.domain.Produit;
import com.gondorchic.app.domain.ProduitJour;
import com.gondorchic.app.service.dto.ProduitDTO;
import com.gondorchic.app.service.dto.ProduitJourDTO;
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
public class ProduitJourMapperImpl implements ProduitJourMapper {

    @Override
    public ProduitJour toEntity(ProduitJourDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProduitJour produitJour = new ProduitJour();

        produitJour.setId( dto.getId() );
        produitJour.setDateDebut( dto.getDateDebut() );
        produitJour.setDateFin( dto.getDateFin() );
        produitJour.produit( produitDTOToProduit( dto.getProduit() ) );

        return produitJour;
    }

    @Override
    public List<ProduitJour> toEntity(List<ProduitJourDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ProduitJour> list = new ArrayList<ProduitJour>( dtoList.size() );
        for ( ProduitJourDTO produitJourDTO : dtoList ) {
            list.add( toEntity( produitJourDTO ) );
        }

        return list;
    }

    @Override
    public List<ProduitJourDTO> toDto(List<ProduitJour> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProduitJourDTO> list = new ArrayList<ProduitJourDTO>( entityList.size() );
        for ( ProduitJour produitJour : entityList ) {
            list.add( toDto( produitJour ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(ProduitJour entity, ProduitJourDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getDateDebut() != null ) {
            entity.setDateDebut( dto.getDateDebut() );
        }
        if ( dto.getDateFin() != null ) {
            entity.setDateFin( dto.getDateFin() );
        }
        if ( dto.getProduit() != null ) {
            if ( entity.getProduit() == null ) {
                entity.produit( new Produit() );
            }
            produitDTOToProduit1( dto.getProduit(), entity.getProduit() );
        }
    }

    @Override
    public ProduitJourDTO toDto(ProduitJour s) {
        if ( s == null ) {
            return null;
        }

        ProduitJourDTO produitJourDTO = new ProduitJourDTO();

        produitJourDTO.setProduit( toDtoProduitId( s.getProduit() ) );
        produitJourDTO.setId( s.getId() );
        produitJourDTO.setDateDebut( s.getDateDebut() );
        produitJourDTO.setDateFin( s.getDateFin() );

        return produitJourDTO;
    }

    @Override
    public ProduitDTO toDtoProduitId(Produit produit) {
        if ( produit == null ) {
            return null;
        }

        ProduitDTO produitDTO = new ProduitDTO();

        produitDTO.setId( produit.getId() );

        return produitDTO;
    }

    protected Produit produitDTOToProduit(ProduitDTO produitDTO) {
        if ( produitDTO == null ) {
            return null;
        }

        Produit produit = new Produit();

        produit.setId( produitDTO.getId() );
        produit.setLibelleProduit( produitDTO.getLibelleProduit() );
        produit.setPrixProduit( produitDTO.getPrixProduit() );
        produit.setQuantiteProduit( produitDTO.getQuantiteProduit() );
        produit.setImageProduit( produitDTO.getImageProduit() );

        return produit;
    }

    protected void produitDTOToProduit1(ProduitDTO produitDTO, Produit mappingTarget) {
        if ( produitDTO == null ) {
            return;
        }

        if ( produitDTO.getId() != null ) {
            mappingTarget.setId( produitDTO.getId() );
        }
        if ( produitDTO.getLibelleProduit() != null ) {
            mappingTarget.setLibelleProduit( produitDTO.getLibelleProduit() );
        }
        if ( produitDTO.getPrixProduit() != null ) {
            mappingTarget.setPrixProduit( produitDTO.getPrixProduit() );
        }
        if ( produitDTO.getQuantiteProduit() != null ) {
            mappingTarget.setQuantiteProduit( produitDTO.getQuantiteProduit() );
        }
        if ( produitDTO.getImageProduit() != null ) {
            mappingTarget.setImageProduit( produitDTO.getImageProduit() );
        }
    }
}
