package aliment;

import zone42.Case;

public class Plante extends Vegetaux {

	public static int propriete_nutritive = 4;
	public static int temps_decomposition = 12;
	
	public Plante(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}

	public static void setProprieteNutritive(int a) {
		propriete_nutritive = a;
	}

	public static void setTempsDecomposition(int a) {
		temps_decomposition = a;
	}

}
