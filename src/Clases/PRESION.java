/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author ERICK IVAN
 * ESTÁ CLASE ES LA ENCARGADA DE MEDIR LA PRESION DE LOS SENSORES 
 */
public class PRESION {
   public static void main(String[]args){
          double dd=500, p;
//       d=45;//cambia recibe datos de arduino
       
        
        
        p=dd/1023;
         System.out.println("resultado de presión d/1023: "+p+"                ");
        p=p-0.04;
        p=p/0.0012858;
        System.out.println("resultado de presión: "+p+"                "+dd);
   }
       public double p=0;
       public double temp_maxima=0;
    public void main_presion(int d){
       double dd=d;
//       d=45;//cambia recibe datos de arduino
       
        
        
        p=dd/1023;
         System.out.println("resultado de presión d/1023: "+p+"                ");
        p=p-0.04;
        p=p/0.0012858;
        System.out.println("resultado de presión: "+p+"                "+dd);
        if(p>=350){
            temp_maxima=1;
        }
    }
}
