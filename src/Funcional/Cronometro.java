/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcional;

/**
 * Clase encargada de gestionar el contador del
 * juego con un tiempo de 3:59 minutos.
 * @author Juan Esteban Rozo Urbina
 * @author David Alexander Arias Parra
 * @author Juan Andres Gonzalez Aria
 * @author Emmanuel Steven Rojas Arcila
 * @since AgroBomberman 1.0
 */
public class Cronometro extends Thread {
    
    private int minutos;
    private int segundos;
    private boolean activo;

    @Override
    public void run() {
        while (true) {
            if (activo) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.getMessage();
                }
                restarSegundo();
            }
        }
    }
    
    public Cronometro() {
        this.minutos = 3;
        this.segundos = 59;
        this.activo = true;
    }

    /**
     * Metodo que retorna el valor de los minutos restantes.
     * @return  
     */
    public int getMinutos() {
        return this.minutos;
    }

    /**
     * Metodo que cambia el valor de los minutos restantes:
     * @param minutos 
     */
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * Metodo que retorna el valor de los segundos restantes.
     * @return  
     */
    public int getSegundos() {
        return this.segundos;
    }

    /**
     * Metodo que cambia el valor de los segundos restantes:
     * @param segundos 
     */
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    /**
     * Metodo que le resta un segundo al contador
     * si no quedan resta un minuto.
     */
    private void restarSegundo() {
        if (segundos <= 59 && segundos > 0) {
            segundos--;
        } else if (segundos == 0) {
            segundos = 59;
            minutos--;
        }
    }

    /**
     * Metodo que retorna el valor de activo.
     * @return  
     */
    public boolean isActivo() {
        return this.activo;
    }

    /**
     * Metodo que cambia el valor del booleano 
     * activo para parar o continuar el contador:
     * @param activo  
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Metodo que retorna un String representa 
     * el tiempo restante, formato: Min:Seg .
     * @return  
     */
    public String getCadena() {
        if (this.minutos < 10 && this.segundos < 10) {
            return "0" + this.minutos + ":" + "0" + this.segundos;
        } else if (this.minutos >= 10 && this.segundos < 10) {
            return this.minutos + ":" + "0" + this.segundos;
        } else if (this.minutos < 10 && this.segundos >= 10) {
            return "0" + this.minutos + ":" + this.segundos;
        } else {
            return +this.minutos + ":" + this.segundos;
        }
    }
}

