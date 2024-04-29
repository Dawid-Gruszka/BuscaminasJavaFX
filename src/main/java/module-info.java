module buscaminas.pruebajavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens buscaminas.pruebajavafx to javafx.fxml;
    exports buscaminas.pruebajavafx;
    exports buscaminas.pruebajavafx.controllers;
    opens buscaminas.pruebajavafx.controllers to javafx.fxml;
    exports buscaminas.pruebajavafx.Models;
    opens buscaminas.pruebajavafx.Models to javafx.fxml;
}