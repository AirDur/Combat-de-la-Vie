package consommateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConsommateurTest {
	
	@Test
	void test_reproduction() {
		Consommateur c = new Loup(Sexe.femelle, 20);
		
		Consommateur e = new Loup(Sexe.male, 20);
		
		Consommateur f = c.se_reproduire(e);
	}

}
