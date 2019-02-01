package consommateur;

import aliment.*;
import zone42.Case;
import zone42.Grille;

public abstract class Carnivore extends Consommateur {
	
	public Carnivore(Sexe s, int v, Case c) {
		super(s, v, c);
	}
	
	@Override
	public boolean manger(Aliment a) {
		if(a.getClass() == Cadavre.class) {
			return false;
		}
		int n = a.getPropriete_nutritive();
		return true;
	}

	@Override
	public Aliment recherche_aliment() {
		// TODO Auto-generated method stub
		return null;
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
    			Consommateur ac = recherche_proie();
    			
    			if(ac !=  null) {
    				if(attaquer(ac)) {
    					ar = recherche_aliment();
    					manger(ar);
    				}
    			} else {
    				//se_deplacer(ac);
    			}
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
	
	private boolean attaquer(Consommateur c) {
		return c.se_defendre(this);
	}

	private Consommateur recherche_proie() {
		Grille g = Grille.getinstance();
		//???
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);
}
