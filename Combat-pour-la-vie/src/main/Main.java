package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import graphisme.*;
import zone42.Astar;
import zone42.Case;
import zone42.EtatCase;
import zone42.Zone42;

public class Main {

	private final static int dimension_grille = 20;
	
	public static void main(String [] args) throws InterruptedException {
	
		Zone42 zone2 = Zone42.getInstance(dimension_grille);
		Fenetre fen_jeu = Fenetre.CreerFenetre(zone2.getTaille(), zone2);
		boolean flag =false;
		zone2.initialisation();

		//zone2.faire_passer_le_temps();
		Astar my_a = new Astar( zone2.getGrille_info(),
				zone2.getGrille_info().get_case(1, 0), zone2.getGrille_info().get_case(19, 0));
		ArrayList<Case> chemin = new ArrayList<Case>();
		chemin=my_a.get_chemin();
	
		System.out.println(chemin);
		Iterator<Case> it = chemin.iterator();
		Case old_cc=null;
		while(true) {
			if(it.hasNext()) {
				if(flag) zone2.getGrille_info().setEtat(EtatCase.libre, old_cc);
				Case cc= (Case)it.next();
				zone2.getGrille_info().setEtat(EtatCase.animal, cc);
				old_cc=cc;
				flag=true;
			}
			fen_jeu.repaint();
		
			Thread.sleep(500);
		}
	}
	
}
