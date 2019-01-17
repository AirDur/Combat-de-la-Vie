package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import graphisme.*;
import zone42.Zone42;

public class Main {

	public Main() {}
	
	private int dimension_grille=20;
	
	public Fenetre CreerFenetre(int tailleGrille, Zone42 espace_jeu) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width; 
		int hauteur = dim.height;
		Fenetre fen = new Fenetre("Le Jeu",largeur/4,hauteur/4,largeur/2,hauteur/2,tailleGrille,espace_jeu.getGrille_info());
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		return fen;
	}
	
	
	
	public static void main(String [] args) throws InterruptedException {
		
		Main obj = new Main();
		
		Zone42 zone2 = Zone42.getInstance(20);
		Fenetre fen_jeu = obj.CreerFenetre(zone2.getTaille(), zone2);
		zone2.initialisation();
		while(true) {
			zone2.faire_passer_le_temps();
			fen_jeu.repaint();
			Thread.sleep(500);
		}
	}
	
}
