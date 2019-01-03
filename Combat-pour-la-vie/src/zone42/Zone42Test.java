package zone42;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Zone42Test {

	@Test
	void test() {
		Zone42 zone = new Zone42(10);
		Thread le_thread = new Thread(zone,"Cycle");
		
		le_thread.start();
	}

}
