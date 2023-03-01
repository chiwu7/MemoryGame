package application;

import javafx.scene.image.Image;


public class Cards {

	private Image img ;
	private Image backImg;
	private int id;
	private boolean turn;
	private boolean match;
	
	/*
	 * Crée un nouveau objet de la classe "Cards"
	 * @parem path : le chemin de l'image associée à la carte
	 * @parem id : l'identifiant de la carte
	 */
	public Cards(Image img, int id) {
		this.img = img;
		this.backImg = new Image("file:resources/canard.png", 240,240, false, false);;
		this.id = id;
		turn = false;
		match = false;
	}
	
	/*
	 * Retourne l'image associée à la carte
	 * @return l'image associée à la carte
	 */
	public Image getImg() {
		return img;
	}
	
	/*
	 * Retourne l'image associé au dos de la carte
	 * @return l'image associée au dos de la carte
	 */
	public Image getbackImg() {
		return backImg;
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
	
	public void match() {
		match = true;
	}
	
	public boolean isMatch() {
		return match;
	}
		
}
