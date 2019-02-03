package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import aliment.*;
import zone42.Case;
import zone42.Zone42;

public abstract class Herbivore extends Consommateur {
	
	public Herbivore(Sexe s, int v, Case c) {
		super(s, v, c);
		// TODO Auto-generated constructor stub
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
		
		Iterator<Aliment> it = l.iterator();
		while(it.hasNext()) {
			a_test = (Aliment) it.next();
			if(a_test.getEmplacement().distance(this.getEmplacement()) <distance) {
				a=a_test;
			}
		}
		
		return a;//
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

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);

}
