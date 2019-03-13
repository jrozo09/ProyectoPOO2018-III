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
    private ImageView img2;

    public VentanaMejores() throws FileNotFoundException {
        ImageView img0 = new ImageView(new Image(new FileInputStream("ImagenesJuego/pantalla-tres.png")));

        ImageView img1 = new ImageView(new Image(new FileInputStream("ImagenesJuego/mejores-puntajes-3.png")));

        this.img2 = new ImageView(new Image(new FileInputStream("ImagenesJuego/regresar-3.png")));
        
        Text texto0 = new Text(null);
        Text texto1 = new Text(null);
        Text texto2 = new Text(null);
        Text texto3 = new Text(null);
        Text texto4 = new Text(null);
        Text texto5 = new Text(null);
        Text texto6 = new Text(null);
        Text texto7 = new Text(null);
        Text texto8 = new Text(null);
        Text texto9 = new Text(null);
        
        VBox vbox0 = new VBox();
        vbox0.getChildren().addAll(img1, texto0, texto1, texto2, texto3, 
                texto4, texto5, texto6, texto7, texto8, texto9, img2);
        vbox0.setAlignment(Pos.CENTER);
        vbox0.setSpacing(30);
        
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
