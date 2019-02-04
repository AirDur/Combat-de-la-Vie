package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import zone42.Case;
import zone42.Grille;
import zone42.Math_methods;
import zone42.Zone42;

public class Tigre extends Carnivore{

	public Tigre(Sexe s, int v, Case c) {
		super(s, v, c);
		// TODO Auto-generated constructor stub
	}
	
	public Consommateur recherche_reproducteur() {
		ArrayList<Carnivore> l = Zone42.get_listeCarnivore();
		int distance=999;
		Carnivore c=null, c_test;
		
		if(l!=null) {
			Iterator<Carnivore> it = l.iterator();
			while(it.hasNext()) {
				c_test = (Carnivore) it.next();
				if(c_test instanceof consommateur.Tigre ) {
					
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
	public Tigre se_reproduire(Consommateur c) {
		System.out.println("le sex");
		String nom_classe_this = this.getClass().getName().toString();
    	String nom_classe_c = c.getClass().getName().toString();
    	if(nom_classe_this.equals(nom_classe_c) && this.getSexe() != c.getSexe()) {
    		Case new_emplacement = Grille.getinstance().getCaseProche(this.emplacement, c.emplacement);
    		Sexe es = Sexe.femelle;
    		if(Math_methods.randomWithRange(0,1) == 1) 
    			es = Sexe.male;
    		Tigre l = new Tigre(es, 7, new_emplacement);
    		Zone42.getInstance().ajout_carnivore(l);
    		return l;
    	} else {
    		return null;
    	}
	}
}
