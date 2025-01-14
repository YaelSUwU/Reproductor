package com.sarahi.ventana;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


//clase para dibujar la informacion de la cancion seleccionada
public class CancionInformacion extends VBox {
    private static Label nombre, artista, album, duracion;



    //constructor
    public CancionInformacion() {
        nombre = new Label("Nombre: DESCONOCIDO");
        artista = new Label("Artista: DESCONOCIDO");
        album = new Label("Album: DESCONOCIDO");
        duracion = new Label("Duración: 0:00");

        getChildren().add(nombre);
        getChildren().add(artista);
        getChildren().add(album);
        getChildren().add(duracion);
    }


    //metodo para actualizar la info que dibuja
    public static void actualizarInfo(String nombrep, String artistap, String albump, double duracionp) {
        nombre.setText((nombrep == null) ? "Nombre: DESCONOCIDO" : "Nombre: " + nombrep);
        artista.setText((artistap == null) ? "Artista: DESCONOCIDO" : "Artista: " + artistap);
        album.setText((albump == null) ? "Album: DESCONOCIDO" : "Album: " + albump);
        duracion.setText("Duración: " + formatoTiempo(duracionp));

    }


    //cambia el formato de tiempo de ms a minutos y segundos 00:00
    private static String formatoTiempo(double segundos) {
        int minutos = (int) segundos / 60;
        int seg = (int) segundos % 60;
        return String.format("%02d:%02d", minutos, seg);
    }
}
