package consommateur;

import aliment.Aliment;

public class Carnivore extends Consommateur {
	
	public Carnivore(Sexe s, int v) {
		super(s, v);
		// TODO Auto-generated constructor stub
	}
	
	public void combattre(Consommateur c) {}
	public void se_reproduit() {}
	
	@Override
	public void manger(Aliment a) {
		// TODO Auto-generated method stub
		
	}
}
