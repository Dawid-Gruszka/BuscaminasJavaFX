package buscaminas.buscaminasJavaFX.controllers;

import buscaminas.buscaminasJavaFX.Models.ViewSwitcher2;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PerderController {

    @FXML
    private Button bJugar;

    @FXML
    private Button bSalir;

    @FXML
    void click(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void jugar(ActionEvent event) {
        ViewSwitcher2.cambiarVista(View.INDEX);
    }

}

