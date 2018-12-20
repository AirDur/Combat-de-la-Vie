package consommateur;

import aliment.Aliment;
import aliment.Vegetaux;

public abstract class Consommateur {
	
	private Integer quantite_consommee_par_tour;
	private Integer vie;
	private Integer force_combat;
	
	/**
	 * Sexe du Consommateur
	 */
	private Sexe sexe;
	private Integer energie_deplacement;
	
	/**
	 * Permet de vérifier si le Consommateur est mort
	 */
	private boolean vivant;
	
	/**
	 * Constructeur d'un Consommateur
	 * Set l'attribut vivant à "true".
	 * 
	 * @param s sexe du consommateur, pour gérer la reproduction
	 * @param v vie du consommateur
	 */
	public Consommateur(Sexe s, int v) {
		vivant = true;
		sexe = s;
		vie = v;
	}
	
	public void chercher_la_nourriture(){}
	
	public abstract void manger(Aliment a);
	
	public void se_deplacer() {}
	
	public void meurt() {
		if(vie < 0) {
			vivant = false;
		}
	}

}
