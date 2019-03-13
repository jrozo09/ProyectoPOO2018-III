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
public class ControladorVentanaGanador {
    private VentanaGanador ventana;
    
    public ControladorVentanaGanador() throws FileNotFoundException {
        this.ventana = new VentanaGanador();
        this.ventana.getImg1()
                .setOnMousePressed(new EventoMouse9());
        this.ventana.getImg2()
                .setOnMousePressed(new EventoMouse10());
        
    }
    
    public void mostrarVistaGanador() throws FileNotFoundException{
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrarGanador(singleton.getStage());
    }
    
    class EventoMouse9 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                ControladorVentanaPrincipal controlador 
                        = new ControladorVentanaPrincipal();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaGanador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    } 
    
    class EventoMouse10 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                Escenario controlador 
                        = new Escenario();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaGanador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } 
}
