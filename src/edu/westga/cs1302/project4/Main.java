package edu.westga.cs1302.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Main Class for the application
 * 
 * @author jeremy.trimble
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view//PhotoScrollerGUI.fxml"));
			Pane root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("PhotoScroller By Jeremy Trimble");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launches the app
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
