package com.sarahi.Archivos;

import com.sarahi.ventana.PathAssets;
import com.sarahi.ventana.Ventana;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


//clase para agregar archivos desde el ordenador

public class Archivo {
    private static Stage escenario;
    private static FileChooser explorador;
//arreglo static que permite el obtener archivos sin generar instancia de las clases
    static {
        escenario = new Stage();
        explorador = new FileChooser();
        explorador.setTitle("Agregar canción");
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("Audios", "*.mp3", "*.mp4", "*.wav", "*.aac");
        explorador.getExtensionFilters().add(filtro);
    }

    //una vez obtenido el archivo, crea una copia del mismo en el reproductor
    public static void seleccionarArchivo() {
        PathAssets path=new PathAssets();
        File archivo = explorador.showOpenDialog(escenario);
        File destino = new File(  path.getPath() + "\\" + archivo.getName());
        try {
            copiarArchivo(archivo, destino);
            //Files.copy(Path.of(archivo.getPath()), destino, new StandardCopyOption[]{StandardCopyOption.REPLACE_EXISTING});
        } catch (Exception e) {
            System.out.println("error al cargar los archivos");
        }
    }



    //funcion para copiar el archivo dentro del navegador
    public static void copiarArchivo(File origen, File destino) {
        try (FileInputStream fis = new FileInputStream(origen);
             FileOutputStream fos = new FileOutputStream(destino)) {

            byte[] buffer = new byte[1024];
            int bytesLeidos;

            while ((bytesLeidos = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesLeidos);
            }
            Ventana.actualizarCanciones(1);
            System.out.println("Archivo copiado a: " + destino.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

}
