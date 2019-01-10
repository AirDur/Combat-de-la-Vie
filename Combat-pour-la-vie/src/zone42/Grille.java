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
				tab[i][j].setEc(EtatCase.libre);
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
		String chaine = "Tableau de taille " + x + "*" + y + " : \n";
		for(int i = 0; i < x; i++) {
			chaine = chaine + "[ ";
			for(int j = 0; j < x; j++) {
				EtatCase ec = tab[i][j].getEc();
				switch(ec) {
				case libre: chaine = chaine + ".";
					break;
				case fabriqueVegetaux: chaine = chaine + "f";
					break;
				case vegetal: chaine = chaine + "v";
					break;
				default: chaine = chaine + "X";
					break; 
				}
			}
			chaine = chaine + " ]\n";
		}
		return chaine;
	}

	public EtatCase getEtat(Case c) {
		return c.getEc();
	}
	
	public EtatCase getEtat(int x, int y) {
		return tab[x-1][y-1].getEc();
	}
	
	public void setEtat(EtatCase ec, int x, int y) {
		tab[x-1][y-1].setEc(ec);
	}
	
	public void setEtat(EtatCase ec, Case c) {
		System.out.println("test - : " + tab.length + " / " + tab[0].length);
		tab[c.getVal_x()][c.getVal_y()].setEc(ec);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
