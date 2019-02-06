package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import aliment.*;
import zone42.Case;
import zone42.EtatCase;
import zone42.Grille;
import zone42.Math_methods;
import zone42.Zone42;

public abstract class Herbivore extends Consommateur {
	
	private EtatCase type;
	
	public Herbivore(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		super(s, v, c, cmc, a, ddv, fc);
		type=EtatCase.herbivore;
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
    	if(check_faim()) {
    		Aliment ar = recherche_aliment();
    		if(ar != null) {
    			manger(ar);
    		} else {
    			//se_deplacer(ac);
    		}
    		//se déplace
    		return null;
    	} else {
    		Consommateur r = recherche_reproducteur();
    		if(r != null) {
    			return se_reproduire(r);
    		} else {
    			deplacement_aleatoire(1);
    			return null;
    		}
    	}
    }
	
	protected Case deplacement_aleatoire(int r) {
    	Grille g = Grille.getinstance();
		ArrayList<Case> al = g.getCaseAutour(emplacement, r);
		if(al.size() > 0) {
			int i = Math_methods.randomWithRange(0, al.size()-1);
			Case c = al.get(i);
			g.setEtat(EtatCase.libre, emplacement);
			emplacement = c;
			g.setEtat(EtatCase.consommateur, emplacement);
			return c;
		} else
			return emplacement;
		
	}

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);

}
