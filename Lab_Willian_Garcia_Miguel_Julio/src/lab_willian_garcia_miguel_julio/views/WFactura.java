/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_willian_garcia_miguel_julio.views;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static lab_willian_garcia_miguel_julio.controls.Lab_Willian_Garcia_Miguel_Julio.LaPros;
import static lab_willian_garcia_miguel_julio.controls.Restaurante.ordenes;
import lab_willian_garcia_miguel_julio.models.Mesa;
import static lab_willian_garcia_miguel_julio.views.WMesero.cc;

/**
 *
 * @author Administrador
 */
public class WFactura extends javax.swing.JFrame {

    /**
     * Creates new form WFactura
     */
    public static int mesa;
    public static W2Mesero anterior;

    public WFactura(W2Mesero anterior, int mesa) {
        initComponents();
        this.mesa = mesa;
        this.anterior = anterior;
        try {
            facturar(mesa);
        } catch (Exception ex) {
            Logger.getLogger(WFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSize(419, 267);
        this.setResizable(false);
    }

    public void facturar(int mesa) throws Exception {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        BufferedReader br = new BufferedReader(new FileReader("Archivos\\Facturas\\Factura" + mesa + ".txt"));
        String linea = br.readLine();
        FileWriter fw = new FileWriter("Archivos\\Mesas\\mesa" + mesa + ".txt", true);
        PrintWriter pw = new PrintWriter(fw);
        float total = 0;
        while (linea != null) {
            if (!linea.equals("")) {
                pw.println(linea);//Añadir a mesa general
                StringTokenizer st = new StringTokenizer(linea, ";");
                //añador a jtable;
                while (st.hasMoreTokens()) {
                    String pd = st.nextToken(), c = st.nextToken(), pr = st.nextToken();
                    double subtotal = Double.parseDouble(c) * Double.parseDouble(pr);
                    total += subtotal;
                    modelo.addRow(new Object[]{pd, c, pr, subtotal});
                    st.nextToken();
                    st.nextToken();
                }
                //crear/añadir consolidado platos
                st = new StringTokenizer(linea, ";");
                FileWriter fw2 = new FileWriter("Archivos\\Consolidados\\Platos.txt", true);
                PrintWriter pw2 = new PrintWriter(fw2);
                while (st.hasMoreTokens()) {
                    String pd = st.nextToken(), c = st.nextToken();
                    pw2.println(pd+";"+c);
                    st.nextToken();
                    st.nextToken();//cat
                    st.nextToken();
                }
                fw2.close();
                pw2.close();
                //crear/añadir consolidado cat
                st = new StringTokenizer(linea, ";");
                 fw2 = new FileWriter("Archivos\\Consolidados\\Categorias.txt", true);
                 pw2 = new PrintWriter(fw2);
                while (st.hasMoreTokens()) {
                    st.nextToken();
                    String c = st.nextToken();
                    st.nextToken();
                    String cat = st.nextToken();
                    st.nextToken();
                    pw2.println(cat+";"+c);
                }
                fw2.close();
                pw2.close();
                
            }
            linea = br.readLine();
        }
        modelo.addRow(new Object[]{"", "", "iva", total*0.19});
        modelo.addRow(new Object[]{"", "", "Propina", total*0.1});
        total=total*0.19f+total*0.1f+total;
        modelo.addRow(new Object[]{"", "", "Total", total});
        pw.close();
        fw = new FileWriter("Archivos\\Facturas\\Factura" + mesa + ".txt", false);
        pw = new PrintWriter(fw);
        pw.print("");
        pw.close();
        br.close();
        Mesa temp = LaPros.busMes(mesa);
        temp.setPlatos(null);
        if (ordenes == null) {
            LaPros.busM(cc).delM(mesa);
            temp.setCc(0);
        } else if (!ordenes.busO(mesa)) {
            LaPros.busM(cc).delM(mesa);
            temp.setCc(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Factura");
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        jTable1.setBackground(new java.awt.Color(251, 248, 198));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "producto", "cantidad", "precio","subtotal"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 294, 241);

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(310, 60, 80, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-570, 0, 1030, 850);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 430, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        anterior.update();
        anterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(WFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WFactura(anterior, mesa).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
