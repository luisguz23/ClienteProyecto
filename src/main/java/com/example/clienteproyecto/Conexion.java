package com.example.clienteproyecto;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Conexion {


    Socket clinte;
    /**
     * puerto que se utiliza para realizar la conexion entre el cliente y el servidor
     */
    int puerto = 9000;

    /**
     * ip del host
     */
    String ip="127.0.0.1";
    BufferedReader entrada, teclado;
    PrintStream salida;

     public String message;
    @FXML
    private TextField mensajeTextField;

    @FXML
    ListView<String> resultados;


    /**
     * realiza conexion entre el cliente y el servidor
     *
     */
    //Codigo tomado de: https://www.youtube.com/watch?v=tsr53-zO73o
    public void inicio(){
        try{
            clinte=new Socket(ip,puerto);
            entrada=new BufferedReader(new InputStreamReader(clinte.getInputStream()));
            teclado=new BufferedReader(new InputStreamReader(System.in));
            //String tec=teclado.readLine();
            String tec=mensajeTextField.getText();
            salida=new PrintStream(clinte.getOutputStream());
            salida.println(tec);
            String respuesta=entrada.readLine();
            //respuestaLabel.setText(message);
            message=respuesta;
            System.out.println(message);

            entrada.close();
            salida.close();
            teclado.close();
            clinte.close();
        }catch (Exception e){}
    }

    /**
     * Abre la ventana para agregar un archivo a la carpeta de biblioteca
     * @param event detecta cuando se activa el boton asignado a este metodo
     */
    public void saveFile(ActionEvent event){
        InputStream inputStream = null;
        OutputStream outputStream = null;

        //System.out.println("File Added");
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("C:\\Users\\luisg\\OneDrive\\Documentos\\TEC\\2022\\Segundo semestre 2022\\Datos I\\Proyectos\\ServidorProyecto\\Biblioteca")); //sets current directory

        int response = fileChooser.showOpenDialog(null); //select file to open
        //int response = fileChooser.showSaveDialog(null); //select file to save

        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file.getName());
            System.out.println(file);
            try {
                File archivoOriginal = new File(file.getAbsolutePath());
                File archivoCopia = new File("C:\\Users\\luisg\\OneDrive\\Documentos\\TEC\\2022\\Segundo semestre 2022\\Datos I\\Proyectos\\ServidorProyecto\\Biblioteca\\"+file.getName());
                inputStream = new FileInputStream(archivoOriginal);
                outputStream = new FileOutputStream(archivoCopia);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.close();
                System.out.println("Archivo copiado.");
            } catch (IOException ev) {
                ev.printStackTrace();
            }
        }

    }

    /**
     * Abre la ventana para eliminar un archivo a la carpeta de biblioteca
     * @param event detecta cuando se activa el boton asignado a este metodo
     */

    public void deleteFile(ActionEvent event){
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("C:\\Users\\luisg\\OneDrive\\Documentos\\TEC\\2022\\Segundo semestre 2022\\Datos I\\Proyectos\\ServidorProyecto\\Biblioteca")); //sets current directory

        int response = fileChooser.showOpenDialog(null); //select file to open

        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file.getName());
            file.delete();
            System.out.println(file);
            System.out.println("File Removed");
        }
        else{
            System.out.println("Failed Removing File");
        }
    }

    /**
     * metodo que toma el string de conexion ordenado mediante mediante quicksort y carga al listview
     * @param event detecta cuando se activa el boton asignado a este metodo
     */

    public void btn_quicksort(ActionEvent event){

        String[] palabra= message.split("[ \\u0012 , ; ?!¡¿\\'\\\"\\\\[\\\\]]+");
        for (String item: palabra){
            if (item != " "){
                System.out.println(item);
            }

        }


        resultados.getItems().add(message);

    }


    /**
     * metodo que cierra el programa
     * @param event detecta cuando se activa el boton asignado a este metodo
     */
    public void salir(ActionEvent event){
        Platform.exit();
    }
}
