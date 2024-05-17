package buscaminas.buscaminasJavaFX.route;

public enum View {
    INDEX("/buscaminas/buscaminasJavaFX/Views/Pruebauno.fxml","Menu"),
    MAIN("/buscaminas/buscaminasJavaFX/Views/Buscaminas.fxml","Buscaminas"),
    DERROTA("/buscaminas/buscaminasJavaFX/Views/Perder.fxml","Fin del Juego"),
    VICTORIA("/buscaminas/buscaminasJavaFX/Views/Ganar.fxml","Fin del Juego"),
    ABOUT("/buscaminas/buscaminasJavaFX/Views/About.fxml","Acerca De");

    private  String fileName;
    private String fxmlPath;
    private String title;

    View(String fxmlPath, String title) {
        this.fxmlPath = fxmlPath;
        this.title = title;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public String getTitle() {
        return title;
    }

    View(String fileName){
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }


}
