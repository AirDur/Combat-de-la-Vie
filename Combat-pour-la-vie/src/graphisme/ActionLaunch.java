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
    	
    	//verifier si Zone42 est opérationel puis le lancer.
    	if(Zone42.isOperationnel()) {
    		gg.setEn_cours(true);
    		play();
    	}
    }
    
    private void play() {
    	try {
			while(gg.isEn_cours()) {
				System.out.println("en cours");
				gg.repaint();
				Thread.sleep(Zone42.getTemps_cycle());
				Zone42.faire_passer_le_temps();	
			}
		}catch(InterruptedException e) {
			gg.setEn_cours(false);
			System.out.println("Thread interrupted.");
		}
    }
}

