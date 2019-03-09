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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author emlad
 */
public class VentanaMejores {
    private Scene escena;
    private FileInputStream input0, input1, input2;
    private Image imagen0, imagen1, imagen2;
    private ImageView img0, img1, img2;
    private Text texto0, texto1, texto2, texto3, texto4, texto5, texto6, texto7
            , texto8, texto9;
    private VBox vbox0;
    private StackPane stack;

    public VentanaMejores() throws FileNotFoundException {
        this.input0 = new FileInputStream("ImagenesJuego/pantalla-tres.png");
        this.imagen0 = new Image(input0);
        this.img0 = new ImageView(imagen0);
        
        this.input1 = new FileInputStream("ImagenesJuego/mejores-puntajes-3.png");
        this.imagen1 = new Image(input1);
        this.img1 = new ImageView(imagen1);
        
        this.input2 = new FileInputStream("ImagenesJuego/regresar-3.png");
        this.imagen2 = new Image(input2);
        this.img2 = new ImageView(imagen2);
        
        this.texto0 = new Text(null);
        this.texto1 = new Text(null);
        this.texto2 = new Text(null);
        this.texto3 = new Text(null);
        this.texto4 = new Text(null);
        this.texto5 = new Text(null);
        this.texto6 = new Text(null);
        this.texto7 = new Text(null);
        this.texto8 = new Text(null);
        this.texto9 = new Text(null);
        
        this.vbox0 = new VBox();
        this.vbox0.getChildren().addAll(img1, texto0, texto1, texto2, texto3, 
                texto4, texto5, texto6, texto7, texto8, texto9, img2);
        this.vbox0.setAlignment(Pos.CENTER);
        this.vbox0.setSpacing(30);
        
        this.stack = new StackPane(img0, vbox0);
        
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
