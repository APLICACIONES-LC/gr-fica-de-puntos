/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package USUARIO;

import Conexion_bd.Conectar;
import PDF.Reportin;
import PDF.reporte_rollo;
import TIEMPO.Tiempo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ERICK IVAN
 */
public class SENSORES extends javax.swing.JFrame {

    public String idI;//va a contener los datos de cada ventana nueva
    public String usuario_doctor;//va a contener los datos de cada ventana nueva
    Conectar cu = new Conectar();
    Connection cnu = cu.conexion();
    DecimalFormat decimales = new DecimalFormat("0.00");
    /////////////////////listas///////////////////////////////////////////

    ArrayList<Double> TEMP1 = new <Double> ArrayList();
    ArrayList<Double> TEMP2 = new <Double> ArrayList();
    ArrayList<Double> TEMP3 = new <Double> ArrayList();
    ArrayList<String> TI = new <String> ArrayList();
    ArrayList<String> estados = new <String> ArrayList();
    ArrayList<String> TABLA_LISTA = new <String> ArrayList();
//       ArrayList <String> TIEMPO = new <String> ArrayList();
    String TIEMPO = "";
    ////////////////////////////////////////////////////////////////////// 
    ///////////////////////////////////VARIABLES////////////////////////////////
    String nombre = "Emilio Fuentes Mendez";
    String No_paciente;
    public String nombre_paciente;
    public String edad;
    public String estado;
    public String obspost = "NO SE DIERON OBSERVACIONES POSTERIORES A LA SESIÓN.";
    double tem1 = 0;
    double tem2 = 0;
    double tem3 = 0;
    double h1 = 0;
    double h2 = 0;
    double h3 = 0;
    double tiempo_espera;
    int minutos = 0;
    double rand = 0.0;

    //////////////////////////////////////////////////////////////
    public SENSORES() {
        initComponents();
        Image icon = new ImageIcon(getClass().getResource("/img/iconfehmex.png")).getImage();
        setIconImage(icon);
        this.setExtendedState(MAXIMIZED_BOTH);
        lbl_doctor.setText("DOCTOR: " + nombre);
        TABLA_LISTA.add("TEMPERATURA 1");
        TABLA_LISTA.add("TEMPERATURA 2");
        TABLA_LISTA.add("TEMPERATURA 3");
        TABLA_LISTA.add("MINUTOS");
    }

