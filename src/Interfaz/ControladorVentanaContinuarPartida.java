/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author emlad
 */
public class ControladorVentanaContinuarPartida {
    private VentanaContinuarPartida ventana;

    public ControladorVentanaContinuarPartida() throws FileNotFoundException {
        this.ventana = new VentanaContinuarPartida();
        this.ventana.getImg5()
                .setOnMousePressed(new EventoMouse4());
        
    }
    
    public void mostrarVista(){
        Singleton singleton = Singleton.getSingleton();
        this.ventana.mostrar(singleton.getStage());
    }
    
    class EventoMouse4 implements EventHandler<MouseEvent>{

        @Override
        public void handle(MouseEvent t) {
            try {
                ControladorVentanaPrincipal controlador =
                        new ControladorVentanaPrincipal();
                controlador.mostrarVista();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ControladorVentanaContinuarPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
