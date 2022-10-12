package com.example.clienteproyecto;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Conexion {

    Socket clinte;
    int puerto = 9000;
    String ip="192.168.0.13";
    BufferedReader entrada, teclado;
    PrintStream salida;

    //Codigo tomado de: https://www.youtube.com/watch?v=tsr53-zO73o
    public void inicio(){
        try{
            clinte=new Socket(ip,puerto);
            entrada=new BufferedReader(new InputStreamReader(clinte.getInputStream()));
            teclado=new BufferedReader(new InputStreamReader(System.in));
            String tec=teclado.readLine();
            salida=new PrintStream(clinte.getOutputStream());
            salida.println(tec);
            String message=entrada.readLine();
            System.out.println(message);

            entrada.close();
            salida.close();
            teclado.close();
            clinte.close();
        }catch (Exception e){}
    }
}
