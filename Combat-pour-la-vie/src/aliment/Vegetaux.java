package aliment;


public class Vegetaux extends Aliment{
	
	private Integer quantite_de_production;
	private Integer cycle_de_production;
	
	public Vegetaux(int valeur, int valeur2) {
		super(valeur, valeur2);
	}

	public Integer getQuantite_de_production() {
		return quantite_de_production;
	}

	public void setQuantite_de_production(Integer quantite_de_production) {
		this.quantite_de_production = quantite_de_production;
	}

	public Integer getCycle_de_production() {
		return cycle_de_production;
	}

	public void setCycle_de_production(Integer cycle_de_production) {
		this.cycle_de_production = cycle_de_production;
	}
}
