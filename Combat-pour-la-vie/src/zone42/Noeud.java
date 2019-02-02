package zone42;

public class Noeud {
	private Case c;
	public Noeud sud, nord, est, ouest, pere;
	
	public Noeud(Case lacase,Noeud p) {
		c=lacase;
		pere=p;
	}
	
	public Case get_case(){
		return c;
	}
	
	public void add_sud(Case c) {
		sud = new Noeud(c,this);
	}
	
	public void add_nord(Case c) {
		nord = new Noeud(c,this);
	}
	
	public void add_est(Case c) {
		est = new Noeud(c,this);
	}
	
	public void add_ouest(Case c) {
		ouest = new Noeud(c,this);
	}
	
	public Noeud get_sud(){
		return sud;
	}
	
	public Noeud get_est(){
		return est;
	}
	
	public Noeud get_ouest(){
		return ouest;
	}
	
	public Noeud get_nord(){
		return nord;
	}
	
	public Noeud get_pere() {
		return pere;
	}
}