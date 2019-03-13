/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import GestionArchivos.GestionArchivo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
import javafx.stage.Stage;

/**
 *
 * @author emlad
 */
public class VentanaMejores {
    private Scene escena;
    private ImageView img2;
    private GestionArchivo archivo;

    public VentanaMejores() throws FileNotFoundException {
        this.archivo = new GestionArchivo();
        ImageView img0 = new ImageView(new Image(new FileInputStream("ImagenesJuego/pantalla-tres.png")));

        ImageView img1 = new ImageView(new Image(new FileInputStream("ImagenesJuego/mejores-puntajes-3.png")));

        this.img2 = new ImageView(new Image(new FileInputStream("ImagenesJuego/regresar-3.png")));
        Text texto0 = new Text(null);
        texto0.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto1 = new Text(null);
        texto1.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto2 = new Text(null);
        texto2.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto3 = new Text(null);
        texto3.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto4 = new Text(null);
        texto4.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto5 = new Text(null);
        texto5.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto6 = new Text(null);
        texto6.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto7 = new Text(null);
        texto7.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto8 = new Text(null);
        texto8.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text texto9 = new Text(null);
        texto9.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        
        Text[] textos = new Text[10];
        textos[0] = texto0;
        textos[1] = texto1;
        textos[2] = texto2;
        textos[3] = texto3;
        textos[4] = texto4;
        textos[5] = texto5;
        textos[6] = texto6;
        textos[7] = texto7;
        textos[8] = texto8;
        textos[9] = texto9;
        
        int i = 0;
        
        ArrayList<Integer> puntajes = archivo.cargar();
        for(Integer inter : puntajes){
            
            textos[i].setText(inter.toString());
            i++;
        }
        
        VBox vbox0 = new VBox();
        vbox0.getChildren().addAll(img1, texto0, texto1, texto2, texto3, 
                texto4, texto5, texto6, texto7, texto8, texto9, img2);
        vbox0.setAlignment(Pos.CENTER);
        vbox0.setSpacing(5);
        
        StackPane stack = new StackPane(img0, vbox0);
        
        this.escena = new Scene(stack, 1050, 650, Color.STEELBLUE);
        
    }

    public ImageView getImg2() {
        return img2;
    }
    
    public void mostrar(Stage stage){
        stage.setTitle("Ventana Mejores Puntajes");
        stage.setScene(escena);
        stage.show();
    }
    
}
