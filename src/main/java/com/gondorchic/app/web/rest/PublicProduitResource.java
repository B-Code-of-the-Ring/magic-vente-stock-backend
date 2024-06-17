package com.gondorchic.app.web.rest;

import com.gondorchic.app.repository.ProduitRepository;
import com.gondorchic.app.service.ProduitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/produits")
public class PublicProduitResource extends ProduitResource {

    public PublicProduitResource(ProduitService produitService, ProduitRepository produitRepository) {
        super(produitService, produitRepository);
    }
}
