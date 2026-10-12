package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis desolee " + nomAcheteur
					+ " mais il faut etre un habitant de notre village pour commercer ici.");
			return;
		}

		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] vendeurs = controlAcheterProduit.trouverVendeursProduit(produit);

		if (vendeurs == null || vendeurs.length == 0) {
			System.out.println("Desole, personne ne vend ce produit au marche.");
			return;
		}

		StringBuilder question = new StringBuilder();
		question.append("Chez quel commercant voulez-vous acheter des " + produit + " ?\n");
		for (int i = 0; i < vendeurs.length; i++) {
			question.append((i + 1) + " - " + vendeurs[i] + "\n");
		}

		int choix;
		do {
			choix = Clavier.entrerEntier(question.toString());
			if (choix < 1 || choix > vendeurs.length) {
				System.out.println("Vous devez choisir un chiffre entre 1 et " + vendeurs.length + " !");
			}
		} while (choix < 1 || choix > vendeurs.length);

		String nomVendeur = vendeurs[choix - 1];

		if (!controlAcheterProduit.verifierIdentite(nomVendeur)) {
			System.out.println("Je suis desolee " + nomVendeur
					+ " mais il faut etre un habitant de notre village pour commercer ici.");
			return;
		}

		System.out.println(nomAcheteur + " se deplace jusqu'a l'etal du vendeur " + nomVendeur);
		System.out.println("Bonjour " + nomAcheteur);
		int quantiteVoulue = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");

		int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantiteVoulue);

		if (quantiteAchetee == 0) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue + " " + produit
					+ ", malheureusement il n'y en a plus !");
		} else if (quantiteAchetee < quantiteVoulue) {
			System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue + " " + produit
					+ ", malheureusement " + nomVendeur + " n'en a plus que " + quantiteAchetee
					+ ". " + nomAcheteur + " achete tout le stock de " + nomVendeur + ".");
		} else {
			System.out.println(nomAcheteur + " achete " + quantiteAchetee + " " + produit
					+ " a " + nomVendeur + ".");
		}
	}
}