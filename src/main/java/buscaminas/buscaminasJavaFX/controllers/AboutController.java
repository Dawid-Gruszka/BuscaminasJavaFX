package buscaminas.buscaminasJavaFX.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.awt.*;
import java.net.URI;

/**
 * Controlador de la vista "Acerca de" del juego Buscaminas.
 */
public class AboutController {

    @FXML
    private ImageView imgGit;

    /**
     * Abre el navegador web predeterminado del sistema y carga la página del perfil de GitHub del desarrollador
     * al hacer clic en la imagen del icono de GitHub.
     *
     * @param event el evento de clic del ratón.
     */
    @FXML
    void github(MouseEvent event) {
        String url = "https://github.com/Dawid-Gruszka";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
