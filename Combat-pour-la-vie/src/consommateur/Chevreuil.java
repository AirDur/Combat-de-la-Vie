package consommateur;

import zone42.Math_methods;

public class Chevreuil extends Cervide {

	public Chevreuil(Sexe s, int v) {
		super(s, v);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Chevreuil se_reproduire(Consommateur c) {
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Sexe es = Sexe.femelle;
    		if(Math_methods.randomWithRange(0,1) == 1) es = Sexe.male;
    		return new Chevreuil(es, 7);
    	} else {
    		return null;
    	}
	}

}
