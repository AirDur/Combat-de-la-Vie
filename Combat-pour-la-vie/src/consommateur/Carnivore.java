package consommateur;

import java.util.ArrayList;
import java.util.Iterator;

import aliment.*;
import zone42.Astar;
import zone42.Case;
import zone42.EtatCase;
import zone42.Grille;
import zone42.Math_methods;
import zone42.Zone42;

public abstract class Carnivore extends Consommateur {
	
	public Carnivore(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		super(s, v, c, cmc, a, ddv, fc);
	}
	
	@Override
	public boolean manger(Aliment a) {
		if(a instanceof aliment.Cadavre) {
			int n = a.getPropriete_nutritive();
			setCompteur_faim(getCompteur_faim() + n);
			change_etat_faim();
			Zone42.supprime_aliment(a);
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
		
		if(l!=null) {
			Iterator<Aliment> it = l.iterator();
			while(it.hasNext()) {
				a_test = (Aliment) it.next();
				if(a_test instanceof aliment.Cadavre && 
						a_test.getEmplacement().distance(this.getEmplacement()) <distance) {
					a=a_test;
					distance = a_test.getEmplacement().distance(this.getEmplacement()) ;
				}
			}
		}
		
		return a;
	}

	@Override
	public abstract Consommateur recherche_reproducteur();
	
	@Override
	public Consommateur faire_passer_le_temps() {
		Consommateur ret=null;
		
		//S il a faim
    	if(check_faim()) {
    		Aliment ar = recherche_aliment();
    		if(ar != null) {
    			System.out.println("Aliment trouve");
    			if( this.getEmplacement().proximite(ar.getEmplacement()) ) {
    				manger(ar);
    			}
    		//Pas aliment cherche un proie et se bat avec
    		} else {
    			Consommateur ac = recherche_proie();
    			
    			if(ac !=  null) {
    				System.out.println("proie trouvee");
    				
    				
    				if(this.getEmplacement().proximite(ac.getEmplacement())) {
    					System.out.println("a proximité");
    					if(attaquer(ac)) {
    						
    						if(ac instanceof Herbivore) {
    							System.out.println("Herbivore mort");
    							Zone42.supprime_herbivore((Herbivore) ac);
    						}
    					};
        			}
        			else {
        				Astar as = new Astar(Grille.getinstance(),this.getEmplacement(),ac.getEmplacement());
        				this.deplacement(as.get_chemin(), this.getCapacite_maximale_de_deplacement());
        			}
    					
    					
    				
    			} else {
    				deplacement_aleatoire(1);
    			}
    		}
    	//Sinon se reproduit
    	} else if(this.get_comprep()==0){
    		Consommateur r = recherche_reproducteur();
    		if(r != null) {
    			if(this.getEmplacement().proximite(r.getEmplacement())) {
    				ret= se_reproduire(r);
    			}
    			else {
    				Astar as = new Astar(Grille.getinstance(),this.getEmplacement(),r.getEmplacement());
    				this.deplacement(as.get_chemin(), this.getCapacite_maximale_de_deplacement());
    			}
    		}else {
    			deplacement_aleatoire(1);
    		}
    	} else {
    		this.set_comprep(get_comprep()-1);
			deplacement_aleatoire(1);
    	}
    	this.augmente_age();
    	if(meurt()) {
    		System.out.println("Carnivore mort");
			Zone42.ajout_carn_mort((Carnivore) this );
    	}
    	return ret;
    }
	
	public int se_deplacer(Case c) {
    	if(c!=null) {
	    	Grille g = Grille.getinstance(0, 0);
	    	if( this.est_vivant() && (c.getEc() == EtatCase.libre) ) {
	    		g.setEtat(EtatCase.libre, emplacement);
	    		g.setEtat(EtatCase.carnivore, c);
	    		emplacement = c;
	    		return 1;
	    	} else
	    		return 0;
    	}else return 0;
    }
	
	protected Case deplacement_aleatoire(int r) {
    	Grille g = Grille.getinstance();
		ArrayList<Case> al = g.getCaseAutour(emplacement, r);
		if(al.size() > 0) {
			int i = Math_methods.randomWithRange(0, al.size()-1);
			Case c = al.get(i);
			g.setEtat(EtatCase.libre, emplacement);
			emplacement = c;
			g.setEtat(EtatCase.carnivore, emplacement);
			return c;
		} else
			return emplacement;
		
	}
	
	
	private boolean attaquer(Consommateur h) {
		h.est_attaque();
		//h.prend_coup(this.getForce_combat());
		boolean ret = h.se_defendre(this);
		int v = h.getVie();
		System.out.println("Il reste +"+v+" point de vie a herbivore");
		System.out.println("Il reste +"+this.getVie()+" point de vie a Carnivore");
		return ret;
	}

	private Consommateur recherche_proie() {
		ArrayList<Herbivore> l = Zone42.get_listeHerbivore();
		int distance=999;
		Herbivore h=null, h_test;
		
		if(l!=null) {
			Iterator<Herbivore> it = l.iterator();
			while(it.hasNext()) {
				h_test = (Herbivore) it.next();
				if( h_test.getEmplacement().distance(this.getEmplacement()) <distance) {
					h=h_test;
					distance = h_test.getEmplacement().distance(this.getEmplacement()) ;
				}
			}
		}
		
		return h;
	}

	@Override
	public abstract Consommateur se_reproduire(Consommateur c);
}
