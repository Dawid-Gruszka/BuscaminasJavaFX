package buscaminas.buscaminasJavaFX.controllers;

import buscaminas.buscaminasJavaFX.Models.Casilla;
import buscaminas.buscaminasJavaFX.Models.Tablero;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controlador de la vista principal del juego Buscaminas.
 */
public class BuscaminasController {
    @FXML
    private GridPane gridPane;

    @FXML
    private Label Lcontador;

    @FXML
    private Label Lminas;

    @FXML
    private ImageView imgCarita;

    private Casilla[][] tablero2;
    private int marcadas = 0; // Contador de casillas marcadas
    private int casillasMarcadas = 0;
    private int tiempo = 0; // Contador de tiempo en segundos
    public Timer timer;
    private String dificultad;
    private String jugador;

    private static final Color[] PALETA_COLORES = {
            Color.TRANSPARENT, Color.BLUE, Color.GREEN, Color.RED,
            Color.DARKBLUE, Color.DARKRED, Color.CYAN, Color.BLACK, Color.DARKGRAY
    };
    /**
     * Establece la dificultad del juego.
     *
     * @param dificultad la dificultad del juego.
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
    /**
     * Establece el nombre del jugador.
     *
     * @param nombre el nombre del jugador.
     */
    public void setJugador(String nombre) {
        this.jugador = nombre;
    }
    /**
     * Inicializa el tablero de juego según la dificultad seleccionada.
     */
    public void InicializarTablero() {
        tablero2 = Tablero.crearTablero(dificultad);
        gridPane.getChildren().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();
        marcadas = 0;
        tiempo = 0;
        actualizarContador();
        actualizarMinas();
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
                boton.getStylesheets().add(getClass().getResource("/css/botones.css").toExternalForm());
                boton.setFocusTraversable(false);
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
        imgCarita.setOnMousePressed(event -> {
            Image nuevaImagen = new Image("file:src/main/resources/Image/triste.png");
            imgCarita.setImage(nuevaImagen);
        });
        imgCarita.setOnMouseReleased(event -> {
            Image nuevaImagen = new Image("file:src/main/resources/Image/carita.png");
            imgCarita.setImage(nuevaImagen);
        });
    }
    /**
     * Inicia el temporizador del juego.
     */
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
    /**
     * Descubre la casilla seleccionada en el tablero.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     */
    private void descubrirCasilla(int fila, int columna) {
        Casilla casilla = Tablero.getCasilla(fila, columna);
        if (!casilla.isAbierta() && !casilla.isMarcada()) {
            casilla.setAbierta(true);
            Button boton = getBoton(fila, columna);
            if (casilla.isMina()) {
                ImageView imageView = new ImageView(new Image("file:src/main/resources/Image/mina.png"));
                imageView.setFitWidth(boton.getWidth());
                imageView.setFitHeight(boton.getHeight());
                boton.setGraphic(imageView);
                mostrarFinDelJuego(false);
            } else {
                int numMinasAdyacentes = casilla.getNumMinasAlrededor();
                if (numMinasAdyacentes > 0) {
                    boton.setText(Integer.toString(numMinasAdyacentes));
                    boton.setDisable(true);
                } else {
                    boton.setDisable(true);
                    boton.setStyle("-fx-background-color: #a5a8a8;"); // Gris claro
                }
                boton.setTextFill(PALETA_COLORES[numMinasAdyacentes]);
                if (numMinasAdyacentes == 0) {
                    descubrirCasillasAdyacentes(fila, columna);
                }
            }
        }
        verificarFinDelJuego();
    }
    /**
     * Marca una casilla como posible mina.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     * @param boton el botón que representa la casilla en la interfaz.
     */
    private void marcarCasilla(int fila, int columna, Button boton) {
        Casilla casilla = Tablero.getCasilla(fila, columna);
        if (!casilla.isAbierta()) {
            if (!casilla.isMarcada()) {
                ImageView imageView = new ImageView(new Image("file:src/main/resources/Image/banderin.png"));
                imageView.setFitWidth(boton.getWidth());
                imageView.setFitHeight(boton.getHeight());
                boton.setGraphic(imageView);
                casilla.setMarcada(true);
                marcadas++;
                casillasMarcadas++;
            } else {
                boton.setGraphic(null);
                casilla.setMarcada(false);
                marcadas--;
                casillasMarcadas--;
            }
            actualizarMinas();
            verificarFinDelJuego();
        }
    }
    /**
     * Verifica si el juego ha terminado.
     */
    private void verificarFinDelJuego() {
        boolean todasMarcadas = true;
        boolean todasDescubiertas = true;

        // Verificar si todas las casillas de bomba están marcadas
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

        // Verificar si todas las casillas están descubiertas
        for (int i = 0; i < Tablero.getNumFilas(); i++) {
            for (int j = 0; j < Tablero.getNumColumnas(); j++) {
                Casilla casilla = Tablero.getCasilla(i, j);
                if (!casilla.isMina() && !casilla.isAbierta()) {
                    todasDescubiertas = false;
                    break;
                }
            }
            if (!todasDescubiertas) {
                break;
            }
        }
        // Verificar si el número de marcaciones es correcto
        boolean marcacionesCorrectas = (marcadas == Tablero.getNumMinas());

        // Verificar si se cumplen todas las condiciones para ganar
        if (todasMarcadas && todasDescubiertas && marcacionesCorrectas) {
            mostrarFinDelJuego(true);
        }
    }

