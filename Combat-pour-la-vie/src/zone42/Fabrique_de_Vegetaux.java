package zone42;

import aliment.*;

/**
 * Implémentation du design pattern de Fabrique. Cette classe est une fabrique qui instancie des vegetaux du type passé 
 * en paramètre du constructeur.
 * 
 * @author Romain Duret
 * @see Vegetaux
 */
public abstract class Fabrique_de_Vegetaux {

	/**
	 * Durée de vie en cycle de la fabrique
	 */
	private Integer duree_de_vie;
	
	/**
	 * Etat de la machine
	 */
	private EtatFabrique etat;
	
	/**
	 * Type de vegetaux que produit la fabrique
	 */
	private TypeVegetaux type;
	
	/**
	 * Emplacement du consommateur sur la grille.
	 */
	private Case emplacement;
	
	/**
	 * Quantité produite par cycle de production (entre 1 et 4)
	 */
	private Integer quantite_de_production;
	
	/** 
	 * production tout les X cycles
	 */
	private Integer cycle_de_production;
	
	/**
	 * Compteur pour connaitre quand la machine doit produire.
	 */
	private Integer cycle_courant;
	
	public Fabrique_de_Vegetaux(int vie, int quantite, int cycle, TypeVegetaux tv) {
		duree_de_vie = vie;
		type = tv;
		cycle_de_production = cycle;
		cycle_courant = 0;
		quantite_de_production = 0;
		
		if(vie > 0) 
			etat = EtatFabrique.enmarche;
		else
			etat = EtatFabrique.enpanne;
	}
	
	/**
	 * Vérifie si le cycle actuel correspond bien à un cycle de production. 
	 * Si ça correspond, elle renvoit un vegetal produit, sinon, elle renvoi null en incrémentant le compteur
	 * @return vegetal ou null
	 */
	public Vegetaux utilisation() {
		if(cycle_courant == cycle_de_production) {
			cycle_courant = 0;
			return creerVegetaux();
		} else {
			cycle_courant++;
			return null;
		}
	}
	
	/**
	 * Créer un vegetal. 
	 * Si la fabrique est en panne, elle retourne null.
	 * @return vegetal ou null
	 */
	private Vegetaux creerVegetaux() {
		if(etat == EtatFabrique.enmarche) {
			Vegetaux vegetal = null;
	        switch(this.type)
	        {
	            case foin: vegetal = new Foin(); break;
	            case herbe: vegetal = new Herbe(); break;
	            case plante: vegetal = new Plante(); break;
	            case pomme: vegetal = new Pomme(); break;
	        }
	        return vegetal;
		}
		else return null;
	}
}
