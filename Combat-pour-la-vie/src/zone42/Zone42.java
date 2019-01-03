package zone42;

import java.util.ArrayList;

import aliment.*;
import consommateur.*;

public class Zone42 implements Runnable{
	
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
	/**
	 * L'unité du temps du jeu le cycle
	 */
	private int cycle=0;
	
	public Zone42(int a) {
		tailleGrille = a;
		grille = Grille.getinstance(tailleGrille, tailleGrille);
	}
	
	/**
	 * Methode qui incrément les cycle toutes les
	 * temps millisecondes
	 */
	public void run() {
		System.out.println("Lancement du jeu");
		long temps = 1000;
		try {
			while(cycle < 200) {
				Thread.sleep(temps);
				System.out.println("cycle " + cycle++);
			}
		}catch(InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
		
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
	
	/**
	 * Main de test pour les cycles
	 * @param args
	 */
	public static void main(String [] args) {
		Zone42 zone = new Zone42(10);
		Thread le_thread = new Thread(zone,"Cycle");
		
		le_thread.start();
		
	}
}