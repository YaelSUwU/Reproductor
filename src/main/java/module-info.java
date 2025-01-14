module com.sarahi.reproductor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;


    opens com.sarahi.reproductor to javafx.fxml;
    //opens com.sarahi.consola to javafx.media;
    exports com.sarahi.reproductor;
    exports com.sarahi.consola;
    //exports com.sarahi.ventana;
}