package Screens;

import dao.ConexaoBanco;
import entities.Empregador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.text.ParseException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaPrincipalEmpregador extends javax.swing.JFrame {

    private Empregador empregador;
    private Connection conexao;

    public TelaPrincipalEmpregador(Empregador empregador) {
        initComponents();
        
        this.empregador = empregador;
        
        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection();
            atualizaDash();
        } else {
            JOptionPane.showMessageDialog(null, "Conexão com o banco falhou");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jPizza = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menPerfil = new javax.swing.JMenu();
        menPerfilVisualizar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCriarNovaVagas = new javax.swing.JMenuItem();
        jVisualizarMinhasVagas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jVagasEmpreenchidas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Empregador");
        setResizable(false);

        desktop.setPreferredSize(new java.awt.Dimension(450, 369));
        desktop.setLayout(new java.awt.BorderLayout());

        jPizza.setLayout(new java.awt.BorderLayout());
        desktop.add(jPizza, java.awt.BorderLayout.CENTER);

        menPerfil.setText("Perfil");

        menPerfilVisualizar.setText("Visualizar perfil");
        menPerfilVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPerfilVisualizarActionPerformed(evt);
            }
        });
        menPerfil.add(menPerfilVisualizar);

        jMenuBar1.add(menPerfil);

        jMenu2.setText("Vagas");

        jCriarNovaVagas.setText("Criar nova vaga");
        jCriarNovaVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCriarNovaVagasActionPerformed(evt);
            }
        });
        jMenu2.add(jCriarNovaVagas);

        jVisualizarMinhasVagas.setText("Visualizar minhas vagas ");
        jVisualizarMinhasVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVisualizarMinhasVagasActionPerformed(evt);
            }
        });
        jMenu2.add(jVisualizarMinhasVagas);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Vagas Empreenchidas");

        jVagasEmpreenchidas.setText("Visualizar");
        jVagasEmpreenchidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVagasEmpreenchidasActionPerformed(evt);
            }
        });
        jMenu3.add(jVagasEmpreenchidas);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(746, 503));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menPerfilVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPerfilVisualizarActionPerformed

        TelaPerfilEmpregador tela;
        try {
            tela = new TelaPerfilEmpregador(this.empregador);
            desktop.add(tela);
            tela.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipalEmpregador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menPerfilVisualizarActionPerformed

    private void jVisualizarMinhasVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVisualizarMinhasVagasActionPerformed
        TelaVisualizarVagasEmpregador tela = new TelaVisualizarVagasEmpregador(this.empregador);
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_jVisualizarMinhasVagasActionPerformed

    private void jVagasEmpreenchidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVagasEmpreenchidasActionPerformed
        TelaCandidatosInscritosEmpregador tela = new TelaCandidatosInscritosEmpregador(this.empregador);
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_jVagasEmpreenchidasActionPerformed
    private void jCriarNovaVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCriarNovaVagasActionPerformed
        TelaCriarNovasVagasEmpregador tela = new TelaCriarNovasVagasEmpregador(this.empregador);
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_jCriarNovaVagasActionPerformed

     private void atualizaDash() {
        new Thread() {
            @Override
            public void run() {
                    try {
                        ArrayList<Integer> listaDashboard = dashboard();
                        DefaultPieDataset pizzaChartData = new DefaultPieDataset();
                        pizzaChartData.setValue("Núm Candidatos", listaDashboard.get(0));
                        pizzaChartData.setValue("Núm de Vagas", listaDashboard.get(1));
                        pizzaChartData.setValue("Núm de Empresas", listaDashboard.get(2));
                        // Gráfico em pizza
                        JFreeChart pizzaChart = ChartFactory.createPieChart("Dados Gerais", pizzaChartData);
                        PiePlot piePlot = (PiePlot) pizzaChart.getPlot();
                        piePlot.setSectionPaint("Núm Candidatos", Color.BLUE);
                        piePlot.setSectionPaint("Núm de Vagas", Color.GREEN);
                        piePlot.setSectionPaint("Núm de Empresas", Color.RED);
                        ChartPanel chartPanel = new ChartPanel(pizzaChart);
                        //chartPanel.setPreferredSize(new.java.awt.Dimension(300, 300));
                        
                        jPizza.removeAll();
                       // jPizza.setLayout(new BorderLayout());
                        jPizza.add(chartPanel, BorderLayout.CENTER);
                        
                        jPizza.validate();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado:\n" + ex.getMessage(), "ERRO!", ERROR_MESSAGE);
                    }
            }
        }.start();
    }
    public ArrayList<Integer> dashboard() {
        ArrayList<Integer> listaDashboard = new ArrayList<>();
        String sqlVagas = "SELECT COUNT(*) AS totalVagas FROM Vagas";
        String sqlEmpresas = "SELECT COUNT(*) AS totalEmpresas FROM Empregador";
        String sqlCandidatos = "SELECT COUNT(*) AS totalCandidatos FROM candidato";
        try {
            PreparedStatement pstVagas = conexao.prepareStatement(sqlVagas);
            ResultSet rsVagas = pstVagas.executeQuery();
            if (rsVagas.next()) {
                listaDashboard.add(rsVagas.getInt("totalVagas"));
            }
            PreparedStatement pstEmpresas = conexao.prepareStatement(sqlEmpresas);
            ResultSet rsEmpresas = pstEmpresas.executeQuery();
            if (rsEmpresas.next()) {
                listaDashboard.add(rsEmpresas.getInt("totalEmpresas"));
            }
            PreparedStatement pstCandidatos = conexao.prepareStatement(sqlCandidatos);
            ResultSet rsCandidatos = pstCandidatos.executeQuery();
            if (rsCandidatos.next()) {
                listaDashboard.add(rsCandidatos.getInt("totalCandidatos"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return listaDashboard;
    }
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
            java.util.logging.Logger.getLogger(TelaPrincipalEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(Empregador empregador) {
                new TelaPrincipalEmpregador(empregador).setVisible(true);
            }

            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenuItem jCriarNovaVagas;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPizza;
    public static javax.swing.JMenuItem jVagasEmpreenchidas;
    public static javax.swing.JMenuItem jVisualizarMinhasVagas;
    private javax.swing.JMenu menPerfil;
    public static javax.swing.JMenuItem menPerfilVisualizar;
    // End of variables declaration//GEN-END:variables
}
