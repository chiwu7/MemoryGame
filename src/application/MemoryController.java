package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MemoryController {
	
	@FXML
	private GridPane board;
	
	@FXML
	private ComboBox<String> level;
	
	@FXML
	private Label score, timer;
	
	private GraphicsContext gc;
	
	private List<Cards> cards;
	
	private List<Cards> selectedCards;
	
	private Image [] tabImg = {Constants.img1, Constants.img2, Constants.img3, Constants.img4, Constants.img5, Constants.img6};
	
	@FXML 
	public void initialize() {

		//level.setItems(FXCollections.observableArrayList("Dur", "Facile"));
		cards = new ArrayList<>();
		selectedCards = new ArrayList<>();
		
		for(int i = 0; i<6; i++) {
			Image img = tabImg[i];
			Cards card1 = new Cards(img, i);
			Cards card2 = new Cards(img, i);
			cards.add(card1);
			cards.add(card2);
		}
		
		Collections.shuffle(cards);
		
		setupBoard();
	    drawBoard();

	}

	private void setupBoard() {
		board.getColumnConstraints().clear();
		board.getRowConstraints().clear();
		
		for (int i = 0; i <= 2; i++) {
	        ColumnConstraints column = new ColumnConstraints();
	        column.setPercentWidth(100.0 / 2);
	        board.getColumnConstraints().add(column);
	    }
	    for (int i = 0; i <= 3; i++) {
	        RowConstraints row = new RowConstraints();
	        row.setPercentHeight(100.0 / 3);
	        board.getRowConstraints().add(row);
	    }
	}

	private void drawBoard() {
		double availableWidth = board.getWidth() - Constants.SPACING * 4;
	    double availableHeight = board.getHeight() - Constants.SPACING * 4;
	    double aspectRatio = 3.0 / 4.0; // ratio largeur / hauteur des cartes

	    // Calcule la taille maximale possible des cartes
	    double maxWidth = availableWidth / 3.0;
	    double maxHeight = availableHeight / 4.0;

	    // Calcule la taille optimale des cartes en conservant leur ratio
	    double cardWidth = Math.min(maxWidth, maxHeight / aspectRatio);
	    double cardHeight = cardWidth * aspectRatio;

	    // Affiche les cartes avec leur taille optimale
	    double spacing = Constants.SPACING;
	    double x = spacing;
	    double y = spacing;
	    for (Cards card : observableCards) {
	        gc.drawImage(card.getbackImg(), x, y, cardWidth, cardHeight);
	        x += cardWidth +spacing;
	        if (x + cardWidth > board.getWidth()) {
	            x = spacing;
	            y += cardHeight + spacing;
	        }
	        board.setOnMouseClicked(e -> handleClick(e, card));
	    }
	}
	
	private void handleClick(MouseEvent e, Cards card) {
		System.out.println("test");
	}

	@FXML 
	public void init() {
		
	}
	
	@FXML
	public void run() {
		
	}
	
	@FXML
	public void reset() {
		
	}
	
}
