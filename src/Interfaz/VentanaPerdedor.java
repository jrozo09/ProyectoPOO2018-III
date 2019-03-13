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
public class VentanaPerdedor {
    private Scene escena;
    private ImageView img1, img2;
    
    public VentanaPerdedor() throws FileNotFoundException {
        Text titulo = new Text("AgroBomberman");
        titulo.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 50));

        Text ganador = new Text("Lo siento :( \nHas perdido");
        ganador.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 60));
        ganador.setTextAlignment(TextAlignment.CENTER);
        ganador.setFill(Color.YELLOW);
        
        this.img1 =new ImageView(new Image(new FileInputStream("ImagenesJuego/salir.png")));
        this.img2 =new ImageView(new Image(new FileInputStream("ImagenesJuego/nueva-partida.png")));
        
        VBox vbox = new VBox(titulo,ganador,img2, img1);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        
        ImageView img0 =new ImageView(new Image(new FileInputStream("ImagenesJuego/pantalla-inicio.png")));
        StackPane stack = new StackPane(img0, vbox);
        this.escena = new Scene(stack, 1050, 650, Color.STEELBLUE);
        
    }
     
    public void mostrarGanador(Stage stage) throws FileNotFoundException{
        stage.setTitle("Ventana Principal");
        stage.setScene(escena);
        stage.show();
    }

    public ImageView getImg1() {
        return img1;
    }

    public ImageView getImg2() {
        return img2;
    }
    
    
}
