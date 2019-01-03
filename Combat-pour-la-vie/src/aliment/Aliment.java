package aliment;

import zone42.Case;

/**
 * 
 * @author Romain Duret
 *
 */
public class Aliment {
	
	/**
	 * 
	 */
	private Integer propriete_nutritive;
	
	private Integer temps_avant_decomposition;
	
	/**
	 * Emplacement de l'aliment sur la grille.
	 */
	private Case emplacement;
	
	public Aliment() {
		setPropriete_nutritive(0);
		setTemps_avant_decomposition(0);
	}
	
	public Aliment(int valeur, int valeur2) 
	{
		setPropriete_nutritive(valeur);
		setTemps_avant_decomposition(valeur);
	}

	public Integer getPropriete_nutritive() {
		return propriete_nutritive;
	}

	public void setPropriete_nutritive(Integer propriete_nutritive) {
		this.propriete_nutritive = propriete_nutritive;
	}
	
	public Integer getTemps_avant_decomposition() {
		return temps_avant_decomposition;
	}

	public void setTemps_avant_decomposition(Integer temps_avant_decomposition) {
		this.temps_avant_decomposition = temps_avant_decomposition;
	}

}
