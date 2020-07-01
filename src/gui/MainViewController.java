package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	// Os tr�s atributos do item de menu
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	// Os tr�s m�todos para tratare o evento do menu
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	// Fun��o para abrir uma outra tela e garante que o processamento n�o seja
	// interrompido (synchronized)
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			// Criando refer�ncia para a cena
			Scene mainScene = Main.getMainScene();
			// Pego uma refer�ncia para o VBOx que est� na nossa janela principal
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // Vai pegar o primeiro elemento da
																					// view.

			Node mainMenu = mainVBox.getChildren().get(0); // primeiro filho do VBox na janela principal = MainMenu
			mainVBox.getChildren().clear(); // limpando todos os filhos do mainVBox

			// Incluindo os filhos da janela que estiver abrindo
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro carregando p�gina", e.getMessage(), AlertType.ERROR);
		}
	}
}
