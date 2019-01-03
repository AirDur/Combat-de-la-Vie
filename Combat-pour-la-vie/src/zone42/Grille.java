package zone42;

/**
 * Espace où les differents entités peuvent se mouvoir.
 * 
 * @author Romain Duret
 *
 */
public class Grille {

	/**
	 * Nombre de colonne
	 */
	private Integer x;
	/**
	 * Nombre de ligne
	 */
	private Integer y;
	/**
	 * Création des cases.
	 */
	private Case[][] tab;
	
	/**
	 * attribut pour design pattern Singleton
	 * Instancié à null (pas d'instance créé)
	 */
	public static Grille instance = null;
	
	/**
	 * Constructeur implémenté par le design pattern Singleton
	 * @param a nombre de colonne
	 * @param b nombre de ligne
	 */
	private Grille(int a, int b) {
		x = a;
		y = b;
		tab = new Case[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				tab[i][j] = new Case(i, j);
			}
		}
	}
	
	/**
	 * Récupère l'instance Grille ou en créé une s'il y en a toujours pas.
	 * @param a nombre de colonne
	 * @param b nombre de ligne
	 * @return une nouvelle grille si instance==null, sinon LA grille.
	 */
	public static Grille getinstance(int a, int b) {
		if (instance == null) instance = new Grille(a,b);
		return instance;
	}
	
	/**
	 * Affichage de la grille
	 */
	public String toString() {
		String chaine = "";
		for(int i = 0; i < x; i++) {
			chaine = chaine + "[";
			for(int j = 0; j < x; j++) {
				chaine = chaine + " a ";
				tab[i][j] = new Case(i, j);
			}
			chaine = chaine + "]\n";
		}
		return chaine;
	}
	
	/**
	 * main pour tester le singleton
	 * @param args
	 */
	public static void main(String [] args) {
		
	}
}
