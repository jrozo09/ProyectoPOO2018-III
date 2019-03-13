/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

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
public class Nivel {
    private Scene escena;
    private Muro[][] muros;//Matriz de los muros tanto fijos como paredes de cada nivel.
    private ArrayList<Enemigo>enemigos;//Array de los enemigos.
    private Campesino campesino; //Personaje principal.
    private GraphicsContext lapiz;
    private ArrayList<String> pulsacionTeclado = null;//Array para gestionar los eventos del teclado
    private int secuencia = 0;
    private int numero = 0;
    private int numEnemigos;
    //------------------------------
    private int posx;
    private int posy;
    private int numero2;
    private int secuencia2;
    private ArrayList<String> pulsacionTeclado2 = null;
    
    /**
     * Constructor de la clase encargado de inicializar los atributos. Además 
     * crea los muros fijos del nivel del juego, los enemigos y al campesino.
     * @param escena Se pasa por refeerencia la escena para poder controlar los
     * eventos del teclado con el array pulsacionTeclado.
     * @param posicionX Posición en X del campesino.
     * @param posicionY Posición en Y del campesino.
     * @param imgCampesino Ruta de la imagen del campsino.
     * @param imgEnemigos Ruta de la imagen de los enemigos.
     * @param lapiz 
     * @since AgroBomberman 1.0
     */
    public Nivel(Scene escena,int posicionX, int posicionY, String imgCampesino, String 
            imgEnemigos, GraphicsContext lapiz,String imgPared,int numEnemigos) {
        this.escena = escena;
        this.muros = new Muro[13][21];
        this.enemigos = new ArrayList<>();
        this.campesino = new Campesino(false, null, 3, posicionX, posicionY, new Image(imgCampesino),null,
        lapiz,escena);
        this.lapiz = lapiz;
        this.pulsacionTeclado = new ArrayList<>();
        this.pulsacionTeclado2 = new ArrayList<>();
        this.numEnemigos = numEnemigos;
        
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

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }
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
     */
    public void DibujarCampesino() {
        lapiz.drawImage(this.campesino.getImagen(), 0, 50 * this.secuencia, 50, 51,
                this.campesino.getPosicionX(), this.campesino.getPosicionY(),
                51, 50);
        //Se crea la forma del campesino para detectar colisiones
        Shape torsoCampesino = new Rectangle(this.campesino.getPosicionX()+5,this.campesino.getPosicionY()+5, 40, 40);
        this.campesino.setTorso(torsoCampesino);
    }
    /**
     * Metodo encargado de los eventos del teclado para poder mover al campesino.
     * @since AgroBomberman 1.0
     */
    public void moverCampesino(){
        //Eventos del teclado
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
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_izq.png"));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("RIGHT")) {
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_der.png"));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("UP")) {
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_arr.png"));
            DibujarCampesino();
        }else if (pulsacionTeclado.contains("DOWN")){
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_aba.png"));
            DibujarCampesino();
        }else{
            this.campesino.setImagen(new Image("ImagenesJuego/Campesino_normal.png"));
            DibujarCampesino();
        }
                
