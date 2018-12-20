package consommateur;

import aliment.Aliment;

public class Vegetarien extends Consommateur {
	
	public Vegetarien(Sexe s, int v) {
		super(s, v);
		// TODO Auto-generated constructor stub
	}

	public void se_defendre() {}

	@Override
	public void manger(Aliment a) {
		if(a.getClass().toString() == "Vegetaux") 
			System.out.println("todo"); //TODO
		else 
			System.out.println("Error : can't eat");
		
	}

}
