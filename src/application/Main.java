package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene; // Guardando a referencia nesse atributo

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); // manipular a tela antes
																								// de carregar
			ScrollPane scrollPane = loader.load(); // Carrega a view

			scrollPane.setFitToHeight(true); // Para ajustar a barra do menubar
			scrollPane.setFitToWidth(true); // no aplicativo

			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene); // setando a cena principal
			primaryStage.setTitle("Sample JavaFX application"); // Definindo um título para o palco
			primaryStage.show(); // mostrando

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para pegar a referência
	public static Scene getMainScene() {
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
