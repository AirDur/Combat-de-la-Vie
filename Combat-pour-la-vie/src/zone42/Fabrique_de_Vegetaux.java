package zone42;

import aliment.*;

/**
 * Implémentation du design pattern de Fabrique. Cette classe est une fabrique qui instancie des vegetaux du type passé 
 * en paramètre du constructeur.
 * 
 * @author Romain Duret
 *
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
	
	public Fabrique_de_Vegetaux(int i, TypeVegetaux tv) {
		duree_de_vie = i;
		type = tv;
		if(i > 0) 
			etat = EtatFabrique.enmarche;
		else
			etat = EtatFabrique.enpanne;
	}
	
	/**
	 * Créer un vegetal. Si la fabrique est en panne, elle retourne null.
	 * @return vegetal ou null
	 */
	public Vegetaux creerVegetaux() {
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
