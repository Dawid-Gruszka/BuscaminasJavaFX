package buscaminas.buscaminasJavaFX.Models;

public class Casilla {
    private final int fila;
    private final int columna;
    private boolean esMina;
    private boolean estaAbierta;
    private boolean estaMarcada;
    private int numMinasAlrededor;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.esMina = false;
        this.estaAbierta = false;
        this.estaMarcada = false;
        this.numMinasAlrededor = 0;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isMina() {
        return esMina;
    }

    public void setMina(boolean esMina) {
        this.esMina = esMina;
    }

    public boolean isAbierta() {
        return estaAbierta;
    }

    public void setAbierta(boolean estaAbierta) {
        this.estaAbierta = estaAbierta;
    }

    public boolean isMarcada() {
        return estaMarcada;
    }

    public void setMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }

    public int getNumMinasAlrededor() {
        return numMinasAlrededor;
    }

    public void setNumMinasAlrededor(int numMinasAlrededor) {
        this.numMinasAlrededor = numMinasAlrededor;
    }
}