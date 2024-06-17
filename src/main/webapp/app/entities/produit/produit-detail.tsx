import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './produit.reducer';

export const ProduitDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const produitEntity = useAppSelector(state => state.produit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="produitDetailsHeading">Produit</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{produitEntity.id}</dd>
          <dt>
            <span id="libelleProduit">Libelle Produit</span>
          </dt>
          <dd>{produitEntity.libelleProduit}</dd>
          <dt>
            <span id="prixProduit">Prix Produit</span>
          </dt>
          <dd>{produitEntity.prixProduit}</dd>
          <dt>
            <span id="quantiteProduit">Quantite Produit</span>
          </dt>
          <dd>{produitEntity.quantiteProduit}</dd>
          <dt>
            <span id="imageProduit">Image Produit</span>
          </dt>
          <dd>
            {produitEntity.imageProduit ? (
              <div>
                {produitEntity.imageProduitContentType ? (
                  <a onClick={openFile(produitEntity.imageProduitContentType, produitEntity.imageProduit)}>
                    <img
                      src={`data:${produitEntity.imageProduitContentType};base64,${produitEntity.imageProduit}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {produitEntity.imageProduitContentType}, {byteSize(produitEntity.imageProduit)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/produit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Retour</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/produit/${produitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Editer</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProduitDetail;
