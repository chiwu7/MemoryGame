package application;

import java.lang.reflect.Array;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MemoryController {
	
	@FXML
	private Canvas board;
	
	@FXML
	private ComboBox<String> level;
	
	@FXML
	private Label score, timer;
	
	private GraphicsContext gc;
	
	@FXML 
	public void initialize() {
		//intialisation du contexte graphique
		gc = board.getGraphicsContext2D();
		level.setItems(FXCollections.observableArrayList("Dur", "Facile"));
		view.drawBoard(gc, board);
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
