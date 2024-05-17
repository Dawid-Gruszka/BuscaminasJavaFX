package buscaminas.buscaminasJavaFX.Models;
import buscaminas.buscaminasJavaFX.controllers.BuscaminasController;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ViewSwitcher {

    private static Stage stage;

    public static void setStage(Stage mystage) {
        stage = mystage;
    }

    public static void cambiarVista(View view, String dificultad) {
        try {
            // Cargar la vista FXML
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxmlPath()));
            Parent root = loader.load();

            // Obtener el controlador de la vista cargada
            BuscaminasController controller = loader.getController();

            // Pasar la dificultad al BuscaminasController
            controller.setDificultad(dificultad);

            // Ejecutar InicializarTablero() después de haber configurado la dificultad
            controller.InicializarTablero();

            // Configurar la nueva escena y escenario
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(view.getTitle());
            //Cargamos el Ico
            Image ico = new Image("file:src/main/resources/Image/logo.png");
            stage.getIcons().add(ico);
            stage.setResizable(false);
            // Mostrar la nueva vista
            stage.show();
        } catch (Exception e) {
            // En caso de error al cargar la vista, mostrar un mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error al cargar la vista");
            alert.setContentText("No se pudo cargar la vista: " + view.getFxmlPath());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

}
