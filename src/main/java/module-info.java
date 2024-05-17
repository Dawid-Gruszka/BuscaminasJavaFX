module buscaminas.pruebajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens buscaminas.buscaminasJavaFX to javafx.fxml;
    exports buscaminas.buscaminasJavaFX;
    exports buscaminas.buscaminasJavaFX.controllers;
    opens buscaminas.buscaminasJavaFX.controllers to javafx.fxml;
    exports buscaminas.buscaminasJavaFX.Models;
    opens buscaminas.buscaminasJavaFX.Models to javafx.fxml;
}