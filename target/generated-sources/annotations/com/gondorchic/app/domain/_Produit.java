package com.gondorchic.app.domain;

import jakarta.annotation.Generated;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

@StaticMetamodel(Produit.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public interface _Produit {

	String QUANTITE_PRODUIT = "quantiteProduit";
	String IMAGE_PRODUIT = "imageProduit";
	String PRIX_PRODUIT = "prixProduit";
	String ID = "id";
	String LIBELLE_PRODUIT = "libelleProduit";

	
	/**
	 * @see Produit#quantiteProduit
	 **/
	SortableAttribute<Produit> quantiteProduit = new SortableAttributeRecord<>(QUANTITE_PRODUIT);
	
	/**
	 * @see Produit#imageProduit
	 **/
	TextAttribute<Produit> imageProduit = new TextAttributeRecord<>(IMAGE_PRODUIT);
	
	/**
	 * @see Produit#prixProduit
	 **/
	SortableAttribute<Produit> prixProduit = new SortableAttributeRecord<>(PRIX_PRODUIT);
	
	/**
	 * @see Produit#id
	 **/
	SortableAttribute<Produit> id = new SortableAttributeRecord<>(ID);
	
	/**
	 * @see Produit#libelleProduit
	 **/
	TextAttribute<Produit> libelleProduit = new TextAttributeRecord<>(LIBELLE_PRODUIT);

}

