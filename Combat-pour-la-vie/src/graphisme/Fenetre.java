package graphisme;
import javax.swing.JFrame;

import zone42.Zone42;
import zone42.Grille;

public class Fenetre extends JFrame{
	
	private Grille_graphisme grille;
	private Boutons liste_bouton;
	private Cellule liste_cellule;
	private Zone42 espace_jeu;
	Integer temps;
	
	public Fenetre(String s, int x, int y, int largeur, int hauteur, int tailleGrille, Grille grille_jeu) {
		//espace_jeu=esp_j;
		setBounds(x,y,largeur,hauteur);
		setTitle(s);
		grille = new Grille_graphisme(tailleGrille, grille_jeu);
		add(grille);
	}

}
