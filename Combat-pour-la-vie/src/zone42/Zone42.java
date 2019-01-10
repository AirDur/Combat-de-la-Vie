package zone42;

import java.util.ArrayList;
import java.util.Collection;

import aliment.*;
import consommateur.*;

public class Zone42 implements Runnable{
	
	/**
	 * verrou pour empêcher l'interface graphique de travailler tant que Zone42 n'a pas fini son travail.
	 */
	private boolean lock;
	
	/**
	 * Liste de Fabrique d'aliment de la Zone42
	 */
	
	private ArrayList<Fabrique_de_Vegetaux> list_farbique_vegetaux;
	
	/**
	 * Liste d'aliment de la Zone42
	 */
	
	private ArrayList<Aliment> list_aliment;
	
	/**
	 * Liste des consommateurs de la Zone42
	 */
	private ArrayList<Herbivore> list_herbivore;
	private ArrayList<Carnivore> list_carnivore;
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
	 * Rajoute un Carnivore à la liste
	 * @param c Carnivore à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_carnivore( Carnivore c) {
		list_carnivore.add(c);
		return 1;
	}
	
	/**
	 * Supprime un Carnivore à la liste
	 * @param c Carnivore à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int supprime_carnivore( Carnivore c) {
		list_carnivore.remove(c);
		return 1;
	}
	
	/**
	 * Rajoute un Herbivore à la liste
	 * @param h Herbivore à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_herbivore( Herbivore h) {
		list_herbivore.add(h);
		return 1;
	}
	
	/**
	 * Supprime un Herbivore à la liste
	 * @param h Herbivore à supprimer
	 * @return 1 si ok, 0 si erreur
	 */
	public int supprime_herbivore( Herbivore h) {
		list_carnivore.remove(h);
		return 1;
	}
	
	/**
	 * Rajoute un aliment
	 * @param a aliment à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_aliment(Aliment a) {
		list_aliment.add(a);
		
		return 1;
	}
	
	/**
	 * Initilialise la zone selon les paramètres passés par l'utilisateur
	 * @return 1 si ok, 0 si erreur
	 */
	public int initialisation() {
		//Initialisation des listes
		list_aliment = new ArrayList<Aliment>();
		list_farbique_vegetaux = new ArrayList<Fabrique_de_Vegetaux>(20);
		list_herbivore = new ArrayList<Herbivore>();
		list_carnivore = new ArrayList<Carnivore>();
		
		Case c = new Case(10, 15);
		
		list_farbique_vegetaux.add(new Fabrique_de_Vegetaux(100,1, 5, TypeVegetaux.plante, c));
		grille.setEtat(EtatCase.fabriqueVegetaux, c);
		
		return 1;
	}
	
	public String toString() {
		return grille.toString();
	}
}