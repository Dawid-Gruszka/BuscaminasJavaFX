package buscaminas.buscaminasJavaFX.Models;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase para cambiar la vista en la aplicación JavaFX del juego Buscaminas.
 */
public class ViewSwitcher2 {

    /**
     * Cambia la vista de la aplicación JavaFX a la especificada en el parámetro "view".
     *
     * @param view la vista a la que se cambiará.
     */
    public static void cambiarVista(View view) {
        try {
            // Cargar la vista FXML
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxmlPath()));
            Parent root = loader.load();
            // Configurar la nueva escena y escenario
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(view.getTitle());
            // Cargar el icono de la aplicación
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
