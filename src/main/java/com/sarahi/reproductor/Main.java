package com.sarahi.reproductor;

import javafx.application.Application;
import javafx.stage.Stage;
import com.sarahi.ventana.Ventana;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Ventana().getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}