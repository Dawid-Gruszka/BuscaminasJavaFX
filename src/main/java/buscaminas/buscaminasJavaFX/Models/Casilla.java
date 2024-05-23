package buscaminas.buscaminasJavaFX.Models;

/**
 * Representa una casilla en el juego de Buscaminas.
 * Cada casilla tiene una posición determinada por su fila y columna,
 * y puede tener una mina, estar abierta o marcada, y cuántas minas hay alrededor.
 */
public class Casilla {
    private final int fila;
    private final int columna;
    private boolean esMina;
    private boolean estaAbierta;
    private boolean estaMarcada;
    private int numMinasAlrededor;

    /**
     * Constructor que inicializa una casilla en una posición dada por su fila y columna.
     *
     * @param fila la fila de la casilla.
     * @param columna la columna de la casilla.
     */
    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.esMina = false;
        this.estaAbierta = false;
        this.estaMarcada = false;
        this.numMinasAlrededor = 0;
    }

    /**
     * Devuelve si la casilla contiene una mina.
     *
     * @return true si la casilla contiene una mina, false en caso contrario.
     */
    public boolean isMina() {
        return esMina;
    }

    /**
     * Establece si la casilla contiene una mina.
     *
     * @param esMina true para indicar que la casilla contiene una mina, false en caso contrario.
     */
    public void setMina(boolean esMina) {
        this.esMina = esMina;
    }

    /**
     * Devuelve si la casilla está abierta.
     *
     * @return true si la casilla está abierta, false en caso contrario.
     */
    public boolean isAbierta() {
        return estaAbierta;
    }

    /**
     * Establece si la casilla está abierta.
     *
     * @param estaAbierta true para indicar que la casilla está abierta, false en caso contrario.
     */
    public void setAbierta(boolean estaAbierta) {
        this.estaAbierta = estaAbierta;
    }

    /**
     * Devuelve si la casilla está marcada.
     *
     * @return true si la casilla está marcada, false en caso contrario.
     */
    public boolean isMarcada() {
        return estaMarcada;
    }

    /**
     * Establece si la casilla está marcada.
     *
     * @param estaMarcada true para indicar que la casilla está marcada, false en caso contrario.
     */
    public void setMarcada(boolean estaMarcada) {
        this.estaMarcada = estaMarcada;
    }

    /**
     * Devuelve el número de minas alrededor de la casilla.
     *
     * @return el número de minas alrededor de la casilla.
     */
    public int getNumMinasAlrededor() {
        return numMinasAlrededor;
    }

    /**
     * Establece el número de minas alrededor de la casilla.
     *
     * @param numMinasAlrededor el número de minas alrededor de la casilla.
     */
    public void setNumMinasAlrededor(int numMinasAlrededor) {
        this.numMinasAlrededor = numMinasAlrededor;
    }
}