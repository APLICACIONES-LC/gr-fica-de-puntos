/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GETTERANDSETTER;

/**
 *
 * @author ERICK IVAN
 */
public class RECIBIDOR {
 String obspreli;
  public String a; 
    
    //Constructor:
    public RECIBIDOR(){
        
    }
    
    //Metodo nada:
    public String nada(){
        return "no se realizo ninguna observacion preliminar";
    }
    
    
    
    //Setters y Getters.
    public void setObsp(String n){
        obspreli= n;
    }
    
  
    
    public String getObsp(){
        return obspreli;
    }
    

}// fin de la clase 