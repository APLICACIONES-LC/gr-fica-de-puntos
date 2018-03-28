/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_buscar;

import Conexion_bd.Conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ERICK
 */
public class Buscar_paciente {
       Conectar cu = new Conectar();
    Connection cnu = cu.conexion();
    public String dato1="",dato2="",dato3="",dato4="",dato5="",dato6="";
    public void consulta(String buscar){
   //sentencia encargada de buscar en la base de datos 
  String sqll = "SELECT * FROM db_paciente where id_paciente='" +buscar+ "' || n_completo='"+buscar+"'";
        Statement stt;
        try {
            stt = cnu.createStatement();
            ResultSet rss = stt.executeQuery(sqll);
           
            while (rss.next()) {
                dato1 = rss.getString("id_paciente");
                dato2 = rss.getString("n_completo");
                dato3 = rss.getString("n_edad");
                dato4 = rss.getString("n_diagnostico");
                dato5 = rss.getString("n_obspreliminar");
                dato6 = rss.getString("n_obsfinales");
              JOptionPane.showMessageDialog(null, "USTED ESTA A PUNTO DE INICIAR UNA SESIÓN PARA EL PACIENTE RECUERDE\n"
                                                + "QUE POSIBLEMENTE ESTE PACIENTE YA TUVO UNA SESION ANTERIOR, TODO\n"
                      +                           "SOBRE LA TEMPERATURA SERA MODIFICADO CON LOS NUEVOS DATOS","¡AVISO!",JOptionPane.INFORMATION_MESSAGE);
              
            }} catch (SQLException ex) {
            Logger.getLogger(Buscar_paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(dato1.equals("")){
       JOptionPane.showMessageDialog(null, "verifique bien el N° de parte","ERROR",JOptionPane.ERROR_MESSAGE);
       dato1="";dato2="";dato3="";dato4="";dato5="";dato6="";
            }

    }
}
