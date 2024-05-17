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

public class MenuController implements Initializable {
    @FXML
    private TextField txtNom;
    @FXML
    private  ChoiceBox<String> selector;
    private String[] dificultad = {"Principiante","Intermedio","Experto"};
    @FXML
    private ImageView imgInformacion;
    @FXML
    protected void click() {
        String nombre = this.txtNom.getText();
        try {
            String nom = this.txtNom.getText();
            String difi = selector.getValue();
            if(!nom.isEmpty()){
                if (difi != null){
                    ViewSwitcher.cambiarVista(View.MAIN,difi);
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
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("ERROR FATAL");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void informacion(MouseEvent event) {
        try {
            ViewSwitcher2.cambiarVista(View.ABOUT);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("ERROR FATAL");
            alert.showAndWait();
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selector.getItems().addAll(dificultad);
    }
}
