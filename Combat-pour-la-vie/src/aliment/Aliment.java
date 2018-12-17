package aliment;

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
	
	public Aliment(int valeur) 
	{
		setPropriete_nutritive(valeur);
	}

	public Integer getPropriete_nutritive() {
		return propriete_nutritive;
	}

	public void setPropriete_nutritive(Integer propriete_nutritive) {
		this.propriete_nutritive = propriete_nutritive;
	}
}
