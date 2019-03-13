/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import javafx.scene.image.Image;

/**
 *
 * @author Juanes
 */
public class PowerUp {
    private int posicionX;
    private int posicionY;
    private Image image;

    public PowerUp(int posicionX, int posicionY, Image image) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
