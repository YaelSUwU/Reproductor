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

import static com.sarahi.ventana.ListadoHistorial.historialSeleccionado;

public class PlayList  extends VBox {
    private static PathAssets path;
    private static File carpeta;
    private static String[] listado;
    private static List<String> canciones=new ArrayList<>();

    public static List<String> getCanciones() {
        return canciones;
    }

    public static void setCanciones(List<String> canciones) {
        PlayList.canciones = canciones;
    }

    public PlayList() {

        agregarCanciones(canciones);

    }

    public PlayList(List<String> cancionesConstructor) {
        agregarCanciones(cancionesConstructor);

    }


    //crea el listado de canciones
    public void agregarCanciones(List<String> cancionesConstructor) {
        getChildren().add(new Label("Canciones en fila"));
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
    public static void playlistSeleccionado(String file) {
        PathAssets path=new PathAssets();
        File cancionSeleccionada = new File(path.getPath()+"\\" + file);
        Ventana.reproductor.stop();
        Ventana.setReproductor(new Reproductor(cancionSeleccionada));
        Ventana.reproductor.play();
        Ventana.agregarListadoHistorial(file);
    }

    public static void nextCancion(String nombreCancion) {
        path=new PathAssets();
        carpeta = new File(path.getPath());
        listado= Ventana.getCancionesPlaylist().toArray(new String[0]);

        for (int i = 0; i < listado.length-1; i++) {
            indexSelecionadoPL(listado[i]);
        }

    }


    public static void indexSelecionadoPL(String file) {
        System.out.println("index");
        path=new PathAssets();
        File cancionSeleccionada = new File(path.getPath()+"\\" + file);
        Ventana.reproductor.stop();
        Ventana.setReproductor(new Reproductor(cancionSeleccionada));
        Ventana.reproductor.play();
        //Ventana.agregarListadoHistorial(file);

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
        setMargin(boton, new Insets(10));
        boton.setMinHeight(20);
        boton.setMinWidth(100);
        boton.setMaxWidth(150);
        boton.setMaxHeight(30);
        boton.setStyle(
                "-fx-background-color: transparent;"
        );

    }
}
