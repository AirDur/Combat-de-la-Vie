package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import zone42.Case;
import zone42.Grille;
import zone42.Math_methods;
import zone42.Zone42;

public class Cervide extends Herbivore {

	public Cervide(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		super(s, v, c, cmc, a, ddv, fc);
	}
	
	@Override
	public Consommateur recherche_reproducteur() {
		ArrayList<Herbivore> l = Zone42.get_listeHerbivore();
		int distance=999;
		Herbivore c=null, c_test;
		
		if(l!=null) {
			Iterator<Herbivore> it = l.iterator();
			while(it.hasNext()) {
				c_test = (Herbivore) it.next();
				if(c_test instanceof consommateur.Cervide ) {
					
					if( (this.getSexe()==Sexe.male && c_test.getSexe()==Sexe.femelle) ||
							(this.getSexe()==Sexe.femelle && c_test.getSexe()==Sexe.male)) {
						if(c_test.getEmplacement().distance(this.getEmplacement()) <distance) {
							c=c_test;
							distance = c_test.getEmplacement().distance(this.getEmplacement()) ;
						}
					}
				}
			}
		}
		return c;
	}

	@Override
	public Cervide se_reproduire(Consommateur c) {
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Case new_emplacement = Grille.getinstance().getCaseProche(this.emplacement);
    		if(new_emplacement!=null) {
	    		Sexe es = Sexe.femelle;
	    		if(Math_methods.randomWithRange(0,1) == 1) 
	    			es = Sexe.male;
	    		Cervide l = new Cervide(es, Math_methods.randomWithRange(this.getVie()-1, c.getVie()+1),
    				new_emplacement, 
    				Math.max(this.getCapacite_maximale_de_deplacement(), c.getCapacite_maximale_de_deplacement()), 0, 
    				Math_methods.randomWithRange(this.getDuree_De_Vie()-1, c.getDuree_De_Vie()+1), 
    				Math_methods.randomWithRange(this.getForce_combat()-1, c.getDuree_De_Vie()+1));
	    		//Zone42.getInstance().ajout_Herbivore(l);
	    		this.set_comprep(60);
	    		c.set_comprep(60);
	    		return l;
    		}
    		else {
    			return null;
    		}
    	} else {
    		return null;
    	}
	}

}
