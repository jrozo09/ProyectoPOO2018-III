/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase encargada de dscribir las características de los enemigos del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Enemigo extends Personaje {
    
    private String orientacion;
    
    /**
     * Contructor de la clase encargado de inicializar los atributos.
     * @param orientacion dirección el cual está mirando el enemigo.
     * @param posicionX posición en X del enemigo.
     * @param posicionY posición en Y del enemigo.
     * @param imagen imagen con la que se define el aspecto del enemigo en el juego.
     * @param torso contorno del enemigo en el juego.
     * @param lapiz 
     * @since AgroBomberman 1.0
     */
    public Enemigo(String orientacion, int posicionX, int posicionY, 
            Image imagen, Shape torso,GraphicsContext lapiz) {
        super(posicionX, posicionY, imagen, torso);
        this.orientacion = orientacion;
    }
    
    /**
     * Metodo que retorna un String con la dirección en la cual está mirando el enemigo.
     * @return 
     * @since AgroBomberman 1.0
     */
    public String getOrientacion() {
        return orientacion;
    }
    
    /**
     * Metodo que permite cambiar la dirección en la cual está mirando el enemigo.
     * @param orientacion 
     * @since AgroBomberman 1.0
     */
    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }
}
