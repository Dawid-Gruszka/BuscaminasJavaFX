package buscaminas.buscaminasJavaFX.Models;

import java.util.Random;

public class Tablero {
    private static Casilla[][] casillas;
    private static int numFilas;
    private static int numColumnas;
    private static int numMinas;

    public static int getNumFilas() {
        return numFilas;
    }

    public static int getNumColumnas() {
        return numColumnas;
    }

    public static int getNumMinas() {
        return numMinas;
    }

    public static Casilla[][] crearTablero(String dificultad) {
        switch (dificultad) {
            case "Principiante":
                numFilas = 8;
                numColumnas = 8;
                break;
            case "Intermedio":
                numFilas = 14;
                numColumnas = 14;
                break;
            case "Experto":
                numFilas = 16;
                numColumnas = 16;
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

    public static Casilla getCasilla(int fila, int columna) {
        return casillas[fila][columna];
    }
}