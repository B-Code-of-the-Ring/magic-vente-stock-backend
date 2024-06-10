export interface IProduit {
  id?: number;
  libelleProduit?: string;
  prixProduit?: number;
  quantiteProduit?: number;
  imageProduit?: string | null;
}

export const defaultValue: Readonly<IProduit> = {};
