package aliment;

import zone42.Case;

/**
 * Classe Cadavre (aliment pour carnivore)
 * 
 * @author Romain Duret
 * @see Carnivore, Aliment
 */
public class Cadavre extends Aliment {

	public static final int propriete_nutritive = 25;
	public static final int temps_decomposition = 30;
	
	public Cadavre(Case emplacement) {
		super(propriete_nutritive, temps_decomposition, emplacement);
	}
	
}
