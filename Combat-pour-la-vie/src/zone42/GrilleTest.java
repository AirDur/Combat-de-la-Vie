package zone42;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GrilleTest {

	@Test
	void test() {
		Grille lagrille = Grille.getinstance(10, 10);
		Grille lagrille2 = Grille.getinstance(1, 2);
		
		System.out.println(lagrille2);
	}

}
