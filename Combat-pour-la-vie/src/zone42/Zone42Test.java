package zone42;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Zone42Test {

	@Test
	void test() throws InterruptedException {
		Zone42 zone = new Zone42(10);
		Thread le_thread = new Thread(zone,"Cycle");
		
		//le_thread.start();
		//le_thread.join(20000);
		
		int i=0;
		while(true) {
			System.out.println(i++);
			Thread.sleep(1000);
		}
	}

}
