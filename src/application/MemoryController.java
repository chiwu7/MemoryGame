package application;

import java.lang.Thread;
import java.lang.reflect.Array;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.logging.Handler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

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
	
	private int pairFound; 
	
	private int numberPair;
	
	private int updateScore;
	private Timeline tm = null;
	private int tempsRestants;
	
	@FXML 
	public void initialize() {
		tempsRestants = 3;
		cards = new ArrayList<>();
		selectedCards = new ArrayList<>();
    	selectedCards.clear();
		updateScore = 0;
		for(int i = 0; i<6; i++) {
			Image img = tabImg[i];
			Cards card1 = new Cards(img, i);
			Cards card2 = new Cards(img, i);
			cards.add(card1);
			cards.add(card2);
		}
		numberPair = cards.size() / 2;
		Collections.shuffle(cards);
		setupBoard();
	    drawBoard();
	    
	}
	/*
	 * Met au bon formatage le gridPane
	 */
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

	/*
	 * Affiche les images sur le plateau
	 */
	private void drawBoard() {
		 board.getChildren().clear(); // Vide le GridPane avant de redessiner

		    int rowIndex = 0;
		    int colIndex = 0;
		    setLabelScore();
		    // Parcourt la liste de cartes et crée une ImageView pour chacune
		    for (Cards card : cards) {
		        ImageView imageView = new ImageView();
	            imageView.setFitWidth(150);
	            imageView.setFitHeight(150);
		        // Si la carte est face cachée, utilise l'image du dos de la carte et on l'a rend cliquable
		        if (!card.isTurn()) {
		            imageView.setImage(card.getbackImg());
		            imageView.setOnMouseClicked(event -> handleClick(card));
		        // Si la carte est tournée et n'a pas trouvé sa paire, on utilise l'image de face
		        } else if (card.isTurn() && !card.isMatch()){   
		            imageView.setImage(card.getImg());
		        // Si la carte a trouvé sa paire, on utilise l'image de face et on grise l'image
		        } else if (card.isMatch()){
		            imageView.setImage(card.getImg());
		            imageView.setOpacity(0.5);
		        }

		        // Ajoute l'ImageView au GridPane
		        board.add(imageView, colIndex, rowIndex);

		        // Incrémente les indices de colonne et de ligne
		        colIndex++;
		        if (colIndex >= 3) {
		            colIndex = 0;
		            rowIndex++;
		        }
		        
		        
		    }
	}
	
	/*
	 * Gestion des clics sur les cartes
	 */
	private void handleClick(Cards card) {
		if (!card.isTurn()) {
			// retourne la carte
	        card.turn(); 
	        System.out.println("test");
	        // Modifie la carte dans la liste
	        int index = cards.indexOf(card);
	        cards.set(index, card);
	        //On redessinne le plateau
	        drawBoard();
	        //On ajoute la carte dans la liste des cartes sélectionnées
	        selectedCards.add(card);
	        
	        //Si la liste des cartes sélectionné est égale à deux on effectue le traitement
	        //Pour savoir si les deux cartes sélectionnés sont paires ou non
	        if (selectedCards.size() == 2) {
	        	Cards firstCard = selectedCards.get(0);
	            Cards secondCard = selectedCards.get(1);
	        	if (firstCard.getImg().equals(secondCard.getImg())) {
	        		firstCard.match();
	        		secondCard.match();
	        		int indexFirstCard = cards.indexOf(firstCard);
	        		int indexSecondCard = cards.indexOf(secondCard);
	        		cards.set(indexFirstCard, firstCard);
	        		cards.set(indexSecondCard, secondCard);
	        		//On pense à mettre à jour le score
	        		updateScore += 10;
	        		pairFound++;
	        		if (pairFound == numberPair)
	        			drawBoard();
	        	} else {
	                 firstCard.hide();
	                 secondCard.hide();
	                 int indexFirstCard = cards.indexOf(firstCard);
	                 int indexSecondCard = cards.indexOf(secondCard);
	                 cards.set(indexFirstCard, firstCard);
	                 cards.set(indexSecondCard, secondCard);
	                 //On met à jour le score si une paire n'est pas trouvé
	                 updateScore -= 1;
	        	}
	        		selectedCards.clear();
	        } 
	    }
		
		
	}

	@FXML 
	public void init() {

	}
	
	@FXML
	public void run() {
		Timer();
	}
	
	@FXML
	public void reset() {
		initialize();
	}
	
	/*
	 * Met à jour le label du score
	 */
	public void setLabelScore() {
		score.setText("score : " + updateScore);
	}
	
	public void Timer() {
		tm = new Timeline(new KeyFrame(Duration.seconds(1), ae -> setTime()));
		tm.setCycleCount(Animation.INDEFINITE);
		tm.play();
	}
	
	public void setTime() {
		tempsRestants--;
		timer.setText("Timer : " + tempsRestants +"s");
		if (tempsRestants == 0) {
			tm.stop();
			//On attend la fin de l'animation d'affichage
			Platform.runLater(() -> {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("MemoryGame");
	            alert.setHeaderText("Results :");
	            alert.setContentText("Perdu");
	            alert.setOnCloseRequest(event -> {
	                initialize();
	            });
	            alert.showAndWait();
	        });
		}

	}
	
}
