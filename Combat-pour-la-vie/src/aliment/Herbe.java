package aliment;

import zone42.Case;

public class Herbe extends Vegetaux {

	private static int propriete_nutritive = 5;
	private static int temps_decomposition = 20;
	
	public Herbe(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}
	
	public static void setProprieteNutritive(int a) {
		propriete_nutritive = a;
	}

	public static void setTempsDecomposition(int a) {
		temps_decomposition = a;
	}

}
