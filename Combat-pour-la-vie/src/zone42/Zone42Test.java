package zone42;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Zone42Test {

	@Test
	void test() throws InterruptedException {
		Zone42 zone = Zone42.getInstance();
		//Thread le_thread = new Thread(zone,"Cycle");
		
		//le_thread.start();
		//le_thread.join(20000);
		
		
		int i=0;
		while(i < 10) {
			System.out.println(i++);
			Thread.sleep(100);
		}
		System.out.print(zone.toString());
	}
	
	/*@Test
	void testGrille() throws InterruptedException {
		Zone42 zone2 = Zone42.getInstance(10);
		zone2.initialisation();
		Thread le_thread = new Thread(zone2,"Cycle");
		
		//le_thread.start();
		//le_thread.join(20000);
		
		int i=0;
		while(i < 10) {
			zone2.faire_passer_le_temps();
			System.out.print(zone2.toString());
			Thread.sleep(1000);
		}
	}*/
	
	@Test
	void testChemin( ) {
		Zone42 zone = Zone42.getInstance();
		Grille gr = zone.getGrille_info();
		Astar my_a = new Astar(gr, gr.get_case(0, 0), gr.get_case(5, 5));
		ArrayList<Case> chemin = my_a.get_chemin();
		System.out.println(chemin);
	}
	
}
