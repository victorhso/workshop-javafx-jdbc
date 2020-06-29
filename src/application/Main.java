package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); //manipular a tela antes de carregar
			Parent parent = loader.load();  //Carrega a view
			Scene mainScene = new Scene(parent); 
			primaryStage.setScene(mainScene); //setando a cena principal
			primaryStage.setTitle("Sample JavaFX application"); //Definindo um t�tulo para o palco
			primaryStage.show(); //mostrando
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
