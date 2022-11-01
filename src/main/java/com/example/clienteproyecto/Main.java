package com.example.clienteproyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * inicializa un fxml del server
     * @param stage escenario cargado
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Text Finder");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * ejecuta el programa
     * @param args no recibe
     */
    public static void main(String[] args) {

        launch();
    }
}