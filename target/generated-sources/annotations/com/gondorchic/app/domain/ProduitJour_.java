package com.gondorchic.app.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(ProduitJour.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ProduitJour_ {

	public static final String PRODUIT = "produit";
	public static final String DATE_DEBUT = "dateDebut";
	public static final String ID = "id";
	public static final String DATE_FIN = "dateFin";

	
	/**
	 * @see com.gondorchic.app.domain.ProduitJour#produit
	 **/
	public static volatile SingularAttribute<ProduitJour, Produit> produit;
	
	/**
	 * @see com.gondorchic.app.domain.ProduitJour#dateDebut
	 **/
	public static volatile SingularAttribute<ProduitJour, LocalDate> dateDebut;
	
	/**
	 * @see com.gondorchic.app.domain.ProduitJour#id
	 **/
	public static volatile SingularAttribute<ProduitJour, Long> id;
	
	/**
	 * @see com.gondorchic.app.domain.ProduitJour#dateFin
	 **/
	public static volatile SingularAttribute<ProduitJour, LocalDate> dateFin;
	
	/**
	 * @see com.gondorchic.app.domain.ProduitJour
	 **/
	public static volatile EntityType<ProduitJour> class_;

}

