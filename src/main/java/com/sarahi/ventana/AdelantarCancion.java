package com.sarahi.ventana;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;


//clase que dibuja el slider para pintar las canciones
public class AdelantarCancion extends VBox {


    //se crea una instancia de el mismo para que se mantenga esperando la cancion a reproducir y se puedan actualizar los datos
    //desde el mismo
    private static AdelantarCancion adelantarCancion = new AdelantarCancion();

    private static MediaPlayer cancion;
    private static Label tiempo;
    private static Slider barra;


    //getter
    public static AdelantarCancion getAdelantarCancion() {
        return adelantarCancion;
    }


    //constructor, asigna el valor minimo, maximo y el valor index que indica donde se encuentra la cancion
    private AdelantarCancion() {
        tiempo = new Label("0:00");
        barra = new Slider();
        barra.setMin(0);
        barra.setMax(100);
        barra.setValue(0);

        //se agrega al contenedor en la posicion central
        this.setAlignment(Pos.CENTER);
        getChildren().addAll(tiempo, barra);
    }



    //event listener que movera el slider de manera automatica por cada segundo que pase en el reproductor,
    //esta clase edita su instancia de el mismo cada que se reproduce la cancion
    //por eso el currentimepropertyu
    //la instancia barra.valueProperty actualiza estos datos siendo llamada desde la funcion anterior de cancion
    private static void establecerListener() {
        cancion.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double porcentaje = (newValue.toSeconds() / cancion.getTotalDuration().toSeconds()) * 100;
            barra.setValue(porcentaje);

            tiempo.setText(formatoTiempo(newValue.toSeconds()));
        });

        barra.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (barra.isValueChanging()) {
                cancion.seek(cancion.getTotalDuration().multiply(newValue.doubleValue() / 100));
            }
        });
    }


    //asigna el formato del label de ms a 00:00 s
    private static String formatoTiempo(double segundos) {
        int minutos = (int) segundos / 60;
        int seg = (int) segundos % 60;
        return String.format("%02d:%02d", minutos, seg);
    }


    //se le asigna la cancion que se esta reproduciendo
    public static void setCancion(MediaPlayer cancion) {
        AdelantarCancion.cancion = cancion;
        establecerListener();
    }
}
