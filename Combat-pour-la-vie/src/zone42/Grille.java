package zone42;

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
	
	public static Grille instance =null;
	public static Grille getinstance(int a, int b) {
		if (instance==null) instance = new Grille(a,b);
		return instance;
	}
	
	public String toString() {
		String chaine="x="+x+"y="+y;
		
		return chaine;
	}
	
	public static void main(String [] args) {
		Grille lagrille = Grille.getinstance(5, 5);
		Grille lagrille2 = Grille.getinstance(1, 2);
		
		System.out.println(lagrille2);
	}
}
