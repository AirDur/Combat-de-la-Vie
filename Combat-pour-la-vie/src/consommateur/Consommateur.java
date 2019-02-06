package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	private int compteur_reproduction=15;
	
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
	 * Durée de vie du consommateur
	 */
	private Integer duree_de_vie;
	/**
	 * Emplacement du consommateur sur la grille.
	 */
	protected Case emplacement;
	
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
	 * @param c emplacement du consommateur
	 * @param cmc capacité maxiaml de déplacement
	 * @param a age
	 * @param ddv duree de vie (age maximale)
	 * @param fc force combat
	 */
	public Consommateur(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		vivant = true;
		sexe = s;
		vie = v;
		emplacement = c;
		compteur_faim = 44;
		etat_faim = EtatFaim.satisfait;
		Grille g = Grille.getinstance();
		//g.setEtat(EtatCase.consommateur, emplacement);
		capacite_maximale_de_deplacement = cmc;
		age = a;
		duree_de_vie = ddv;
		force_combat = fc;
	}

    /**
     * Déplace l'animal vers la case indiqué
     * @param c case
     * @param g grille
     * @return 1 si ça marche, 0 si la case est occupée, trop loin ou le Consommateur mort.
     */
    public int se_deplacer(Case c) {
    	if(c!=null) {
	    	Grille g = Grille.getinstance(0, 0);
	    	if(vivant && (c.getEc() == EtatCase.libre) ) {
	    		g.setEtat(EtatCase.libre, emplacement);
	    		g.setEtat(EtatCase.carnivore, c);
	    		emplacement = c;
	    		return 1;
	    	} else
	    		return 0;
    	}else return 0;
    }
    
    public int deplacement(ArrayList<Case> l, int nb_case) {
    	
    	Iterator<Case> it = l.iterator();
    	int i=0;
    	Case c=null;
    	
    	while(it.hasNext() && i<=nb_case) {
    		c = (Case) it.next();
    		i++;
    	}
    	return se_deplacer(c);
    }
    
    /**
     * Si le Consommateur est vivant et ses PV en dessous de zero, alors il devient "mort" (vivant = false)
     * @return 1 s'il meurt, 0 s'il reste vivant
     */
    public boolean meurt() {
    	if(vie <= 0 && vivant || age > duree_de_vie) {
    		vivant = false;
    		return true;
    	}
    	else
    		return false;
    }
    
    /**
     * Se défend d'une attaque d'un Carnivore.
     * Le défenseur perd (force_attaque - force_defenseur/2) de vie.
     * S'il est encore vivant, il contre attaque et inflige forge_defenseur/2 de vie.
     * @param c
     * @return true si l'attaque provoque un mort, false sinon.
     */
    public boolean se_defendre(Consommateur c) {
    	int i = c.getForce_combat() - (this.getForce_combat()/2);
    	if(i > 0) {
    		this.vie = vie - i;
    	}
    	if(!meurt()) {
    		c.vie = c.vie -(this.getForce_combat()/2);
    		return false;
    	} else {
    		return true;
    	}
    }

    /**
     * Vérifie si l'animal a faim.
     * @return true s'il a faim ou famine, false sinon.
     */
	protected boolean check_faim() {
		this.setCompteur_faim(compteur_faim-1);
		System.out.println("Compteur faim = "+this.getCompteur_faim());
		change_etat_faim();
    	if(etat_faim == EtatFaim.satisfait) {
    		return false;
    	}else {
    		System.out.println("c'est la faim");
    		return true;
    	}
    }
	
	
	/**
	 * Déplace aléatoirement l'animal dans une zone de rayon r
	 * @param r rayon maximal de déplacement
	 * @return Case de déplacement
	 */
	protected abstract Case deplacement_aleatoire(int r) ;
	
	
	/**
	 * Change l'état de faim de l'animal en fonction de son compteur_faim.
	 */
	protected void change_etat_faim() {
		if(compteur_faim >= Zone42.getSeuil_faim()) {
			etat_faim = EtatFaim.satisfait;
		} else if(compteur_faim < Zone42.getSeuil_faim() && compteur_faim >= Zone42.getSeuil_famine() ) {
			etat_faim = EtatFaim.faim;
		} else if(compteur_faim < Zone42.getSeuil_famine()) {
			etat_faim = EtatFaim.famine;
		}
	}
	
	/**
	 * recherche un reproducteur proche.
	 * @return Consommateur
	 */
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
	
    public abstract Aliment recherche_aliment();
    
    public abstract Consommateur faire_passer_le_temps();
    
    public abstract Consommateur se_reproduire(Consommateur c);
    
    public abstract boolean manger(Aliment a);
    
	public String toString() {
		return "Consommateur de type " + this.getClass().getName().toString() + " : \n"
				+ "Case : " + this.emplacement.toString();
	}
    
	protected Sexe getSexe() {
		return sexe;
	}

	protected void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	protected Integer getForce_combat() {
		return force_combat;
	}

	protected void setForce_combat(Integer force_combat) {
		this.force_combat = force_combat;
	}

	protected Integer getCompteur_faim() {
		return compteur_faim;
	}

	protected void setCompteur_faim(Integer compteur_faim) {
		if(0 <= compteur_faim && compteur_faim < 100)
			this.compteur_faim = compteur_faim;
	}

	public Case getEmplacement() {
		return emplacement;
	}

	protected Integer getCapacite_maximale_de_deplacement() {
		return capacite_maximale_de_deplacement;
	}

	protected void setCapacite_maximale_de_deplacement(Integer capacite_maximale_de_deplacement) {
		this.capacite_maximale_de_deplacement = capacite_maximale_de_deplacement;
	}
	
	protected Integer getDuree_De_Vie() {
		return duree_de_vie;
	}
	
	protected Integer getVie() {
		return vie;
	}
	
	protected int get_comprep() {
		return compteur_reproduction;
	}
	
	protected void set_comprep(int val) {
		compteur_reproduction=val;
	}
}
