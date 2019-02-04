package zone42;

import java.util.*;
import org.ini4j.*;
import java.io.*;
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
	
	private static ArrayList<Fabrique_de_Vegetaux> list_fabrique_vegetaux;
	
	/**
	 * Liste d'aliment de la Zone42
	 */
	
	private static ArrayList<Aliment> list_aliment;
	
	/**
	 * Liste des consommateurs de la Zone42
	 */
	private static ArrayList<Herbivore> list_herbivore;
	private static ArrayList<Carnivore> list_carnivore;
	/**
	 * taille de la grille de jeu
	 */
	private static Integer tailleGrille;
	/**
	 * la grille en elle-même
	 */
	private static Grille grille;
	/**
	 * L'unité du temps du jeu le cycle
	 */
	private int cycle=0;

	private static Integer seuil_faim;

	private static Integer seuil_famine;

	private static int temps_cycle;
	
	/**
	 * attribut pour design pattern Singleton
	 * Instancié à null (pas d'instance créé)
	 */
	public static Zone42 instance = null;

	private static boolean operationnel;
	
	/**
	 * Constructeur implémenté par le design pattern Singleton
	 */
	private Zone42() {
		operationnel = false;
		tailleGrille = 30;
		grille = Grille.getinstance();
	}
	
	/**
	 * Récupère l'instance Zone42 ou en créé une s'il y en a toujours pas.
	 * @param a 
	 * @return une nouvelle zone42 si instance==null, sinon LA Zone 42.
	 */
	public static Zone42 getInstance() {
		if (instance == null) instance = new Zone42();
		return instance;
	}
	
	public static int initialisation(File fichier) {
		//http://ini4j.sourceforge.net/tutorial/OneMinuteTutorial.java.html
        Wini ini;
        int no_problem = 0;
		try {
			ini = new Wini(fichier);
			
			temps_cycle = ini.get("global", "temps_cycle", int.class);
			if(10 > temps_cycle || temps_cycle > 10000) {
				no_problem = 1;
				temps_cycle = 1000;
			}
			
			seuil_faim = ini.get("global", "seuil_faim", int.class);
			if(99 < seuil_faim || seuil_faim < 2) {
				no_problem = 2;
				seuil_faim = 70;
			}
			
			seuil_famine = ini.get("global", "seuil_famine", int.class);
			if(98 < seuil_famine || seuil_famine < 1 || seuil_famine >= seuil_faim) {
				no_problem = 3;
				seuil_famine = 25;
			}
			
			tailleGrille = ini.get("global", "taille_grille", int.class);
			if(tailleGrille < 5 || tailleGrille > 50) {
				tailleGrille = 30;
				no_problem = 4;
			}
			grille = Grille.changeInstance(tailleGrille, tailleGrille);
			
			
			int nb_fabrique_vegetaux = ini.get("Fabrique_Vegetaux", "nombre", int.class);
			list_fabrique_vegetaux = new ArrayList<Fabrique_de_Vegetaux>(nb_fabrique_vegetaux);
			for(int i = 1; i <= nb_fabrique_vegetaux; i++) {
				int vie = ini.get("Fabrique_Vegetaux_"+i, "duree_de_vie", int.class);
				if(vie < 10 || vie > 100000000) {
					vie = 100;
					no_problem = 5;
				}
				int quantite = ini.get("Fabrique_Vegetaux_"+i, "capacite_production", int.class);
				if(quantite < 1 || quantite > 5) {
					quantite = 2;
					no_problem = 6;
				}
				int cycle = ini.get("Fabrique_Vegetaux_"+i, "cycle_production", int.class);
				if(cycle < 1 || cycle > 100000000) {
					cycle = 10;
					no_problem = 7;
				}
				int x = ini.get("Fabrique_Vegetaux_"+i, "x", int.class);
				if(x < 0 || x >= tailleGrille) {
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					no_problem = 8;
				}
				int y = ini.get("Fabrique_Vegetaux_"+i, "y", int.class);
				if(y < 0 || y >= tailleGrille) {
					y = Math_methods.randomWithRange(0, tailleGrille-1);
					no_problem = 9;
				}
				String type_production = ini.get("Fabrique_Vegetaux_"+i, "type_production");
				
				Case c = new Case(x, y);
				if(Grille.getinstance().getEtat(c) != EtatCase.libre) {
					c = new Case(Math_methods.randomWithRange(0, tailleGrille-1), Math_methods.randomWithRange(0, tailleGrille-1));
				}
				
				try {
					Fabrique_de_Vegetaux fdv = new Fabrique_de_Vegetaux(vie, quantite, cycle, type_production, c);
					list_fabrique_vegetaux.add(fdv);
					Grille.getinstance().setEtat(EtatCase.fabriqueVegetaux, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			int valeur_ener_Foin = ini.get("Foin", "valeur_energetique", int.class);
			if(valeur_ener_Foin < 1 || valeur_ener_Foin > 1000) {
				valeur_ener_Foin = 10;
				no_problem = 10;
			}
			int valeur_decompo_Foin = ini.get("Foin", "temps_decomposition", int.class);
			if(valeur_decompo_Foin < 1 || valeur_decompo_Foin > 1000) {
				valeur_decompo_Foin = 10;
				no_problem = 11;
			}
			
			int valeur_ener_Herbe = ini.get("Herbe", "valeur_energetique", int.class);
			if(valeur_ener_Herbe < 1 || valeur_ener_Herbe > 1000) {
				valeur_ener_Herbe = 10;
				no_problem = 12;
			}
			int valeur_decompo_Herbe = ini.get("Herbe", "temps_decomposition", int.class);
			if(valeur_decompo_Herbe < 1 || valeur_decompo_Herbe > 1000) {
				valeur_decompo_Herbe = 10;
				no_problem = 13;
			}
			
			int valeur_ener_Plante = ini.get("Plante", "valeur_energetique", int.class);
			if(valeur_ener_Plante < 1 || valeur_ener_Plante > 1000) {
				valeur_ener_Plante = 10;
				no_problem = 14;
			}
			int valeur_decompo_Plante = ini.get("Plante", "temps_decomposition", int.class);
			if(valeur_decompo_Plante < 1 || valeur_decompo_Plante > 1000) {
				valeur_decompo_Plante = 10;
				no_problem = 15;
			}
			
			int valeur_ener_Pomme = ini.get("Pomme", "valeur_energetique", int.class);
			if(valeur_ener_Pomme < 1 || valeur_ener_Pomme > 1000) {
				valeur_ener_Pomme = 10;
				no_problem = 16;
			}
			int valeur_decompo_Pomme = ini.get("Pomme", "temps_decomposition", int.class);
			if(valeur_decompo_Pomme < 1 || valeur_decompo_Pomme > 1000) {
				valeur_decompo_Pomme = 10;
				no_problem = 17;
			}
			Foin.setProprieteNutritive(valeur_ener_Foin);
			Herbe.setProprieteNutritive(valeur_ener_Herbe);
			Plante.setProprieteNutritive(valeur_ener_Plante);
			Pomme.setProprieteNutritive(valeur_ener_Pomme);
			
			Foin.setTempsDecomposition(valeur_decompo_Foin);
			Herbe.setTempsDecomposition(valeur_decompo_Herbe);
			Plante.setTempsDecomposition(valeur_decompo_Plante);
			Pomme.setTempsDecomposition(valeur_decompo_Pomme);
	        
	        operationnel = true;
	        return no_problem;
		} catch (IOException e) {
			return -1;
		}        
	}
	
	/**
	 * Methode qui incrément les cycle toutes les
	 * temps millisecondes
	 */
	public void run() {
		System.out.println("Lancement du jeu");
		try {
			while(cycle < 200) {
				Thread.sleep(temps_cycle);
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
		c.getEmplacement().setEc(EtatCase.animal);
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
		return tailleGrille;
	}
	
	public Grille getGrille_info() {
		return grille;
	}
	
	public static ArrayList<Aliment> get_listeAliment(){
		return list_aliment;
	}
	
	public static ArrayList<Herbivore> get_listeHerbivore(){
		return list_herbivore;
	}
	
	public static ArrayList<Carnivore> get_listeCarnivore(){
		return list_carnivore;
	}
	
	public String toString() {
		return "Zone appell grille : " + grille.toString();
	}

	public long getTempsCycle() {
		return temps_cycle;
	}
	
	public static Integer getSeuil_faim() {
		return seuil_faim;
	}

	protected void setSeuil_faim(Integer seuil_faim) {
		Zone42.seuil_faim = seuil_faim;
	}

	public static Integer getSeuil_famine() {
		return seuil_famine;
	}

	protected void setSeuil_famine(Integer seuil_famine) {
		Zone42.seuil_famine = seuil_famine;
	}

	protected static boolean isOperationnel() {
		return operationnel;
	}

	protected static void setOperationnel(boolean operationnel) {
		Zone42.operationnel = operationnel;
	}
}