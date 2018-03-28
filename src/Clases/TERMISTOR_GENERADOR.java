/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author ERICK IVAN
 */
public class TERMISTOR_GENERADOR {
        public double T=0;
   public static void main(String []args){
     double T=0;
   double D=45;
   double A=0.0014686257262683;
     System.out.println("valor de A: "+A);
   double B=0.000238082958452564;//0.00023808	
     System.out.println("valor de B: "+B);
   double C=1.02990010305808E-7;//0.00000010299
     System.out.println("valor de C: "+C);
   
   
   double G=62.9;
   double Io=0.00001;//10MICRO AMPERS =0.00001 AMPERS
   double Rt=0;
   double Ln=0;
   double Ln3=0;
   double H=5/(1023*62.9*0.00001); // Vcc/(1023*G)
   Rt=D*H; 
   //Rt=Rt/(1023*G*Io);
     System.out.println("valor de Rt: "+Rt);
   
   Ln=Math.log(Rt);
     System.out.println("valor de LN: "+Ln);
   Ln3=Math.pow( (Math.log(Rt)),3 ); //Math.log(Math.pow(Rt,3));
     System.out.println("valor de Ln^3: "+Ln3);
   T=( 1/( A + B*(Ln) + C*(Ln3) ) ) - 273.15;
   //T=T-273.15;
         System.out.println("valor de T: "+T);
      
   }
     public void main_termistor(int D){
      
    D=45;
   double A=0.0014686257262683;
     System.out.println("valor de A: "+A);
   double B=0.000238082958452564;//0.00023808	
     System.out.println("valor de B: "+B);
   double C=1.02990010305808E-7;//0.00000010299
     System.out.println("valor de C: "+C);
   
   
   double G=62.9;
   double Io=0.00001;//10MICRO AMPERS =0.00001 AMPERS
   double Rt=0;
   double Ln=0;
   double Ln3=0;
   double H=5/(1023*62.9*0.00001); // Vcc/(1023*G)
   Rt=D*H; 
   //Rt=Rt/(1023*G*Io);
     System.out.println("valor de Rt: "+Rt);
   
   Ln=Math.log(Rt);
     System.out.println("valor de LN: "+Ln);
   Ln3=Math.pow( (Math.log(Rt)),3 ); //Math.log(Math.pow(Rt,3));
     System.out.println("valor de Ln^3: "+Ln3);
   T=( 1/( A + B*(Ln) + C*(Ln3) ) ) - 273.15;
   //T=T-273.15;
         System.out.println("valor de T: "+T);
    }
}
