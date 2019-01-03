package zone42;

/**
 * Interface pour implémenter une foction qui permet de déplacer les entités.
 * 
 * @author Romain Duret
 *
 */
public interface Deplacable {

	public Case emplacement = new Case();
	
	/**
	 * Déplace l'entité vers la nouvelle case.
	 * @return 0 si erreur, 1 si déplacement ok.
	 */
	public int se_deplacer(Case c);
	
}
