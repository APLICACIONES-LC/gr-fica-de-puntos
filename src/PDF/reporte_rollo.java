/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import Conexion_bd.Conectar;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.ZapfDingbatsList;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ERICK IVAN
 */
public class reporte_rollo {
  Conectar cu = new Conectar();
    Connection cnu = cu.conexion();
    public String Nombre_doctor;
    public String paciente;
    public String opost;
    public String tempratura1;
    public String tempratura2;
      public String tempratura3;
public String nombre_paciente;
public String edad;
public String estado;
String prediagnostico;
  public ArrayList<String> TABLA_LISTA = new <String> ArrayList();

    public void PDF(){
id_paciente();
        // Creacion del documento con los margenes
        Document document = new Document(PageSize.A7, 10, 15, 15, 15);
        try {

            // El archivo pdf que vamos a generar
            FileOutputStream fileOutputStream = new FileOutputStream(
                    "reportechicoPaciente"+paciente+".pdf");

            // Obtener la instancia del PdfWriter
            PdfWriter.getInstance(document, fileOutputStream);

            // Abrir el documento
            document.open();

            Image image = null;

            // Obtenemos el logo de datojava
            image = Image.getInstance("logoimms.png");
            image.scaleAbsolute(80f, 60f);

            // Crear las fuentes para el contenido y los titulos
            Font fontContenido = FontFactory.getFont(
                    FontFactory.TIMES_ROMAN.toString(), 9, Font.NORMAL,
                    BaseColor.DARK_GRAY);
            Font fontTitulos = FontFactory.getFont(
                    FontFactory.TIMES_BOLDITALIC, 9, Font.UNDERLINE,
                    BaseColor.RED);

            // Creacion de una tabla
            PdfPTable table = new PdfPTable(1);

            // Agregar la imagen anterior a una celda de la tabla
            PdfPCell cell = new PdfPCell(image);

            
            
            
            
            
            
            // Propiedades de la celda
            cell.setColspan(5);
            cell.setBorderColor(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

            // Agregar la celda a la tabla
            table.addCell(cell);

            // Agregar la tabla al documento
            document.add(table);

                    // Creacion del parrafo
            Paragraph paragraph = new Paragraph();
           
            paragraph.add(new Phrase("   Datos principales:", fontTitulos));

            // Agregar el parrafo al documento
            document.add(paragraph);

            // La lista final
            List listaFinal = new List(List.UNORDERED);
            ListItem listItem = new ListItem();
            List list = new List();

            // Crear sangria en la lista
            list.setListSymbol(new Chunk(""));
            ListItem itemNuevo = new ListItem("");

            // ZapfDingbatsListm, lista con simbolos
            List listSymbol = new ZapfDingbatsList(51);

            // Agregar contenido a la lista
            listSymbol.add(new ListItem("Nombre del paciente: "+nombre_paciente, fontContenido));
            listSymbol.add(new ListItem("Edad del paciente: "+edad+" años", fontContenido));
            listSymbol.add(new ListItem("Estado del paciente: "+estado, fontContenido));
            listSymbol.add(new ListItem("Nombre del Doctor: "+ Nombre_doctor+", "+ new Date(), fontContenido));

            itemNuevo.add(listSymbol);
            list.add(itemNuevo);
            listItem.add(list);

            // Agregar todo a la lista final
            listaFinal.add(listItem);

            // Agregar la lista
            document.add(listaFinal);

            paragraph = new Paragraph();
            paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
             paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
             paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
             paragraph.add(new Phrase(Chunk.NEWLINE));
            paragraph.add(new Phrase(Chunk.NEWLINE));
          
          document.add(paragraph);
          
   ////////////////////////////////////////////////////////-----------------------------------------------------------------------------------------       
  // Obtenemos el logo de datojava
            image = Image.getInstance(paciente+".png");
            image.scaleAbsolute(250f, 170f);
  image.setRotationDegrees(270);
 // Creacion de una tabla
            PdfPTable table2 = new PdfPTable(1);

            // Agregar la imagen anterior a una celda de la tabla
            PdfPCell cell2 = new PdfPCell(image);

              // Propiedades de la celda
            cell2.setColspan(5);
            cell2.setBorderColor(BaseColor.WHITE);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            // Agregar la celda a la tabla
            table2.addCell(cell2);

            // Agregar la tabla al documento
            document.add(table2);

//            // Cargar otra imagen
//		    table = new PdfPTable(1);
//                         table.setWidthPercentage(100);
//           image = Image.getInstance(paciente+".png");
//           image.scaleAbsolute(100, 100);
//           image.setRotationDegrees(270);
//           
//            
//            table.addCell(image);
//
//            // Agregar la tabla al documento
//            document.add(table);

  /////////////////--------------------------------------------------------------------------------------------------------------------
               Paragraph parah = new Paragraph();
            // Agregar un titulo con su respectiva fuente
            parah .add(new Phrase("   Tabla de resultados", fontTitulos));
            // Agregar saltos de linea
            parah .add(new Phrase(Chunk.NEWLINE));
            parah .add(new Phrase(Chunk.NEWLINE));
    document.add(parah );

       //////////////////////////////////////////////////////////////////            
            
            
    
            
                 //////////////////////////////
            
            
              PdfPTable table4 = new PdfPTable(4);                
            table4.setWidthPercentage(90);
             float[] values = new float[4];
            values[0] = 25;
            values[1] = 25;
            values[2] = 25;
            values[3] = 25;
          
            table4.setWidths(values);
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
                for (int i = 0; i <= TABLA_LISTA.size() - 1; i++) {
                    table4.addCell(""+TABLA_LISTA.get(i));
                }
            
           
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
            
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Final de la tabla"));
            
            // Indicamos cuantas columnas ocupa la celda
             
            celdaFinal.setColspan(4);
            table4.addCell(celdaFinal);
            
            // Agregamos la tabla al documento            
            document.add(table4);
            
            
            /////////////////
            // Agregar un titulo con su respectiva fuente
            paragraph.add(new Phrase("   Observaciones Preliminares:", fontTitulos));

            // Agregar saltos de linea
            paragraph.add(new Phrase(Chunk.NEWLINE));
            

            // Agregar contenido con su respectiva fuente
            paragraph.add(new Phrase(" \n"+prediagnostico+"\n", fontContenido));

            paragraph.add(new Phrase(Chunk.NEWLINE));
           
            document.add(paragraph);

       //////////////////////////////////////////////////////////////////
              Paragraph parahH = new Paragraph();
               /////////////////UYJ
            // Agregar un titulo con su respectiva fuente
            parahH.add(new Phrase("   Observaciones Posteriores \n   a la sesion:", fontTitulos));

            // Agregar saltos de linea
            parahH.add(new Phrase(Chunk.NEWLINE));
       

            // Agregar contenido con su respectiva fuente
            parahH.add(new Phrase( " \n"+opost+"\n",fontContenido));

            parahH.add(new Phrase(Chunk.NEWLINE));
      
            document.add(parahH); 
         

       //////////////////////////////////////////////////////////////////   
            //////////////////////////////////////////////////////
            
            
            
            // Crear tabla nueva con dos posiciones
            table = new PdfPTable(2);
            float[] longitudes = {5.0f, 5.0f};

            // Establecer posiciones de celdas
            table.setWidths(longitudes);
            cell = new PdfPCell();

            // Cargar imagenes del producto y agregarlas
            image = Image.getInstance("fehlmex.png");
            image.scaleAbsolute(10f, 10f);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.addCell(image);
            image = Image.getInstance("fehlmex.png");
            image.scaleAbsolute(10f, 10f);
            table.addCell(image);

            // Agregar la tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();

            // Abrir el archivo
            File file = new File("reportechicoPaciente"+paciente+".pdf");
            Desktop.getDesktop().open(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //Externo
   private void id_paciente() {
//     int contador_id=0;
//     int c=0;
//        String[] Registro = new String[1];
        String sql = "SELECT * FROM db_paciente WHERE id_paciente='"+paciente+"'";
        Statement st;
        try {
            st = cnu.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                prediagnostico=rs.getString("n_obspreliminar");
//                Registro[0] = rs.getString("id_paciente");
//                c = Integer.parseInt(Registro[0]);
            }
//            for (int i = 0; i <= c; i++) {
//                contador_id = i + 1;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(Reportin.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
