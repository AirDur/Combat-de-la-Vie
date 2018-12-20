package zone42;

public class Grille {

	private Integer x;
	private Integer y;
	private Case[][] tab;
	
	public Grille(int a, int b) {
		x = a;
		y = b;
		tab = new Case[x][y];
	}
}
