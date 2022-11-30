package dad.gesaula.ui.main;

import dad.gesaula.ui.controller.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GesAulaApp extends Application {

	public static Stage primaryStage;
	private RootController rootController = new RootController();

	@Override
	public void start(Stage primaryStage) throws Exception {

		GesAulaApp.primaryStage = primaryStage;

		primaryStage.setTitle("GesAula");
		primaryStage.getIcons().add(new Image("/images/app-icon-64x64.png"));
		primaryStage.setScene(new Scene(rootController.getView()));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
