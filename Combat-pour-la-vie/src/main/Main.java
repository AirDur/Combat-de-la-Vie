package main;

import graphisme.*;
import zone42.Grille;
import zone42.Zone42;

public class Main {
	
	public static void main(String [] args) throws InterruptedException {
	
		
		Zone42 zone2 = Zone42.getInstance();
		@SuppressWarnings("unused")
		Fenetre fen_jeu = Fenetre.CreerFenetre(zone2.getTaille(), zone2);
		@SuppressWarnings("unused")
		Grille g = zone2.getGrille_info();
		
	}
	
}
