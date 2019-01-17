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
	private EtatCase ec;

	/**
	 * Constructeur
	 * 
	 * @param a Abcisse
	 * @param b	Ordonnée
	 */
	public Case(int a, int b) {
		setVal_x(a);
		setVal_y(b);
		setEc(EtatCase.libre);
	}

	public int getVal_x() {
		return val_x;
	}

	public void setVal_x(int val_x) {
		this.val_x = val_x;
	}

	public int getVal_y() {
		return val_y;
	}

	public void setVal_y(int val_y) {
		this.val_y = val_y;
	}

	public EtatCase getEc() {
		return ec;
	}

	public void setEc(EtatCase ec) {
		this.ec = ec;
	}

	/**
	 * renvoi la distance de déplacement entre deux cases
	 * @param emplacement 
	 * @return distance de déplacement entre deux cases
	 */
	public Integer distance(Case emplacement) {
		return Math.abs(this.val_x - emplacement.val_x) + Math.abs(this.val_y - emplacement.val_y);
	}
	
	public String toString() {
		return "Case [" + val_x + ", " + val_y + "] - état : "+ ec;
	}
}
