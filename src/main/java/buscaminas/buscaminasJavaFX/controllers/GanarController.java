package buscaminas.buscaminasJavaFX.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


/**
 * Controlador para la ventana de ganadores en la aplicación Buscaminas. Maneja
 * la visualización y gestión de los datos de los ganadores.
 */
public class GanarController {
    /**
     * Botón para jugar otra vez.
     */
    @FXML
    private Button bJugar;
    /**
     * Etiqueta que muestra el nombre del jugador.
     */
    @FXML
    private Label lblNom;
    /**
     * Tabla que muestra los datos de los jugadores ganadores.
     */
    @FXML
    private TableView<Map<String, String>> tableView;
    /**
     * Columna de la tabla que muestra el tiempo de los jugadores.
     */
    @FXML
    private TableColumn<Map<String, String>, String> tblTiempo;
    /**
     * Columna de la tabla que muestra el nombre de los jugadores.
     */
    @FXML
    private TableColumn<Map<String, String>, String> tblNom;

    private String jugador;
    private String tiempoGanador;
    private String dificultad;

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
     * @param jugador el nombre del jugador.
     */
    public void setJugador(String jugador) {
        this.jugador = jugador;
        lblNom.setText(jugador);
    }

    /**
     * Establece el tiempo ganador del jugador.
     *
     * @param tiempoGanador el tiempo ganador del jugador.
     */
    public void setTiempoGanador(String tiempoGanador) {
        this.tiempoGanador = tiempoGanador;
    }

    /**
     * Maneja el evento de clic en el botón para jugar otra vez, cerrando la ventana actual.
     *
     * @param event el evento de acción del botón.
     */
    @FXML
    void jugar(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja el evento de clic en el botón para salir, cerrando la aplicación.
     *
     * @param event el evento de acción del botón.
     */
    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Guarda el nombre del jugador y su tiempo ganador en un archivo.
     *
     * @param ruta la ruta del archivo donde se guardarán los datos.
     */
    private void guardarGanador(String ruta) {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            if (jugador != null && tiempoGanador != null) {
                fw.write(jugador + "," + tiempoGanador + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los datos de los ganadores desde un archivo y los muestra en la tabla.
     *
     * @param ruta la ruta del archivo desde donde se cargarán los datos.
     */
    private void cargarGanadores(String ruta) {
        ObservableList<Map<String, String>> data = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String jugador = parts[0].trim();
                    String tiempo = parts[1].trim();
                    if (!jugador.isEmpty() && !tiempo.isEmpty()) {
                        try {
                            Integer.parseInt(tiempo);
                            Map<String, String> row = new HashMap<>();
                            row.put("jugador", jugador);
                            row.put("tiempo", tiempo);
                            data.add(row);
                        } catch (NumberFormatException e) {
                            System.err.println("Formato no válido");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        data.sort((row1, row2) -> {
            int tiempo1 = Integer.parseInt(row1.get("tiempo"));
            int tiempo2 = Integer.parseInt(row2.get("tiempo"));
            return Integer.compare(tiempo1, tiempo2);
        });
        tableView.setItems(data);
    }

    /**
     * Inicializa el controlador guardando los datos del ganador y cargando los ganadores
     * correspondientes a la dificultad seleccionada.
     */
    public void inicializar() {
        String ruta;
        if (Objects.equals(dificultad, "Principiante")) {
            ruta = "src/main/resources/GPrincipiante.txt";
        } else if (Objects.equals(dificultad, "Intermedio")) {
            ruta = "src/main/resources/GIntermedio.txt";
        } else if (dificultad.equals("Experto")) {
            ruta = "src/main/resources/GExperto.txt";
        } else {
            return;
        }
        guardarGanador(ruta);
        cargarGanadores(ruta);
        tblNom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get("jugador")));
        tblTiempo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get("tiempo")));
    }
}