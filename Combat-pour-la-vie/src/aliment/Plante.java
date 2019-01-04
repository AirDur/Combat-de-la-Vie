package aliment;

import zone42.Case;

public class Plante extends Vegetaux {

	public static final int propriete_nutritive = 4;
	public static final int temps_decomposition = 12;
	
	public Plante(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}

}
