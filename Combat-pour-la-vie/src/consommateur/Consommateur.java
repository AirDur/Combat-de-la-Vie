package consommateur;

import aliment.*;
import zone42.*;

public abstract class Consommateur implements Deplacable {
	
	/**
	 * Compteur pour la faim. Va entre 0 et 100.
	 */
	private Integer compteur_faim;
	/**
	 * Déclare l'état de la faim de l'individu (dépend du compteur_faim)
	 */
	private EtatFaim etat_faim;
	/**
	 * Vie de l'animal.
	 */
	private Integer vie;
	/**
	 * Force de combat pour les affrontements
	 */
	private Integer force_combat;
	
	/**
	 * Sexe du Consommateur
	 */
	private Sexe sexe;
	
	/**
	 * Capacité de déplacement qu'un consommateur peut effectuer durant un tour.
	 */
	private Integer capacite_maximale_de_deplacement;
	
	/**
	 * Age du consommateur (en durée de cycle)
	 */
	private Integer age;
	
	/**
	 * Durée de vie du consommateur (+/- 20%)
	 */
	private Integer duree_de_vie;
	/**
	 * Emplacement du consommateur sur la grille.
	 */
	private Case emplacement;
	
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
		emplacement = new Case(8,9);
	}

    public abstract int manger(Aliment a);
    
    /**
     * Déplace l'animal vers la case indiqué
     * @param c case
     * @param g grille
     * @return 1 si ça marche, 0 si la case est occupée, trop loin ou l'animal mort.
     */
    public int se_deplacer(Case c, Grille g) {
    	if(vivant && c.getEc() == EtatCase.libre && capacite_maximale_de_deplacement < c.distance(emplacement)) {
    		g.setEtat(EtatCase.libre, emplacement);
    		g.setEtat(EtatCase.animal, c);
    		emplacement = c;
    		return 1;
    	} else
    		return 0;
    }
    
    public int meurt() {
    	return 0;
    }
    
    public int se_defendre(Consommateur c) {
    	return 0;
    }
    public abstract Aliment recherche_aliment(Grille g);

    public abstract Consommateur se_reproduire(Consommateur c);
    
    public abstract Consommateur recherche_reproducteur(Grille g);

}
