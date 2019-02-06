package graphisme;

import java.awt.event.*;

import zone42.Zone42;

/**
 * Fonctions correspond aux actions d'un bouton
 */

public class ActionLaunch implements ActionListener {
	
	private Grille_graphisme gg;
	
    public ActionLaunch(Grille_graphisme grille_graphisme) {
    	gg = grille_graphisme;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
    	if(Zone42.isOperationnel()) {
    		gg.setEn_cours(true);
    		Fenetre.timer.start();
    	}
    }
    
}
