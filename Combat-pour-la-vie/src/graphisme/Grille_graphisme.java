package graphisme;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import zone42.*;

public class Grille_graphisme extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Integer taille, taille_max, squareSize;
	private JButton b_upload, b_launch, b_pause, b_stop;
	private Grille grille_info;
	private boolean en_cours;
	
	public Grille_graphisme(int t, int tm, Grille grille_jeu) {
		grille_info = grille_jeu;
		taille = t;
		taille_max = tm;
		squareSize = taille_max/taille;	
		addButtons();
		setEn_cours(false);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fills the background color.
        Graphics2D g2 = (Graphics2D) g;
        
        //recalcul :
        grille_info = Grille.getinstance();
        taille = grille_info.getTaille();
        squareSize = taille_max/taille;
        
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
            						
            		case carnivore: g2.setColor(Color.red);
            						break;
            						
            		case cadavre: g2.setColor(Color.orange);
            					break;	
            		
            		case herbivore: g2.setColor(Color.BLUE);
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
		b_launch = createButton("Launch", "Launch the animation", new ActionLaunch(this));
		b_stop = createButton("Stop", "Stop the animation", new ActionStop(this));
		b_pause = createButton("Pause", "Pause the animation", new ActionPause(this));
	    add(b_upload); add(b_launch); add(b_pause); add(b_stop);
	    
	    int start = 515;
	    b_upload.setBounds(start, 10, 100, b_upload.getPreferredSize().height);
	    b_launch.setBounds(start, 60 + insets.top, 100, b_upload.getPreferredSize().height);
	    b_pause.setBounds(start, 110 + insets.top, 100, b_upload.getPreferredSize().height);
	    b_stop.setBounds(start, 160 + insets.top, 100, b_upload.getPreferredSize().height);
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

	public boolean isEn_cours() {
		return en_cours;
	}

	public void setEn_cours(boolean ec) {
		en_cours = ec;
	}

}
