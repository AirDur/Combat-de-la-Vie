package aliment;

import zone42.Case;

public class Herbe extends Vegetaux {

	public static final int propriete_nutritive = 5;
	public static final int temps_decomposition = 20;
	
	public Herbe(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}

}
