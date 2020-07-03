package gui.util;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		// Acessando o Stage onde o controle está
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
}
