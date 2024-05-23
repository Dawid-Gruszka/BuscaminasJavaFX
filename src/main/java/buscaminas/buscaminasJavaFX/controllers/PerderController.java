package buscaminas.buscaminasJavaFX.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Controlador de la vista de pérdida del juego Buscaminas.
 */
public class PerderController {

    @FXML
    private Button bJugar;

    @FXML
    private Button bSalir;

    /**
     * Cierra la aplicación al hacer clic en el botón "Salir".
     *
     * @param event el evento de acción.
     */
    @FXML
    void click(ActionEvent event) {
        System.exit(0);
    }
    /**
     * Cierra la ventana de pérdida y permite al jugador volver a jugar al hacer clic en el botón "Jugar".
     *
     * @param event el evento de acción.
     */
    @FXML
    void jugar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
