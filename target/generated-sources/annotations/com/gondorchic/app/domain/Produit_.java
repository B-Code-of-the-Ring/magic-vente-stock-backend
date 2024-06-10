package com.gondorchic.app.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(Produit.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Produit_ {

	public static final String QUANTITE_PRODUIT = "quantiteProduit";
	public static final String LIBELLE_PRODUIT = "libelleProduit";
	public static final String IMAGE_PRODUIT = "imageProduit";
	public static final String ID = "id";
	public static final String PRODUIT_JOURS = "produitJours";
	public static final String PRIX_PRODUIT = "prixProduit";

	
	/**
	 * @see com.gondorchic.app.domain.Produit#quantiteProduit
	 **/
	public static volatile SingularAttribute<Produit, Integer> quantiteProduit;
	
	/**
	 * @see com.gondorchic.app.domain.Produit#libelleProduit
	 **/
	public static volatile SingularAttribute<Produit, String> libelleProduit;
	
	/**
	 * @see com.gondorchic.app.domain.Produit#imageProduit
	 **/
	public static volatile SingularAttribute<Produit, String> imageProduit;
	
	/**
	 * @see com.gondorchic.app.domain.Produit#id
	 **/
	public static volatile SingularAttribute<Produit, Long> id;
	
	/**
	 * @see com.gondorchic.app.domain.Produit#produitJours
	 **/
	public static volatile SetAttribute<Produit, ProduitJour> produitJours;
	
	/**
	 * @see com.gondorchic.app.domain.Produit
	 **/
	public static volatile EntityType<Produit> class_;
	
	/**
	 * @see com.gondorchic.app.domain.Produit#prixProduit
	 **/
	public static volatile SingularAttribute<Produit, BigDecimal> prixProduit;

}

