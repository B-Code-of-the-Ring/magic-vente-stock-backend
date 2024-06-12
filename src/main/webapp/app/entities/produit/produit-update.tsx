import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduit } from 'app/shared/model/produit.model';
import { getEntity, updateEntity, createEntity, reset } from './produit.reducer';

export const ProduitUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const produitEntity = useAppSelector(state => state.produit.entity);
  const loading = useAppSelector(state => state.produit.loading);
  const updating = useAppSelector(state => state.produit.updating);
  const updateSuccess = useAppSelector(state => state.produit.updateSuccess);

  const handleClose = () => {
    navigate('/produit' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.prixProduit !== undefined && typeof values.prixProduit !== 'number') {
      values.prixProduit = Number(values.prixProduit);
    }
    if (values.quantiteProduit !== undefined && typeof values.quantiteProduit !== 'number') {
      values.quantiteProduit = Number(values.quantiteProduit);
    }

    const entity = {
      ...produitEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...produitEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="magicventestockApp.produit.home.createOrEditLabel" data-cy="ProduitCreateUpdateHeading">
            Créer ou éditer un Produit
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="produit-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Libelle Produit"
                id="produit-libelleProduit"
                name="libelleProduit"
                data-cy="libelleProduit"
                type="text"
                validate={{
                  required: { value: true, message: 'Ce champ est obligatoire.' },
                  maxLength: { value: 200, message: 'Ce champ doit faire moins de 200 caractères.' },
                }}
              />
              <ValidatedField
                label="Prix Produit"
                id="produit-prixProduit"
                name="prixProduit"
                data-cy="prixProduit"
                type="text"
                validate={{
                  required: { value: true, message: 'Ce champ est obligatoire.' },
                  min: { value: 0, message: 'Ce champ doit être supérieur à 0.' },
                  max: { value: 99999999999999999999.99, message: 'Ce champ doit être inférieur à {99999999999999999999.99}.' },
                  validate: v => isNumber(v) || 'Ce champ doit être un nombre.',
                }}
              />
              <ValidatedField
                label="Quantite Produit"
                id="produit-quantiteProduit"
                name="quantiteProduit"
                data-cy="quantiteProduit"
                type="text"
                validate={{
                  required: { value: true, message: 'Ce champ est obligatoire.' },
                  min: { value: 0, message: 'Ce champ doit être supérieur à 0.' },
                  validate: v => isNumber(v) || 'Ce champ doit être un nombre.',
                }}
              />
              <ValidatedBlobField
                label="Image Produit"
                id="produit-imageProduit"
                name="imageProduit"
                data-cy="imageProduit"
                isImage
                accept="image/*"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/produit" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Retour</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Sauvegarder
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ProduitUpdate;
