package graphisme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import zone42.EtatCase;
import zone42.Grille;
import zone42.Zone42;

public class Grille_graphisme extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Integer taille, squareSize;
	private Zone42 espace_jeu;
	private JButton b_upload, b_launch, b_pause, b_stop, b_help;
	private Grille grille_info;
	Cellule liste_cellule;
	
	public Grille_graphisme(int t, int taille_max, Grille grille_jeu) {
		grille_info = grille_jeu;
		taille = t;
		squareSize = taille_max/t;
		
		addButtons();
	}
		

	
	
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
            						
            		case animal: System.out.println("animal");g2.setColor(Color.red);
            						break;
            		default:
            						break;
            	}
            			

            	g2.fillRect(11 + c*squareSize, 11 + r*squareSize, squareSize - 1,
            			squareSize - 1);
            }
        }
    }
	
	/**
	 * ajoute les boutons au JPanel
	 */
	private void addButtons() {
		Insets insets = getInsets();
		b_upload = createButton("Select", "Select a new initialisation file", new ActionSelect(this));
		b_launch = createButton("Launch", "Launch the animation", new ActionLaunch());
		b_stop = createButton("Stop", "Stop the animation", new ActionPause());
		b_pause = createButton("Pause", "Pause the animation", new ActionPause());
		b_help = createButton("Help", "Open an Help window", new ActionStop());
	    add(b_upload); add(b_launch); add(b_pause); add(b_stop); add(b_help);
	    
	    int start = (taille*squareSize) + 15;
	    b_upload.setBounds(start, 10, 100, b_upload.getPreferredSize().height);
	    b_launch.setBounds(start, 60 + insets.top, 100, b_upload.getPreferredSize().height);
	    b_pause.setBounds(start, 110 + insets.top, 100, b_upload.getPreferredSize().height);
	    b_stop.setBounds(start, 160 + insets.top, 100, b_upload.getPreferredSize().height);
	    b_help.setBounds(start, 210 + insets.top, 100, b_upload.getPreferredSize().height);
	}
	
	/**
	 * Méthode simple qui créer un boutons avec tous les paramètres
	 * @param name nom du boutons
	 * @param tooltip bulle d'information au survol de la souris
	 * @param al
	 * @return le boutton
	 */
	private static JButton createButton(String name, String tooltip, ActionListener al) {
		JButton jb = new JButton(name);
		jb.addActionListener((ActionListener) al);
		jb.setBackground(Color.white);
		jb.setToolTipText(tooltip);
		return jb;
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
