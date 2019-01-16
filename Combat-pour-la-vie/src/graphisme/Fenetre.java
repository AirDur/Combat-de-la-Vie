package graphisme;
import javax.swing.JFrame;

import zone42.Zone42;

public class Fenetre extends JFrame{
	
	private Grille grille;
	private Boutons liste_bouton;
	private Cellule liste_cellule;
	private Zone42 espace_jeu;
	Integer temps;
	
	public Fenetre(String s, int x, int y, int largeur, int hauteur) {
		setBounds(x,y,largeur,hauteur);
		setTitle(s);
		grille = new Grille();
		add(grille);
	}
}
