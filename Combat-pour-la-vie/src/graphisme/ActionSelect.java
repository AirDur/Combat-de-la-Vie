package graphisme;

import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import zone42.Zone42;

/**
 * Fonctions correspond aux actions d'un bouton
 */

public class ActionSelect implements ActionListener {
	
	private Grille_graphisme gg;
	
    public ActionSelect(Grille_graphisme grille_graphisme) {
    	gg = grille_graphisme;
    }

	@Override
    public void actionPerformed(ActionEvent evt) {
		gg.setEn_cours(false);
        JFileChooser dialogue = new JFileChooser(new File("."));
        File fichier;
        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	fichier = dialogue.getSelectedFile();
        	int error = Zone42.initialisation(fichier);
        	if(error != 0) {
        		JOptionPane.showMessageDialog(null, "Erreur lors du chargement du fichier (erreur = " + error +")", "Erreur", JOptionPane.ERROR_MESSAGE);
        	} else {
        		JOptionPane.showMessageDialog(null, "Fichier d'initialisation chargé", "Chargement complet", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
        gg.repaint();
    }
}

