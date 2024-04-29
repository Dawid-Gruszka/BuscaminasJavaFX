package buscaminas.pruebajavafx.excepciones;


public class CasillaInvalida extends Exception{

    public CasillaInvalida () {
        super("La casilla que intentas acceder no es valida \n You are trying to reach an inaccessible position");
    }
}