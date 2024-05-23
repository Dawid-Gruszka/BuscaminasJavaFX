package buscaminas.buscaminasJavaFX.Models;

import java.util.Random;

/**
 * Clase que representa el tablero del juego de Buscaminas.
 * Gestiona la creación del tablero, la colocación de minas y el cálculo de minas adyacentes.
 */
public class Tablero {
    private static Casilla[][] casillas;
    private static int numFilas;
    private static int numColumnas;
    private static int numMinas;

    /**
     * Devuelve el número de filas del tablero.
     *
     * @return el número de filas.
     */
    public static int getNumFilas() {
        return numFilas;
    }

    /**
     * Devuelve el número de columnas del tablero.
     *
     * @return el número de columnas.
     */
    public static int getNumColumnas() {
        return numColumnas;
    }

    /**
     * Devuelve el número de minas en el tablero.
     *
     * @return el número de minas.
     */
    public static int getNumMinas() {
        return numMinas;
    }

    /**
     * Crea el tablero de acuerdo a la dificultad especificada.
     * Inicializa las casillas y coloca las minas aleatoriamente.
     *
     * @param dificultad la dificultad del juego (Principiante, Intermedio, Experto).
     * @return una matriz bidimensional de casillas que representa el tablero.
     */
    public static Casilla[][] crearTablero(String dificultad) {
        switch (dificultad) {
            case "Principiante":
                numFilas = 8;
                numColumnas = 8;
                break;
            case "Intermedio":
                numFilas = 10;
                numColumnas = 10;
                break;
            case "Experto":
                numFilas = 12;
                numColumnas = 12;
                break;
            default:
                System.out.println("Error");
                break;
        }

        casillas = new Casilla[numFilas][numColumnas];

        // Inicializar todas las casillas con instancias vacías
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }

        // Colocar minas aleatoriamente
        colocarMinas();

        // Calcular el número de minas adyacentes para cada casilla
        calcularNumMinasAdyacentes();

        return casillas;
    }

    /**
     * Coloca minas aleatoriamente en el tablero.
     * Aproximadamente 1/6 de las casillas serán minas.
     */
    private static void colocarMinas() {
        Random random = new Random();
        numMinas = numFilas * numColumnas / 6; // Aproximadamente 1/6 de las casillas serán minas

        for (int i = 0; i < numMinas; i++) {
            int fila = random.nextInt(numFilas);
            int columna = random.nextInt(numColumnas);
            if (!casillas[fila][columna].isMina()) {
                casillas[fila][columna].setMina(true);
            } else {
                i--; // Si ya hay una mina en esta posición, repetir el proceso
            }
        }
    }

    /**
     * Calcula el número de minas adyacentes para cada casilla.
     * Este método se ejecuta después de que las minas hayan sido colocadas.
     */
    private static void calcularNumMinasAdyacentes() {
        int[][] direcciones = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numColumnas; j++) {
                if (!casillas[i][j].isMina()) {
                    int numMinasAdyacentes = 0;
                    for (int[] direccion : direcciones) {
                        int nuevaFila = i + direccion[0];
                        int nuevaColumna = j + direccion[1];
                        if (nuevaFila >= 0 && nuevaFila < numFilas && nuevaColumna >= 0 && nuevaColumna < numColumnas) {
                            if (casillas[nuevaFila][nuevaColumna].isMina()) {
                                numMinasAdyacentes++;
                            }
                        }
                    }
                    casillas[i][j].setNumMinasAlrededor(numMinasAdyacentes);
                }
            }
        }
    }

    /**
     * Devuelve la casilla en la posición especificada.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     * @return la casilla en la posición especificada.
     */
    public static Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }
}