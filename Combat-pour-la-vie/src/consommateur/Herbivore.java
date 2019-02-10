package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import aliment.*;
import zone42.Astar;
import zone42.Case;
import zone42.EtatCase;
import zone42.Grille;
import zone42.Math_methods;
import zone42.Zone42;

public abstract class Herbivore extends Consommateur {
	
	private EtatCase type;
	
	public Herbivore(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		super(s, v, c, cmc, a, ddv, fc);
		type = EtatCase.herbivore;
	}
	
	
	@Override
	public boolean manger(Aliment a) {
		if(a instanceof aliment.Vegetaux) {
			int n = a.getPropriete_nutritive();
			setCompteur_faim(getCompteur_faim() + n);
			change_etat_faim();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Aliment recherche_aliment() {
		ArrayList<Aliment> l = Zone42.get_listeAliment();
		int distance=999;
		Aliment a=null, a_test;
		
		if(l!=null) {
			Iterator<Aliment> it = l.iterator();
			while(it.hasNext()) {
				a_test = (Aliment) it.next();
				if( nourriture_vegan(a_test) &&  
						a_test.getEmplacement().distance(this.getEmplacement()) <distance) {
					a=a_test;
					distance = a_test.getEmplacement().distance(this.getEmplacement()) ;
				}
			}
		}
		
		return a;
	}

	private boolean nourriture_vegan(Aliment a_test) {
		return ((a_test instanceof aliment.Vegetaux || a_test instanceof aliment.Plante || a_test instanceof aliment.Pomme 
				|| a_test instanceof aliment.Foin || a_test instanceof aliment.Herbe ));
	}
	
	@Override
	public Consommateur recherche_reproducteur() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Consommateur faire_passer_le_temps() {
		
		Consommateur ret=null;
		
		if(get_est_attaque() ) {
			non_attaque();
			return null;
		}
		else if(check_faim()) {
    		Aliment ar = recherche_aliment();
    		if(ar != null) {
    			if( this.getEmplacement().proximite(ar.getEmplacement()) ) {
    				manger(ar);
    				Zone42.supprime_aliment(ar);
    			}
    			else {
        			Astar as = new Astar(Grille.getinstance(),this.getEmplacement(),ar.getEmplacement());
        			ArrayList<Case> chem_herb = as.get_chemin();
        			if(chem_herb!=null) {
        				this.deplacement(chem_herb, this.getCapacite_maximale_de_deplacement());
        			}else deplacement_aleatoire(1);
        			
        		}
    		}else deplacement_aleatoire(1);
    		//se déplace
    		return null;
    	} else if(this.get_comprep()==0){
    		
    		Consommateur r = recherche_reproducteur();
    		if(r != null) {
    			
    			if(this.getEmplacement().proximite(r.getEmplacement())) {
    				ret= se_reproduire(r);
    			}
    			else {
    				Astar as = new Astar(Grille.getinstance(),this.getEmplacement(),r.getEmplacement());
    				ArrayList<Case> chem_herb = as.get_chemin();
    				if(chem_herb!=null)
        				this.deplacement(chem_herb, this.getCapacite_maximale_de_deplacement());
    			}
    		}else {
    			deplacement_aleatoire(1);
    		}
    	} else {
    		this.set_comprep(get_comprep()-1);
			deplacement_aleatoire(1);
    	}
    	this.augmente_age();
    	if(meurt()) {
			Zone42.ajout_herb_mort((Herbivore) this );
    	}
    	return ret;
    }
	
	public int se_deplacer(Case c) {
    	if(c!=null) {
	    	Grille g = Grille.getinstance(0, 0);
	    	if( this.est_vivant() && (c.getEc() == EtatCase.libre) ) {
	    		g.setEtat(EtatCase.libre, emplacement);
	    		g.setEtat(EtatCase.herbivore, c);
	    		emplacement = c;
	    		return 1;
	    	} else
	    		return 0;
    	}else return 0;
    }
	
	protected Case deplacement_aleatoire(int r) {
    	Grille g = Grille.getinstance();
		ArrayList<Case> al = g.getCaseAutour(emplacement, r);
		if(al.size() > 0) {
			int i = Math_methods.randomWithRange(0, al.size()-1);
			Case c = al.get(i);
			g.setEtat(EtatCase.libre, emplacement);
			emplacement = c;
			g.setEtat(EtatCase.herbivore, emplacement);
			return c;
		} else
			return emplacement;
		
	}

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);

}
