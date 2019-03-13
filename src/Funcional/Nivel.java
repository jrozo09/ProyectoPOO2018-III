/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 * Clase encargada de gestionar la interfaz grafica que se muestra en pantalla y
 * controlar la funcionalidad del juego. 
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Nivel{
    private Scene escena;
    private Muro[][] muros;//Matriz de los muros tanto fijos como paredes de cada nivel.
    private ArrayList<Enemigo>enemigos;//Array de los enemigos.
    private Campesino campesino; //Personaje principal.
    private GraphicsContext lapiz;
    private ArrayList<String> pulsacionTeclado = null;//Array para gestionar los eventos del teclado
    private int secuencia = 0;
    private int numero = 0;
    private int numEnemigos;
    private int puntaje;
    //------------------------------
    private boolean kill=true;
    private int contador=0;
    //------------------------------
    private int posx;
    private int posy;
    private int numero2;
    private int secuencia2;
    private ArrayList<String> pulsacionTeclado2 = null;

    //------------------------------
    private int numero3; //Maneja la animación de la semilla
    
    /**
     * Constructor de la clase encargado de inicializar los atributos. Además 
     * crea los muros fijos del nivel del juego, los enemigos y al campesino.
     * @param escena
     * @param posicionX posición en X del campesino.
     * @param posicionY posición en X del campesino.
     * @param imgCampesino imágen con la que se va a observar el campesino en el
     * juego.
     * @param imgEnemigos imágen con la que se van a observar a los enemigos en el
     * juego.
     * @param lapiz
     * @param imgPared imágen con la que se van a observar las paredes en el
     * juego.
     * @param numEnemigos cantidad de enemigos del juego.
     * @param vidas número de vidas del campesino.
     * @throws FileNotFoundException 
     * @since AgroBomberman 1.0
     */
    public Nivel(Scene escena,int posicionX, int posicionY, String imgCampesino, String 
            imgEnemigos, GraphicsContext lapiz,String imgPared,int numEnemigos,int vidas,
            int puntaje) throws FileNotFoundException {
        this.escena = escena;
        this.muros = new Muro[13][21];
        this.enemigos = new ArrayList<>();
        this.campesino = new Campesino(false, null, vidas, posicionX, posicionY, new Image(new FileInputStream(imgCampesino)),null,
        lapiz,escena);
        this.lapiz = lapiz;
        this.pulsacionTeclado = new ArrayList<>();
        this.pulsacionTeclado2 = new ArrayList<>();
        this.numEnemigos = numEnemigos;
        this.posx = this.campesino.getPosicionX();
        this.posy = this.campesino.getPosicionY();
        
        this.puntaje = puntaje;

        this.numero3 = 0;
        
        //Se invoca el método para crear los muros tanto fijos como las paredes.
        crearMuros();
        //Se invoca el metodo para agragar las imagenes de las paredes del juego
        crearPared(imgPared);
        //Se crean los enemigos
        crearEnemigos(imgEnemigos);
        
        //Eventos pulsacion
        escena.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !pulsacionTeclado.contains(code) )
                        pulsacionTeclado.add( code );
                    if ( !pulsacionTeclado2.contains(code) )
                        pulsacionTeclado2.add( code );
                }
            });

        escena.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    pulsacionTeclado.remove( code );
                }
            });

    }
    
    /**
     * Método que retorna el Array de enemigos del juego.
     * @return 
     * @since AgroBomberman 1.0
     */
    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }
    /**
     * Método encargado de comparar las posiciones entre una pared y un enemigo
     * y así verificar si estas coinciden.
     * @param p pared con la que se va a comparar.
     * @return 
     * @since AgroBomberman 1.0
     */
    public boolean Rectificar(Pared p){
        for (int i = 0; i < this.enemigos.size(); i++) {
            if((this.enemigos.get(i).getPosicionX()==p.getPosicionX())&&(
                    this.enemigos.get(i).getPosicionY()==p.getPosicionY())){
                return false;
            }
        }
        return true;
    }
    /**
     * Metodo encargado de crear el campesino para poder visuliozarlo en el escenario.
     * @since AgroBomberman 1.0
     */
    public void DibujarCampesino() {
        /*lapiz.drawImage(this.campesino.getImagen(), 0, 50 * this.secuencia, 50, 51,
                this.campesino.getPosicionX(), this.campesino.getPosicionY(),
                51, 50);
        //Se crea la forma del campesino para detectar colisiones
        Shape torsoCampesino = new Rectangle(this.campesino.getPosicionX()+5,this.campesino.getPosicionY()+5, 40, 40);
        this.campesino.setTorso(torsoCampesino);*/
        lapiz.drawImage(this.campesino.getImagen(), 0, 50 * this.secuencia, 50, 51,
                this.campesino.getPosicionX(), this.campesino.getPosicionY(),
                51, 50);
        //Se crea la forma del campesino para detectar colisiones
        if (kill) {
            Shape torsoCampesino = new Rectangle(this.campesino.getPosicionX()+5,this.campesino.getPosicionY()+5, 40, 40);
            this.campesino.setTorso(torsoCampesino);
        }
    }
    /**
     * Metodo encargado de los eventos del teclado para poder mover al campesino.
     * @since AgroBomberman 1.0
     */
    public void moverCampesino(int numNivel) throws FileNotFoundException{
        /*//Eventos del teclado
        if (pulsacionTeclado.contains("LEFT"))
                   this.campesino.moverIzquierda();
                if (pulsacionTeclado.contains("RIGHT"))
                    this.campesino.moverDerecha();
                if (pulsacionTeclado.contains("UP"))
                    this.campesino.moverArriba();
                if (pulsacionTeclado.contains("DOWN"))
                    this.campesino.moverAbajo();
                
        //Se establecen las imagenes del personaje principal segun el sentido en el que se
        //mueva
        if(this.numero % 7 == 1){
                if(this.secuencia == 3){
                  this.secuencia = 0;
                }else{
                  this.secuencia++;
                }
        }
        if (pulsacionTeclado.contains("LEFT")){
            this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_izq.png")));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("RIGHT")) {
            this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_der.png")));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("UP")) {
            this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_arr.png")));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("DOWN")){
            this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_aba.png")));
            DibujarCampesino();
        }else{
            this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_normal.png")));
            DibujarCampesino();
        }
                
         this.numero++;*/
       
                
        //Se establecen las imagenes del personaje principal segun el sentido en el que se
        //mueva
        if(this.numero % 7 == 1){
                if(this.secuencia == 3){
                  this.secuencia = 0;
                }else{
                  this.secuencia++;
                }
        }
        if (kill) {
             //Eventos del teclado
            if (pulsacionTeclado.contains("LEFT"))
                       this.campesino.moverIzquierda();
                    if (pulsacionTeclado.contains("RIGHT"))
                        this.campesino.moverDerecha();
                    if (pulsacionTeclado.contains("UP"))
                        this.campesino.moverArriba();
                    if (pulsacionTeclado.contains("DOWN"))
                        this.campesino.moverAbajo();
            if (pulsacionTeclado.contains("LEFT")) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_izq.png")));
                DibujarCampesino();
            } else if (pulsacionTeclado.contains("RIGHT")) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_der.png")));
                DibujarCampesino();
            } else if (pulsacionTeclado.contains("UP")) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_arr.png")));
                DibujarCampesino();
            } else if (pulsacionTeclado.contains("DOWN")) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_aba.png")));
                DibujarCampesino();
            } else {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Campesino_normal.png")));
                DibujarCampesino();
            }
        }else{
            if (numNivel==1) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Muerte_N1.png")));
            }else if (numNivel==2) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Muerte_N2.png")));
            }else if (numNivel==3) {
                this.campesino.setImagen(new Image(new FileInputStream("ImagenesJuego/Muerte_N3.png")));
            }
            this.campesino.setTorso(null);
            DibujarCampesino();
            this.contador++;
            if (this.contador==100) {
                this.campesino.setVidas(this.campesino.getVidas()-1);
                this.kill =true;
                this.contador=0;
                this.campesino.setPosicionX(50);
                this.campesino.setPosicionY(50);
            }
        }
                
         this.numero++;
    }
    /**
     * Metodo encargado de crear los enemigos buscando casillas vacias para 
     * establecer sus posiciones iniciales.
     * @param imgEnemigos Imagen de los enemigos.
     * @throws FileNotFoundException
     * @since AgroBomberman 1.0
     */
    public void crearEnemigos(String imgEnemigos) throws FileNotFoundException{
        int c = this.numEnemigos;
        int x = 0;
        int y = 0; 
        boolean rectificarPos;
        while(c!=0){
            x = (int)Math.floor(Math.random()*(12-3+1)+3);
            y = (int)Math.floor(Math.random()*(20-3+1)+3);
            if(!this.muros[x][y].getClass().getName().equals("Funcional.MuroFijo")){
                Pared p = (Pared)this.muros[x][y];
                if(p.getImagen()==null){
                    rectificarPos = Rectificar(p);
                        if (rectificarPos) {
                            
                            Shape forma = new Rectangle(this.muros[x][y].getPosicionX()
                                    , this.muros[x][y].getPosicionY(), 50, 50);
                            Enemigo e = new Enemigo("Arriba", this.muros[x][y].getPosicionX(),
                            this.muros[x][y].getPosicionY(), new Image(new FileInputStream(imgEnemigos)), forma,
                            this.lapiz);
                            this.enemigos.add(e);
                            c--;
                        }
                    }
            }
        }
    }

    
    /**
     * Metodo encargado de añadir enemigos.
     * @param e
     * @return Retorna un valor booleano que retctifica la añadición del enemigo.
     * @since AgroBomberman 1.0
     */
    public boolean addEnemigos(Enemigo e){
        return this.enemigos.add(e);
    }

    /**
     * Se crean los muros tanto fijos como las paredes.
     * @since AgroBomberman 1.0
     */
    public void crearMuros(){
        //Se crean los muros y paredes del juego.
        //Las paredes se inicializan con la imagen en null para despues cambiar el estado de 
        //null para que tengan imagen y así impedir el paso.
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if ((i==0)||(i==12)) {
                    Shape f = new Rectangle(50*j, 50*i, 50, 50);
                    this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                }else if(i%2!=0){
                    if((j==0)||(j==20)){
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                    }else{
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new Pared(null, null, null, 50*j, 50*i, f);
                    }
                }else if(i%2==0){
                    if (j%2==0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new MuroFijo(50*j, 50*i, f);
                    }else if (j%2!=0) {
                        Shape f = new Rectangle(50*j, 50*i, 50, 50);
                        this.muros[i][j] = new Pared(null, null, null, 50*j, 50*i, f);
                    }
                }
            }
        }
    }
    /*public boolean detectarColisonMuro(Shape s){
        
    }
    
    public boolean detectarColisonPared(Shape s){
        
    }*/
    
    /**
     * Método encargado de detectar las colisiones entre el campesino y las 
     * partes superior e inferior de los muros fijos y paredes.
     * @since AgroBomberman 1.0
     */
    public void DetectarColisionSuperiorCampesino(){
        /*//Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionY();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                 this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                   if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                       if (this.pulsacionTeclado.contains("UP")) {
                           this.campesino.setPosicionY(detector + 2);
                       }else if (this.pulsacionTeclado.contains("DOWN")) {
                           this.campesino.setPosicionY(detector - 2);
                       }
                    }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if(p.getImagen()!=null){
                            if (this.pulsacionTeclado.contains("UP")) {
                                this.campesino.setPosicionY(detector + 2);
                            } else if (this.pulsacionTeclado.contains("DOWN")) {
                                this.campesino.setPosicionY(detector - 2);
                            }
                        }
                    } 
                }
            }
        
        }*/
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionY();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (this.campesino.getTorso()!=null) {
                    Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                            this.muros[i][j].getForma());
                    if (intersection.getBoundsInLocal().getWidth() != -1) {
                        if (this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")) {
                            if (this.pulsacionTeclado.contains("UP")) {
                                this.campesino.setPosicionY(detector + 2);
                            } else if (this.pulsacionTeclado.contains("DOWN")) {
                                this.campesino.setPosicionY(detector - 2);
                            }
                        } else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                            Pared p = (Pared) this.muros[i][j];
                            if (p.getImagen() != null) {
                                if (this.pulsacionTeclado.contains("UP")) {
                                    this.campesino.setPosicionY(detector + 2);
                                } else if (this.pulsacionTeclado.contains("DOWN")) {
                                    this.campesino.setPosicionY(detector - 2);
                                }
                            }
                        }
                    }
                }
            }
        
        }
    }
    /**
     * Método encargado de detectar las colisiones entre el campesino y las 
     * partes laterales de los muros fijos y paredes.
     * @since AgroBomberman 1.0
     */
    public void DetectarColisionLadosCampesino(){
        /*//Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionX();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                 this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                   if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                       if (this.pulsacionTeclado.contains("LEFT")) {
                           this.campesino.setPosicionX(detector + 2);
                       } else if (this.pulsacionTeclado.contains("RIGHT")) {
                           this.campesino.setPosicionX(detector - 2);
                       }
                    }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if(p.getImagen()!=null){
                             if (this.pulsacionTeclado.contains("LEFT")) {
                                this.campesino.setPosicionX(detector + 2);
                            } else if (this.pulsacionTeclado.contains("RIGHT")) {
                                this.campesino.setPosicionX(detector - 2);
                            }
                        }
                    } 
                }
           }
        }*/
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionX();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (this.campesino.getTorso()!=null) {
                    Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                            this.muros[i][j].getForma());
                    if (intersection.getBoundsInLocal().getWidth() != -1) {
                        if (this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")) {
                            if (this.pulsacionTeclado.contains("LEFT")) {
                                this.campesino.setPosicionX(detector + 2);
                            } else if (this.pulsacionTeclado.contains("RIGHT")) {
                                this.campesino.setPosicionX(detector - 2);
                            }
                        } else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                            Pared p = (Pared) this.muros[i][j];
                            if (p.getImagen() != null) {
                                if (this.pulsacionTeclado.contains("LEFT")) {
                                    this.campesino.setPosicionX(detector + 2);
                                } else if (this.pulsacionTeclado.contains("RIGHT")) {
                                    this.campesino.setPosicionX(detector - 2);
                                }
                            }
                        }
                    }
                }
           }
        }
    }
    /**
     * Metodo encargado de crear las paredes, asignarles una imagen de manera
     * aleatoria para que se visualice en el juego.
     * @param imgPared String de la ruta de la imagen de las paredes.
     * @throws FileNotFoundException
     * @since AgroBomberman 1.0
     */
    public void crearPared(String imgPared) throws FileNotFoundException{
        Random aleatorio = new Random();
        int numero;
        Image img = new Image(new FileInputStream(imgPared));
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 21; j++) {
                if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                    if(!(((i==1)&&(j==1))||((i==1)&&(j==2))||((i==2)&&(j==1)))){
                        numero = aleatorio.nextInt(2);
                        if(numero % 2 == 0){
                            Pared p = (Pared) this.muros[i][j];
                            p.setImagen(img);
                            this.muros[i][j] = p;
                        }
                    }
                }
            }
        }
        int fila;
        int columna;
        int contador1 = 0;
        while(contador1==0){
            fila = aleatorio.nextInt(13);
            columna = aleatorio.nextInt(21);
            if(this.muros[fila][columna].getClass().getName().equals("Funcional.Pared")){
                Pared p = (Pared)this.muros[fila][columna];
                if(p.getImagen()!=null){
                    Shape contorno = new Rectangle(p.getPosicionX()+5, p.getPosicionY()+5, 
                            30, 30);
                    Puerta puerta = new Puerta(p.getPosicionX(), p.getPosicionY(),
                    new Image(new FileInputStream("ImagenesJuego/puerta.png")),contorno);
                    p.setPuerta(puerta);
                    this.muros[fila][columna]=p;
                    contador1++;
                }
            }
        }
        int contador2=0;
        while(contador2==0){
            fila = aleatorio.nextInt(13);
            columna = aleatorio.nextInt(21);
            if(this.muros[fila][columna].getClass().getName().equals("Funcional.Pared")){
                Pared p = (Pared)this.muros[fila][columna];
                if(p.getImagen()!=null){
                    if (p.getPuerta()==null) {
                        Shape contorno = new Rectangle(p.getPosicionX()+5, p.getPosicionY()+5, 
                            30, 30);
                        PowerUp power = new PowerUp(p.getPosicionX(), p.getPosicionY(),
                                new Image(new FileInputStream("ImagenesJuego/power_up.png")),
                        contorno);
                        p.setPower(power);
                        this.muros[fila][columna]=p;
                        contador2++;
                    }
                }
            }
        }
    }
    /**
     * Metodo encargado de dibujar las paredes en el juego.
     * @since AgroBomberman 1.0
     */
    public void dibujarParedes(){
        for (int i = 1; i < 13; i++) {
            for (int j = 1; j < 21; j++) {
                if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                   Pared p = (Pared) this.muros[i][j];
                    if (p.getPuerta()!=null) {
                        lapiz.drawImage(p.getPuerta().getImage(), p.getPosicionX(), p.getPosicionY());
                    }
                    if (p.getPower()!=null) {
                        lapiz.drawImage(p.getPower().getImage(), p.getPosicionX(), p.getPosicionY());
                    }
                   lapiz.drawImage(p.getImagen(), p.getPosicionX(), p.getPosicionY());
                }
            }
        }
    }
    
    /**
     * Método encargado de dibujar a un determinado enemigo dentro del juego.
     * @param num númmero del enemigo el cual se debe dibujar.
     * @since AgroBomberman 1.0
     */
    public void DibujarEnemigos(int num){
        Shape torsoEnemigo = new Rectangle(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50,50);
        this.enemigos.get(num).setTorso(torsoEnemigo);
        /*lapiz.strokeRect(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50, 50);*/
        lapiz.drawImage(this.enemigos.get(num).getImagen(),0, 50*this.secuencia, 50,51, 
                this.enemigos.get(num).getPosicionX(),this.enemigos.get(num).getPosicionY(),51,50);
        
    }
    
    /**
     * Método encargado de verificar la cantidad muros con los que choca un 
     * determinado enemigo.
     * @param num número del enemigo el cual se va a evaluar.
     * @return 
     * @since AgroBomberman 1.0
     */
    public int RectificarMuros(int num){
    double pos_x1 = this.enemigos.get(num).getPosicionX()+50;
    double pos_y1 = this.enemigos.get(num).getPosicionY();
     /*if (pos_x1%10!=0) {
            pos_x1 = pos_x1/10;
            pos_x1 = Math.floor(pos_x1);
            pos_x1= pos_x1*10;
        }
    if (pos_y1 % 10 != 0) {
        pos_y1 = pos_y1 / 10;
        pos_y1 = Math.floor(pos_y1);
        pos_y1 = pos_y1 * 10;
    }*/
        
     double pos_x2 = this.enemigos.get(num).getPosicionX()-50;
     double pos_y2 = this.enemigos.get(num).getPosicionY();
     /*if (pos_x2%10!=0) {
            pos_x2 = pos_x2/10;
            pos_x2 = Math.floor(pos_x2);
            pos_x2= pos_x2*10;
        }
        if (pos_y2%10!=0) {
            pos_y2 = pos_y2/10;
            pos_y2 = Math.floor(pos_y2);
            pos_y2= pos_y2*10;
        }*/
        
     double pos_x3 = this.enemigos.get(num).getPosicionX();
     double pos_y3 = this.enemigos.get(num).getPosicionY()+50;
     /*if (pos_x3%10!=0) {
            pos_x3 = pos_x3/10;
            pos_x3 = Math.floor(pos_x3);
            pos_x3= pos_x3*10;
        }
        if (pos_y3%10!=0) {
            pos_y3 = pos_y3/10;
            pos_y3 = Math.floor(pos_y3);
            pos_y3= pos_y3*10;
        }*/
        
     double pos_x4 = this.enemigos.get(num).getPosicionX();
     double pos_y4 = this.enemigos.get(num).getPosicionY()-50;
     /*if (pos_x4%10!=0) {
            pos_x4 = pos_x4/10;
            pos_x4 = Math.floor(pos_x4);
            pos_x4= pos_x4*10;
        }
        if (pos_y4%10!=0) {
            pos_y4 = pos_y4/10;
            pos_y4 = Math.floor(pos_y4);
            pos_y4= pos_y4*10;
        }*/
        int rectificar=0;
        int X = 0;
        int Y = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                X = this.muros[i][j].getPosicionX();
                Y = this.muros[i][j].getPosicionY();
                if (this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")) {
                  if(((X==pos_x1)&&(Y==pos_y1))||((X==pos_x2)&&(Y==pos_y2))||((X==pos_x3)
                          &&(Y==pos_y3))||((X==pos_x4)&&(Y==pos_y4))){
                      rectificar++;
                  }
                }else if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                    Pared p = (Pared) this.muros[i][j];
                    if (p.getImagen()!=null) {
                        if(((X==pos_x1)&&(Y==pos_y1))||((X==pos_x2)&&(Y==pos_y2))||((X==pos_x3)
                          &&(Y==pos_y3))||((X==pos_x4)&&(Y==pos_y4))){
                            rectificar++;
                        }
                    }
                }
            }
        }
        //System.out.println(rectificar);
        if (rectificar==4) {
            return 1;
        }
        return 2;
    }
    
    /**
     * Método encargado de establcer la dirección en la cual se va a mover un 
     * enemigo luego de chocarse contra un muro fijo o una pared.
     * @param num número del enemigo el cual se va a evaluar.
     * @return 
     * @since AgroBomberman 1.0
     */
    public boolean DireccionMover(int num){
        double x = this.enemigos.get(num).getPosicionX();
        double y = this.enemigos.get(num).getPosicionY();
        /*if (x%10!=0) {
            x = x/10;
            x = Math.floor(x);
            x= (int)x*10;
        }
        if (y%10!=0) {
            y = y/10;
            y = Math.floor(y);
            y= (int)y*10;
        }*/
        if(this.enemigos.get(num).getOrientacion().equals("Derecha")){
            x = x+50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Izquierda")) {
            x=x-50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Arriba")) {
            y= y-50;
        }else if (this.enemigos.get(num).getOrientacion().equals("Abajo")) {
            y=y+50;
        }
        int verificar=0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
               if(this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")){
                   if ((this.muros[i][j].getPosicionX()==x)&&
                        (this.muros[i][j].getPosicionY()==y)) {
                    return false;
                    }
               }else if(this.muros[i][j].getClass().getName().equals("Funcional.Pared")){
                   Pared p = (Pared)this.muros[i][j];
                   if (p.getImagen()!=null) {
                       if ((this.muros[i][j].getPosicionX()==x)&&
                        (this.muros[i][j].getPosicionY()==y)) {
                            return false;
                    }
                   }
               }
            }
        }
        return true;
    }
    /**
     * Método encargado de mover a los enemigos dentro del juego.
     * @param num número del enemigo que se va a mover.
     * @param numNivel número de nivel en el que se encuentra el jugador.
     * @throws FileNotFoundException 
     * @since AgroBomberman 1.0
     */
    public void moverEnemigos(int num,int numNivel) throws FileNotFoundException{
        int verificar;
        boolean mover;
        if (this.enemigos.get(num).getOrientacion().equals("Derecha")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:          
                    mover = DireccionMover(num);
                    if (!mover) {
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_arr.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_arr.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_arr.png")));
                        }
                        this.enemigos.get(num).setOrientacion("Arriba");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_der.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_der.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_der.png")));
                        }
                        this.enemigos.get(num).moverDerecha();
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Izquierda")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                        
                    mover = DireccionMover(num);
                    if (!mover) {
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_aba.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_aba.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_aba.png")));
                        }
                        this.enemigos.get(num).setOrientacion("Abajo");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_izq.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_izq.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_izq.png")));
                        }
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                        this.enemigos.get(num).moverIzquierda();
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Arriba")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                       
                    mover = DireccionMover(num);
                    if (!mover) {
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_izq.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_izq.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_izq.png")));
                        }
                        this.enemigos.get(num).setOrientacion("Izquierda");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_arr.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_arr.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_arr.png")));
                        }
                        this.enemigos.get(num).moverArriba();
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }else if (this.enemigos.get(num).getOrientacion().equals("Abajo")) {
            verificar=RectificarMuros(num);
            switch(verificar){
                case 1:
                    DibujarEnemigos(num);
                    break;
                case 2:
                                        
                    mover = DireccionMover(num);
                    if (!mover) {
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_der.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_der.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_der.png")));
                        }
                        this.enemigos.get(num).setOrientacion("Derecha");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N1_aba.png")));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N2_aba.png")));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image(new FileInputStream("ImagenesJuego/Enemigo_N3_aba.png")));
                        }
                        this.enemigos.get(num).moverAbajo();
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                    }
                    DibujarEnemigos(num);
                    break;
                default: break;
            }
        }
     
    }

    /**
     * Método que me retorna el campesino.
     * @return 
     * @since AgroBomberman 1.0
     */
    public Campesino getCampesino() {
        return campesino;
    }
    
    /**
     * Método que se encarga de establecer cuando el enemigo ha encontrado la 
     * puerta y así puede pasar de nivel.
     * @return 
     * @since AgroBomberman 1.0
     */
    public boolean cambiarNivel(){
        /*for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                    Pared p = (Pared)this.muros[i][j];
                    if (p.getPuerta()!=null) {
                        Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                        p.getPuerta().getContorno());
                        if (intersection.getBoundsInLocal().getWidth() != -1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;*/
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                    Pared p = (Pared)this.muros[i][j];
                    if ((p.getPuerta()!=null)&&(this.campesino.getTorso()!=null)) {
                        Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                        p.getPuerta().getContorno());
                        if (intersection.getBoundsInLocal().getWidth() != -1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * Método que me permite establcer una colisión entre allguno de los enemigos
     * y el campesino.
     * @since AgroBomberman 1.0
     */
    public void colisionCampesinoEnemigo(){
        for (int i = 0; i < this.enemigos.size(); i++) {
            if (this.campesino.getTorso()!=null) {
                Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                        this.enemigos.get(i).getTorso());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                    this.kill=false;
                }
            }
        }
    }
    /**
     * Método encargado de mostrar visualmente la cantidad de vidas que tiene
     * el campesino.
     * @throws FileNotFoundException 
     * @since AgroBomberman 1.0
     */
    public void DibujarVidas() throws FileNotFoundException{
        if (this.campesino.getVidas()==3) {
            lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/vidas_3.png")), 
                    70, 0, 150, 51);
        }else if (this.campesino.getVidas()==2) {
            lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/vidas_2.png")), 
                    70, 0, 100,51);
        }else if (this.campesino.getVidas()==1) {
            lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/vidas_1.png")), 
                    70, 0, 50, 51);
        }
        /*lapiz.drawImage(this.campesino.getImagen(), 0, 50 * this.secuencia, 50, 51,
                this.campesino.getPosicionX(), this.campesino.getPosicionY(),
                51, 50);*/
    }
    //-------------------------------------
    //-------------------------------------
    
    /**
     * Metodo que retorna las coordenadas del elemento de tipo pared con el que choca
     * el Shape evaluado
     * @param s Shape para evaluar con el que se va a evaluar si choco con algun 
     * elemente de tipo Pared.
     * @since AgroBomberman 1.0
     */
    
    public int[] QuePared(Shape s) {
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int[] pos = new int[2];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(s,
                        this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                    if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if (p.getImagen() != null) {
                            pos[0] = this.muros[i][j].getPosicionX();
                            pos[1] = this.muros[i][j].getPosicionY();
                            p.setImagen(null);
                            this.muros[i][j] = p;
                            return pos;
                        }

                    }
                }
            }

        }
        pos[0] = -70;
        pos[1] = -70;
        return pos;
    }

    /**
     * Metodo encargado de regular el tiempo que dura la animación de poner la
     * semilla
     *
     * @since AgroBomberman 1.0
     */
    public void DuracionSemilla() throws FileNotFoundException {
        numero3++;
        if (this.numero3 == 90) {
            //if (pulsacionTeclado2.contains("X")) {
            pulsacionTeclado2.remove("X");
            secuencia2 = 0;
            this.numero3 = 0;
            //}
            this.numero2 = 0;
            //contadorSem.stop();
        }
    }
    
    public boolean powerUp(){
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                    Pared p = (Pared)this.muros[i][j];
                    if ((p.getPower()!=null)&&(this.campesino.getTorso()!=null)) {
                        Shape intersection = SVGPath.intersect(this.campesino.getTorso(),
                        p.getPower().getContorno());
                        if (intersection.getBoundsInLocal().getWidth() != -1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * Metodo encargado de permitir que cuando el jugador oprima la tecla X se
     * ponga la semilla
     *
     * @since AgroBomberman 1.0
     */
    public void PonerSemilla() throws FileNotFoundException {

        if (numero3 == 0) {
            posx = this.campesino.getPosicionX();
            posy = this.campesino.getPosicionY();
        }
        if (pulsacionTeclado2.contains("X")) {

            DuracionSemilla();

            this.numero2++;
            int contx = 0, conty = 0, auxPosx = posx, auxPosy = posy;

            while (auxPosx % 50 != 0) {
                auxPosx--;
                contx++;
            }

            while (auxPosy % 50 != 0) {
                auxPosy--;
                conty++;
            }

            if (contx - 25 > 0) {
                posx = posx + contx - 25;
            } else {
                posx = posx - contx;
            }

            if (conty - 25 > 0) {
                posy = posy + conty - 25;
            } else {
                posy = posy - conty;
            }
            //creacion del shape de la semilla que detecta si hay muros al rededor 
            //para ver cual estremo dibuja
            Shape semArr = new Rectangle(posx + 20, posy - 5, 10, 10);//arriba
            Shape semAba = new Rectangle(posx + 20, posy + 45, 10, 10); //abajo
            Shape semIzq = new Rectangle(posx - 5, posy + 20, 10, 10); //izq
            Shape semDer = new Rectangle(posx + 45, posy + 20, 10, 10); //der

            this.campesino.setSemillas(new Semilla(posx,
                    posy, semArr, semAba, semDer, semIzq));

            //dibuja la semilla
            dibujarUfo(posx, posy);

            if (this.numero2 % 15 == 0) {
                if (this.secuencia2 == 9) {
                    this.secuencia2 = 0;
                } else {
                    this.secuencia2++;
                }
            }
        }
    }

    
    /**
     * Metodo que se encarga de gestionar la animación de la semilla, donde si secuencia2 es menor que 5
     * dibuja la semilla y en caso contrario dibuja la explosion de la semilla
     * @param x entero que da la coordenada en x para dibujar la semilla
     * @param y entero que da la coordenada en y para dibujar la semilla
     * @since AgroBomberman 1.0
     */
    public void dibujarUfo(int x, int y) throws FileNotFoundException {
        if (secuencia2 < 5) {
            dibujarSemilla1(x, y, secuencia2);
        } else {
            dibujarExplosion(x, y, secuencia2);
        }
    }

    
    /**
     * Metodo que se encarga de gestionar la animación de la explosion semilla, dependiendo de 
     * sus colisiones con objetos de tipo Muro y sus consecuencias, como la muerte de personajes.
     * @param x entero que da la coordenada en x para dibujar la semilla
     * @param y entero que da la coordenada en y para dibujar la semilla
     * @param sec entero que representa la secuencia en la que debe dicujarse la explosion
     * @since AgroBomberman 1.0
     */
    public void dibujarExplosion(int x, int y, int sec) throws FileNotFoundException {
        lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-centro-2.png")), 0, 0, 50, 50, x, y, 50, 50);

        //Se crean los shape que me controlan el dibujo de las componentes de la explosion
        Shape semArr = new Rectangle(x + 20, y - 5, 10, 10);//arriba
        Shape semAba = new Rectangle(x + 20, y + 45, 10, 10); //abajo
        Shape semIzq = new Rectangle(x - 5, y + 20, 10, 10); //izq
        Shape semDer = new Rectangle(x + 45, y + 20, 10, 10); //der

        Shape semMedArr = new Rectangle(x + 20, y - 55, 10, 10);//arriba
        Shape semMedAba = new Rectangle(x + 20, y + 95, 10, 10); //abajo
        Shape semMedIzq = new Rectangle(x - 55, y + 20, 10, 10); //izq
        Shape semMedDer = new Rectangle(x + 95, y + 20, 10, 10); //der

        lapiz.strokeOval(x + 20, y - 55, 10, 10);
        lapiz.strokeOval(x + 20, y + 95, 10, 10);
        lapiz.strokeOval(x - 55, y + 20, 10, 10);
        lapiz.strokeOval(x + 95, y + 20, 10, 10);

        this.campesino.setSemillas(new Semilla(x, y, semArr, semAba, semDer, semIzq));

        boolean interArr = DetectarColisionMuroFijo(this.campesino.getSemillas().getArriba());
        boolean interAba = DetectarColisionMuroFijo(this.campesino.getSemillas().getAbajo());;
        boolean interDer = DetectarColisionMuroFijo(this.campesino.getSemillas().getDerecha());;
        boolean interIzq = DetectarColisionMuroFijo(this.campesino.getSemillas().getIzquierda());;

        if (this.campesino.getPowerUp()) {
            System.out.println("entro");
            if (!interArr) {  //mira si hay colision con un muro a la arriba de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-arr-2.png")), 0, 0, 50, 50, x, y - 50, 50, 50);
                if(!DetectarColisionMuroFijo(semMedArr)&&!DetectarColisionPared(semMedArr)){
                   lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-arr-2.png")), 0, 0, 50, 50, x, y - 100, 50, 50); 
                }
            }
            if (!interAba) { //mira si hay colision con un muro a la abajo de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-aba-2.png")), 0, 0, 50, 50, x, y + 50, 50, 50);
                if(!DetectarColisionMuroFijo(semMedAba) &&!DetectarColisionPared(semMedAba)){
                   lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-aba-2.png")), 0, 0, 50, 50, x, y + 100, 50, 50); 
                }
            }
            if (!interDer) { //mira si hay colision con un muro a la izquierda de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-der-2.png")), 0, 0, 50, 50, x+50, y , 50, 50);
                if(!DetectarColisionMuroFijo(semMedDer)&&!DetectarColisionPared(semMedDer)){
                   lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-der-2.png")), 0, 0, 50, 50, x+100, y, 50, 50); 
                }
            }
            if (!interIzq) { //mira si hay colision con un muro a la derecha de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-izq-2.png")), 0, 0, 50, 50, x-50, y, 50, 50);
                if(!DetectarColisionMuroFijo(semMedIzq)&&!DetectarColisionPared(semMedIzq)){
                   lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-izq-2.png")), 0, 0, 50, 50-100, x, y, 50, 50); 
                }
            }

        }else{
            if (!interArr) {  //mira si hay colision con un muro a la arriba de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-arr-2.png")), 0, 0, 50, 50, x, y - 50, 50, 50);
            }
            if (!interAba) { //mira si hay colision con un muro a la abajo de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-aba-2.png")), 0, 0, 50, 50, x, y + 50, 50, 50);
            }
            if (!interDer) { //mira si hay colision con un muro a la izquierda de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-der-2.png")), 0, 0, 50, 50, x + 50, y, 50, 50);
            }
            if (!interIzq) { //mira si hay colision con un muro a la derecha de la semilla
                lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/sem-izq-2.png")), 0, 0, 50, 50, x - 50, y, 50, 50);
            }
        }
        //Se crean los shape que me controlan la destruccion del arbol
        boolean interArr2 = DetectarColisionPared(this.campesino.getSemillas().getArriba());
        boolean interAba2 = DetectarColisionPared(this.campesino.getSemillas().getAbajo());
        boolean interDer2 = DetectarColisionPared(this.campesino.getSemillas().getDerecha());
        boolean interIzq2 = DetectarColisionPared(this.campesino.getSemillas().getIzquierda());

        //Guardan la pocisiondel arbol que debe destruir
        int[] posArr = QuePared(this.campesino.getSemillas().getArriba());
        int[] posAba = QuePared(this.campesino.getSemillas().getAbajo());
        int[] posDer = QuePared(this.campesino.getSemillas().getDerecha());
        int[] posIzq = QuePared(this.campesino.getSemillas().getIzquierda());

        if (interArr2) {
            int[] pos = posArr;
            animacionDestruccionArbol(sec, interArr2, pos[0], pos[1]);
        }
        if (interAba2) {
            int[] pos2 = posAba;
            animacionDestruccionArbol(sec, interAba2, pos2[0], pos2[1]);
        }
        if (interDer2) {
            int[] pos3 = posDer;
            animacionDestruccionArbol(sec, interDer2, pos3[0], pos3[1]);
        }
        if (interIzq2) {
            int[] pos4 = posIzq;
            animacionDestruccionArbol(sec, interIzq2, pos4[0], pos4[1]);
        }

        //Se verifica si algun enemigo choco con la semilla  para ejecutar la muerte del enemigo
        EliminarEnemigo(x, y);
        EliminarEnemigo(x, y);
        EliminarEnemigo(x, y);
        EliminarEnemigo(x, y);

        colisionCampesinoSemilla(x, y);
    }

    /**
     * Metodo que se encarga de gestionar la animación de la explosion semilla, dependiendo de 
     * sus colisiones con objetos de tipo Muro.
     * @param x entero que da la coordenada en x para dibujar la semilla
     * @param y entero que da la coordenada en y para dibujar la semilla
     * @param sec entero que representa la secuencia en la que debe dicujarse la explosion
     * @since AgroBomberman 1.0
     */
    public void dibujarSemilla1(int x, int y, int sec) throws FileNotFoundException {
        lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/semilla-exp.png")), 0, 50 * sec, 50, 50, x, y, 50, 50);

        Shape semArr = new Rectangle(x + 20, y - 5, 10, 10);//arriba
        Shape semAba = new Rectangle(x + 20, y + 45, 10, 10); //abajo
        Shape semIzq = new Rectangle(x - 5, y + 20, 10, 10); //izq
        Shape semDer = new Rectangle(x + 45, y + 20, 10, 10); //der

        this.campesino.setSemillas(new Semilla(x, y, semArr, semAba, semDer, semIzq));

        lapiz.strokeRect(x, y, 50, 50);
        lapiz.strokeOval(x + 20, y - 5, 10, 10);//arriba
        lapiz.strokeOval(x + 20, y + 45, 10, 10); //abajo
        lapiz.strokeOval(x - 5, y + 20, 10, 10); //izq
        lapiz.strokeOval(x + 45, y + 20, 10, 10); //der
    }

    /**
     * Metodo que se encarga de gestionar la animación de la explosion semilla, dependiendo de 
     * sus colisiones con objetos de tipo Muro y sus consecuencias, como la muerte de personajes.
     * @param secuencia entero que representa la secuencia en la que debe dicujarse la destruccion
     * del arbol
     * @param x booleano indica si debe o no dibujarse la destruccion del arbol
     * @param posix entero que da la coordenada en x para dibujar la animacion
     * @param posiy entero que da la coordenada en y para dibujar la animacion
     * @since AgroBomberman 1.0
     */
    public void animacionDestruccionArbol(int secuencia, boolean x, int posix, int posiy) throws FileNotFoundException {
        if (x) {
            lapiz.drawImage(new Image(new FileInputStream("ImagenesJuego/destruccion-arbol.png")), 0, 50 * this.secuencia, 50, 50, posix, posiy, 50, 50);
        }
        //puntaje = puntaje +10;
    }

    /**
     * Metodo que retorna si hubo colision con algun objeto de tipo MuroFijo con respecto al
     * Shape a evaluar
     * @param s Shape para evaluar con el que se va a evaluar si choco con algun 
     * elemente de tipo MuroFijo.
     * @since AgroBomberman 1.0
     */
    public boolean DetectarColisionMuroFijo(Shape s) {
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(s,
                        this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                    if (this.muros[i][j].getClass().getName().equals("Funcional.MuroFijo")) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * Metodo que retorna si hubo colision con algun objeto de tipo Pared con respecto al
     * Shape a evaluar
     * @param s Shape para evaluar con el que se va a evaluar si choco con algun 
     * elemente de tipo Pared.
     * @since AgroBomberman 1.0
     */
    public boolean DetectarColisionPared(Shape s) {
        //Se detectan las colisiones del campesino y los muros y paredes
        //Si una pared tiene su atributo imagen como null, no se impedirá el paso
        //hasta que se le asigne una imgen.
        int detector = this.campesino.getPosicionX();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 21; j++) {
                Shape intersection = SVGPath.intersect(s,
                        this.muros[i][j].getForma());
                if (intersection.getBoundsInLocal().getWidth() != -1) {
                    if (this.muros[i][j].getClass().getName().equals("Funcional.Pared")) {
                        Pared p = (Pared) this.muros[i][j];
                        if (p.getImagen() != null) {
                            return true;
                        }

                    }
                }
            }

        }
        return false;
    }

    /**
     * Metodo encargado de evaluar si hubo una colision de los elementos de la
     * explosion con la semilla, y en ese caso eliminar el enemigo
     *
     * @since AgroBomberman 1.0
     */
    public void EliminarEnemigo(int x1, int y1) throws FileNotFoundException {

        Shape semArr = new Rectangle(x1 + 20, y1 - 45, 10, 50);//arriba
        Shape semAba = new Rectangle(x1 + 20, y1 + 45, 10, 50); //abajo
        Shape semIzq = new Rectangle(x1 - 45, y1 + 20, 50, 10); //izq
        Shape semDer = new Rectangle(x1 + 45, y1 + 20, 50, 10); //der

        for (int i = 0; i < this.enemigos.size(); i++) {
            Shape intersection = SVGPath.intersect(semArr, this.enemigos.get(i).getTorso());
            Shape intersection2 = SVGPath.intersect(semAba, this.enemigos.get(i).getTorso());
            Shape intersection3 = SVGPath.intersect(semIzq, this.enemigos.get(i).getTorso());
            Shape intersection4 = SVGPath.intersect(semDer, this.enemigos.get(i).getTorso());

            if (intersection.getBoundsInLocal().getWidth() != -1) {
                this.enemigos.remove(i);
            } else if (intersection2.getBoundsInLocal().getWidth() != -1) {
                this.enemigos.remove(i);
            }
            if (intersection3.getBoundsInLocal().getWidth() != -1) {
                this.enemigos.remove(i);
            }
            if (intersection4.getBoundsInLocal().getWidth() != -1) {
                this.enemigos.remove(i);
            }
        }
    }

    /**
     * Metodo encargado de evaluar si hubo una colision de los elementos de la
     * explosion con la semilla, y en ese caso eliminar el enemigo
     *
     * @since AgroBomberman 1.0
     */
    public void colisionCampesinoSemilla(int x1, int y1) {

        Shape semArr = new Rectangle(x1 + 20, y1 - 45, 10, 50);//arriba
        Shape semAba = new Rectangle(x1 + 20, y1 + 45, 10, 50); //abajo
        Shape semIzq = new Rectangle(x1 - 45, y1 + 20, 50, 10); //izq
        Shape semDer = new Rectangle(x1 + 45, y1 + 20, 50, 10); //der

        Shape intersection = SVGPath.intersect(semArr, this.campesino.getTorso());
        Shape intersection2 = SVGPath.intersect(semAba, this.campesino.getTorso());
        Shape intersection3 = SVGPath.intersect(semIzq, this.campesino.getTorso());
        Shape intersection4 = SVGPath.intersect(semDer, this.campesino.getTorso());

        if (intersection.getBoundsInLocal().getWidth() != -1) {
            this.kill = false;
        } else if (intersection2.getBoundsInLocal().getWidth() != -1) {
            this.kill = false;
        }
        if (intersection3.getBoundsInLocal().getWidth() != -1) {
            this.kill = false;
        }
        if (intersection4.getBoundsInLocal().getWidth() != -1) {
            this.kill = false;
        }
    }

    public void setNumEnemigos(int n){
        this.numEnemigos=n;
    }
    
    /**
     * Metodo getter del parametro numEnemigos de Nivel
     * @since AgroBomberman 1.0
     */
    public int getNumEnemigos(){
        return this.numEnemigos;
    }
    
}
