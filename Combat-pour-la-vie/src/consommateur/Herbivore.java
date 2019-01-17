package consommateur;

import aliment.Aliment;

public abstract class Herbivore extends Consommateur {
	
	public Herbivore(Sexe s, int v) {
		super(s, v);
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
    			deplacement_aleatoire();
    			return null;
    		}
    	}
    }

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);

}
