package buscaminas.pruebajavafx.Models;

public class Tablero {
    private static String [][] tablero;

    public static String[][] crearTablero(String dificultad) {
        switch (dificultad) {
            case "Principiante":
                tablero = new String[8][8];
                break;
            case "Intermedio":
                tablero = new String[14][14];
                break;
            case "Experto":
                tablero= new String[18][18];
                break;
            default:
                System.out.println("Error");
                break;
        }
        return tablero;
    }
}
