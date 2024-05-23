package buscaminas.buscaminasJavaFX.route;

/**
 * Enumeración que representa las vistas disponibles en la aplicación Buscaminas.
 */
public enum View {
    /**
     * Vista del menú principal.
     */
    INDEX("/buscaminas/buscaminasJavaFX/Views/Main.fxml","Menu"),

    /**
     * Vista del juego Buscaminas.
     */
    MAIN("/buscaminas/buscaminasJavaFX/Views/Buscaminas.fxml","Buscaminas"),

    /**
     * Vista de la pantalla de derrota.
     */
    DERROTA("/buscaminas/buscaminasJavaFX/Views/Perder.fxml","Fin del Juego"),

    /**
     * Vista de la pantalla de victoria.
     */
    VICTORIA("/buscaminas/buscaminasJavaFX/Views/Ganar.fxml","Fin del Juego"),

    /**
     * Vista de la pantalla "Acerca De".
     */
    ABOUT("/buscaminas/buscaminasJavaFX/Views/About.fxml","Acerca De");

    private final String fxmlPath;
    private final String title;

    /**
     * Constructor de la enumeración View.
     *
     * @param fxmlPath la ruta del archivo FXML asociado a la vista.
     * @param title el título de la vista.
     */
    View(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    /**
     * Obtiene la ruta del archivo FXML asociado a la vista.
     *
     * @return la ruta del archivo FXML.
     */
    public String getFxmlPath() {
        return fxmlPath;
    }

    /**
     * Obtiene el título de la vista.
     *
     * @return el título de la vista.
     */
    public String getTitle() {
        return title;
    }
}