package com.sarahi.ventana;

import com.sarahi.consola.Reproductor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


//clase para dibujar los botones de control de musica, para play y pausa
//se implementas la interface acciones que indica las acciones que se deben realizar
public class BotonsControl extends HBox implements Acciones {
    private Button btn_play;
    private Button btn_pause;
    private Button btn_next;
    private Button btn_previous;
    public static HBox caja;

//getters and setters
    public Button getBtn_pause() {
        return btn_pause;
    }

    public void setBtn_pause(Button btn_pause) {
        this.btn_pause = btn_pause;
    }

    public Button getBtn_next() {
        return btn_next;
    }

    public void setBtn_next(Button btn_next) {
        this.btn_next = btn_next;
    }

    public Button getBtn_previous() {
        return btn_previous;
    }

    public void setBtn_previous(Button btn_previous) {
        this.btn_previous = btn_previous;
    }

    public HBox getCaja() {
        return caja;
    }

    public void setCaja(HBox caja) {
        this.caja = caja;
    }

    public Button getBtn_play() {
        return btn_play;
    }

    public void setBtn_play(Button btn_play) {
        this.btn_play = btn_play;
    }


    //constructor, dibuja los botones del reproductor
    public BotonsControl() {
        btn_play = new Button("Reproducir");
        btn_pause = new Button("Pausar");
        estilos(btn_play);
        estilos(btn_pause);
        getChildren().add(btn_play);
        getChildren().add(btn_pause);
        getChildren().add(new CancionInformacion());

        setAlignment(Pos.CENTER);
        eventos();


    }


    //estilos de los botones
    private void estilos(Button boton) {
        setMargin(boton, new Insets(20));
        boton.setMinHeight(40);
        boton.setMinWidth(80);
        boton.setStyle(
                "-fx-background-color: transparent;"
        );

    }


    //eventos de mouse
    public void eventos() {
        btn_play.setOnAction(new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle(ActionEvent actionEvent) {
                                     play();
                                 }
                             }
        );

        btn_pause.setOnAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent actionEvent) {
                                      pause();
                                  }
                              }
        );


    }


    //funciones que permiten acceder a las funciones de la clase reproductor desde esta misma clase
    @Override
    public void play() {
        System.out.println("se dio play");
        Reproductor.play();


    }

    @Override
    public void pause() {
        System.out.println("se dio pausa");
        Reproductor.pausa();
    }


}