         this.numero++;
    }
    /**
     * Metodo encargado de crear los enemigos buscando casillas vacias para 
     * establecer sus posiciones iniciales.
     * @param imgEnemigos Imagen de los enemigos.
     * @since AgroBomberman 1.0
     */
    public void crearEnemigos(String imgEnemigos){
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
                            this.muros[x][y].getPosicionY(), new Image(imgEnemigos), forma,
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
    
    public void DetectarColisionSuperiorCampesino(){
        //Se detectan las colisiones del campesino y los muros y paredes
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
        
        }
    }
    public void DetectarColisionLadosCampesino(){
        //Se detectan las colisiones del campesino y los muros y paredes
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
        }
    }
    /**
     * Metodo encargado de crear las paredes, asignarles una imagen de manera
     * aleatoria para que se visualice en el juego.
     * @param imgPared String de la ruta de la imagen de las paredes.
     * @since AgroBomberman 1.0
     */
    public void crearPared(String imgPared){
        Random aleatorio = new Random();
        int numero;
        Image img = new Image(imgPared);
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
                    new Image("ImagenesJuego/puerta.png"),contorno);
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
                        PowerUp power = new PowerUp(p.getPosicionX(), p.getPosicionY(),
                                new Image("ImagenesJuego/power_up.png"));
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
    
    public void DibujarEnemigos(int num){
        Shape torsoEnemigo = new Rectangle(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50,50);
        this.enemigos.get(num).setTorso(torsoEnemigo);
        /*lapiz.strokeRect(this.enemigos.get(num).getPosicionX(), 
                this.enemigos.get(num).getPosicionY(), 50, 50);*/
        lapiz.drawImage(this.enemigos.get(num).getImagen(),0, 50*this.secuencia, 50,51, 
                this.enemigos.get(num).getPosicionX(),this.enemigos.get(num).getPosicionY(),51,50);
        
    }
    
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
    public void moverEnemigos(int num,int numNivel){
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
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_arr.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_arr.png"));
                        }
                        this.enemigos.get(num).setOrientacion("Arriba");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_der.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_der.png"));
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
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_aba.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_aba.png"));
                        }
                        this.enemigos.get(num).setOrientacion("Abajo");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_izq.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_izq.png"));
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
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_izq.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_izq.png"));
                        }
                        this.enemigos.get(num).setOrientacion("Izquierda");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_izq.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_arr.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_arr.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_arr.png"));
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
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_der.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_der.png"));
                        }
                        this.enemigos.get(num).setOrientacion("Derecha");
                        //this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_der.png"));
                    }else{
                        if (numNivel==1) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N1_aba.png"));
                        }else if (numNivel==2) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N2_aba.png"));
                        }else if (numNivel==3) {
                            this.enemigos.get(num).setImagen(new Image("ImagenesJuego/Enemigo_N3_aba.png"));
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

    public Campesino getCampesino() {
        return campesino;
    }
    
    public boolean cambiarNivel(){
        for (int i = 0; i < 13; i++) {
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
        return false;
    }
    //-------------------------------------
    public int[] QuePared(Shape s){
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
                        Pared p = (Pared)this.muros[i][j];
                        if (p.getImagen()!=null) {
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
    public void PonerSemilla(){
        if (this.numero % 300 == 0) {
            posx = this.campesino.getPosicionX();
            posy = this.campesino.getPosicionY();

            if (pulsacionTeclado2.contains("X")) {
                pulsacionTeclado2.remove("X");
                secuencia2 = 0;
            }
            this.numero2 = 0;
            //contadorSem.stop();
        }
        if (pulsacionTeclado2.contains("X")) {
            this.numero2++;
            //creacion del shape de la semilla que detecta si hay muros al rededor 
            //para ver cual estremo dibuja
            Shape semArr= new Rectangle(posx+20, posy-5, 10, 10);//arriba
            Shape semAba= new Rectangle(posx+20, posy+45, 10, 10); //abajo
            Shape semIzq= new Rectangle(posx-5, posy+20, 10, 10); //izq
            Shape semDer= new Rectangle(posx+45, posy+20, 10, 10); //der
            
            this.campesino.setSemillas(new Semilla(posx,
                    posy,semArr,semAba, semDer, semIzq));
            
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
    public void dibujarUfo(double x, double y) {
        if (secuencia2 < 5) {
            dibujarSemilla1(x, y, secuencia2);
        } else {
            dibujarExplosion(x, y, secuencia2);
        }
    }
    
    public void dibujarExplosion(double x, double y, int sec) {
        lapiz.drawImage(new Image("ImagenesJuego/sem-centro-2.png"), 0, 0, 50, 50, x, y, 50, 50);
        
        
        boolean interArr = DetectarColisionMuroFijo(this.campesino.getSemillas().getArriba());
        boolean interAba = DetectarColisionMuroFijo(this.campesino.getSemillas().getAbajo());;
        boolean interDer = DetectarColisionMuroFijo(this.campesino.getSemillas().getDerecha());;
        boolean interIzq = DetectarColisionMuroFijo(this.campesino.getSemillas().getIzquierda());;
        
        if(!interArr){  //mira si hay colision con un muro a la arriba de la semilla
            lapiz.drawImage(new Image("ImagenesJuego/sem-arr-2.png"), 0, 0, 50, 50, x, y-50, 50, 50);
        }
        if(!interAba){ //mira si hay colision con un muro a la abajo de la semilla
            lapiz.drawImage(new Image("ImagenesJuego/sem-aba-2.png"), 0, 0, 50, 50, x, y+50, 50, 50);
        }
        if(!interDer){ //mira si hay colision con un muro a la izquierda de la semilla
            lapiz.drawImage(new Image("ImagenesJuego/sem-der-2.png"), 0, 0, 50, 50, x+50, y, 50, 50);
        }
        if(!interIzq){ //mira si hay colision con un muro a la derecha de la semilla
            lapiz.drawImage(new Image("ImagenesJuego/sem-izq-2.png"), 0, 0, 50, 50, x-50, y, 50, 50);
        }
        
        boolean interArr2 = DetectarColisionPared(this.campesino.getSemillas().getArriba());
        boolean interAba2 = DetectarColisionPared(this.campesino.getSemillas().getAbajo());
        boolean interDer2 = DetectarColisionPared(this.campesino.getSemillas().getDerecha());
        boolean interIzq2 = DetectarColisionPared(this.campesino.getSemillas().getIzquierda());
        
        int [] posArr = QuePared(this.campesino.getSemillas().getArriba());
        int [] posAba = QuePared(this.campesino.getSemillas().getAbajo());
        int [] posDer = QuePared(this.campesino.getSemillas().getDerecha());
        int [] posIzq = QuePared(this.campesino.getSemillas().getIzquierda());
        
        if(interArr2){
            int [] pos= posArr;
            animacionDestruccionArbol(sec, interArr2,pos[0],pos[1]);
        }
        if(interAba2){
            int [] pos2= posAba;
            animacionDestruccionArbol(sec, interAba2,pos2[0],pos2[1]);
        }
        if(interDer2){
            int [] pos3= posDer;
            animacionDestruccionArbol(sec, interDer2,pos3[0],pos3[1]);
        }
        if(interIzq2){
            int [] pos4= posIzq;
            animacionDestruccionArbol(sec, interIzq2,pos4[0],pos4[1]);
        }
    }
    public void dibujarSemilla1(double x, double y, int sec) {
        lapiz.drawImage(new Image("ImagenesJuego/semilla-exp.png"), 0, 50 * sec, 50, 50, x, y, 50, 50);
        
        lapiz.strokeRect(x, y, 50, 50);
        lapiz.strokeOval(x+20, y-5, 10, 10);//arriba
        lapiz.strokeOval(x+20, y+45, 10, 10); //abajo
        lapiz.strokeOval(x-5, y+20, 10, 10); //izq
        lapiz.strokeOval(x+45, y+20, 10, 10); //der
    }
    
    public void animacionDestruccionArbol(int secuencia, boolean x, int posix, int posiy){
        if(x){
            //this.arbol.setIma(null);
            lapiz.drawImage(new Image("ImagenesJuego/destruccion-arbol.png"), 0, 50 * this.secuencia, 50, 50, posix,posiy, 50, 50);
        }
        //puntaje = puntaje +10;
    } 
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
                        Pared p = (Pared)this.muros[i][j];
                        if (p.getImagen()!=null) {
                            return true;
                        }
                        
                    }
                }
            }

        }
        return false;
    }
}
