package com.sarahi.ventana;

import com.sarahi.consola.Reproductor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


//clase de listado de las canciones en el reproductor
public class ListadoNormal extends VBox {

    private static String[] listado;
    private static File carpeta;

    private static PathAssets path;


    //constructores
    public ListadoNormal() {
        path=new PathAssets();
        carpeta = new File(path.getPath());
        listado = carpeta.list();
        agregarCanciones();
    }


    public ListadoNormal(int orden) {
        path=new PathAssets();
        carpeta = new File(path.getPath());
        listado = carpeta.list();
        if (orden == 1) {
            Arrays.sort(listado);
        } else {
            Arrays.sort(listado, Collections.reverseOrder());
        }
        agregarCanciones();
    }



    //metodo para agregar canciones al listado
    public void agregarCanciones() {
        getChildren().add(new Label("Canciones en galeria"));
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
            getChildren().add(new Label("No hay elementos que reproducir"));
            return;
        } else {
            for (int i = 0; i < listado.length; i++) {
                System.out.println(listado[i]);
                HBox eliminar = new HBox();

                Button boton = new Button(listado[i]);
                estilos(boton);
                boton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        indexSelecionado(boton.getText());
                    }
                });

                Button delete = new Button("\uD83D\uDDD1");
                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        deleteFile(boton.getText());
                    }
                });
                delete.setStyle(
                        "-fx-background-color: transparent;"
                );

                Button agregarPlaylist=new Button("+");
                agregarPlaylist.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("agregar a playlist");
                        agregarCancionPlayList(boton.getText());
                    }
                });
                agregarPlaylist.setStyle("-fx-background-color: transparent;");
                eliminar.getChildren().add(boton);
                eliminar.getChildren().add(agregarPlaylist);
                eliminar.getChildren().add(delete);
                getChildren().add(eliminar);
            }
        }
    }


    //metodo para dar obtener la siguiente cancion a reproducir
    public static void nextCancion(String nombreCancion) {
        path=new PathAssets();
        carpeta = new File(path.getPath());
        listado = carpeta.list();
        //System.out.println(nombreCancion);
        for (int i = 0; i < listado.length; i++) {
            //System.out.println(listado[i]);
            if (listado[i].equals(nombreCancion) && listado.length > i + 1) {
                System.out.println(listado[i]);
                indexSelecionado(listado[i + 1]);
            }
        }

    }



    //estilos de boton
    private void estilos(Button boton) {
        setMargin(boton, new Insets(10));
        boton.setMinHeight(20);
        boton.setMinWidth(100);
        boton.setMaxWidth(150);
        boton.setMaxHeight(30);
        boton.setStyle(
                "-fx-background-color: transparent;"
        );

    }


    //metodo para eliminar cancion del reproductor
    public static void deleteFile(String file) {
        path=new PathAssets();
        File cancionSeleccionada = new File(path.getPath()+"\\" + file);
        try {
            Ventana.reproductor.stop();
            cancionSeleccionada.delete();
            Ventana.actualizarCanciones(1);
        } catch (Exception e) {
            System.out.println("no se logro eliminar el archivo");
        }
    }

    public static void agregarCancionPlayList(String file){
       Ventana.agregarPlayList(file);

    }

    //metodo que reproduce la cancion seleccionada y la agrega al listado de reproducciones
    public static void indexSelecionado(String file) {
        path=new PathAssets();
        File cancionSeleccionada = new File(path.getPath()+"\\" + file);
        Ventana.reproductor.stop();
        Ventana.setReproductor(new Reproductor(cancionSeleccionada));
        Ventana.reproductor.play();
        Ventana.agregarListadoHistorial(file);

    }
}