    Thread hilo1 = new Thread() {
        public void run() {
            try {
                int min = 0;

                while (true) {
                    barra_tiempo.setValue((int) h1);
                    if (h1 == tiempo_espera) {
                        minutos = minutos + min ;
                        TIEMPO = "" + minutos;
                        min = 0;
                        TABLA_LISTA.add("" + txt_tem1.getText());
                        TABLA_LISTA.add("" + txt_tem2.getText());
                        TABLA_LISTA.add("" + txt_tem3.getText());
                        TABLA_LISTA.add("" + TIEMPO);
                        grafica();
                        ChartUtilities.saveChartAsJPEG(new File("" + No_paciente + ".png"), chart, 500, 300);
                        update_temperatura();
                        hilo1.suspend();
                        h1 = 0;

                    }

                    if (tem1 == 0 && rand == 0) {
                        tem1 = tem1 + 1;
                        tem2 = tem2 + 1;
                        tem3 = tem3 + 1;
                        TEMP1.add(tem1);
                        TEMP2.add(tem2);
                        TEMP3.add(tem3);
                        TIEMPO = "0";
                        TABLA_LISTA.add("" + tem1);
                        TABLA_LISTA.add("" + tem2);
                        TABLA_LISTA.add("" + tem3);
                        TABLA_LISTA.add("" + TIEMPO);

                        grafica();

                    } else if (tem1 != 0 && rand == 0) {
                        tem1 = tem1 + 3;
                        tem2 = tem2 + 3;
                        tem3 = tem3 + 3;
                        TEMP1.add(tem1);
                        TEMP2.add(tem2);
                        TEMP3.add(tem3);
                    }
                    TI.add("" + h1);
                    if (tem1 > 69) {

                        rand = (Math.random() * 5 + 70);
                        txt_tem1.setText(decimales.format(rand));

                        TEMP1.add(Double.parseDouble(txt_tem1.getText()));

                    }
                    if (tem2 > 69) {
                        rand = (Math.random() * 5 + 70);
                        txt_tem2.setText(decimales.format(rand));

                        TEMP2.add(Double.parseDouble(txt_tem2.getText()));

                    }
                    if (tem3 > 69) {
                        rand = (Math.random() * 5 + 70);
                        txt_tem3.setText(decimales.format(rand));

                        TEMP3.add(Double.parseDouble(txt_tem3.getText()));

                    }

//                    if (min == 0) {
//                        minutos = minutos + min;
////                     TIEMPO.add(minutos+"min");   
//                        TIEMPO = "" + minutos;
//                       
//                    }
//                  if (min == 5) {
//                        minutos = minutos + min;
////                     TIEMPO.add(minutos+"min");   
//                        TIEMPO = "" + minutos;
//                        min = 0;
//                    }
                    if (min == 2) {//CADA 2 SEG GUARDA DATOS SENSORES PARA EL REPORTE

                        minutos = minutos + min;
                        TIEMPO = "" + minutos;
                        min = 0;
                        if (tem1 > 69) {
                            TABLA_LISTA.add("" + txt_tem1.getText());
                            TABLA_LISTA.add("" + txt_tem2.getText());
                            TABLA_LISTA.add("" + txt_tem3.getText());
                            TABLA_LISTA.add("" + TIEMPO);
                            grafica();
                        } else {

                            TABLA_LISTA.add("" + tem1);
                            TABLA_LISTA.add("" + tem2);
                            TABLA_LISTA.add("" + tem3);
                            TABLA_LISTA.add("" + TIEMPO);
                            grafica();
                        }
                    }

                    System.out.println("Thread 1 sensores" + h1);

                    h1++;
                    min++;
                    hilo1.sleep(1000);
                }
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(SENSORES.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jPanel1 = new javax.swing.JPanel();
        panelTranslucidoComplete21 = new org.edisoncor.gui.panel.PanelTranslucidoComplete2();
        jLabel1 = new javax.swing.JLabel();
        lbl_doctor = new javax.swing.JLabel();
        lbl_paci = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        barra_tiempo = new javax.swing.JProgressBar();
        panel_trans = new org.edisoncor.gui.panel.PanelTranslucidoComplete2();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_tem1 = new javax.swing.JTextField();
        txt_tem2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        lbl_edad = new javax.swing.JLabel();
        lbl_diagnostico = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_tem3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        panelShadow1 = new org.edisoncor.gui.panel.PanelShadow();
        Panel_grafica = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setColorPrimario(new java.awt.Color(0, 0, 0));
        panel1.setColorSecundario(new java.awt.Color(255, 255, 255));
        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/escrtorio.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        panelTranslucidoComplete21.setColorPrimario(new java.awt.Color(0, 204, 0));
        panelTranslucidoComplete21.setColorSecundario(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoimms.png"))); // NOI18N

        lbl_doctor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_doctor.setText("DOCTOR:");

        lbl_paci.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_paci.setText("PACIENTE:");

        jButton10.setBackground(new java.awt.Color(0, 204, 0));
        jButton10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton9.setBackground(new java.awt.Color(204, 0, 0));
        jButton9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel2.setText("Nivel agua alto");

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        jLabel9.setText("Nivel agua baja");

        barra_tiempo.setForeground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout panelTranslucidoComplete21Layout = new javax.swing.GroupLayout(panelTranslucidoComplete21);
        panelTranslucidoComplete21.setLayout(panelTranslucidoComplete21Layout);
        panelTranslucidoComplete21Layout.setHorizontalGroup(
            panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                        .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_doctor)
                            .addComponent(lbl_paci))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                        .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)))
                    .addComponent(barra_tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTranslucidoComplete21Layout.setVerticalGroup(
            panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel2)))
                                .addGap(18, 18, 18)
                                .addGroup(panelTranslucidoComplete21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(panelTranslucidoComplete21Layout.createSequentialGroup()
                                .addComponent(lbl_doctor)
                                .addGap(11, 11, 11)
                                .addComponent(lbl_paci)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barra_tiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_trans.setColorPrimario(new java.awt.Color(255, 255, 255));
        panel_trans.setColorSecundario(new java.awt.Color(0, 204, 0));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setText("Graficar.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Temperatura 1");

        txt_tem1.setEditable(false);
        txt_tem1.setText("23.52");

        txt_tem2.setEditable(false);
        txt_tem2.setText("23.52");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Temperatura 2");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("°C");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("°C");

        lbl_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_nombre.setText("NOMBRE");

        lbl_edad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_edad.setText("EDAD");

        lbl_diagnostico.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_diagnostico.setText("DIAGNOSTICO");

        jButton2.setText("PDF diagnostico");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ingresar observacion");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Temperatura 3");

        txt_tem3.setEditable(false);
        txt_tem3.setText("23.52");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("°C");

        javax.swing.GroupLayout panel_transLayout = new javax.swing.GroupLayout(panel_trans);
        panel_trans.setLayout(panel_transLayout);
        panel_transLayout.setHorizontalGroup(
            panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_transLayout.createSequentialGroup()
                            .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_edad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_diagnostico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panel_transLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_tem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_tem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transLayout.createSequentialGroup()
                            .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(23, 23, 23))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transLayout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_transLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addContainerGap())))
        );
        panel_transLayout.setVerticalGroup(
            panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_transLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(14, 14, 14)
                .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_tem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_transLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_tem3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_nombre)
                .addGap(18, 18, 18)
                .addComponent(lbl_edad)
                .addGap(18, 18, 18)
                .addComponent(lbl_diagnostico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
            .addComponent(jSeparator1)
        );

        javax.swing.GroupLayout Panel_graficaLayout = new javax.swing.GroupLayout(Panel_grafica);
        Panel_grafica.setLayout(Panel_graficaLayout);
        Panel_graficaLayout.setHorizontalGroup(
            Panel_graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        Panel_graficaLayout.setVerticalGroup(
            Panel_graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_grafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_grafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTranslucidoComplete21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel_trans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTranslucidoComplete21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panel_trans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Opciones");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imprimir3.png"))); // NOI18N
        jMenuItem4.setText("Agregar observación");
        jMenu1.add(jMenuItem4);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevopaciente.png"))); // NOI18N
        jMenuItem3.setText("Nuevo paciente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/modificar.png"))); // NOI18N
        jMenuItem1.setText("Editar paciente");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ayuda.png"))); // NOI18N
        jMenuItem2.setText("jMenuItem2");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Tiempo ti = new Tiempo(this, true);
        ti.setVisible(true);
        System.out.println("--------------------" + ti.envia);
        if (ti.envia == 0) {
            JOptionPane.showMessageDialog(this, "Debes ingresar un valor mayor a 0 ", "Sistema ", JOptionPane.ERROR_MESSAGE);
        } else {
//        Object seleccion = JOptionPane.showInputDialog(null, "Seleccione minutos",
//                "Selector de minutos",
//                JOptionPane.QUESTION_MESSAGE, null,
//                new Object[]{"10 minutos", "20 minutos", "30 minutos",
//                    "40 minutos", "50 minutos", "1 hora", "1 hora 10 minutos", "1 hora 20 minutos", "1 hora 30 minutos", "1 hora 40 minutos", "1 hora 50 minutos", "2 horas"},
//                "opcion 1");
//
//        if (seleccion.equals("10 minutos")) {
            ti.envia = ti.envia ;
            tiempo_espera = ti.envia;
            barra_tiempo.setMaximum(ti.envia);
            hilo1.start();
//
//        } else {
//            System.out.print("no elegiste nada");
//        }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println("tem1= " + TEMP1);
        System.out.println("tem1 tamaño= " + TEMP1.size());
        System.out.println("tem2= " + TEMP2);
        System.out.println("tem2 tamaño= " + TEMP2.size());
        System.out.println("tem3= " + TEMP3);
        System.out.println("tem3 tamaño= " + TEMP3.size());
        System.out.println("minutos= " + TI);
        System.out.println("minut= " + TIEMPO);
        System.out.println("estados= " + estados.size());
        Object seleccion = JOptionPane.showInputDialog(null, "Seleccione tipo de hoja",
                "Selector de hoja",
                JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"tamaño CARTA", "tamaño normal"},
                "opcion 1");

        if (seleccion.equals("tamaño CARTA")) {
            try {
                Reportin re = new Reportin();
                re.paciente = No_paciente;
                re.nombre_paciente = nombre_paciente;
                re.edad = edad;
                re.estado = estado;
                re.Nombre_doctor = nombre;
                re.tempratura1 = "" + TEMP1;
                re.tempratura2 = "" + TEMP2;
                re.tempratura3 = "" + TEMP3;
                re.TABLA_LISTA = TABLA_LISTA;
                re.opost = obspost;
                re.PDF();
            } catch (Exception E) {

            }

        } else if (seleccion.equals("tamaño normal")) {
            try {
                reporte_rollo re = new reporte_rollo();
                re.paciente = No_paciente;
                re.nombre_paciente = nombre_paciente;
                re.edad = edad;
                re.estado = estado;
                re.Nombre_doctor = nombre;
                re.tempratura1 = "" + TEMP1;
                re.tempratura2 = "" + TEMP2;
                re.tempratura3 = "" + TEMP3;
                re.TABLA_LISTA = TABLA_LISTA;
                re.opost = obspost;
                re.PDF();
            } catch (Exception E) {

            }

        } else {
            System.out.print("no elegiste nada");
        }