    /**
     * Muestra el mensaje de fin del juego.
     *
     * @param victoria true si el jugador ha ganado, false si ha perdido.
     */
    private void mostrarFinDelJuego(boolean victoria) {
        timer.cancel();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (victoria) {
            mostrarVentanaGanar();
            InicializarTablero();
            casillasMarcadas = 0;
        } else {
            mostrarVentanaPerder();
            InicializarTablero();
            casillasMarcadas = 0;
        }
    }

    /**
     * Muestra la ventana de derrota.
     */
    private void mostrarVentanaPerder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/buscaminas/buscaminasJavaFX/Views/Perder.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Has perdido");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error al cargar la vista");
            alert.setContentText("No se pudo cargar la vista de derrota.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Muestra la ventana de victoria.
     */
    private void mostrarVentanaGanar() {
        try {
            String contador = Lcontador.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/buscaminas/buscaminasJavaFX/Views/Ganar.fxml"));
            Parent root2 = loader.load();
            GanarController controlador = loader.getController();
            controlador.setJugador(jugador);
            controlador.setTiempoGanador(contador);
            controlador.setDificultad(dificultad);
            controlador.inicializar();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));
            stage.setTitle("Has Ganado!");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error al cargar la vista");
            alert.setContentText("No se pudo cargar la vista de victoria.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el botón correspondiente a una casilla específica en el tablero.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     * @return el botón que representa la casilla.
     */
    private Button getBoton(int fila, int columna) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == fila && GridPane.getColumnIndex(node) == columna) {
                return (Button) node;
            }
        }
        return null;
    }

    /**
     * Descubre las casillas adyacentes a una casilla sin minas adyacentes.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     */
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

    /**
     * Actualiza el contador de tiempo en la interfaz.
     */
    private void actualizarContador() {
        Lcontador.setText(String.format("%03d", tiempo));
    }
    /**
     * Maneja el evento de clic en la imagen de carita, reiniciando el tablero.
     */
    @FXML
    private void carita() {
        InicializarTablero();
    }
    /**
     * Actualiza el número de minas restantes en la interfaz.
     */
    private void actualizarMinas() {
        int minasRestantes = Tablero.getNumMinas() - marcadas;
        Lminas.setText(String.format("%03d", minasRestantes));
    }
}