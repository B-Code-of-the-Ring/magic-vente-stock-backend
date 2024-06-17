export interface IProduit {
  id?: number;
  libelleProduit?: string;
  prixProduit?: number;
  quantiteProduit?: number;
  imageProduitContentType?: string | null;
  imageProduit?: string | null;
}

export const defaultValue: Readonly<IProduit> = {};
