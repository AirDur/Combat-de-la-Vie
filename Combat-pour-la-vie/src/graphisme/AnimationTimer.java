package graphisme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import zone42.Zone42;

public class AnimationTimer implements ActionListener {

	private Grille_graphisme gg;
	
    public AnimationTimer(Grille_graphisme grille_graphisme) {
    	gg = grille_graphisme;
    }
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("en cours");
		gg.repaint();
		//Thread.sleep(Zone42.getTemps_cycle());
		Zone42.faire_passer_le_temps();	
		
	}

}
