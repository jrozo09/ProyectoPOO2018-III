/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author emlad
 */
public class VentanaPrincipal {
    private Scene escena;
    private Text titulo;
    private VBox vbox0, vbox1;
    private FileInputStream input0, input1, input2, input3, input4;
    private Image imagen0, imagen1, imagen2, imagen3, imagen4;
    private ImageView img0, img1, img2, img3;
    private StackPane stack;

    public VentanaPrincipal() throws FileNotFoundException {
        this.titulo = new Text("AgroBomberman");
        this.titulo.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 50));
        this.input0 = new FileInputStream("ImagenesJuego/nueva-partida.png");
        this.imagen0 = new Image(input0);
        this.img0 = new ImageView(imagen0);
        this.input1 = new FileInputStream("ImagenesJuego/continuar-partida.png");
        this.imagen1 = new Image(input1);
        this.img1 = new ImageView(imagen1);
        this.input2 = new FileInputStream("ImagenesJuego/mejores-puntajes.png");
        this.imagen2 = new Image(input2);
        this.img2 = new ImageView(imagen2);
        this.input3 = new FileInputStream("ImagenesJuego/salir.png");
        this.imagen3 = new Image(input3);
        this.img3 = new ImageView(imagen3);
        this.vbox1 = new VBox(img0, img1, img2, img3);
        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox1.setSpacing(30);
        this.vbox0 = new VBox(titulo, vbox1);
        vbox0.setAlignment(Pos.CENTER);
        vbox0.setSpacing(150);
        this.input4 = new FileInputStream("ImagenesJuego/pantalla-inicio.png");
        this.imagen4 = new Image(input4);
        this.stack = new StackPane(new ImageView(imagen4), vbox0);
        this.escena = new Scene(stack, 1050, 650, Color.STEELBLUE);
    }

    public ImageView getImg0() {
        return img0;
    }

    public ImageView getImg1() {
        return img1;
    }

    public ImageView getImg2() {
        return img2;
    }

    public ImageView getImg3() {
        return img3;
    }

    public void mostrar(Stage stage){
        stage.setTitle("Ventana Principal");
        stage.setScene(escena);
        stage.show();
    }
}
