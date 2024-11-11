
package Screens;

//import entities.Candidato;
import entities.Empregador;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TelaPrincipalEmpregador extends javax.swing.JFrame {

    private Empregador empregador;
    public TelaPrincipalEmpregador(Empregador empregador) {
        initComponents();
        this.empregador = empregador;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menPerfil = new javax.swing.JMenu();
        menPerfilVisualizar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCriarNovaVagas = new javax.swing.JMenuItem();
        jVisualizarMinhasVagas = new javax.swing.JMenuItem();
        jVisualizarTodasVagas = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jVagasEmpreenchidas = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Empregador");
        setResizable(false);

        desktop.setPreferredSize(new java.awt.Dimension(450, 369));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );

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

        jVisualizarTodasVagas.setText("Visualizar todas as vagas");
        jVisualizarTodasVagas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVisualizarTodasVagasActionPerformed(evt);
            }
        });
        jMenu2.add(jVisualizarTodasVagas);

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
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(832, 503));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menPerfilVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPerfilVisualizarActionPerformed

        TelaPerfilEmpregador tela;        
        try {
            tela = new TelaPerfilEmpregador(this.empregador);
            desktop.add(tela);
            tela.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipalCandidato.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jVisualizarTodasVagasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVisualizarTodasVagasActionPerformed
        TelaAbaDosCandidatos tela;
        try {
            tela = new TelaAbaDosCandidatos(empregador);
            tela.setVisible(true);
            desktop.add(tela);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir a tela: " + e.getMessage());
        }
    
       
    }//GEN-LAST:event_jVisualizarTodasVagasActionPerformed

    
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
    public static javax.swing.JMenuItem jVagasEmpreenchidas;
    public static javax.swing.JMenuItem jVisualizarMinhasVagas;
    private javax.swing.JMenuItem jVisualizarTodasVagas;
    private javax.swing.JMenu menPerfil;
    public static javax.swing.JMenuItem menPerfilVisualizar;
    // End of variables declaration//GEN-END:variables
}
