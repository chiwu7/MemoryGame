package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class view {
	
	static Image pomme = new Image("file:resources/pomme.jpg", 240, 240, false, false);
	private static final int CARD_WIDTH = 100;
	private static final int CARD_HEIGHT = 150;
	private static final int SPACING = 10;
	
	private view() {
		
	}
	
	public static void drawBoard(GraphicsContext gc, Canvas board) {
		
		double cardWidth = (board.getWidth() - SPACING * 4) / 3.0;
        double cardHeight = (board.getHeight() - SPACING * 4) / 3.0;
        
		for (int i = 0; i<16; i++) {
			
            // Calculer les coordonnées x et y de l'image
            int x = (i % 3) * (int)(cardWidth + SPACING) + SPACING;
            int y = (i / 3) * (int)(cardHeight + SPACING) + SPACING;

            // Dessiner l'image
            gc.drawImage(pomme, x, y, cardWidth, cardHeight);
		}
	}
	
}
