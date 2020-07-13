package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;
import javafx.event.ActionEvent;

public class DepartmentListController implements Initializable {

	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnID;

	@FXML
	private TableColumn<Department, String> tableColumnName;

	@FXML
	private Button btNew;

	private ObservableList<Department> obslist;

	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	private void initializeNodes() {
		// Padrão JavaFX para iniciar o comportamento das colunas
		tableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Pegando referencia para o stage complementar a tela das colunas
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // Para fazer o tableview acomapanhar a
																				// altura da janela
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}

		List<Department> list = service.findAll();

		obslist = FXCollections.observableArrayList(list); // Instancia o observablelist pegando os dados originais da
															// lista normal.
		tableViewDepartment.setItems(obslist); // Carregar os itens na tableview e mostrar na tela
	}

	// Formulário para preencher um novo departamento
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(new DepartmentService()); //injeção de dependencia
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Digite os dados do departamento");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false); // Janela pode ou não ser redimensionada
			dialogStage.initOwner(parentStage); //
			dialogStage.initModality(Modality.WINDOW_MODAL); // Vai ser modal ou outro comportamento -> enquando não
																// fechá-la, não se pode acessar a janela anterior
			dialogStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IOException", "Error load view", e.getMessage(), AlertType.ERROR);
		}
	}
}
