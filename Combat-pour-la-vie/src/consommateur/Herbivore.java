package consommateur;

import aliment.Aliment;
import zone42.Case;

public abstract class Herbivore extends Consommateur {
	
	public Herbivore(Sexe s, int v, Case c) {
		super(s, v, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int manger(Aliment a) {
		if(a.getClass().toString() == "Vegetaux") 
			System.out.println("todo"); //TODO
		else 
			System.out.println("Error : can't eat");
		return 0;
		
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
