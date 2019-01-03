package consommateur;

import aliment.Aliment;
import zone42.Case;

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

	@Override
	public int se_deplacer(Case c) {
		// TODO Auto-generated method stub
		return 0;
	}

}
