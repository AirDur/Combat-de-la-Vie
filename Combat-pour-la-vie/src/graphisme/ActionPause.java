package graphisme;

import java.awt.event.*;

import zone42.Zone42;

/**
 * Fonctions correspond aux actions d'un bouton
 */

public class ActionPause implements ActionListener {
	
	private Grille_graphisme gg;
	
    public ActionPause(Grille_graphisme grille_graphisme) {
    	gg = grille_graphisme;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
    	if(Fenetre.timer.isRunning() && Zone42.isOperationnel() && gg.isEn_cours()) {
    		Fenetre.timer.stop();
    	} else {
    		Fenetre.timer.start();
    	}
    }
}

