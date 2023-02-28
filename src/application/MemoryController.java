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

public class MemoryController {
	
	@FXML
	private Canvas board;
	
	@FXML
	private ComboBox<String> level;
	
	@FXML
	private Label score, timer;
	
	private GraphicsContext gc;
	
	private List<Cards> cards;
	
	private List<Cards> selectedCards;
	
	private ObservableList<Cards> observableCards;

	private FilteredList<Cards> filteredCards;
	
	private Image [] tabImg = {Constants.img1, Constants.img2, Constants.img3, Constants.img4, Constants.img5, Constants.img6};
	
	@FXML 
	public void initialize() {
		//intialisation du contexte graphique
		gc = board.getGraphicsContext2D();
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
		
		//Création de la liste observable de cartes
		observableCards = FXCollections.observableArrayList(cards);
		
		//Création de la liste filtrée de cartes non sélectionnées
	    filteredCards = new FilteredList<>(observableCards, card -> !card.isTurn());

		//Ajout d'un écouteur sur la liste filtrée pour mettre à jour l'interface
	    filteredCards.addListener((Change<? extends Cards> change) -> {
	        // Mettre à jour l'interface ici
	    });
	    
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
	    double padding = Constants.SPACING;
	    double x = padding;
	    double y = padding;
	    for (Cards card : observableCards) {
	        gc.drawImage(card.getbackImg(), x, y, cardWidth, cardHeight);
	        x += cardWidth + padding;
	        if (x + cardWidth > board.getWidth()) {
	            x = padding;
	            y += cardHeight + padding;
	        }
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
