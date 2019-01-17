package consommateur;

import aliment.*;
import zone42.*;

public abstract class Consommateur {
	
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

    /**
     * Déplace l'animal vers la case indiqué
     * @param c case
     * @param g grille
     * @return 1 si ça marche, 0 si la case est occupée, trop loin ou le Consommateur mort.
     */
    public int se_deplacer(Case c) {
    	Grille g = Grille.getinstance(0, 0);
    	if(vivant && c.getEc() == EtatCase.libre && capacite_maximale_de_deplacement < c.distance(emplacement)) {
    		g.setEtat(EtatCase.libre, emplacement);
    		g.setEtat(EtatCase.animal, c);
    		emplacement = c;
    		return 1;
    	} else
    		return 0;
    }
    
    /**
     * Si le Consommateur est vivant et ses PV en dessous de zero, alors il devient "mort" (vivant = false)
     * @return 1 s'il meurt, 0 s'il reste vivant
     */
    public int meurt() {
    	if(vie <= 0 && vivant) {
    		vivant = false;
    		return 1;
    	}
    	else
    		return 0;
    }
    
    /**
     * Se défend d'une attaque d'un Carnivore.
     * @param c
     * @return
     */
    public int se_defendre(Consommateur c) {
    	return 0;
    }

	public boolean check_faim() {
    	if(etat_faim != EtatFaim.satisfait)
    		return false;
    	else 
    		return true;
    }
	
	protected Case deplacement_aleatoire() {
		return null;
	}
	
    public abstract Aliment recherche_aliment();
    
    public abstract Consommateur faire_passer_le_temps();
    
    public abstract Consommateur se_reproduire(Consommateur c);
    
    public Consommateur recherche_reproducteur() {
    	//retourne autour si un consommateur est de même classe et de sexe opposé.
    	Sexe sexe_partenaire;
    	Grille g = Grille.getinstance();
    	if(sexe == Sexe.femelle) {
    		sexe_partenaire = Sexe.male;
    	} else {
    		sexe_partenaire = Sexe.femelle;
    	}
    	return null;
    }
    
    public abstract int manger(Aliment a);
    
	
	protected Sexe getSexe() {
		return sexe;
	}

	protected void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

}
