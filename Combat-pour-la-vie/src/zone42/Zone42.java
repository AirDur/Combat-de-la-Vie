package zone42;

import java.util.ArrayList;
import consommateur.Consommateur;

public class Zone42 {
	private boolean lock;
	private ArrayList<Consommateur> list_consommateur;
	private Grille grille;
	
	public Zone42() {
		grille = new Grille();
	}
	
	public void faire_passer_le_temps() {};
	
	public void lock() {
		setLock(true);
	}
	public void delock() {
		setLock(false);
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}
}