/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.shape.Shape;

/**
 * Clase que define los muros que pueden ser o muros fijos o paredes.
 * @@author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Muro {
    private int posicionX;
    private int posicionY;
    private Shape forma;
    
    /**
     * Contructor de la clase encargado de inicializar los atributos.
     * @param posicionX posición en el eje X del muro.
     * @param posicionY posición en el eje Y del muro.
     * @param forma contorno del muro.
     * @since AgroBomberman 1.0
     */
    public Muro(int posicionX, int posicionY, Shape forma) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.forma = forma;
    }
    
    /**
     * Metodo que retorna un objeto de tipo Shape que corresponde al ccontorno 
     * del muro.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Shape getForma() {
        return forma;
    }
    
    /**
     * Método que retorna la posición del muro en X.
     * @return 
     * @since AgroBomberman 1.0
     */
    public int getPosicionX() {
        return posicionX;
    }
    
    /**
     * Método que retorna la posición del muro en Y.
     * @return 
     * @since AgroBomberman 1.0
     */
    public int getPosicionY() {
        return posicionY;
    }
    
    
    
}
