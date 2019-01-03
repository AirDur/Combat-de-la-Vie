package zone42;

import java.util.ArrayList;

import aliment.*;
import consommateur.*;

public class Zone42 {
	
	/**
	 * verrou pour empêcher l'interface graphique de travailler tant que Zone42 n'a pas fini son travail.
	 */
	private boolean lock;
	/**
	 * Liste des consommateurs de la Zone42
	 */
	private ArrayList<Consommateur> list_consommateur;
	/**
	 * taille de la grille de jeu
	 */
	private Integer tailleGrille;
	/**
	 * la grille en elle-même
	 */
	private Grille grille;
	
	public Zone42(int a) {
		tailleGrille = a;
		grille = Grille.getinstance(tailleGrille, tailleGrille);
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
	
	/**
	 * Rajoute un consommateur à la liste
	 * @param c consommateur à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_consommateur(Consommateur c) {
		return 0;
	}
	
	/**
	 * Rajoute un aliment
	 * @param a aliment à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_aliment(Aliment a) {
		return 0;
	}
	
	/**
	 * Initilialise la zone selon les paramètres passés par l'utilisateur
	 * @return 1 si ok, 0 si erreur
	 */
	public int initialisation() {
		return 0;
	}
}