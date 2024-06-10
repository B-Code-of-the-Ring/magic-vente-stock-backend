import dayjs from 'dayjs';
import { IProduit } from 'app/shared/model/produit.model';

export interface IProduitJour {
  id?: number;
  dateDebut?: dayjs.Dayjs | null;
  dateFin?: dayjs.Dayjs | null;
  produit?: IProduit | null;
}

export const defaultValue: Readonly<IProduitJour> = {};
