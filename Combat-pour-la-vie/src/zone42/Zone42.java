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
	private static int cycle=0;

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
		list_aliment = new ArrayList<Aliment>(1000);
		list_herbivore = new ArrayList<Herbivore>(1000);
		list_carnivore = new ArrayList<Carnivore>(1000);
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
	
	/**
	 * Lit le fichier et rempli Zone42 selon les parametres
	 * @param fichier
	 * @return 0 si aucune erreur, -1 si erreur de fichier. un nombre pour débugger sinon.
	 */
	public static int initialisation(File fichier) {
		raz();
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
				}
				int quantite = ini.get("Fabrique_Vegetaux_"+i, "capacite_production", int.class);
				if(quantite < 1 || quantite > 5) {
					quantite = 2;
				}
				
				int cycle = ini.get("Fabrique_Vegetaux_"+i, "cycle_production", int.class);
				if(cycle < 1 || cycle > 100000000) {
					cycle = 10;
				}
				
				int x = ini.get("Fabrique_Vegetaux_"+i, "x", int.class);
				if(x < 0 || x >= tailleGrille) {
					x = Math_methods.randomWithRange(0, tailleGrille-1);
				}
				
				int y = ini.get("Fabrique_Vegetaux_"+i, "y", int.class);
				if(y < 0 || y >= tailleGrille) {
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				}
				String type_production = ini.get("Fabrique_Vegetaux_"+i, "type_production").toLowerCase();
				
				Case c = Grille.getinstance().get_case(x, y);
				if(Grille.getinstance().getEtat(c) != EtatCase.libre) {
					c = Grille.getinstance().get_case(Math_methods.randomWithRange(0, tailleGrille-1), Math_methods.randomWithRange(0, tailleGrille-1));
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
			if(valeur_ener_Plante < 1 || valeur_ener_Plante > 1000 ) {
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
	        
			int nb_bos_taurus = ini.get("Animal", "Bos_taurus", int.class);
			int nb_caribou = ini.get("Animal", "Caribou", int.class);
			int nb_chevreuil = ini.get("Animal", "Chevreuil", int.class);
			int nb_lion = ini.get("Animal", "Lion", int.class);
			int nb_loup = ini.get("Animal", "Loup", int.class);
			int nb_mouton = ini.get("Animal", "Mouton", int.class);
			int nb_tigre = ini.get("Animal", "Tigre", int.class);
			
			System.out.println(nb_bos_taurus);
			for(int i = 1; i <= nb_bos_taurus; i++) {
				
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Bos_taurus_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Bos_taurus_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Bos_taurus_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Bos_taurus_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Bos_taurus_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Bos_taurus_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Bos_taurus_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Bos_taurus_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Bos_taurus_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Bos_taurus bt = new Bos_taurus(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_herbivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_caribou; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 25;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Carbibou_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Carbibou_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Carbibou_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Carbibou_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Carbibou_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Carbibou_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Carbibou_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Carbibou_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Carbibou_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Caribou bt = new Caribou(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_herbivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_chevreuil; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Chevreuil_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Chevreuil_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Chevreuil_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Chevreuil_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Chevreuil_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Chevreuil_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Chevreuil_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Chevreuil_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Chevreuil_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Chevreuil bt = new Chevreuil(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_herbivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_lion; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Lion_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Lion_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Lion_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Lion_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Lion_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Lion_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Lion_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Lion_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Lion_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Lion bt = new Lion(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_carnivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_loup; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Loup_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Loup_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Loup_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Loup_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Loup_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Loup_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Loup_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Loup_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Loup_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Loup bt = new Loup(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_carnivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_mouton; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Mouton_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Mouton_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Mouton_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Mouton_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Mouton_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Mouton_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Mouton_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Mouton_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Mouton_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Mouton bt = new Mouton(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_herbivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
			
			for(int i  = 1; i < nb_tigre; i++) {
				int age = 1;
				int x = Math_methods.randomWithRange(0, tailleGrille-1);
				int y = Math_methods.randomWithRange(0, tailleGrille-1);
				Sexe sexe;
				if(Math_methods.randomWithRange(1,2) == 1) sexe = Sexe.femelle;
				else sexe = Sexe.male;
				int pv = 30;
				int capacite_maximale_de_deplacement = 3;
				int duree_de_vie = 100;
				int force_de_combat = 5;
				Case c;
				
				
				if(ini.get("Tigre_"+i, "present", int.class)==1) {
					
					int new_pv = ini.get("Tigre_"+i, "pv", int.class);
					if(new_pv > 1 || new_pv < 99)
						pv = new_pv;
					
					String new_sexe = ini.get("Tigre_"+i, "sexe");
					if(new_sexe.toLowerCase() == "male")
						sexe = Sexe.male;
					else if(new_sexe.toLowerCase() == "femelle")
						sexe = Sexe.femelle;
					
					int new_x = ini.get("Tigre_"+i, "x", int.class);
					if(new_x >= 0 || new_x < tailleGrille)
						x = new_x;
					
					int new_y = ini.get("Tigre_"+i, "y", int.class);
					if(new_y >= 0 || new_y < tailleGrille)
						y = new_y;
					
					int new_capacite_maximale_de_deplacement = ini.get("Bos_taurus_"+i, "capacite_maximale_de_deplacement", int.class);
					if(new_capacite_maximale_de_deplacement > 0 || new_capacite_maximale_de_deplacement < Math.min(tailleGrille/2, 5))
						capacite_maximale_de_deplacement = new_capacite_maximale_de_deplacement;
					
					int new_duree_de_vie = ini.get("Bos_taurus_"+i, "duree_de_vie", int.class);
					if(new_duree_de_vie > 10 || new_duree_de_vie < 1000)
						duree_de_vie = new_duree_de_vie;
					
					int new_age = ini.get("Bos_taurus_"+i, "age", int.class);
					if(new_age > 0 || new_age < duree_de_vie)
						age = new_age;
					
					int new_force_de_combat = ini.get("Bos_taurus_"+i, "force_combat", int.class);
					if(new_force_de_combat > 0 || new_force_de_combat < pv)
						force_de_combat = new_force_de_combat;	
				}
				
				do {
					c = Grille.getinstance().get_case(x, y);
					x = Math_methods.randomWithRange(0, tailleGrille-1);
					y = Math_methods.randomWithRange(0, tailleGrille-1);
				} while(c.getEc() != EtatCase.libre);
				
				try {
					Tigre bt = new Tigre(sexe, pv, c, capacite_maximale_de_deplacement, age, duree_de_vie, force_de_combat);
					list_carnivore.add(bt);
					Grille.getinstance().setEtat(EtatCase.consommateur, c);
				} catch(Exception e){
					System.out.println(e);
				}
			}
	        operationnel = true;
	        return no_problem;
		} catch (IOException e) {
			return -1;
		}        
	}
	
	/**
	 * Mise à Zéro de la grille (normalement)
	 */
	private static void raz() {
		cycle = 0;

		seuil_faim = 70;
		seuil_famine = 25;
		operationnel = false;
		tailleGrille = 30;
		grille = Grille.getinstance();
		list_fabrique_vegetaux = new ArrayList<Fabrique_de_Vegetaux>(1000);
		list_aliment = new ArrayList<Aliment>(1000);
		list_herbivore = new ArrayList<Herbivore>(1000);
		list_carnivore = new ArrayList<Carnivore>(1000);
		
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
		c.getEmplacement().setEc(EtatCase.consommateur);
		list_carnivore.add(c);
		return 1;
	}
	
	
	/**
	 * Supprime un Carnivore à la liste
	 * @param c Carnivore à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public boolean supprime_carnivore(Carnivore c) {
		c.getEmplacement().setEc(EtatCase.libre);
		return list_carnivore.remove(c);
	}
	
	/**
	 * Rajoute un Herbivore à la liste
	 * @param h Herbivore à rajouter
	 * @return 1 si ok, 0 si erreur
	 */
	public int ajout_herbivore( Herbivore h) {
		h.getEmplacement().setEc(EtatCase.consommateur);
		list_herbivore.add(h);
		return 1;
	}
	
	/**
	 * Supprime un Herbivore à la liste
	 * @param h Herbivore à supprimer
	 * @return 1 si ok, 0 si erreur
	 */
	public boolean supprime_herbivore(Herbivore h) {
		h.getEmplacement().setEc(EtatCase.consommateur);
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