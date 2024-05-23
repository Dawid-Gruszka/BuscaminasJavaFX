package buscaminas.buscaminasJavaFX.Models;
import buscaminas.buscaminasJavaFX.controllers.BuscaminasController;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Optional;

/**
 * Clase que gestiona el cambio de vistas en la aplicación.
 */
public class ViewSwitcher {

    private static Stage stage;

    /**
     * Establece el escenario principal de la aplicación.
     *
     * @param mystage el escenario principal.
     */
    public static void setStage(Stage mystage) {
        stage = mystage;
    }

    /**
     * Cambia la vista actual a la vista especificada.
     *
     * @param view la vista a la que se desea cambiar.
     * @param dificultad la dificultad del juego.
     * @param nombre el nombre del jugador.
     */
    public static void cambiarVista(View view, String dificultad, String nombre) {
        try {
            var alert = new Alert(Alert.AlertType.CONFIRMATION);
            var close = new Button("salir de la aplicación");
            // Cargar la vista FXML
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFxmlPath()));
            Parent root = loader.load();
            // Obtener el controlador de la vista cargada
            BuscaminasController controller = loader.getController();
            controller.setJugador(nombre);
            // Pasar la dificultad al Buscaminas
            controller.setDificultad(dificultad);
            // Ejecutar InicializarTablero() después de haber configurado la dificultad
            controller.InicializarTablero();
            // Configurar la nueva escena y escenario
            Scene scene = new Scene(root);
            scene.getStylesheets().add(ViewSwitcher.class.getResource("/css/botones.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(view.getTitle());
            // Cargamos el Ico
            Image ico = new Image("file:src/main/resources/Image/logo.png");
            stage.getIcons().add(ico);
            stage.setResizable(false);
            stage.setOnCloseRequest((t) -> {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    if (controller.timer != null) {
                        controller.timer.cancel();
                    }
                    stage.close();
                } else {
                    t.consume();
                }
            });
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
