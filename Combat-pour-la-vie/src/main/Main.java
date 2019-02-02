package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import graphisme.*;
import zone42.Zone42;

public class Main {

	private final static int dimension_grille = 20;
	
	public static void main(String [] args) throws InterruptedException {
	
		Zone42 zone2 = Zone42.getInstance(dimension_grille);
		Fenetre fen_jeu = Fenetre.CreerFenetre(zone2.getTaille(), zone2);
		zone2.initialisation();
		while(true) {
			zone2.faire_passer_le_temps();
			fen_jeu.repaint();
			Thread.sleep(500);
		}
	}
	
}
