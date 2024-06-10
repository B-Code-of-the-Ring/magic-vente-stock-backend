import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ProduitJour from './produit-jour';
import ProduitJourDetail from './produit-jour-detail';
import ProduitJourUpdate from './produit-jour-update';
import ProduitJourDeleteDialog from './produit-jour-delete-dialog';

const ProduitJourRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ProduitJour />} />
    <Route path="new" element={<ProduitJourUpdate />} />
    <Route path=":id">
      <Route index element={<ProduitJourDetail />} />
      <Route path="edit" element={<ProduitJourUpdate />} />
      <Route path="delete" element={<ProduitJourDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ProduitJourRoutes;
