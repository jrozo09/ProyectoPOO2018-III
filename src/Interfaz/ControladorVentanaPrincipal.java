/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author emlad
 */
public class ControladorVentanaPrincipal {
    private VentanaPrincipal ventana;

    public ControladorVentanaPrincipal() throws FileNotFoundException {
        this.ventana = new VentanaPrincipal();
        this.ventana.getImg0()
                .setOnMousePressed(new EventoMouse0());
        this.ventana.getImg2()
                .setOnMousePressed(new EventoMouse2());
        this.ventana.getImg3()
                .setOnMousePressed(new EventoMouse3());
    }
    
    public void mostrarVista(){
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrar(singleton.getStage());
    }
    
    class EventoMouse0 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                Escenario controlador = 
                        new Escenario();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } 
    
    class EventoMouse2 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                ControladorVentanaMejores controlador =
                        new ControladorVentanaMejores();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    } 
    
    class EventoMouse3 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            System.exit(0);
        }
        
    } 
}
