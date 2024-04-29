package buscaminas.pruebajavafx.excepciones;


public class JuegoTerminado extends Exception {

    public JuegoTerminado() {
        super("El juego ya terminó \n The game is over my friend");
    }

}