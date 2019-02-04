package aliment;

import zone42.Case;

public class Pomme extends Vegetaux {

	private static int propriete_nutritive = 3;
	private static int temps_decomposition = 8;
	
	public Pomme(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}
	
	public static void setProprieteNutritive(int a) {
		propriete_nutritive = a;
	}

	public static void setTempsDecomposition(int a) {
		temps_decomposition = a;
	}
}
