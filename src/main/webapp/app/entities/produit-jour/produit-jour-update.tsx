import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduit } from 'app/shared/model/produit.model';
import { getEntities as getProduits } from 'app/entities/produit/produit.reducer';
import { IProduitJour } from 'app/shared/model/produit-jour.model';
import { getEntity, updateEntity, createEntity, reset } from './produit-jour.reducer';

export const ProduitJourUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const produits = useAppSelector(state => state.produit.entities);
  const produitJourEntity = useAppSelector(state => state.produitJour.entity);
  const loading = useAppSelector(state => state.produitJour.loading);
  const updating = useAppSelector(state => state.produitJour.updating);
  const updateSuccess = useAppSelector(state => state.produitJour.updateSuccess);

  const handleClose = () => {
    navigate('/produit-jour' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProduits({}));
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

    const entity = {
      ...produitJourEntity,
      ...values,
      produit: produits.find(it => it.id.toString() === values.produit?.toString()),
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
          ...produitJourEntity,
          produit: produitJourEntity?.produit?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="magicventestockApp.produitJour.home.createOrEditLabel" data-cy="ProduitJourCreateUpdateHeading">
            Créer ou éditer un Produit Jour
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="produit-jour-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField label="Date Debut" id="produit-jour-dateDebut" name="dateDebut" data-cy="dateDebut" type="date" />
              <ValidatedField label="Date Fin" id="produit-jour-dateFin" name="dateFin" data-cy="dateFin" type="date" />
              <ValidatedField id="produit-jour-produit" name="produit" data-cy="produit" label="Produit" type="select">
                <option value="" key="0" />
                {produits
                  ? produits.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/produit-jour" replace color="info">
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

export default ProduitJourUpdate;
