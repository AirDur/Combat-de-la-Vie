package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import aliment.Aliment;
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
		//zone2.initialisation();

		
		Case c7 = zone2.getGrille_info().get_case(2, 2);
		Loup l = new Loup(Sexe.femelle,100,c7);
		zone2.ajout_carnivore(l);
		
		Case c10 = zone2.getGrille_info().get_case(11,11);
		Loup l2 = new Loup(Sexe.male,100,c10);
		zone2.ajout_carnivore(l2);
		Loup go_cheufre = (Loup) l2.recherche_reproducteur();
		
		
	
		while(true) {
			
			Astar my_a = new Astar( zone2.getGrille_info(),
					 l2.getEmplacement(),go_cheufre.getEmplacement());
			
			
			ArrayList<Case> chemin = new ArrayList<Case>();
			chemin=my_a.get_chemin();
			
			l2.deplacement(chemin, 2);
			if(l.getEmplacement().distance(l2.getEmplacement() )==1){
				l.se_reproduire(l2);
			}
			fen_jeu.repaint();
		
			Thread.sleep(500);
		}
	}
	
}
