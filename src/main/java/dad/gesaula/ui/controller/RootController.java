package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RootController implements Initializable {

	// model
	private GrupoController grupoController = new GrupoController();
	private AlumnosController alumnosController = new AlumnosController();

	// view
	@FXML
	private Tab alumnosTab, grupoTab;
	@FXML
	private TabPane dataPane;
	@FXML
	private Button guardarButton, nuevoButton;
	@FXML
	private TextField nombreFicheroText;
	@FXML
	private GridPane view;

	public RootController() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
			loader.setController(this);
			loader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grupoTab.setContent(grupoController.getView());
		alumnosTab.setContent(alumnosController.getView());

	}

	@FXML
	void onGuardarAction(ActionEvent event) {

	}

	@FXML
	void onNuevoAction(ActionEvent event) {

	}

	public GridPane getView() {
		return view;
	}

}
