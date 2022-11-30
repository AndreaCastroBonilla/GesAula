package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Sexo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PanelController implements Initializable {

	// model
	private ObjectProperty<Sexo> generos = new SimpleObjectProperty<>();

	// view
	@FXML
	private CheckBox RepiteCheck;
	@FXML
	private TextField apellidosText, nombreText;
	@FXML
	private DatePicker fechaNacPicker;
	@FXML
	private ComboBox<Sexo> sexoCombo;
	@FXML
	private GridPane view;

	public PanelController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PanelView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sexoCombo.getItems().setAll(Sexo.values());
		generos.bind(sexoCombo.getSelectionModel().selectedItemProperty());

	}

	public GridPane getView() {
		return view;
	}

}
