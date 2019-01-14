package consommateur;

import aliment.Aliment;
import zone42.Case;
import zone42.Grille;

public class Carnivore extends Consommateur {
	
	public Carnivore(Sexe s, int v) {
		super(s, v);
		// TODO Auto-generated constructor stub
	}
	
	public void combattre(Consommateur c) {}
	public void se_reproduit() {}
	
	@Override
	public int manger(Aliment a) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	@Override
	public int se_deplacer(Case c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Aliment recherche_aliment(Grille g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consommateur se_reproduire(Consommateur c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consommateur recherche_reproducteur(Grille g) {
		// TODO Auto-generated method stub
		return null;
	}
}
