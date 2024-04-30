package buscaminas.pruebajavafx.controllers;

import buscaminas.pruebajavafx.Models.Tablero;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BuscaminasController implements Initializable {
    @FXML
    private GridPane gridPane;

    private String[][] tablero2;

    public void InicializarTablero() {
        tablero2 = Tablero.crearTablero("Principiante");
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        int filas = tablero2.length;
        int columnas = tablero2[0].length;
        double maxAnchoColumna = 583.0 / columnas;
        double maxAltoFila = 330.0 / filas;
        for (int i = 0; i < filas; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(maxAltoFila);
            rowConstraints.setPrefHeight(maxAltoFila);
            rowConstraints.setMaxHeight(maxAltoFila);
            gridPane.getRowConstraints().add(rowConstraints);
        }
        for (int i = 0; i < columnas; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setMinWidth(maxAnchoColumna);
            columnConstraints.setPrefWidth(maxAnchoColumna);
            columnConstraints.setMaxWidth(maxAnchoColumna);
            gridPane.getColumnConstraints().add(columnConstraints);
        }
        for (int i = 0; i < tablero2.length; i++) {
            for (int x = 0; x < tablero2[i].length; x++) {
                Button boton = new Button();
                boton.setMinSize(maxAnchoColumna, maxAltoFila);
                boton.setMaxSize(maxAnchoColumna, maxAltoFila);
                boton.setOnAction(event -> {
                    int fila = GridPane.getRowIndex(boton);
                    int columna = GridPane.getColumnIndex(boton);
                    System.out.println("Botón [" + fila + ", " + columna + "] clickado.");
                });
                gridPane.add(boton, x, i);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        InicializarTablero();
    }
}
