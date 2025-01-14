package com.sarahi.consola;

import com.sarahi.ventana.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;


//clase del reproductor, en esta permite la reproduccion, escucha de video o cancion
public class Reproductor {
    private static MediaPlayer mediaPlayer;


    private static MediaView mediaView;
    private static Media cancion;
    private static String nombre;
    private static String artista;
    private static String album;
    private static String duracion;

//getters and setters
    public static MediaView getMediaView() {
        return mediaView;
    }

    public static void setMediaView(MediaView mediaView) {
        Reproductor.mediaView = mediaView;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Reproductor.nombre = nombre;
    }

    public static String getArtista() {
        return artista;
    }

    public static void setArtista(String artista) {
        Reproductor.artista = artista;
    }

    public static String getAlbum() {
        return album;
    }

    public static void setAlbum(String album) {
        Reproductor.album = album;
    }

    public static String getDuracion() {
        return duracion;
    }

    public static void setDuracion(String duracion) {
        Reproductor.duracion = duracion;
    }


    //constructor del reproductor
    //el primer constructor agrega la primer cancion del reproductor,
    //llama al archivo y crea el contenido media, este se asigna al media player que permitira su reproduccion
    //en caso de ser archivo de videa crea la instancia de la clase Mediaview y la agrega a la clase ventana
    public Reproductor() {

        PathAssets path=new PathAssets();
        File file_cancion = new File(path.getPath()+"\\mala vida.mp3");
        if (file_cancion.exists()) {
            System.out.println("existe");
        }
try {
    cancion = new Media(file_cancion.toURI().toString());
    cancion.setOnError(() -> System.out.println("error de cancion"));
    mediaPlayer = new MediaPlayer(cancion);
    mediaPlayer.setOnReady(() -> {
        editarinfo(file_cancion.getName());
    });
    mediaPlayer.setOnError(() -> System.out.println("error de cancion"));
    if (file_cancion.getName().endsWith(".mp4")) {
        System.out.println("video mp4");
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setPreserveRatio(true);
        Ventana.agregarReproductor(mediaView);
    }
}catch (Exception e){
    System.out.println("agregar carpeta assets pf y redirigir el path a la carpeta formato C:\\doc");
}
    }


    public Reproductor(File file) {

        File file_cancion = file;
        if (file_cancion.exists()) {
            System.out.println("existe");
        }

        cancion = new Media(file_cancion.toURI().toString());
        cancion.setOnError(() -> System.out.println("error de cancion"));


        mediaPlayer = new MediaPlayer(cancion);
        AdelantarCancion.setCancion(mediaPlayer);
        mediaPlayer.setOnReady(() -> {
            editarinfo(file_cancion.getName());
        });
        mediaPlayer.setOnError(() -> System.out.println("error de cancion"));
        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("termino la cancion");
            ListadoNormal.nextCancion(file_cancion.getName());
        });
        if (file.getName().endsWith(".mp4")) {
            System.out.println("video mp4");
            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setPreserveRatio(true);
            Ventana.agregarReproductor(mediaView);
        }

    }


    //cada que se empieza a reproducir una cancion asigna la metadata en la clase CancionInformacion
    public void editarinfo(String name) {
        CancionInformacion.actualizarInfo(
                name != null ? name : "DESCONOCIDO",
                cancion.getMetadata().get("album") != null ? cancion.getMetadata().get("album").toString() : "DESCONOCIDO",
                cancion.getMetadata().get("artist") != null ? cancion.getMetadata().get("artist").toString() : "DESCONOCIDO",
                cancion.getDuration() != null ? cancion.getDuration().toSeconds() : 0
        );
    }


    //metodos para acciones en el reproducto reproducir, pausar y detener
    public static void play() {
        System.out.println("empezar a reproducir");
        mediaPlayer.play();
    }

    ;

    public static void pausa() {
        System.out.println("pausar musica");
        mediaPlayer.pause();
    }

    ;

    public static void stop() {
        System.out.println("detener musica");
        mediaPlayer.stop();
    }


}
