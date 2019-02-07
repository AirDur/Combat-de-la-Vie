package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import aliment.Aliment;
import aliment.Vegetaux;
import consommateur.Carnivore;
import consommateur.Chevreuil;
import consommateur.Consommateur;
import consommateur.Herbivore;
import consommateur.Loup;
import consommateur.Mouton;
import consommateur.Sexe;
import graphisme.*;
import zone42.Astar;
import zone42.Case;
import zone42.EtatCase;
import zone42.Grille;
import zone42.Zone42;

public class Main {

	private final static int dimension_grille = 20;
	
	public static void main(String [] args) throws InterruptedException {
	
		
		Zone42 zone2 = Zone42.getInstance();
		Fenetre fen_jeu = Fenetre.CreerFenetre(zone2.getTaille(), zone2);
		Grille g = zone2.getGrille_info();
		
		//public Loup(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) {
		Loup l = new Loup(Sexe.male, 200, g.get_case(1, 1), 2,0,10,10 );
		Loup l3 = new Loup(Sexe.femelle, 200, g.get_case(1, 1), 2,0,10,10 );
		Loup l2 = new Loup(Sexe.male, 200, g.get_case(2, 2), 2,0,10,10 );
		zone2.ajout_carnivore(l);
		zone2.ajout_carnivore(l2);
		zone2.ajout_carnivore(l3);
		
		Chevreuil c = new Chevreuil(Sexe.femelle,20, g.get_case(18, 18),10,0,10,10 );
		zone2.ajout_herbivore(c);
		Mouton m2 = new Mouton(Sexe.male,20, g.get_case(10, 2),10,0,10,10 );
		zone2.ajout_herbivore(m2);
		Mouton m = new Mouton(Sexe.femelle,20, g.get_case(10, 2),10,0,10,10 );
		zone2.ajout_herbivore(m);
		
		Vegetaux v = new Vegetaux(10,10, zone2.getGrille_info().get_case(5, 5));
		zone2.ajout_aliment(v);
		
		Iterator<Herbivore> it_h;
		Iterator<Carnivore> it_c;
		ArrayList<Carnivore> l_cn= new ArrayList<Carnivore>();
		ArrayList<Herbivore> l_hn= new ArrayList<Herbivore>();
		while(true) {
			
			it_c = Zone42.get_listeCarnivore().iterator();
			while(it_c.hasNext()) {
				Consommateur cn=it_c.next().faire_passer_le_temps();
				if(cn!=null) l_cn.add((Carnivore) cn);
			}
			
			it_h = Zone42.get_listeHerbivore().iterator();
			while(it_h.hasNext()) {
				Consommateur hn=it_h.next().faire_passer_le_temps();
				if(hn!=null) l_hn.add((Herbivore) hn);
			}
			
			it_h = Zone42.get_list_herbivore_mort().iterator();
			while(it_h.hasNext()) {
				Zone42.supprime_herbivore(it_h.next());
			}
			
			it_c = Zone42.get_list_carnivore_mort().iterator();
			while(it_c.hasNext()) {
				Zone42.supprime_carnivore(it_c.next());
			}
			
			if(l_cn!=null) Zone42.get_listeCarnivore().addAll(l_cn);
			if(l_hn!=null) Zone42.get_listeHerbivore().addAll(l_hn);
			fen_jeu.repaint();
			Thread.sleep(500);
			
		}
		
	}
	
}
