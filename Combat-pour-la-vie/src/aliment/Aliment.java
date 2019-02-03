package aliment;

import zone42.Case;

/**
 * Ressource à consommer pour les consommateurs. 
 * 
 * @author Romain Duret
 */
public class Aliment {
	
	/**
	 * Capacité de satisfaire la faim des consommateurs
	 */
	private Integer propriete_nutritive;
	
	/**
	 * temps en cycle avant leurs disparitions
	 */
	private Integer temps_avant_decomposition;
	
	/**
	 * Emplacement de l'aliment sur la grille.
	 */
	private Case emplacement;
	
	private TypeAliment type;
	
	/**
	 * constructeur vide
	 */
	public Aliment() {
		setPropriete_nutritive(0);
		setTemps_avant_decomposition(0);
	}
	
	
	/**
	 * Constructeur simple
	 * @param valeur_nutritive Capacité de satisfaire la faim des consommateurs
	 * @param valeur_conservation temps en cycle avant disparition
	 * @param c emplacement de l'aliment
	 */
	public Aliment(int valeur_nutritive, int valeur_conservation,TypeAliment t, Case c) 
	{
		type=t;
		setPropriete_nutritive(valeur_nutritive);
		setTemps_avant_decomposition(valeur_conservation);
		setEmplacement(c);
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

	public Case getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Case emplacement) {
		this.emplacement = emplacement;
	}
	
	public TypeAliment getType() {
		return type;
	}

}
