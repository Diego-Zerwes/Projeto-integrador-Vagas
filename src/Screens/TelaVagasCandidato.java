package Screens;

import dao.ConexaoBanco;
import entities.Candidato;
import entities.CandidatoVaga;
import entities.Vagas;
import java.sql.*;
import javax.swing.JOptionPane;
//a linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

public class TelaVagasCandidato extends javax.swing.JInternalFrame {

    Candidato candidato = new Candidato();
    Vagas vaga = new Vagas();

    Connection conexao = null;
    PreparedStatement pst = null;
    PreparedStatement pstCandVagas = null;
    ResultSet rs = null;
    ResultSet rsCandVagas = null;

    public TelaVagasCandidato(Candidato candidato) {
        initComponents();
        this.candidato = candidato;

        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection(); 
            new Thread(new Runnable() {
                @Override
                public void run() {
                    pesquisar_vaga();
                }
            }).start();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void pesquisar_vaga() {
        String sql = "SELECT idVagas AS Id, descricao AS Descricão, remuneracao AS Remuneracão, requisitos AS Requisitos, idEmpregador FROM Vagas WHERE descricao like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtPesquisarVaga.getText() + "%");
            rs = pst.executeQuery();
            tblVagas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar as vagas! " + e.getMessage());
        }
    }

    public void candidatar() {
        String sql = "INSERT INTO CandidatoVagas (idCandidato, idVagas) VALUES (?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            int linhaSelecionada = tblVagas.getSelectedRow();
            int idVaga = Integer.parseInt(tblVagas.getModel().getValueAt(linhaSelecionada, 0).toString());
            pst.setInt(2, idVaga);
            int candidatar = pst.executeUpdate();
            if (candidatar > 0) {
                JOptionPane.showMessageDialog(null, "Candidatura efetuada com sucesso! ");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao se candidatar à vaga! ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados! " + e.getMessage());
        }
    }

    public void cancelarCandidatura() {
        String sql = "DELETE FROM CandidatoVagas WHERE idVagas=? AND idCandidato=? ";
        try {
            pst = conexao.prepareStatement(sql);
            int linhaSelecionada = tblVagas.getSelectedRow();
            int idVaga = Integer.parseInt(tblVagas.getModel().getValueAt(linhaSelecionada, 0).toString());
            pst.setInt(1, idVaga);
            pst.setInt(2, this.candidato.getIdCandidato());
            int cancelarCandidatura = pst.executeUpdate();
            if (cancelarCandidatura > 0) {
                JOptionPane.showMessageDialog(null, "Candidatura cancelada com sucesso! ");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cancelar candidatura! ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados! " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagas = new javax.swing.JTable();
        txtPesquisarVaga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCancelarCandidatura = new javax.swing.JButton();
        btnCandidatar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Vagas em geral");
        setPreferredSize(new java.awt.Dimension(730, 480));

        tblVagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Salário", "Requisitos", "idEmpregador"
            }
        ));
        tblVagas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVagasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVagas);

        txtPesquisarVaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarVagaActionPerformed(evt);
            }
        });
        txtPesquisarVaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarVagaKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_search_icon.png"))); // NOI18N
        jLabel1.setText("(descrição)");

        btnCancelarCandidatura.setText("Cancelar candidatura");
        btnCancelarCandidatura.setEnabled(false);
        btnCancelarCandidatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCandidaturaActionPerformed(evt);
            }
        });

        btnCandidatar.setText("Candidatar");
        btnCandidatar.setEnabled(false);
        btnCandidatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCandidatarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelarCandidatura)
                        .addGap(23, 23, 23)
                        .addComponent(btnCandidatar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtPesquisarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel1))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(txtPesquisarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelarCandidatura)
                    .addComponent(btnCandidatar))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarVagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarVagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisarVagaActionPerformed

    private void txtPesquisarVagaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarVagaKeyReleased
        // chamando o método pesquisar_vaga;
        pesquisar_vaga();
    }//GEN-LAST:event_txtPesquisarVagaKeyReleased

    private void tblVagasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVagasMouseClicked
        // habilitando os botões de cadastrar e cancelar candidatura
        btnCancelarCandidatura.setEnabled(true);
        btnCandidatar.setEnabled(true);
    }//GEN-LAST:event_tblVagasMouseClicked

    private void btnCandidatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCandidatarActionPerformed
        // chamado o método candidatar
        candidatar();
    }//GEN-LAST:event_btnCandidatarActionPerformed

    private void btnCancelarCandidaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCandidaturaActionPerformed
        // chamando o método cancelarCandidatura
        cancelarCandidatura();
    }//GEN-LAST:event_btnCancelarCandidaturaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarCandidatura;
    private javax.swing.JButton btnCandidatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVagas;
    private javax.swing.JTextField txtPesquisarVaga;
    // End of variables declaration//GEN-END:variables
}
