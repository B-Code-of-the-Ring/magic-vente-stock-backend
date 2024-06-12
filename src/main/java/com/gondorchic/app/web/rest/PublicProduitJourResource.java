package com.gondorchic.app.web.rest;

import com.gondorchic.app.repository.ProduitJourRepository;
import com.gondorchic.app.service.ProduitJourService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/produit-jours")
public class PublicProduitJourResource extends ProduitJourResource {

    public PublicProduitJourResource(ProduitJourService produitJourService, ProduitJourRepository produitJourRepository) {
        super(produitJourService, produitJourRepository);
    }
}
