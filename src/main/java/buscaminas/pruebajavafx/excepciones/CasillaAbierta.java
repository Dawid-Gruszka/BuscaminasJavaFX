package buscaminas.pruebajavafx.excepciones;


public class CasillaAbierta extends Exception {

    public CasillaAbierta(){
        super("La casilla a la que intentas acceder \n ya se encuentra abierta");
    }

}