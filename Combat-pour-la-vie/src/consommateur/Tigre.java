package consommateur;

import zone42.Case;
import zone42.Grille;
import zone42.Math_methods;

public class Tigre extends Carnivore{

	public Tigre(Sexe s, int v, Case c) {
		super(s, v, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Tigre se_reproduire(Consommateur c) {
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Case new_emplacement = Grille.getinstance().getCaseProche(this.emplacement, c.emplacement);
    		Sexe es = Sexe.femelle;
    		if(Math_methods.randomWithRange(0,1) == 1) 
    			es = Sexe.male;
    		return new Tigre(es, 7, new_emplacement);
    	} else {
    		return null;
    	}
	}
}
