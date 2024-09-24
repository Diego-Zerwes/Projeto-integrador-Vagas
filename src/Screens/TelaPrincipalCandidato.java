package Screens;

public class TelaPrincipalCandidato extends javax.swing.JFrame {

    public TelaPrincipalCandidato() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        menPerfil = new javax.swing.JMenu();
        menPerfilVisualizar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menVagasVisualizar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menCandidaturasVisualizar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Candidato");
        setResizable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );

        menPerfil.setText("Perfil");
        menPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPerfilActionPerformed(evt);
            }
        });

        menPerfilVisualizar.setText("Visualizar perfil");
        menPerfilVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menPerfilVisualizarActionPerformed(evt);
            }
        });
        menPerfil.add(menPerfilVisualizar);

        jMenuBar1.add(menPerfil);

        jMenu2.setText("Vagas");

        menVagasVisualizar.setText("Visualizar vagas");
        menVagasVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menVagasVisualizarActionPerformed(evt);
            }
        });
        jMenu2.add(menVagasVisualizar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Candidaturas");

        menCandidaturasVisualizar.setText("Visualizar candidaturas");
        menCandidaturasVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menCandidaturasVisualizarActionPerformed(evt);
            }
        });
        jMenu3.add(menCandidaturasVisualizar);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Currículo");

        jMenuItem1.setText("Visualizar ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        setSize(new java.awt.Dimension(600, 400));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPerfilActionPerformed
        // TODO add your handling code here:
        TelaPerfilCandidato tela = new TelaPerfilCandidato();
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_menPerfilActionPerformed

    private void menPerfilVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menPerfilVisualizarActionPerformed
        // TODO add your handling code here:
        TelaPerfilCandidato tela = new TelaPerfilCandidato();
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_menPerfilVisualizarActionPerformed

    private void menVagasVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menVagasVisualizarActionPerformed
        // TODO add your handling code here:
        TelaVagas tela = new TelaVagas();
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_menVagasVisualizarActionPerformed

    private void menCandidaturasVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menCandidaturasVisualizarActionPerformed
        // TODO add your handling code here:
        TelaCandidaturas tela = new TelaCandidaturas();
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_menCandidaturasVisualizarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        CurriculoCandidato tela = new CurriculoCandidato();
        tela.setVisible(true);
        desktop.add(tela);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalCandidato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalCandidato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    public static javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenuItem menCandidaturasVisualizar;
    private javax.swing.JMenu menPerfil;
    public static javax.swing.JMenuItem menPerfilVisualizar;
    public static javax.swing.JMenuItem menVagasVisualizar;
    // End of variables declaration//GEN-END:variables
}