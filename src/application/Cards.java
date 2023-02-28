package application;

import javafx.scene.image.Image;

public class Cards {

	private String img;
	private int id;
	private boolean turn;
	
	/*
	 * Crée un nouveau objet de la classe "Cards"
	 * @parem path : le chemin de l'image associée à la carte
	 * @parem id : l'identifiant de la carte
	 */
	public Cards(String path, int id) {
		this.img = path;
		this.id = id;
		turn = false;
	}
	
	/*
	 * Retourne le chemin de l'image associée à la carte
	 * @return le chemin de l'image associée à la carte
	 */
	public String getImg() {
		return img;
	}
	
	/*
	 * Vérifie si la carte est retournée ou non
	 * @return true si la carte est retournée, sinon false
	 */
	public boolean isTurn() {
		return turn;
	}
	
	/*
	 * Retourne la carte pour la rendre visible
	 */
	public void turn() {
		turn = true;
	}
	
	/*
	 * Cache la carte pour la rendre invisible
	 */
	public void hide() {
		turn = false;
	}
	
	/*
	 * Retourne l'id de la carte
	 * @return l'id de la carte
	 */
	public int getId() {
		return id;
	}
		
}
