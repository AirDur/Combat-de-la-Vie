package graphisme;

import java.awt.event.*;

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
    	gg.setEn_cours(false);
    }
}

