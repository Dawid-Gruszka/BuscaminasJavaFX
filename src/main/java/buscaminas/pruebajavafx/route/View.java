package buscaminas.pruebajavafx.route;

public enum View {
    INDEX("/buscaminas/pruebajavafx/Views/Pruebauno.fxml","Menu"),
    MAIN("/buscaminas/pruebajavafx/Views/Buscaminas.fxml","Buscaminas"),
    ABOUT("/buscaminas/pruebajavafx/Views/About.fxml","Acerca De");

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
