/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * Subclase de la super clase Personaje.Clase encargada de gestionar la 
 * información del personaje principal del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Campesino extends Personaje{
    
    private boolean powerUp;
    private Semilla semillas;
    private int vidas;

    /**
     * Constructor de la clase encargado de inicializar los atributos.
     * @param powerUp Entero que incrementará el alcance de la explosión de la 
     * semilla del campesino.
     * @param semillas Semillas
     * @param vidas Numero de vidas del campsino.
     * @param posicionX Posición en X del campesino.
     * @param posicionY Posición en Y del campesino.
     * @since AgroBomberman 1.0
     */
    public Campesino(boolean powerUp, Semilla semillas, int vidas, int posicionX, 
            int posicionY, Image imagen,Shape torso,GraphicsContext lapiz,Scene escena) {
        super(posicionX, posicionY, imagen,torso);
        this.powerUp = powerUp;
        this.semillas = semillas;
        this.vidas = vidas;
    
    }    
    /**
     * Metodo que retorna el valor del powerUp.
     * @return 
     */
    public boolean getPowerUp() {
        return powerUp;
    }
    
    /**
     * Metodo que cambia el valor del powerUp:
     * @param powerUp 
     */
    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    
    /**
     * Metodo que retorna la cantidad de vidas del personaje.
     * @return 
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * Metodo que cambia la cantidad de vidas del personaje.
     * @param vidas 
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public Semilla getSemillas() {
        return semillas;
    }

    public void setSemillas(Semilla semillas) {
        this.semillas = semillas;
    }
    @Override
    public void moverDerecha() {
        this.posicionX= this.posicionX+2;
    }
    
    /**
     * Metodo encargado de cambiar la posicion en X (negativo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverIzquierda() {
        this.posicionX= this.posicionX-2;
    }

    /**
     * Metodo encargado de cambiar la posicion en Y (negativo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverArriba() {
        this.posicionY= this.posicionY-2;
    }
    
    /**
     * Metodo encargado de cambiar la posicion en Y (positivo) del personaje.
     * @since AgroBomberman 1.0
     */
    public void moverAbajo() {
        this.posicionY= this.posicionY+2;
    }

}
