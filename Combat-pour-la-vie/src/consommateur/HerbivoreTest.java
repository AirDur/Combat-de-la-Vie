package consommateur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aliment.*;
import zone42.Case;

class HerbivoreTest {

	@Test
	void testManger() {
		Herbivore h = new Chevreuil(Sexe.femelle, 9, new Case(9,0));
		assertEquals(true, h.manger(new Foin(new Case(8,0))));
		assertEquals(false, h.manger(new Cadavre(new Case(8,0))));
	}

}
