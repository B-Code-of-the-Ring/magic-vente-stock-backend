import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './produit-jour.reducer';

export const ProduitJourDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const produitJourEntity = useAppSelector(state => state.produitJour.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="produitJourDetailsHeading">Produit Jour</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{produitJourEntity.id}</dd>
          <dt>
            <span id="dateDebut">Date Debut</span>
          </dt>
          <dd>
            {produitJourEntity.dateDebut ? (
              <TextFormat value={produitJourEntity.dateDebut} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="dateFin">Date Fin</span>
          </dt>
          <dd>
            {produitJourEntity.dateFin ? <TextFormat value={produitJourEntity.dateFin} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>Produit</dt>
          <dd>{produitJourEntity.produit ? produitJourEntity.produit.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/produit-jour" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Retour</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/produit-jour/${produitJourEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProduitJourDetail;
