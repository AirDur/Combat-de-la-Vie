package zone42;

import java.util.*;

import aliment.*;
import consommateur.*;

public class Zone42 implements Runnable {
	
	/**
	 * verrou pour empêcher l'interface graphique de travailler tant que Zone42 n'a pas fini son travail.
	 */
	@Deprecated
	private boolean lock;
	
	/**
	 * Liste de Fabrique d'aliment de la Zone42
	 */
	
	private ArrayList<Fabrique_de_Vegetaux> list_fabrique_vegetaux;
	
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
	
	/**
	 * attribut pour design pattern Singleton
	 * Instancié à null (pas d'instance créé)
	 */
	public static Zone42 instance = null;
	
	/**
	 * Constructeur implémenté par le design pattern Singleton
	 */
	private Zone42(int a) {
		tailleGrille = a;
		grille = Grille.getinstance(tailleGrille, tailleGrille);
	}
	
	/**
	 * Récupère l'instance Zone42 ou en créé une s'il y en a toujours pas.
	 * @param a 
	 * @return une nouvelle zone42 si instance==null, sinon LA Zone 42.
	 */
	public static Zone42 getInstance(int a) {
		if (instance == null) instance = new Zone42(a);
		return instance;
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
	
	public void faire_passer_le_temps() {
		// fabriques de végétaux : 
		for(Fabrique_de_Vegetaux fv : list_fabrique_vegetaux) {
			ArrayList<Vegetaux> al = fv.utilisation();
			if(al != null) {
				for(Vegetaux v : al) {
					System.out.println("vegetaux x = "+ v.getEmplacement().getVal_x() +"vegetaux y = "+v.getEmplacement().getVal_y());
					grille.setEtat(EtatCase.vegetal, v.getEmplacement().getVal_x(), v.getEmplacement().getVal_y());
				}
				list_aliment.addAll(al);
			}	
		}
		// check herbivore :
		
		// check carnivore :
		
		// clean (virer aliment périmé et les animaux morts).
	};
	
	@Deprecated
	public void lock() {
		setLock(true);
	}
	
	@Deprecated
	public void delock() {
		setLock(false);
	}

	@Deprecated
	public boolean isLock() {
		return lock;
	}

	@Deprecated
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
	public boolean supprime_carnivore(Carnivore c) {
		return list_carnivore.remove(c);
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
	public boolean supprime_herbivore(Herbivore h) {
		return list_herbivore.remove(h);
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
	
	public int getTaille() {
		return this.tailleGrille;
	}
	
	public Grille getGrille_info() {
		return grille;
	}
	
	/**
	 * Initilialise la zone selon les paramètres passés par l'utilisateur
	 * @return 1 si ok, 0 si erreur
	 */
	public int initialisation() {
		//Initialisation des listes
		list_aliment = new ArrayList<Aliment>();
		list_fabrique_vegetaux = new ArrayList<Fabrique_de_Vegetaux>(20);
		list_herbivore = new ArrayList<Herbivore>();
		list_carnivore = new ArrayList<Carnivore>();
		
		Case c = new Case(0, 0);
		list_fabrique_vegetaux.add(new Fabrique_de_Vegetaux(100,1, 1, TypeVegetaux.plante, c));
		grille.setEtat(EtatCase.fabriqueVegetaux, c);
		
		Case c2 = new Case(1, 8);
		list_fabrique_vegetaux.add(new Fabrique_de_Vegetaux(100,1, 1, TypeVegetaux.plante, c2));
		grille.setEtat(EtatCase.fabriqueVegetaux, c2);
		
		
		return 1;
	}
	
	public String toString() {
		return "Zone appell grille : " + grille.toString();
	}
}