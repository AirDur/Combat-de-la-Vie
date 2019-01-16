package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import graphisme.*;

public class Main {

	public static void main(String [] args) {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width; 
		int hauteur = dim.height;
		Fenetre fen = new Fenetre("Le Jeu",largeur/4,hauteur/4,largeur/2,hauteur/2);
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		
	}
	
}
