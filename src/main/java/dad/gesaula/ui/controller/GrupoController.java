package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GrupoController implements Initializable {

	// view
	@FXML
	private Slider actitudSlider, examenSlider, practicaSlider;
	@FXML
	private TextField denominacionText;
	@FXML
	private DatePicker finPicker, inicioPicker;
	@FXML
	private GridPane view;

	public GrupoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public GridPane getView() {
		return view;
	}

}
