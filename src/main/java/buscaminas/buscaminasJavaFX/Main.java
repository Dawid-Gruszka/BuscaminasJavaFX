package buscaminas.buscaminasJavaFX;

import buscaminas.buscaminasJavaFX.Models.ViewSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            ViewSwitcher.setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Views/Pruebauno.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 574, 402);
            stage.setResizable(false);
            stage.setTitle("Menu");
            stage.setScene(scene);
            Image ico = new Image("file:src/main/resources/Image/logo.png");
            stage.getIcons().add(ico);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}