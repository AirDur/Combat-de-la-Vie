package graphisme;

import java.awt.event.*;

/**
 * Fonctions correspond aux actions d'un bouton
 */

public class ActionStop implements ActionListener {
	
private Grille_graphisme gg;
	
    public ActionStop(Grille_graphisme grille_graphisme) {
    	gg = grille_graphisme;
    }

	@Override
    public void actionPerformed(ActionEvent evt) {
    	Fenetre.timer.stop();
    	gg.setEn_cours(false);
    }
}

