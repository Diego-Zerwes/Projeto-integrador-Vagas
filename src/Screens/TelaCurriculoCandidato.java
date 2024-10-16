
package Screens;

import dao.ConexaoBanco;
import entities.Candidato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class TelaCurriculoCandidato extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Candidato candidato = new Candidato();

    public TelaCurriculoCandidato(Candidato candidato) {
        initComponents();
        this.candidato = candidato;

        ConexaoBanco conn = new ConexaoBanco();
        if (conn.conectar()) {
            conexao = conn.getConnection();
            plotarDadosCurriculo();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }
    
    public void plotarDadosCurriculo() {
        String sql = "SELECT c.nome, c.RG, c.dataNascimento, cur.experiencia, cur.graduacao "
                + "FROM candidato AS c "
                + "INNER JOIN curriculo AS cur ON (c.idCandidato = cur.idCandidato) "
                + "WHERE c.idCandidato = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString("nome"));
                txtRg.setText(rs.getString("RG"));
                txtDataNasc.setText(rs.getString("dataNascimento"));
                txtExp.setText(rs.getString("experiencia"));
                txtGrad.setText(rs.getString("graduacao"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }
    
    public void editarCurriculo() {
        String sql = "UPDATE curriculo AS c SET c.experiencia=?, c.graduacao=? WHERE c.idCandidato=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtExp.getText());
            pst.setString(2, txtGrad.getText());
            pst.setInt(3, this.candidato.getIdCandidato());
            int editarCurriculo = pst.executeUpdate();
            if(editarCurriculo > 0) {
                JOptionPane.showMessageDialog(null, "Currículo editado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }
    
    public void excluirCurriculo(){
        String sql = "DELETE FROM curriculo WHERE idCandidato=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            int excluirCurriculo = pst.executeUpdate();
            if(excluirCurriculo > 0) {
                txtExp.setText("");
                txtGrad.setText("");
                JOptionPane.showMessageDialog(null, "Currículo excluído com sucesso!"); 
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtExp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGrad = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnExc = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        txtDataNasc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Currículo");
        setPreferredSize(new java.awt.Dimension(730, 480));

        jLabel1.setText("Experiência");

        jLabel2.setText("Graduação");

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnExc.setText("Excluir");
        btnExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome candidato");

        txtNome.setEnabled(false);

        jLabel5.setText("RG");

        txtRg.setEnabled(false);

        txtDataNasc.setEnabled(false);

        jLabel6.setText("Data Nasc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEdit)
                        .addGap(18, 18, 18)
                        .addComponent(btnExc))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtExp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGrad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnExc))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // editando o currículo        
        editarCurriculo();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcActionPerformed
        // excluindo o currículo  
        excluirCurriculo();
    }//GEN-LAST:event_btnExcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtDataNasc;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtGrad;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    // End of variables declaration//GEN-END:variables
}
