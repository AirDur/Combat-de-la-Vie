package consommateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aliment.Cadavre;
import aliment.Foin;
import zone42.Case;
import zone42.Grille;

class ConsommateurTest {
	
	@Test
	void test_reproduction() {
		
	}
	
	@Test 
	void test_deplacement_aleatoire() {
		//Grille g = Grille.getinstance(30, 30);
		//(Sexe s, int v, Case c, int cmc, int a, int ddv, int fc) 
		Consommateur c = new Tigre(Sexe.male, 19, Grille.getinstance().get_case(8, 2),10,10,10,10);
		System.out.println("Test / before : " + c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
	}
	
	@Test
	void testManger_Herbivore() {
		Herbivore h = new Chevreuil(Sexe.femelle, 9, Grille.getinstance().get_case(9, 0),10,10,10,10);
		assertEquals(true, h.manger(new Foin(new Case(8,0))));
		assertEquals(false, h.manger(new Cadavre(new Case(8,0))));
	}
	
	@Test
	void testManger_Carnivore() {
		Carnivore h = new Loup(Sexe.femelle, 9, Grille.getinstance().get_case(9, 0),10,10,10,10);
		assertEquals(false, h.manger(new Foin(new Case(8,0))));
		assertEquals(true, h.manger(new Cadavre(new Case(8,0))));
	}

}
