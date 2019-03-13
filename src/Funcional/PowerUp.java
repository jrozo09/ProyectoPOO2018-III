/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Juanes
 */
public class PowerUp {
    private int posicionX;
    private int posicionY;
    private Image image;
    private Shape contorno;

    public PowerUp(int posicionX, int posicionY, Image image,Shape contorno) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.image = image;
        this.contorno=contorno;
    }

    public Shape getContorno() {
        return contorno;
    }

    
    public Image getImage() {
        return image;
    }
}
