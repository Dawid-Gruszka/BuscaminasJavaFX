package buscaminas.buscaminasJavaFX.controllers;

import buscaminas.buscaminasJavaFX.Models.ViewSwitcher;
import buscaminas.buscaminasJavaFX.Models.ViewSwitcher2;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador para el menú principal de la aplicación Buscaminas. Maneja las interacciones
 * del usuario en la interfaz de menu.
 */
public class MenuController implements Initializable {

    /**
     * Campo de texto para que el usuario introduzca su nombre.
     */
    @FXML
    private TextField txtNom;

    /**
     * Selector de opciones para elegir la dificultad del juego.
     */
    @FXML
    private ChoiceBox<String> selector;

    /**
     * Opciones de dificultad disponibles.
     */
    private String[] dificultad = {"Principiante", "Intermedio", "Experto"};

    /**
     * Imagen informativa en la interfaz de usuario.
     */
    @FXML
    private ImageView imgInformacion;

    /**
     * Maneja el evento de clic en el botón para iniciar el juego. Verifica que el nombre
     * y la dificultad estén seleccionados y cambia la vista al juego principal.
     */
    @FXML
    protected void click() {
        String nombre = this.txtNom.getText();
        try {
            String nom = this.txtNom.getText();
            String difi = selector.getValue();
            if (!nom.isEmpty()) {
                if (difi != null) {
                    ViewSwitcher.cambiarVista(View.MAIN, difi, nom);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Selecciona la dificultad para poder jugar");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Introduce tu nombre para empezar");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("ERROR FATAL");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Maneja el evento de clic en la imagen informativa, cambiando la vista a la
     * pantalla de información.
     *
     * @param event el evento de clic del ratón.
     */
    @FXML
    void informacion(MouseEvent event) {
        try {
            ViewSwitcher2.cambiarVista(View.ABOUT);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("ERROR FATAL");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Inicializa el controlador. Añade las opciones de dificultad al selector.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selector.getItems().addAll(dificultad);
    }
}
