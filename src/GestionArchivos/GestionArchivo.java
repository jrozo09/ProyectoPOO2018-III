/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArchivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author emlad
 */
public class GestionArchivo {
    private String  ruta; 

    public GestionArchivo() {
        this.ruta = "puntajes.txt";
    }

    public void guardar(int puntaje) throws FileNotFoundException, IOException{
        File archivo = new File(ruta);
        if(!archivo.exists())
            archivo.createNewFile();
        
        PrintStream salida 
                = new PrintStream(archivo);
        salida.print(puntaje);
        
        salida.flush();
        salida.close();
        
    }
    
    public ArrayList<Integer> cargar() throws FileNotFoundException{
        ArrayList<Integer> puntajes = 
                new ArrayList<>();
        Scanner lectura 
                = new Scanner(new File(ruta));
        while(lectura.hasNextInt()){
            puntajes.add(lectura.nextInt());
        }
        return puntajes;
    }
            
}
