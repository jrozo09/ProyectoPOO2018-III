/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.shape.Shape;

/**
 *
 * @author Juanes
 */
public class Semilla {
    private int posX;
    private int posY;
    private Shape arriba;
    private Shape abajo;
    private Shape derecha;
    private Shape izquierda;

    public Semilla(int posX, int posY, Shape arriba, Shape abajo, Shape derecha, Shape izquierda) {
        this.posX = posX;
        this.posY = posY;
        this.arriba = arriba;
        this.abajo = abajo;
        this.derecha = derecha;
        this.izquierda = izquierda;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Shape getArriba() {
        return arriba;
    }

    public void setArriba(Shape arriba) {
        this.arriba = arriba;
    }

    public Shape getAbajo() {
        return abajo;
    }

    public void setAbajo(Shape abajo) {
        this.abajo = abajo;
    }

    public Shape getDerecha() {
        return derecha;
    }

    public void setDerecha(Shape derecha) {
        this.derecha = derecha;
    }

    public Shape getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Shape izquierda) {
        this.izquierda = izquierda;
    }
    
    
}
