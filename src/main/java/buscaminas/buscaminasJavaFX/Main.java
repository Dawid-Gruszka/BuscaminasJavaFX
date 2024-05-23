package buscaminas.buscaminasJavaFX;

import buscaminas.buscaminasJavaFX.Models.ViewSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Configura el escenario principal y carga la escena inicial.
 */
public class Main extends Application {
    /**
     * Inicia la aplicación configurando el escenario principal y cargando la escena inicial.
     * @param stage el escenario principal para esta aplicación, sobre el cual se puede configurar la escena de la aplicación.
     * @throws IOException si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Configurar el escenario para el cambio de vista
            ViewSwitcher.setStage(stage);
            // Cargar el archivo FXML inicial
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 574, 402);
            // Configurar las propiedades del escenario
            stage.setResizable(false);
            stage.setTitle("Menu");
            // Establecer la escena en el escenario
            stage.setScene(scene);
            // Configurar el icono de la aplicación
            Image ico = new Image("file:src/main/resources/Image/logo.png");
            stage.getIcons().add(ico);
            // Mostrar el escenario
            stage.show();
        } catch (IOException e) {
            // Imprimir la traza de la pila si hay un error al cargar el archivo FXML
            e.printStackTrace();
        }
    }

    /**
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}