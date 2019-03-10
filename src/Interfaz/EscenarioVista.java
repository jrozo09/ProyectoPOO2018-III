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
import javafx.scene.control.Button;
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
    private StackPane stack;
    private VBox vbox0;
    private HBox hbox0, hbox1;
    private FileInputStream input0, input1, input2, input3;
    private Image imagen0, imagen1, imagen2, imagen3;
    private ImageView img0, img1, img2, img3;
    private Text titulo;
    
    public EscenarioVista() throws FileNotFoundException {
        this.titulo = new Text("AgroBomberman");
        this.titulo.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 50));
        this.input1 = new FileInputStream("ImagenesJuego/pausa.png");
        this.imagen1 = new Image(input1);
        this.img1 = new ImageView(imagen1);
        this.input2 = new FileInputStream("ImagenesJuego/continuar.png");
        this.imagen2 = new Image(input2);
        this.img2 = new ImageView(imagen2);
        this.hbox1 = new HBox(img2, img1);
        this.hbox1.setAlignment(Pos.CENTER);
        this.hbox1.setSpacing(30);
        this.input3 = new FileInputStream("ImagenesJuego/regresar 2.png");
        this.imagen3 = new Image(input3);
        this.img3 = new ImageView(imagen3);
        this.hbox0 = new HBox(img3, titulo, hbox1);
        this.hbox0.setAlignment(Pos.CENTER);
        this.hbox0.setSpacing(110);
        this.input0 = new FileInputStream("ImagenesJuego/fondoPausa.png");
        this.imagen0 = new Image(input0);
        this.img0 = new ImageView(imagen0);
        
        
        this.stack = new StackPane(img0,hbox0);
        Pane layout  = new Pane();  
        Canvas canvas = new Canvas(1052,650);
        layout.getChildren().add(canvas);
        this.vbox0 = new VBox(stack, layout);
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

    
    
    
}
