package graphisme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

import zone42.EtatCase;
import zone42.Grille;
import zone42.Zone42;

public class Grille_graphisme extends JPanel{
	
	private Integer taille;
	private Zone42 espace_jeu;
	private Grille grille_info;
	Cellule liste_cellule;
	
	public Grille_graphisme(int t, Grille grille_jeu) {
		grille_info=grille_jeu;
		taille=t;
	}
		

	private int squareSize = 15;
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fills the background color.
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(10, 10, (taille*squareSize)+1, (taille*squareSize)+1);

        
        for (int r = 0; r < taille; r++) {
            for (int c = 0; c < taille; c++) {
            	EtatCase ec = grille_info.getEtat(c, r);
            	
            	switch(ec) {
            	
            		case libre: g2.setColor(Color.WHITE); 
            					break;
            		case fabriqueVegetaux : g2.setColor(Color.GRAY); 
											break;
											
            		case vegetal: g2.setColor(Color.GREEN);
            						break;
            						
            		case animal: g2.setColor(Color.red);
            						break;
            	}
            			

            	g2.fillRect(11 + c*squareSize, 11 + r*squareSize, squareSize - 1,
            			squareSize - 1);
            }
        }
    }
	/*public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fills the background color.
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(10, 10, columns*squareSize+1, rows*squareSize+1);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
            			g2.setColor(Color.WHITE); 

            	g2.fillRect(11 + c*squareSize, 11 + r*squareSize, squareSize - 1,
            			squareSize - 1);
            }
        }
    }*/
}
