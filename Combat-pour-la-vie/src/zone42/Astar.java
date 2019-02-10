package zone42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Astar {
	
	public Case depart; 
	private Case arrive;
	private int nb_case = 10000;
	private int nb_elts = 0;
	private Grille grille;
	private Case [] tab= new Case[nb_case];
	private MonCompare moncomp = new MonCompare();
	public ArrayList<Case> come_from = new ArrayList<Case>();
	public ArrayList<Case> case_listee = new ArrayList<Case>();
	public ArrayList<Noeud> liste_noeud = new ArrayList<Noeud>();
	ArrayList<Case> chemin = new ArrayList<Case>();
	
	
	public Astar(Grille g, Case dep, Case arr) {
		depart=arr;
		arrive=dep;
		grille=g;
		for(int i=0 ; i<nb_case ; i++) {
			Case lacase = new Case(0,0);
			lacase.setValH(999);
			tab[i]=lacase;
		}
	}
	
	public void add(Case c) {
		tab[nb_elts]=c;
		nb_elts++;
		
		Arrays.sort(tab,moncomp);
	}
	
	public Case remove() {
		Case ret = null;
		if(nb_elts>0) {
			ret = tab[0];
			tab[0]= case_init();
			nb_elts--;
			Arrays.sort(tab,moncomp);
		}
		return ret;
	}
	
	public Case case_init() {
		Case lacase = new Case(0,0);
		lacase.setValH(999);
		return lacase;
	}
	
	public String toString() {
		String chaine;
		
		chaine="[ ";
		for(int i=0; i<nb_elts; i++) {
			chaine=chaine + tab[i].getValH()+" - ";
		}
		chaine = chaine + "]";
		return chaine;
	}
	
	public Noeud get_noeud(Case c) {
		Iterator<Noeud> it = liste_noeud.iterator();
		boolean trouve = false;
		Noeud n=null;
		
		while(it.hasNext() && !trouve) {
			n=it.next();
			if(n.get_case() == c) trouve=true;
		}
		return n;
	}
	
	public void fait_fils(Case c) {
		Case sud = grille.case_sud(c);
		if(sud!=null) {
			if(sud==arrive || (!come_from.contains(sud) && sud.getEc()==EtatCase.libre 
					&& !case_listee.contains(sud)) ) {
				sud.setValH(sud.distance(arrive));
				get_noeud(c).add_sud(sud);
				add(sud);
				liste_noeud.add(get_noeud(c).get_sud());
				case_listee.add(sud);
			}
		}
		
		
		Case nord = grille.case_nord(c);
		if(nord!=null) {
			if(nord==arrive || (!come_from.contains(nord) && nord.getEc()==EtatCase.libre
					&& !case_listee.contains(nord)) ){
				nord.setValH(nord.distance(arrive));
				get_noeud(c).add_nord(nord);
				add(nord);
				liste_noeud.add(get_noeud(c).get_nord());
				case_listee.add(nord);
			}
		}
		
		
		Case est = grille.case_est(c);
		if(est!=null) {
			if(est==arrive || (!come_from.contains(est) && est.getEc()==EtatCase.libre
					&& !case_listee.contains(est)) ){
				est.setValH(est.distance(arrive));
				get_noeud(c).add_est(est);
				add(est);
				case_listee.add(est);
				liste_noeud.add(get_noeud(c).get_est());
			}
		}
		
		Case ouest = grille.case_ouest(c);
		if(ouest!=null) {
			if(ouest==arrive || (!come_from.contains(ouest) && ouest.getEc()==EtatCase.libre
					&& !case_listee.contains(ouest)) ){
				ouest.setValH(ouest.distance(arrive));
				get_noeud(c).add_ouest(ouest);
				add(ouest);
				case_listee.add(ouest);
				liste_noeud.add(get_noeud(c).get_ouest());
			}
		}
	}
	
	public void retrace_chemin(Case c) {
		Noeud n = get_noeud(c).get_pere();
		chemin.add(c);
		
		while(n.get_pere()!=null) {
			chemin.add(n.get_case());
			n=n.get_pere();
		}
	}
	
	
	public ArrayList<Case> get_chemin() {
		
		if(depart!=arrive) {
			
			Case current = depart;
			boolean bug=false;
			
			current.setValH(current.distance(arrive));
			Noeud start = new Noeud(current, null);
			liste_noeud.add(start);
			
			while(current !=null && current.getValH()!=0){
				if(nb_elts>2500) bug=true;
				fait_fils(current);
				come_from.add(current);
				current = remove();
			}
			
			if(current==null) return null;
			else{
				retrace_chemin(current);
				return chemin;
			}
		}else return null;
	}
	
}
