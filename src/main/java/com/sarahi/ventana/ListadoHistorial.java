package com.sarahi.ventana;

import com.sarahi.consola.Reproductor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


//clase que dibuja el historial de reproducciones
public class ListadoHistorial extends VBox {
    private static List<String> canciones = new ArrayList<String>();
    public static List<String> getCanciones() {
        return canciones;
    }
    public static void setCanciones(List<String> canciones) {
        ListadoHistorial.canciones = canciones;
    }


    //constructores
    public ListadoHistorial() {

        agregarCanciones(canciones);

    }

    public ListadoHistorial(List<String> cancionesConstructor) {
        agregarCanciones(cancionesConstructor);

    }


    //crea el listado de canciones
    public void agregarCanciones(List<String> cancionesConstructor) {
        getChildren().add(new Label("Historial de reproducciones"));
        try {
            for (int i = 0; i < cancionesConstructor.size(); i++
            ) {
                Button boton = agregarBoton(cancionesConstructor.get(i));
                getChildren().add(boton);
            }
        } catch (Exception e) {
            getChildren().add(new Label("sin canciones en el historial"));
        }
    }


    //en caso que se seleccione reproducir una cancion del historial
    public static void historialSeleccionado(String file) {
        File cancionSeleccionada = new File("B:\\doc\\Proyectos\\web\\spring\\Reproductor\\assets\\musica\\" + file);
        Ventana.reproductor.stop();
        Ventana.setReproductor(new Reproductor(cancionSeleccionada));
        Ventana.reproductor.play();
        Ventana.agregarListadoHistorial(file);
    }



    //genera el boton del listado con su evento
    public static Button agregarBoton(String nombre) {
        Button boton = new Button(nombre);
        estilos(boton);
        boton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                historialSeleccionado(boton.getText());
            }
        });
        return boton;

    }


    //estilos
    private static void estilos(Button boton) {
        setMargin(boton, new Insets(20));
        boton.setMinHeight(20);
        boton.setMinWidth(100);
        boton.setMaxWidth(150);
        boton.setMaxHeight(30);
        boton.setStyle(
                "-fx-background-color: transparent;"
        );

    }


}