//        System.out.println("Número aleatorio real entre 0 y 30: " + (Math.random() * 5+70) );
// for (int i = 0; i <= TEMP1.size() - 1; i++) {
//
//            line_chart_dataset.addValue(TEMP1.get(i), "TEMPERATURA1", TI.get(i));
//
//        }
//        for (int i = 0; i <= TEMP2.size() - 1; i++) {
//
//            line_chart_dataset.addValue(TEMP2.get(i), "TEMPERATURA2", TI.get(i));
//
//        }
//
// //line_chart_dataset.addValue(35, "visitas", "2min");
////        line_chart_dataset.addValue(30, "visitas", "3min");
////         line_chart_dataset.addValue(tem2, "dias", "1min");
////        line_chart_dataset.addValue(20, "dias", "2min");
//        // Creando el Grafico
//        JFreeChart chart = ChartFactory.createLineChart("Temperatura",
//                "Minutos", "Temperaturas", line_chart_dataset, PlotOrientation.VERTICAL,
//                true, true, false);
//
//        // Mostrar Grafico
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setSize(1300, 600);
//        chartPanel.setLocation(10, 20);
//        Panel_grafica.add(chartPanel);
//        Panel_grafica.setVisible(false);
//        Panel_grafica.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Insercion_OBS_POST dialog = new Insercion_OBS_POST(new javax.swing.JFrame(), true);
        dialog.id_paciente = No_paciente;
        dialog.setVisible(true);
        obspost = dialog.observacion;
        System.out.println("" + obspost);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Paciente_existente pa = new Paciente_existente();
        pa.idI = idI;
        pa.usuario_doctor = usuario_doctor;
        pa.lbl_usuario.setText("Usuario: " + usuario_doctor);
        pa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Paciente_existente pa = new Paciente_existente();
        pa.idI = idI;
        pa.usuario_doctor = usuario_doctor;
        pa.lbl_usuario.setText("Usuario: " + usuario_doctor);
        pa.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SENSORES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SENSORES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SENSORES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SENSORES.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SENSORES().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_grafica;
    private javax.swing.JProgressBar barra_tiempo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lbl_diagnostico;
    public javax.swing.JLabel lbl_doctor;
    public javax.swing.JLabel lbl_edad;
    public javax.swing.JLabel lbl_nombre;
    public javax.swing.JLabel lbl_paci;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.PanelShadow panelShadow1;
    private org.edisoncor.gui.panel.PanelTranslucidoComplete2 panelTranslucidoComplete21;
    private org.edisoncor.gui.panel.PanelTranslucidoComplete2 panel_trans;
    private javax.swing.JTextField txt_tem1;
    private javax.swing.JTextField txt_tem2;
    private javax.swing.JTextField txt_tem3;
    // End of variables declaration//GEN-END:variables
 JFreeChart chart;
    DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

    private void grafica() {

        for (int i = 0; i <= TEMP1.size() - 1; i++) {

            line_chart_dataset.addValue(TEMP1.get(i), "TEMPERATURA1", TIEMPO);

        }
        for (int i = 0; i <= TEMP2.size() - 1; i++) {

            line_chart_dataset.addValue(TEMP2.get(i), "TEMPERATURA2", TIEMPO);

        }
        for (int i = 0; i <= TEMP3.size() - 1; i++) {

            line_chart_dataset.addValue(TEMP3.get(i), "TEMPERATURA3", TIEMPO);

        }

        //line_chart_dataset.addValue(35, "visitas", "2min");
//        line_chart_dataset.addValue(30, "visitas", "3min");
//         line_chart_dataset.addValue(tem2, "dias", "1min");
//        line_chart_dataset.addValue(20, "dias", "2min");
        // Creando el Grafico    
//          chart = ChartFactory.createLineChart("Temperatura",
//                "Minutos", "Temperaturas en °C", line_chart_dataset, PlotOrientation.VERTICAL,
//                true, true, false);
        chart = ChartFactory.createLineChart("Temperatura", "Minutos", "Temperaturas en °C",
                line_chart_dataset, PlotOrientation.VERTICAL, true, true, false);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//Puntos en las líneas o no
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShapesVisible(2, true);
//líneas visibles o no
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesLinesVisible(2, false);
////etiquetas con los valores visibles o no
//renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//renderer.setItemLabelsVisible(false);

        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setSize(1000, 470);
        chartPanel.setLocation(10, 20);
        Panel_grafica.add(chartPanel);
        Panel_grafica.setVisible(false);
        Panel_grafica.setVisible(true);
    }

    void update_temperatura() {
        String te1 = "" + TEMP1;
        String te2 = "" + TEMP2;
        String te3 = "" + TEMP3;
        System.out.println("---------" + te1);
        System.out.println("---------" + te2);
        System.out.println("---------" + te3);
        try {
            String ins = "UPDATE db_paciente set n_temperatura = ? , n_temperatura2=? , n_temperatura3=? where id_paciente = ?";
            PreparedStatement pst = cnu.prepareStatement(ins);
            pst.setString(1, te1);
            pst.setString(2, te2);
            pst.setString(3, te3);
            pst.setString(4, No_paciente);
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Insercción Correcta", "Sistema ", JOptionPane.DEFAULT_OPTION, null);

            } else {
                JOptionPane.showMessageDialog(this, "La inserccion tuvo problemas", "Sistema ", JOptionPane.ERROR_MESSAGE);
            cnu.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SENSORES.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
// private double total_de_venta(double porcentaje) {
//        DecimalFormat decimales = new DecimalFormat("0.00");
//        pretotal = pretotal + porcentaje;
//        Txt_total.setText("" + decimales.format(pretotal));
//        return pretotal;
//    }
