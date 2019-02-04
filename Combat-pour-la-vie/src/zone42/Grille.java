package zone42;

import java.util.ArrayList;

/**
 * Espace où les differents entités peuvent se mouvoir.
 * 
 * @author Romain Duret
 *
 */
public class Grille {

	/**
	 * Nombre de colonne
	 */
	private Integer x;
	/**
	 * Nombre de ligne
	 */
	private Integer y;
	/**
	 * Création des cases.
	 */
	private Case[][] tab;
	
	/**
	 * attribut pour design pattern Singleton
	 * Instancié à null (pas d'instance créé)
	 */
	public static Grille instance = null;
	
	/**
	 * Constructeur implémenté par le design pattern Singleton
	 * @param a nombre de colonne
	 * @param b nombre de ligne
	 */
	private Grille(int a, int b) {
		x = a;
		y = b;
		tab = new Case[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < x; j++) {
				tab[i][j] = new Case(i, j);
				tab[i][j].setEc(EtatCase.libre);
			}
		}
	}
	
	/**
	 * Récupère l'instance Grille ou en créé une s'il y en a toujours pas.
	 * @param a nombre de colonne
	 * @param b nombre de ligne
	 * @return une nouvelle grille si instance==null, sinon LA grille.
	 */
	public static Grille getinstance(int a, int b) {
		if (instance == null) instance = new Grille(a,b);
		return instance;
	}
	
	public static Grille getinstance() {
		if (instance == null) instance = new Grille(30, 30);
		return instance;
	}
	
	public static Grille changeInstance(Integer tailleGrille, Integer tailleGrille2) {
		if(instance != null) instance = new Grille(tailleGrille, tailleGrille2);
		else return getinstance(tailleGrille, tailleGrille2);
		return instance;
	}
	
	/**
	 * Affichage de la grille 
	 * @return un string
	 */
	public String toString() {
		String chaine = "Tableau de taille " + x + "*" + y + " : \n";
		for(int j = 0; j < x; j++) {
			chaine = chaine + "[ ";
			for(int i = 0; i < x; i++) {
				EtatCase ec = tab[i][j].getEc();
				switch(ec) {
				case libre: chaine = chaine + ".";
					break;
				case fabriqueVegetaux: chaine = chaine + "f";
					break;
				case vegetal: chaine = chaine + "v";
					break;
				case consommateur: chaine = chaine + "i";
					break;
				default: chaine = chaine + "X";
					break; 
				}
			}
			chaine = chaine + " ]\n";
		}
		return chaine;
	}

	/**
	 * Retourne toutes les cases libres autour de emplacement
	 * @param emplacement une Case
	 * @param r rayon de toutes les cases libres demandés.
	 * @return un arryalist de case libre
	 */
	public ArrayList<Case> getCaseAutour(Case emplacement, int r) {
		ArrayList<Case> al = new ArrayList<Case>();
		
		int x_emplacement = emplacement.getVal_x();
		int y_emplacement = emplacement.getVal_y();
		int i, j;
		
		for(i = x_emplacement , j = y_emplacement-r ; i > -r+x_emplacement ; i--, j++) {
			if(i>=0 && i<getX() && j>=0 && j<getY() && getEtat(i,j) == EtatCase.libre){
					al.add(tab[i][j]);
			}
		}
		
		for(i = x_emplacement-r , j = y_emplacement ; i < x_emplacement ; i++, j++) {
			if(i>=0 && i<getX() && j>=0 && j<getY() && getEtat(i,j) == EtatCase.libre){
				al.add(tab[i][j]);
			}
		}
		for(i = x_emplacement ,j = y_emplacement+r ;i < r+x_emplacement ; i++, j--) {
			if(i>=0 && i<getX() && j>=0 && j<getY() && getEtat(i,j) == EtatCase.libre){
				al.add(tab[i][j]);
			}
		}
		
		for(i = r+x_emplacement ,j = y_emplacement ;i > x_emplacement ;i--,j--) {
			if(i>=0 && i<getX() && j>=0 && j<getY() && getEtat(i,j) == EtatCase.libre){
				al.add(tab[i][j]);
			}
		}
		
		if(r>1) al.addAll(this.getCaseAutour(emplacement, r-1));
		return al;
	}

	public Case getCaseProche(Case emplacement) {
		int emp_x, emp_y;
		
		Case c_ret=null;
		emp_x=emplacement.getVal_x();
		emp_y=emplacement.getVal_y();
		
		int i = emp_x-1; 
		while(i<=emp_x+1 && c_ret==null){
			int j = emp_y-1; 
			while(j<=emp_y+1 && c_ret==null){
				if( this.est_dans_grille(i, j)) {
					if(this.getEtat(this.get_case(i, j))==EtatCase.libre) {
						c_ret= this.get_case(i, j);
					}
				}
				j++;
			}
			i++;
		}
		return c_ret;
	}
	
	/*
	Case getEmplacementLibre(int r, Case c_fabrique) {
		
		int x,y;
		//int r=2;
		
		int x_emplacement = c_fabrique.getVal_x();
		int y_emplacement = c_fabrique.getVal_y();
		System.out.println("x_emplacement = "+x_emplacement+" / y_emplacement = "+y_emplacement);
		
		
		for(x=x_emplacement ,y=y_emplacement-r ;x>-r+x_emplacement ;x--,y++) {
			if(x>=0 && x<this.getX() && y>=0 && y<g.getY() ) {
				System.out.println("Case ("+x+","+y+")");
				if( g.getEtat(x,y) == EtatCase.libre){
					return new Case(x,y);
				}
			}
		}
		
		for(x=x_emplacement-r ,y=y_emplacement ;x<x_emplacement ;x++,y++) {
			if(x>=0 && x<g.getX() && y>=0 && y<g.getY() ) {
				System.out.println("Case ("+x+","+y+")");
				if( g.getEtat(x,y) == EtatCase.libre){
					return new Case(x,y);
				}
			}
		}
		//
		for(x=x_emplacement ,y=y_emplacement+r ;x<r+x_emplacement ;x++,y--) {
			if(x>=0 && x<g.getX() && y>=0 && y<g.getY() ) {
				System.out.println("Case ("+x+","+y+")");
				if( g.getEtat(x,y) == EtatCase.libre){
					return new Case(x,y);
				}
			}
		}
		
		for(x=r+x_emplacement ,y=y_emplacement ;x>x_emplacement ;x--,y--) {
			if(x>=0 && x<g.getX() && y>=0 && y<g.getY() ) {
				System.out.println("Case ("+x+","+y+")");
				if( g.getEtat(x,y) == EtatCase.libre){
					return new Case(x,y);
				}
			}
		}
		
		if(r>1) return getEmplacementLibre(r-1, c_fabrique);
		return null;
		
	}*/
	
	public boolean est_dans_grille(int v_x, int v_y) {
		boolean ret=true;
		
		if( v_x >= x || v_y>= y) ret=false;
		if( v_x<0 || v_y<0 ) ret =false;
		
		return ret;
	}
	
	public Case case_sud(Case c) {
		int c_x = c.getVal_x();
		int c_y = c.getVal_y();
		
		if( c_x<x && c_x>=0 && c_y+1<y && c_y+1>=0 )
			return tab[c_x][c_y+1];
		else
			return null;
	}
	
	public Case case_est(Case c) {
		int c_x = c.getVal_x();
		int c_y = c.getVal_y();
		
		if( c_x+1<x && c_x+1>=0 && c_y<y && c_y>=0 )
			return tab[c_x+1][c_y];
		else
			return null;
	}
	
	public Case case_ouest(Case c) {
		int c_x = c.getVal_x();
		int c_y = c.getVal_y();
		
		if( c_x-1<x && c_x-1>=0 && c_y<y && c_y>=0 )
			return tab[c_x-1][c_y];
		else
			return null;
	}
	
	public Case case_nord(Case c) {
		int c_x = c.getVal_x();
		int c_y = c.getVal_y();
		
		if( c_x<x && c_x>=0 && c_y-1<y && c_y-1>=0 )
			return tab[c_x][c_y-1];
		else
			return null;
	}
	
	public Case get_case(int x, int y) {
		return tab[x][y];
	}
	
	public EtatCase getEtat(Case c) {
		return c.getEc();
	}
	
	public EtatCase getEtat(int x, int y) {
		return tab[x][y].getEc();
	}
	
	public void setEtat(EtatCase ec, int x, int y) {
		tab[x][y].setEc(ec);
	}
	
	public void setEtat(EtatCase ec, Case c) {
		tab[c.getVal_x()][c.getVal_y()].setEc(ec);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
