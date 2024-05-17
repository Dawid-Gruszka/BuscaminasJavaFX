package buscaminas.buscaminasJavaFX.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.awt.*;
import java.net.URI;

public class AboutController {
        @FXML
        private ImageView imgGit;

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
