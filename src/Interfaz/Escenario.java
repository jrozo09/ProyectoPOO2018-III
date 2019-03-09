/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Funcional.LoopJuego;
import com.sun.glass.ui.Size;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clase encargada de crear el escenario y el Canvas del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class Escenario{
    private EscenarioVista ventana;

    public Escenario() throws FileNotFoundException{
        this.ventana =  
                new EscenarioVista();
        this.ventana.getImg1()
                .setOnMousePressed(new EventoMouse6());
        this.ventana.getImg2()
                .setOnMousePressed(new EventoMouse7());
        this.ventana.getImg3()
                .setOnMousePressed(new EventoMouse8());
    }
    
    public void mostrarVista(){
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrar(singleton.getStage());
    }

    class EventoMouse6 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            Singleton singleton = Singleton.getSingleton();
            ventana.pausar(singleton.getStage());
        }
        
    } 
    
    class EventoMouse7 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            Singleton singleton = Singleton.getSingleton();
            ventana.mostrar(singleton.getStage()); 
        }
        
    } 
    
    class EventoMouse8 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                ControladorVentanaPrincipal controlador = 
                        new ControladorVentanaPrincipal();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Escenario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    } 
}
