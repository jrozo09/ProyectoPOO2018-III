/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.shape.Shape;

/**
 * Clase encargada de gestionar la información de los muros fijos del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class MuroFijo extends Muro{
    
    /**
     * Contructor de la clase encargado de inicializar los atributos.
     * @param posicionX posición en el eje X del muro.
     * @param psocionY posición en el eje Y del muro.
     * @param forma contorno del muro.
     */
    public MuroFijo(int posicionX, int psocionY, Shape forma) {
        super(posicionX, psocionY, forma);
    }
    
}
