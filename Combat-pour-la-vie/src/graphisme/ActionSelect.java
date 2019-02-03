package graphisme;

import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;

import zone42.Zone42;

/**
 * Fonctions correspond aux actions d'un bouton
 */

public class ActionSelect implements ActionListener {
	
    @Override
    public void actionPerformed(ActionEvent evt) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        File fichier;
        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	fichier = dialogue.getSelectedFile();
        	if(!Zone42.initialisation(fichier)) {
        		//creer une fenetre d'erreur
        	}
        }
    }
}

