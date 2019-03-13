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
public class ControladorVentanaPerdedor {
    private VentanaPerdedor ventana;
    
    public ControladorVentanaPerdedor() throws FileNotFoundException {
        this.ventana = new VentanaPerdedor();
        this.ventana.getImg1()
                .setOnMousePressed(new EventoMouse11());
        this.ventana.getImg2()
                .setOnMousePressed(new EventoMouse12());
        
    }
    
    public void mostrarVistaGanador() throws FileNotFoundException{
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrarGanador(singleton.getStage());
    }
    
    class EventoMouse11 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                ControladorVentanaPrincipal controlador 
                        = new ControladorVentanaPrincipal();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaPerdedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    } 
    
    class EventoMouse12 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                Escenario controlador 
                        = new Escenario();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaPerdedor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    } 
}
