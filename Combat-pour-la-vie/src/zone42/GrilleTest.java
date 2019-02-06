package zone42;

import org.junit.jupiter.api.Test;

class GrilleTest {

	@SuppressWarnings("unused")
	@Test
	void test() {
		Grille lagrille = Grille.getinstance(10, 10);
		Grille lagrille2 = Grille.getinstance(1, 2);
		
		System.out.println(lagrille2);
	}

}
