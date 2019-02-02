package graphisme;
import java.awt.Dimension;
import java.awt.Toolkit;

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
	
	public static Fenetre CreerFenetre(int tailleGrille, Zone42 espace_jeu) {
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

}
