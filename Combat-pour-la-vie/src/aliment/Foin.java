package aliment;

import zone42.Case;

public class Foin extends Vegetaux {

	public static final int propriete_nutritive = 5;
	public static final int temps_decomposition = 25;
	
	public Foin(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}

}
