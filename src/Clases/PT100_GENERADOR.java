/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Scanner;

/**
 *
 * @author ERICK IVAN
 */
public class PT100_GENERADOR {
  
   public  int D=45;
    public double T=0; 
    public double temp_maxima=0;
    
      public  static void main(String[]args){
    int VCC=5;
     int D=1000;
     double T=0; 
    double b= -5.7750E-7;
    double a= 3.9083E-3;
    double c=0;
    double R0=100;
    double G=883;
    double r=97.6;
    double t2N_1=1023;
    double I=0.0001;
    
    double HH=0;
     System.out.println("A= "+a);
    
    System.out.println("B= "+b);
    HH= 5/(1023*0.0001*883);
    System.out.println("H= "+HH);
    T=(-a+Math.sqrt( (a*a)-(4*b*(1-(D*HH + r)/R0)) ) ) / (2*b); //1 - (D*(5/(1023*883*0.0001)) + r) / R0
//    IG=I*G;
//    double dosN_2IG=IG*1023;
//    c=(D*VCC)/dosN_2IG+r;
//    c=(1-c)/R0;
   
    System.out.println("T= "+T);
    
         
       
      
    }
    
    public  void main_pt100(int D){
     int VCC=5;
//     int D=45;
//     double T=0; 
    double b= -5.7750E-7;
    double a= 3.9083E-3;
    double c=0;
    double R0=100;
    double G=883;
    double r=97.6;
    double t2N_1=1023;
    double I=0.0001;
    
    double HH=0;
     System.out.println("A= "+a);
    
    System.out.println("B= "+b);
    HH= 5/(1023*0.0001*883);
    System.out.println("H= "+HH);
    T=(-a+Math.sqrt( (a*a)-(4*b*(1-(D*HH + r)/R0)) ) ) / (2*b); //1 - (D*(5/(1023*883*0.0001)) + r) / R0
//    IG=I*G;
//    double dosN_2IG=IG*1023;
//    c=(D*VCC)/dosN_2IG+r;
//    c=(1-c)/R0;
   
    System.out.println("T= "+T);
    if(T>=138.9){
        temp_maxima=1;
    }
         
    }

}
