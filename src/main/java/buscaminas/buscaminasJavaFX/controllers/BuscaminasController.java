package buscaminas.buscaminasJavaFX.controllers;

import buscaminas.buscaminasJavaFX.Models.Casilla;
import buscaminas.buscaminasJavaFX.Models.Tablero;
import buscaminas.buscaminasJavaFX.Models.ViewSwitcher2;
import buscaminas.buscaminasJavaFX.route.View;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.util.Timer;
import java.util.TimerTask;

public class BuscaminasController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Label Lcontador;

    @FXML
    private Label Lminas;

    private Casilla[][] tablero2;
    private int marcadas = 0; // Contador de casillas marcadas
    private int casillasMarcadas = 0;
    private int tiempo = 0; // Contador de tiempo en segundos
    private Timer timer;
    private String dificultad;

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public void InicializarTablero() {
        tablero2 = Tablero.crearTablero(dificultad);
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        marcadas = 0;
        tiempo = 0;
        actualizarContador(); // Actualizar contador de tiempo
        actualizarMinas(); // Actualizar el número de minas restantes
        if (timer != null) {
            timer.cancel();
        }
        iniciarTimer();

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
                int finalI = i;
                int finalX = x;
                boton.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        descubrirCasilla(finalI, finalX);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        marcarCasilla(finalI, finalX, boton);
                    }
                });
                gridPane.add(boton, x, i);
            }
        }
    }

    private void iniciarTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    tiempo++;
                    actualizarContador();
                });
            }
        }, 1000, 1000);
    }

    private void descubrirCasilla(int fila, int columna) {
        Casilla casilla = Tablero.getCasilla(fila, columna);
        if (!casilla.isAbierta() && !casilla.isMarcada()) {
            casilla.setAbierta(true);
            Button boton = getBoton(fila, columna);
            if (casilla.isMina()) {
                // Mostrar imagen de mina
                ImageView imageView = new ImageView(new Image("file:src/main/resources/Image/mina.png"));
                imageView.setFitWidth(boton.getWidth());
                imageView.setFitHeight(boton.getHeight());
                boton.setGraphic(imageView);
                mostrarFinDelJuego(false);
            } else {
                // Mostrar número de minas adyacentes en el botón
                boton.setText(Integer.toString(casilla.getNumMinasAlrededor()));
                if (casilla.getNumMinasAlrededor() == 0) {
                    // Si la casilla no tiene minas adyacentes, descubrir las casillas adyacentes
                    descubrirCasillasAdyacentes(fila, columna);
                }
            }
        }
    }

    private void marcarCasilla(int fila, int columna, Button boton) {
        Casilla casilla = Tablero.getCasilla(fila, columna);
        if (!casilla.isAbierta()) {
            if (!casilla.isMarcada()) {
                // Marcar la casilla con un banderín
                ImageView imageView = new ImageView(new Image("file:src/main/resources/Image/banderin.png"));
                imageView.setFitWidth(boton.getWidth());
                imageView.setFitHeight(boton.getHeight());
                boton.setGraphic(imageView);
                casilla.setMarcada(true);
                marcadas++;
                casillasMarcadas++;
            } else {
                // Desmarcar la casilla
                boton.setGraphic(null);
                casilla.setMarcada(false);
                marcadas--;
                casillasMarcadas--;
            }
            // Actualizar el contador de minas restantes
            actualizarMinas();
            // Verificar si el juego ha terminado
            verificarFinDelJuego();
        }
    }


    private void verificarFinDelJuego() {
        boolean todasMarcadas = true;
        for (int i = 0; i < Tablero.getNumFilas(); i++) {
            for (int j = 0; j < Tablero.getNumColumnas(); j++) {
                Casilla casilla = Tablero.getCasilla(i, j);
                if (casilla.isMina() && !casilla.isMarcada()) {
                    todasMarcadas = false;
                    break;
                }
            }
            if (!todasMarcadas) {
                break;
            }
        }
        if (todasMarcadas && (casillasMarcadas == Tablero.getNumMinas())) {
            mostrarFinDelJuego(true);
        }
    }

    private void mostrarFinDelJuego(boolean victoria) {
        timer.cancel();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (victoria) {
            ViewSwitcher2.cambiarVista(View.VICTORIA);
            InicializarTablero();
            casillasMarcadas = 0;
        } else {
            ViewSwitcher2.cambiarVista(View.DERROTA);
            InicializarTablero();
            casillasMarcadas = 0;
        }
    }

    private Button getBoton(int fila, int columna) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == fila && GridPane.getColumnIndex(node) == columna) {
                return (Button) node;
            }
        }
        return null;
    }

    private void descubrirCasillasAdyacentes(int fila, int columna) {
        int[][] direcciones = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] direccion : direcciones) {
            int nuevaFila = fila + direccion[0];
            int nuevaColumna = columna + direccion[1];
            if (nuevaFila >= 0 && nuevaFila < Tablero.getNumFilas() && nuevaColumna >= 0 && nuevaColumna < Tablero.getNumColumnas()) {
                descubrirCasilla(nuevaFila, nuevaColumna);
            }
        }
    }

    private void actualizarContador() {
        Lcontador.setText(String.format("%03d", tiempo));
    }

    private void actualizarMinas() {
        int minasRestantes = Tablero.getNumMinas() - marcadas;
        Lminas.setText(String.format("%03d", minasRestantes));
    }
}