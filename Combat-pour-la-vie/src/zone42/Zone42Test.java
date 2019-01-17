package zone42;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Zone42Test {

	@Test
	void test() throws InterruptedException {
		Zone42 zone = Zone42.getInstance(10);
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
	
	@Test
	void testGrille() throws InterruptedException {
		Zone42 zone2 = Zone42.getInstance(10);
		zone2.initialisation();
		Thread le_thread = new Thread(zone2,"Cycle");
		
		//le_thread.start();
		//le_thread.join(20000);
		
		int i=0;
		while(i < 1000) {
			zone2.faire_passer_le_temps();
			System.out.print(zone2.toString());
			Thread.sleep(1000);
		}
	}
	
}
