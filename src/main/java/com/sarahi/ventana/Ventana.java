// vista de UI donde se mostrara el reproductor general, esta clase solo crea el BorderPane


package com.sarahi.ventana;

import com.sarahi.Archivos.Archivo;
import com.sarahi.consola.Reproductor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;

import java.util.ArrayList;
import java.util.List;

public class Ventana {
    private Integer widthVentana = 1080;
    private Integer heightVentana = 920;
    private static BorderPane panel;
    private static Integer filtro = 1;
    private static List<String> cancionesHistorial = new ArrayList<String>();

    public static Reproductor reproductor;

    public static Reproductor getReproductor() {
        return reproductor;
    }

    public static void setReproductor(Reproductor reproductor) {

        Ventana.reproductor = reproductor;
    }


    //getters and setters
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene scene;

    private Integer getWidthVentana() {
        return widthVentana;
    }

    private void setWidthVentana(Integer widthVentana) {
        this.widthVentana = widthVentana;
    }

    private Integer getHeightVentana() {
        return heightVentana;
    }

    private void setHeightVentana(Integer heightVentana) {
        this.heightVentana = heightVentana;
    }

    private BorderPane getPanel() {
        return panel;
    }

    private void setPanel(BorderPane panel) {
        panel = panel;
    }


    //constructor que pinta las partes de la aplicacion
    public Ventana() {
        reproductor = new Reproductor();
        panel = new BorderPane();


        VBox reproduccionBox = new VBox();
        reproduccionBox.getChildren().add(AdelantarCancion.getAdelantarCancion());
        reproduccionBox.getChildren().add(new BotonsControl());

        panel.setBottom(reproduccionBox);
        actualizarCanciones(1);
        //panel.setLeft(actualizarCanciones(1));
        panel.setRight(new ListadoHistorial());
        //panel.setCenter(root);
        scene = new Scene(this.panel, this.widthVentana, this.heightVentana);

    }


    //metodo para agregar el reproductor de video
    public static void agregarReproductor(MediaView media) {
        panel.setCenter(media);
    }


    //crea una nueva instancia que dibuja cada cancion que se escucha en el historial
    public static void actualizarHistorial(List<String> cancionesconst) {
        panel.setRight(new ListadoHistorial(cancionesconst));
    }


    //agrega la cancion nueva al historial
    public static void agregarListadoHistorial(String cancion) {
        cancionesHistorial.add(cancion);
        actualizarHistorial(cancionesHistorial);
    }


    //actualiza el listado de canciones en el menu cada que se agrega una cancion
    public static void actualizarCanciones(Integer fil) {


        //se acomoda los elementos en un contenedor
        VBox ListadoCanciones = new VBox();
        HBox ListadoBotones = new HBox();
        Button agregar = new Button("Agregar cancion");
        Button filtrar = new Button("A - Z");
        Button filtrardesc = new Button("Z - A");

        //estilos
        filtrar.setStyle(
                "-fx-background-color: #f4f4f4;"
        );
        filtrardesc.setStyle(
                "-fx-background-color: #f4f4f4;"
        );

        //eventos de mouse
        filtrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("se llamo al filtro");

                Ventana.actualizarCanciones(1);
            }
        });

        filtrardesc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Ventana.actualizarCanciones(2);
            }
        });


        ListadoCanciones.setMargin(agregar, new Insets(20));
        agregar.setMinHeight(5);
        agregar.setMinWidth(30);
        agregar.setStyle(
                "-fx-background-color: #f4f4f4;"
        );
        agregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Archivo.seleccionarArchivo();
            }
        });

    //agrega y pinta los compenentes
        ListadoBotones.getChildren().add(agregar);
        ListadoBotones.getChildren().add(filtrar);
        ListadoBotones.getChildren().add(filtrardesc);

        ListadoCanciones.getChildren().add(ListadoBotones);
        ListadoCanciones.getChildren().add(new ListadoNormal(fil));
        panel.setLeft(ListadoCanciones);

    }

}
