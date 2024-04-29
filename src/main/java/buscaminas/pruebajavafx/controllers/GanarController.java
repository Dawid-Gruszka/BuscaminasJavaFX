package buscaminas.pruebajavafx.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class GanarController implements Initializable {

    @FXML
    private Label lblNom;

    @FXML
    private TableColumn<?, ?> tblNom;

    @FXML
    private TableColumn<?, ?> tblTiempo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
