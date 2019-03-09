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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
public class VentanaContinuarPartida {
    private Scene escena;
    private Text texto1, texto2, texto3, texto4, texto5, texto6, texto7
            , texto8, texto9, texto10, texto11, texto12;
    private FileInputStream input0, input1, input2, input3, input4, input5
            , input6, input7;
    private Image imagen0, imagen1, imagen2, imagen3, imagen4, imagen5
            , imagen6, imagen7;
    private ImageView img0, img1, img2, img3, img4, img5, img6;
    private HBox hbox0, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6;
    private VBox vbox0, vbox1, vbox2, vbox3, vbox4;
    private StackPane stack;

    public VentanaContinuarPartida() throws FileNotFoundException {
        this.input0 = new FileInputStream("ImagenesJuego/partidas.png");
        this.imagen0 = new Image(input0);
        this.img0 = new ImageView(imagen0);
        
        
        this.input1 = new FileInputStream("ImagenesJuego/icon-partida.png");
        this.imagen1 = new Image(input1);
        this.img1 = new ImageView(imagen1);
        
        this.input2 = new FileInputStream("ImagenesJuego/icon-partida.png");
        this.imagen2 = new Image(input2);
        this.img2 = new ImageView(imagen2);
        
        this.input3 = new FileInputStream("ImagenesJuego/icon-partida.png");
        this.imagen3 = new Image(input3);
        this.img3 = new ImageView(imagen3);
        
        this.input4 = new FileInputStream("ImagenesJuego/icon-partida.png");
        this.imagen4 = new Image(input4);
        this.img4 = new ImageView(imagen4);
        
        this.texto1 = new Text("Partida Nº1");
        this.texto1.setFont(Font.font(null, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.texto2 = new Text("nivel 2");
        this.texto3 = new Text("2 vidas");
        
        this.vbox1 = new VBox(texto1, texto2, texto3);
        this.vbox1.setAlignment(Pos.CENTER_LEFT);
        this.vbox1.setSpacing(10);
        this.hbox3 = new HBox(img1,vbox1);
        this.hbox3.setAlignment(Pos.CENTER);
        this.hbox3.setSpacing(20);
        
        
        this.texto4 = new Text("Partida Nº2");
        this.texto4.setFont(Font.font(null, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.texto5 = new Text("nivel 1");
        this.texto6 = new Text("1 vida");
        
        this.vbox2 = new VBox(texto4, texto5, texto6);
        this.vbox2.setAlignment(Pos.CENTER_LEFT);
        this.vbox2.setSpacing(10);
        this.hbox4 = new HBox(img2,vbox2);
        this.hbox4.setAlignment(Pos.CENTER);
        this.hbox4.setSpacing(20);
        
    
        this.texto7 = new Text("Partida Nº3");
        this.texto7.setFont(Font.font(null, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.texto8 = new Text(null);
        this.texto9 = new Text(null);
        
        this.vbox3 = new VBox(texto7, texto8, texto9);
        this.vbox3.setAlignment(Pos.CENTER_LEFT);
        this.vbox3.setSpacing(10);
        this.hbox5 = new HBox(img3,vbox3);
        this.hbox5.setAlignment(Pos.CENTER);
        this.hbox5.setSpacing(20);
        
        
        this.texto10 = new Text("Partida Nº4");
        this.texto10.setFont(Font.font(null, FontWeight.MEDIUM, FontPosture.REGULAR, 20));
        this.texto11 = new Text(null);
        this.texto12 = new Text(null);
        
        this.vbox4 = new VBox(texto10, texto11, texto12);
        this.vbox4.setAlignment(Pos.CENTER_LEFT);
        this.vbox4.setSpacing(10);
        this.hbox6 = new HBox(img4,vbox4);
        this.hbox6.setAlignment(Pos.CENTER);
        this.hbox6.setSpacing(20);
        
        
        this.input5 = new FileInputStream("ImagenesJuego/regresar 2.png");
        this.imagen5 = new Image(input5);
        this.img5 = new ImageView(imagen5);
        
    
        this.hbox1 = new HBox(hbox3,hbox4);
        this.hbox1.setAlignment(Pos.CENTER);
        this.hbox1.setSpacing(350);
        this.hbox2 = new HBox(hbox5,hbox6);
        this.hbox2.setAlignment(Pos.CENTER);
        this.hbox2.setSpacing(350);
        
        this.vbox0 = new VBox(img0,hbox1,hbox2,img5);
        this.vbox0.setAlignment(Pos.CENTER);
        this.vbox0.setSpacing(90);
        
        this.input6 = new FileInputStream("ImagenesJuego/pantalla-dos.png");
        this.imagen6 = new Image(input6);
        this.img6 = new ImageView(imagen6);
        this.stack = new StackPane(img6, vbox0);
        this.escena = new Scene(stack, 1050, 650, Color.STEELBLUE);
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

    public ImageView getImg4() {
        return img4;
    }

    public ImageView getImg5() {
        return img5;
    }
    
    public void mostrar(Stage stage){
        stage.setTitle("Ventana Continuar Partida");
        stage.setScene(escena);
        stage.show();
    }
}
