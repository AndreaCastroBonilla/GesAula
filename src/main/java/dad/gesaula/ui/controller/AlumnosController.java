package dad.gesaula.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.ui.model.Alumno;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AlumnosController implements Initializable {

	// model
	private ListProperty<Alumno> alumnos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private IntegerProperty selectedName = new SimpleIntegerProperty();

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
	private AnchorPane derechaPane;
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
		// cargar imágenes
		loadImages();

		// bindings
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fechaNacColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());
		dataTable.itemsProperty().bind(alumnos);

		// disable botón eliminar
		selectedName.bind(dataTable.getSelectionModel().selectedIndexProperty());
		eliminarButton.disableProperty().bind(selectedName.lessThan(0));

	}

	private void loadImages() {
		Image image1 = new Image(getClass().getResourceAsStream("/images/add-32x32.png"));
		ImageView imageView1 = new ImageView(image1);
		nuevoButton.setGraphic(imageView1);
		Image image2 = new Image(getClass().getResourceAsStream("/images/del-32x32.png"));
		ImageView imageView2 = new ImageView(image2);
		eliminarButton.setGraphic(imageView2);
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar alumno");
		alert.setHeaderText("Se va a eliminar el alumno " + "'" + alumnos.get(selectedName.intValue()) + "'.");
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
