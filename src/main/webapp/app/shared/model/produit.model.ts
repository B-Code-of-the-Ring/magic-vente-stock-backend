export interface IProduit {
  id?: number;
  libelleProduit?: string;
  prixProduit?: number | null;
  quantiteProduit?: number | null;
  imageProduit?: string | null;
}

export const defaultValue: Readonly<IProduit> = {};
