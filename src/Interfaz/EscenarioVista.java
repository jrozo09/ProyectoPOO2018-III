/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Funcional.LoopJuego;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
public class EscenarioVista {
    private Scene escena;
    private LoopJuego juego;
    private ImageView img1, img2, img3;

    
    public EscenarioVista() throws FileNotFoundException {
        Text titulo = new Text("AgroBomberman");
        titulo.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 50));
        this.img1 = new ImageView(new Image(new FileInputStream("ImagenesJuego/pausa.png")));
        this.img2 = new ImageView(new Image(new FileInputStream("ImagenesJuego/continuar.png")));
        HBox hbox1 = new HBox(img2, img1);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setSpacing(30);
        this.img3 = new ImageView(new Image(new FileInputStream("ImagenesJuego/regresar 2.png")));
        HBox hbox0 = new HBox(img3, titulo, hbox1);
        hbox0.setAlignment(Pos.CENTER);
        hbox0.setSpacing(110);
        ImageView img0 = new ImageView(new Image(new FileInputStream("ImagenesJuego/fondoPausa.png")));
        
        
        StackPane stack = new StackPane(img0,hbox0);
        Pane layout  = new Pane();  
        Canvas canvas = new Canvas(1052,650);
        layout.getChildren().add(canvas);
        VBox vbox0 = new VBox(stack, layout);
        this.escena = new Scene(vbox0,1052,725, Color.WHITESMOKE);
        
        GraphicsContext lapiz = canvas.getGraphicsContext2D();
        this.juego = new LoopJuego(escena, lapiz);
    }
    
    public void mostrar(Stage stage){
        stage.setTitle("Ventana Principal");
        stage.setScene(escena);
        stage.show();
        juego.getCronometro().setActivo(true);        
        juego.start();
    }
    
    public void pausar(Stage stage){
        stage.setTitle("Ventana Principal");
        stage.setScene(escena);
        stage.show();
        juego.stop();
        juego.getCronometro().setActivo(false);
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

    public LoopJuego getJuego() {
        return juego;
    }
    
}
