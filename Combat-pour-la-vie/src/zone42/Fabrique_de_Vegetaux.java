package zone42;

import java.util.ArrayList;

import aliment.*;

/**
 * Implémentation du design pattern de Fabrique. Cette classe est une fabrique qui instancie des vegetaux du type passé 
 * en paramètre du constructeur.
 * 
 * @author Romain Duret
 * @see Vegetaux
 */
public class Fabrique_de_Vegetaux {

	/**
	 * Durée de vie en cycle de la fabrique
	 */
	private Integer duree_de_vie;
	
	/**
	 * Age de la fabrique
	 */
	private Integer age_fabrique;
	
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
	
	public Fabrique_de_Vegetaux(int vie, int quantite, int cycle, TypeVegetaux tv, Case c) {
		duree_de_vie = vie;
		age_fabrique = 0;
		
		type = tv;
		
		cycle_de_production = cycle;
		cycle_courant = 0;
		quantite_de_production = quantite;
		
		setEmplacement(c);
		
		if(vie > 0) 
			etat = EtatFabrique.enmarche;
		else
			etat = EtatFabrique.enpanne;
	}
	
	/**
	 * Vérifie si le cycle actuel correspond bien à un cycle de production. 
	 * Vérifie si la fabrique tombe en panne lors de production de vegetaux
	 * Si ça correspond, elle renvoit un arraylist de vegetaux produit selon la quantite maximale de production de la machine.
	 *  sinon, elle renvoi null en incrémentant le compteur
	 * @return un arraylist de vegetaux ou null
	 */
	public ArrayList<Vegetaux> utilisation() {
		age_fabrique++;
		if(etat == EtatFabrique.enmarche) {
			if(cycle_courant == cycle_de_production) {
				ArrayList<Vegetaux> alv = new ArrayList<Vegetaux>();
				for(int i = 0; i < quantite_de_production; i++) {
					Vegetaux veg = creerVegetaux();
					if(veg!=null) alv.add(veg);
				}
				cycle_courant = 0;
				devientEnPanne();
				return alv;
			} else {
				cycle_courant++;
				return null;
			}
		}
		else return null;
	}
	
	/**
	 * Vérifie si ça devientEnPanne.
	 * (voir https://cdn.discordapp.com/attachments/516909121153269762/530749121594916864/unknown.png )
	 */
	private void devientEnPanne() {
		if(age_fabrique > (0.8 * this.duree_de_vie)) {
			//TODO laisser ça à Thomas
		}
		if(age_fabrique > (1.2 * this.duree_de_vie)) {
			etat = EtatFabrique.enpanne;
		}
	}

	/**
	 * Créer un vegetal et le retourne. S'il n'y a pas d'emplacement Libre, retourne null
	 * @return vegetal ou null
	 */
	private Vegetaux creerVegetaux() {
				Case c = Grille.getinstance().getCaseAutour(emplacement, 3).get(0);
		if(c != null) {
			System.out.println("On donne la case "+c.getVal_x()+","+c.getVal_y());
			Vegetaux vegetal = null;
	        switch(this.type)
	        {
	            case foin: 		vegetal = new Foin(c); break;
	            case herbe: 	vegetal = new Herbe(c); break;
	            case plante:	vegetal = new Plante(c); break;
	            case pomme: 	vegetal = new Pomme(c); break;
	        }
	        return vegetal;
		} else
			return null;
		
	}

	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}
}
