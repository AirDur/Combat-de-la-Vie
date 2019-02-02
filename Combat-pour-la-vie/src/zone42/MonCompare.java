package zone42;

import java.util.Comparator;

public class MonCompare implements Comparator<Case>{

	@Override
	public int compare(Case arg0, Case arg1) {
		
		return arg0.getValH()-arg1.getValH();
	}

}
