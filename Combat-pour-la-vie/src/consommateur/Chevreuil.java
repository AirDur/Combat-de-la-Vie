package consommateur;

import zone42.Case;
import zone42.Grille;
import zone42.Math_methods;

public class Chevreuil extends Cervide {

	public Chevreuil(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		super(s, v, c, cmc, a, ddv, fc);
	}
	
	@Override
	public Chevreuil se_reproduire(Consommateur c) {
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Case new_emplacement = Grille.getinstance().getCaseProche(this.emplacement, c.emplacement);
    		Sexe es = Sexe.femelle;
    		if(Math_methods.randomWithRange(0,1) == 1) 
    			es = Sexe.male;
    		return new Chevreuil(es, Math_methods.randomWithRange(this.getVie()-1, c.getVie()+1),
    				new_emplacement, 
    				Math.max(this.getCapacite_maximale_de_deplacement(), c.getCapacite_maximale_de_deplacement()), 0, 
    				Math_methods.randomWithRange(this.getDuree_De_Vie()-1, c.getDuree_De_Vie()+1), 
    				Math_methods.randomWithRange(this.getForce_combat()-1, c.getDuree_De_Vie()+1));
    	} else {
    		return null;
    	}
	}

}
