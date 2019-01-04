package aliment;

import zone42.Case;

public class Pomme extends Vegetaux {

	public static final int propriete_nutritive = 3;
	public static final int temps_decomposition = 8;
	
	public Pomme(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}
}
