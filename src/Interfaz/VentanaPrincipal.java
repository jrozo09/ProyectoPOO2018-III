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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author emlad
 */
public class VentanaPrincipal {
    private Scene escena;
    private ImageView img0, img2, img3;

    public VentanaPrincipal() throws FileNotFoundException {
        Text titulo = new Text("AgroBomberman");
        titulo.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 50));
        Text instrucciones = new Text("Te Mueves \nCon las Flechas \nPones La Bomba \ncon X");
        instrucciones.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 20));
        instrucciones.setTextAlignment(TextAlignment.RIGHT);
        instrucciones.setFill(Color.WHITE);
        HBox hbox =  new HBox(instrucciones);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.setMaxSize(1000, 350);

        this.img0 = new ImageView(new Image(new FileInputStream("ImagenesJuego/nueva-partida.png")));

        this.img2 = new ImageView(new Image(new FileInputStream("ImagenesJuego/mejores-puntajes.png")));

        this.img3 = new ImageView(new Image(new FileInputStream("ImagenesJuego/salir.png")));
        VBox vbox1 = new VBox(img0, img2, img3);
        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox1.setSpacing(30);
        VBox vbox0 = new VBox(titulo, vbox1);
        vbox0.setAlignment(Pos.CENTER);
        vbox0.setSpacing(150);

        StackPane stack = new StackPane(new ImageView(new Image
                (new FileInputStream("ImagenesJuego/pantalla-inicio.png")))
                , hbox, vbox0);
        this.escena = new Scene(stack, 1050, 650, Color.STEELBLUE);
    }

    public ImageView getImg0() {
        return img0;
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
