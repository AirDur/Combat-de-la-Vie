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
		
	}
	
}
