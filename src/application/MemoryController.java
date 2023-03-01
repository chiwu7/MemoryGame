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
import javafx.scene.image.ImageView;
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
		 board.getChildren().clear(); // Vide le GridPane avant de redessiner

		    int rowIndex = 0;
		    int colIndex = 0;

		    // Parcourt la liste de cartes et crée une ImageView pour chacune
		    for (Cards card : cards) {
		        ImageView imageView = new ImageView();

		        // Si la carte est face cachée, utilise l'image du dos de la carte
		        if (!card.isTurn()) {
		            imageView.setImage(card.getbackImg());
		            imageView.setFitWidth(100);
		            imageView.setFitHeight(100);
		        } else {
		            // Sinon, utilise l'image de la carte elle-même
		            imageView.setImage(card.getImg());
		            imageView.setFitWidth(100);
		            imageView.setFitHeight(100);
		        }

		        // Ajoute l'ImageView au GridPane
		        board.setGridLinesVisible(true);
		        board.add(imageView, colIndex, rowIndex);

		        // Incrémente les indices de colonne et de ligne
		        colIndex++;
		        if (colIndex >= 3) {
		            colIndex = 0;
		            rowIndex++;
		        }
		        
		        imageView.setOnMouseClicked(event -> handleClick(card));
		    }
	}
	
	private void handleClick(Cards card) {
		if (!card.isTurn()) {
	        card.turn(); // retourne la carte

	        // Modifie la carte dans la liste
	        int index = cards.indexOf(card);
	        cards.set(index, card);

	        // Redessine le plateau de jeu
	        drawBoard();
	    }
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
