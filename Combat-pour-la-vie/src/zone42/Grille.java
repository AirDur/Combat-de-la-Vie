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
	 * Constructeur
	 * @param a nombre de colonne
	 * @param b nombre de ligne
	 */
	public Grille(int a, int b) {
		x = a;
		y = b;
		tab = new Case[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				tab[i][j] = new Case(i, j);
			}
		}
	}
}
