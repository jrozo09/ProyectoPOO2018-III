/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import Interfaz.ControladorVentanaGanador;
import Interfaz.ControladorVentanaPerdedor;
import GestionArchivos.GestionArchivo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Clase encargada de ejecutar las acciones del juego en un bucle. Asímismo, 
 * mustra la interfaz gráfica del juego.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Arias
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class LoopJuego extends AnimationTimer implements Runnable{
    private Cronometro cronometro;
    private boolean cont;
    private Nivel nivel;
    private Scene escena; //Para controlar los eventos del teclado y para el cambio de nivel.
    private GraphicsContext lapiz; //Objeto con el que se va a "dibujar" en el canvas
    private Image Fondo; //Imagen de fondo
    private int numNivel = 1;
    private int puntaje = 0;
    private int i=0;
    private GestionArchivo archivo;
    private int c =0;
    
    /**
     * Constructor de la calse para inicializar los atributos.
     * @param escena
     * @param lapiz
     * @throws IllegalArgumentException
     * @throws FileNotFoundException 
     * @since AgroBomberman 1.0
     */
    public LoopJuego(Scene escena, GraphicsContext lapiz) throws IllegalArgumentException, 
            FileNotFoundException{
        this.escena = escena;
        this.lapiz = lapiz;
        this.nivel = new Nivel(escena,50, 50, "ImagenesJuego/Campesino_normal.png", 
                "ImagenesJuego/Enemigo_N1_aba.png", this.lapiz,"ImagenesJuego/Pared.png",7,
                3, puntaje);
        this.Fondo = new Image(new FileInputStream("ImagenesJuego/Fondo.png"));
        //this.Enemigo = new Image(Enemigo);
        this.cronometro = new Cronometro();
        this.cont = true;
        this.archivo = new GestionArchivo();
    }
    
    /**
     * Método encargado de poner en un buccle la ejecución de las instrucciones 
     * del juego.
     * @param now 
     * 
     */
    @Override
    public void handle(long now) {
        //Se establece el fondo.
        lapiz.drawImage(this.Fondo, 0, 0 );
        
        //dibujar cronometro
        lapiz.setFill(Color.WHITE);
        lapiz.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        if(this.cont){
            cronometro.start();
            this.cont = false;
        }
        lapiz.fillText(cronometro.getCadena(),900, 40);
        
        //dibujar el nivel donde se encuentra
        lapiz.fillText("Nivel: " + this.numNivel,700, 40);
        
        //dibujar el puntaje acumulado
        lapiz.fillText("Puntaje: " + this.puntaje,400, 40);
        
        
        if ((this.cronometro.getMinutos()==0)&&(this.cronometro.getSegundos()==0)) {
            this.nivel.getCampesino().setVidas(this.nivel.getCampesino().getVidas()-1);
            this.cronometro.setMinutos(4);
            this.cronometro.setSegundos(0);
        }
        /*Se implementa el siguiente fragmento de codigo para poder visulizar
        las formas de los elementos del juego y así poder detectar(visualmente)
        cuando halla una colisión.
        */
        /*for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if ((i==0)||(i==13)) {
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    lapiz.strokeRect(50*j, 50*i, 50, 50);
                }else if(i%2!=0){
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    lapiz.strokeRect(50*j, 50*i, 50, 50);
                }else if(i%2==0){
                    if (j%2==0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        lapiz.strokeRect(50*j, 50*i, 50, 50);
                    }else if (j%2!=0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        lapiz.strokeRect(50*j, 50*i, 50, 50);
                    }
                }
            }
        }*/
        //Metodo para dibujas las vidas del campesino
        try{
            this.nivel.DibujarVidas();
        }catch(FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        //Metodo para dibujar las paredes
        this.nivel.dibujarParedes();
        run();
    }      
    
    /**
     * Método encargado de manejar los hilos del juego.
     */
    @Override 
    public void run(){
        try{
            if (this.nivel.powerUp()&&this.c==0) {
                this.nivel.getCampesino().setPowerUp(true);
                c++;
            }
            //Metodo para mover el campesiono de acuerdo a las distintas animaciones
            this.nivel.moverCampesino(this.numNivel);
            //Metodos para reportar si hay colision entre el personaje y algun muro
            //Se detecctan las colisiones superior-inferior y laterales por separado.
            this.nivel.DetectarColisionLadosCampesino();
            this.nivel.DetectarColisionSuperiorCampesino();
            //Metodo para crear la semilla
            this.nivel.PonerSemilla();
            //Metodo para mover los enemigos del juego.
            for (int i = 0; i < this.nivel.getEnemigos().size(); i++) {
                this.nivel.moverEnemigos(i,this.numNivel);
            }
        }catch(FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        //Metodo para detectar la colision del campesino con algún enemigo
        this.nivel.colisionCampesinoEnemigo();
        int j=this.i;
        while(this.i <(this.nivel.getNumEnemigos()-this.nivel.getEnemigos().size())){
            this.i++;
        }
        if(j != this.i){
            this.puntaje = this.puntaje+(100*(this.nivel.getNumEnemigos()-this.nivel.getEnemigos().size()-j));
        }else{
            this.puntaje = this.puntaje+(100*(this.nivel.getNumEnemigos()-this.nivel.getEnemigos().size()-this.i));
        }
        if (this.nivel.getCampesino().getVidas()==0) {
            stop();
        }
        //Metodo para rectificar cuando el campesino pase de nivel.
        if ((this.nivel.cambiarNivel())&&(this.numNivel==1)&&this.nivel.getEnemigos().size()<1) {
            setNumNivel(2);
            this.i = 0;
            Nivel nivel;
            try {
                nivel = new Nivel(this.escena, 50, 50, "ImagenesJuego/Campesino_normal.png", 
                        "ImagenesJuego/Enemigo_N2_aba.png", this.lapiz,"ImagenesJuego/Pared.png",10
                ,this.nivel.getCampesino().getVidas(),this.puntaje);
                setNivel(nivel);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if((this.nivel.cambiarNivel())&&(this.numNivel==2) && this.nivel.getEnemigos().size()<1){
            try {
                setNumNivel(3);
                this.i = 0;
                Nivel nivel = new Nivel(this.escena, 50, 50, "ImagenesJuego/Campesino_normal.png",
                        "ImagenesJuego/Enemigo_N3_aba.png", this.lapiz,"ImagenesJuego/Pared.png",15,
                this.nivel.getCampesino().getVidas(),puntaje);
                setNivel(nivel);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if((this.nivel.cambiarNivel())&&(this.numNivel==3)){
            try {
                ArrayList<Integer> puntajes = archivo.cargar();
                puntajes.add(this.puntaje);
                archivo.guardar(puntajes);
            } catch (IOException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
            ControladorVentanaGanador controlador;
            try {
                controlador = new ControladorVentanaGanador();
                controlador.mostrarVistaGanador();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(this.nivel.getCampesino().getVidas()==0){
            try {
                ArrayList<Integer> puntajes = archivo.cargar();
                puntajes.add(this.puntaje);
                archivo.guardar(puntajes);
            } catch (IOException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                ControladorVentanaPerdedor controlador 
                        = new ControladorVentanaPerdedor();
                controlador.mostrarVistaGanador();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoopJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Método que permite cambiar el número de nivel en el que va el jugador.
     * @param numNivel
     * @since AgroBomberman 1.0
     */
    public void setNumNivel(int numNivel) {
        this.numNivel = numNivel;
    }
    
    /**
     * Método que permite cambiar de nivel.
     * @param nivel
     * @since AgroBomberman 1.0
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }
    
    /**
     * Metodo que retorna el objeto cronometro.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Cronometro getCronometro() {
        return cronometro;
    }

    public int getPuntaje() {
        return puntaje;
    }
    
    
    
    /**
     * Metodo que cambia el valor del booleano cont.
     * @param cont 
     * @since AgroBomberman 1.0
     */
    public void setCont(boolean cont) {
        this.cont = cont;
    }
    
    /**
     * Metodo que retorna el objeto nivel.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Nivel getNivel() {
        return nivel;
    }

    
}
