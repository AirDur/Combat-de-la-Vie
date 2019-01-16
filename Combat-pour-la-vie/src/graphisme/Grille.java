package graphisme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Grille extends JPanel{
	
	private Integer taille;
	Cellule liste_cellule;
	
	public Grille() {
		setLayout(new GridLayout(3, 3, 1, 1)); 
			setBackground(Color.RED); 
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
	}
}
