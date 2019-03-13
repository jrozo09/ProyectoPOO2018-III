/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import GestionArchivos.GestionArchivo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Escenario{
    private EscenarioVista ventana;
    private GestionArchivo archivo;

    public Escenario() throws FileNotFoundException {
        this.archivo = new GestionArchivo();
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
            try {ArrayList<Integer> puntajes = archivo.cargar();
                puntajes.add(ventana.getJuego().getPuntaje());
                archivo.guardar(puntajes);
            } catch (IOException ex) {
                Logger.getLogger(Escenario.class.getName()).log(Level.SEVERE, null, ex);
            }
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
