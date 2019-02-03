package graphisme;
import java.awt.*;

import javax.swing.*;

import zone42.Zone42;
import zone42.Grille;

public class Fenetre extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private Grille_graphisme grille;
	private Cellule liste_cellule;
	private Zone42 espace_jeu;
	private Integer temps;

	private static Integer taille_max = 500;
	
	public Fenetre(String s, int x, int y, int largeur, int hauteur, int tailleGrille, Grille grille_jeu) {
		setBounds(x,y, largeur ,hauteur);
		setTitle(s);
		grille = new Grille_graphisme(tailleGrille, taille_max, grille_jeu);
		grille.setLayout(new BorderLayout());
		add(grille);
	}
	
	public static Fenetre CreerFenetre(int tailleGrille, Zone42 espace_jeu) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width; 
		int hauteur = dim.height;
		Fenetre fen = new Fenetre("Le Jeu", largeur/3, hauteur/4, taille_max + 125, taille_max + 50, tailleGrille, espace_jeu.getGrille_info());
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setVisible(true);
		fen.setLayout(null);
		return fen;
	}
}
