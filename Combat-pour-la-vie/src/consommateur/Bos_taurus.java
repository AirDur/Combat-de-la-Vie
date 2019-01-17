package consommateur;

import zone42.Grille;
import zone42.Math_methods;
import zone42.Case;

public class Bos_taurus extends Herbivore {

	public Bos_taurus(Sexe s, int v, Case c) {
		super(s, v, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bos_taurus se_reproduire(Consommateur c) {
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Case new_emplacement = Grille.getinstance().getCaseProche(this.emplacement, c.emplacement);
    		Sexe es = Sexe.femelle;
    		if(Math_methods.randomWithRange(0,1) == 1) 
    			es = Sexe.male;
    		return new Bos_taurus(es, 7, new_emplacement);
    	} else {
    		return null;
    	}
	}

}
