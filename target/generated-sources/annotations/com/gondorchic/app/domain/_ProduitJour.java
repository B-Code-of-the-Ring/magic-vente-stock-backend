package com.gondorchic.app.domain;

import jakarta.annotation.Generated;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.impl.SortableAttributeRecord;

@StaticMetamodel(ProduitJour.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public interface _ProduitJour {

	String PRODUIT = "produit";
	String DATE_FIN = "dateFin";
	String ID = "id";
	String DATE_DEBUT = "dateDebut";

	
	/**
	 * @see ProduitJour#produit
	 **/
	SortableAttribute<ProduitJour> produit = new SortableAttributeRecord<>(PRODUIT);
	
	/**
	 * @see ProduitJour#dateFin
	 **/
	SortableAttribute<ProduitJour> dateFin = new SortableAttributeRecord<>(DATE_FIN);
	
	/**
	 * @see ProduitJour#id
	 **/
	SortableAttribute<ProduitJour> id = new SortableAttributeRecord<>(ID);
	
	/**
	 * @see ProduitJour#dateDebut
	 **/
	SortableAttribute<ProduitJour> dateDebut = new SortableAttributeRecord<>(DATE_DEBUT);

}

