package gui.util;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentStage(ActionEvent event) {
		// Acessando o Stage onde o controle est�
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
}
