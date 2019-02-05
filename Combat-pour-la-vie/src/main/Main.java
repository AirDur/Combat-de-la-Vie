package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import aliment.Aliment;
import consommateur.Carnivore;
import consommateur.Consommateur;
import consommateur.Loup;
import consommateur.Sexe;
import graphisme.*;
import zone42.Astar;
import zone42.Case;
import zone42.EtatCase;
import zone42.Zone42;

public class Main {

	private final static int dimension_grille = 20;
	
	public static void main(String [] args) throws InterruptedException {
	
		
		Zone42 zone2 = Zone42.getInstance();
		Fenetre fen_jeu = Fenetre.CreerFenetre(zone2.getTaille(), zone2);
		

		
		Case c7 = zone2.getGrille_info().get_case(2, 2);
		Loup l = new Loup(Sexe.femelle,100,c7,3,10,10,10);
		zone2.ajout_carnivore(l);
		
		Case c10 = zone2.getGrille_info().get_case(11,11);
		Loup l2 = new Loup(Sexe.male,100,c10,10,10,10,10);
		zone2.ajout_carnivore(l2);
		Loup go_cheufre = (Loup) l2.recherche_reproducteur();
		
		
	
		while(true) {
			
			ArrayList<Carnivore> nouveaux_nees = new ArrayList<Carnivore>();
			ArrayList<Carnivore> ll = zone2.get_listeCarnivore();
			Iterator<Carnivore> it = ll.iterator();
			Carnivore c=null,c_nee;
			
			while(it.hasNext()) {
				c= it.next();
				c_nee=(Carnivore) c.faire_passer_le_temps();
				if(c_nee!=null) nouveaux_nees.add(c_nee);
			}
			
			ll.addAll(nouveaux_nees);
			fen_jeu.repaint();
		
			Thread.sleep(500);
		}
	}
	
}
