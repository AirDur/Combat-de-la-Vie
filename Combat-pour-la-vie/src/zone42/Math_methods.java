package zone42;

public class Math_methods {

	public static int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random()*range) + min;
	}
}
