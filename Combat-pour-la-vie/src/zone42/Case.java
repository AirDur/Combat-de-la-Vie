package zone42;

/**
 * Une case de la classe Grille
 * @see Grille
 * @author Romain Duret
 *
 */
public class Case {

	private int val_x;
	private int val_y;

	/**
	 * Constructeur
	 * 
	 * @param a Abcisse
	 * @param b	Ordonnée
	 */
	public Case(int a, int b) {
		val_x = a;
		val_y = b;
	}
}
