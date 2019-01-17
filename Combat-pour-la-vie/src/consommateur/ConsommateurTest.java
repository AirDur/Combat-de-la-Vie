package consommateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zone42.Case;
import zone42.Grille;

class ConsommateurTest {
	
	@Test
	void test_reproduction() {
		Consommateur c = new Loup(Sexe.femelle, 20, new Case(9, 11));
		
		Consommateur e = new Loup(Sexe.male, 20, new Case(9, 10));
		
		Consommateur f = c.se_reproduire(e);
	}
	
	@Test 
	void test_deplacement_aleatoire() {
		Grille g = Grille.getinstance(30, 30);
		Consommateur c = new Tigre(Sexe.male, 19, new Case(8, 2));
		System.out.println("Test / before : " + c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
		c.deplacement_aleatoire(2);
		System.out.println("Test / after : " +c.toString());
	}

}
