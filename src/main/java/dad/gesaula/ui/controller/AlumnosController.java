package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AlumnosController implements Initializable {

	private PanelController panelController = new PanelController();
	
	// model
	private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Alumno> selected = new SimpleObjectProperty<>();

	// view
	@FXML
	private ImageView addImage, deleteImage;
	@FXML
	private TableColumn<Alumno, String> apellidosColumn, nombreColumn;
	@FXML
	private TableColumn<Alumno, LocalDate> fechaNacColumn;
	@FXML
	private TableView<Alumno> dataTable;
	@FXML
	private Button eliminarButton, nuevoButton;

	@FXML
	private BorderPane rightPanel;
	
	@FXML
	private Label infoLabel;
	@FXML
	private SplitPane view;

	public AlumnosController() {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosView.fxml"));
			loader.setController(this);
			loader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fechaNacColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
		dataTable.itemsProperty().bind(alumnos);

		// disable botón eliminar
		selected.bind(dataTable.getSelectionModel().selectedItemProperty());
		eliminarButton.disableProperty().bind(selected.isNull());

		selected.addListener((o, ov, nv) -> {
			if (nv != null) {
				System.out.println("hay uno seleccionado");
				rightPanel.setCenter(panelController.getView());
			} else {
				System.out.println("NO hay uno seleccionado");
				rightPanel.setCenter(infoLabel);
			}
		});
		
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar alumno");
		alert.setHeaderText("Se va a eliminar el alumno " + "'" + selected + "'.");
		alert.setContentText("¿Está seguro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Alumno selected = dataTable.getSelectionModel().getSelectedItem();
			alumnos.remove(selected);
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Alumno alumno = new Alumno();
		alumno.setNombre("Sin nombre");
		alumno.setApellidos("Sin apellidos");
		alumnos.add(alumno);

	}

	public SplitPane getView() {
		return view;
	}

}
