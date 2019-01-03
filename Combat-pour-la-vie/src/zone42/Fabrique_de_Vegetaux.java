package zone42;

import aliment.*;

public abstract class Fabrique_de_Vegetaux {

	/**
	 * Durée de vie en cycle de la fabrique
	 */
	private Integer duree_de_vie;
	/**
	 * Etat de la machine
	 */
	private EtatFabrique etat;
	
	public Fabrique_de_Vegetaux(int i) {
		duree_de_vie = i;
		if(i > 0) 
			etat = EtatFabrique.enmarche;
		else
			etat = EtatFabrique.enpanne;
	}
	
	public Vegetaux creerVegetaux(TypeVegetaux type) {
		Vegetaux vegetal = null;
        switch(type)
        {
            case foin: vegetal = new Foin(); break;
            case herbe: vegetal = new Herbe(); break;
            case plante: vegetal = new Plante(); break;
            case pomme: vegetal = new Pomme(); break;
        }
        return vegetal;
	}
}
