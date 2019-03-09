/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author emlad
 */
public class InterfaceAVerSiFunciona extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Singleton singleton = Singleton.getSingleton();
        singleton.setStage(primaryStage);

        ControladorVentanaPrincipal controlador = new ControladorVentanaPrincipal();
        controlador.mostrarVista();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Application.launch(args);
        }catch(IllegalArgumentException i){
            System.out.println("Error en la direccion de una imagen...");
        }catch (NullPointerException n){
            System.out.println("Error, se está apuntando a un objeto nulo...");
        }
    }

}
