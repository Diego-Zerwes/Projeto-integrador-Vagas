/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Screens;

import dao.ConexaoBanco;
import entities.Empregador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class TelaPerfilEmpregador extends javax.swing.JInternalFrame {
    private Empregador empregador;
    Connection conexao = null;
    PreparedStatement pstEmpregador = null;
    PreparedStatement pstEndereco = null;
    PreparedStatement pstContato = null;
    ResultSet rsEmpregador = null;
    ResultSet rsEndereco = null;
    ResultSet rsContato = null;

    public TelaPerfilEmpregador(Empregador empregador) throws ParseException {
        initComponents();
        
        this.empregador = empregador;
        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection();
            plotarDados();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados");
        }
    }

    public void plotarDados() throws ParseException {
        String sqlEmpregador = "SELECT idEmpregador, nomeEmpregador, razaoSocialEmpregador, cnpjEmpregador, inscricaoEstadual, senha FROM Empregador WHERE idEmpregador = ?";
        String sqlEndereco = "SELECT rua, estado, cidade, CEP FROM endereco WHERE idEmpregador = ?";
        String sqlContato = "SELECT telefone, celular, email FROM contato WHERE idEmpregador = ?";
        try {
            pstEmpregador = conexao.prepareStatement(sqlEmpregador);
            pstEmpregador.setInt(1, empregador.getIdEmpregador()); // Passa o login como parâmetro para a consulta
            rsEmpregador = pstEmpregador.executeQuery();
            if (rsEmpregador.next()) {
                // Preenche os campos com os dados do usuário
                jidEmpregador.setText(rsEmpregador.getString("idEmpregador"));
                jNomeFantasia.setText(rsEmpregador.getString("nomeEmpregador"));
                jCNPJ.setText(rsEmpregador.getString("cnpjEmpregador"));
                jIE.setText(rsEmpregador.getString("inscricaoEstadual"));
                jSenha.setText(rsEmpregador.getString("senha"));

                pstEndereco = conexao.prepareStatement(sqlEndereco);
                pstEndereco.setInt(1, empregador.getIdEmpregador());
                rsEndereco = pstEndereco.executeQuery();

                pstContato = conexao.prepareStatement(sqlContato);
                pstContato.setInt(1, empregador.getIdEmpregador());
                rsContato = pstContato.executeQuery();

                if (rsEndereco.next() && rsContato.next()) {
                    jRua.setText(rsEndereco.getString("rua"));
                    jEstado.setText(rsEndereco.getString("estado"));
                    jCidade.setText(rsEndereco.getString("cidade"));
                    jCEP.setText(rsEndereco.getString("CEP"));

                    jTel.setText(rsContato.getString("telefone"));
                    jCel.setText(rsContato.getString("celular"));
                    jEmail.setText(rsContato.getString("email"));
                } else {
                    JOptionPane.showMessageDialog(null, "Endereço ou Contato não encontrado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar os dados: " + ex.getMessage());
        }
    }

    public void salvar() {
        String sqlEmpregador = "UPDATE Empregador SET nomeEmpregador = ?, cnpjEmpregador = ?, inscricaoEstadual = ?, senha = ? WHERE idEmpregador = ?";
        String sqlEndereco = "UPDATE endereco SET rua = ?, estado = ?, cidade = ?, CEP = ? WHERE idEmpregador = ?";
        String sqlContato = "UPDATE contato SET telefone = ?, celular = ?, email = ? WHERE idEmpregador = ?";
        try {
            conexao.setAutoCommit(false);

            pstEmpregador = conexao.prepareStatement(sqlEmpregador);
            pstEmpregador.setString(1, jNomeFantasia.getText());
            pstEmpregador.setString(2, jCNPJ.getText());
            pstEmpregador.setString(3, jIE.getText());
            pstEmpregador.setString(4, new String(jSenha.getPassword()));
            pstEmpregador.setInt(5, empregador.getIdEmpregador());
            int linhasAtualizadasEmpregador = pstEmpregador.executeUpdate();

            pstEndereco = conexao.prepareStatement(sqlEndereco);
            pstEndereco.setString(1, jRua.getText());
            pstEndereco.setString(2, jEstado.getText());
            pstEndereco.setString(3, jCidade.getText());
            pstEndereco.setString(4, jCEP.getText());
            pstEndereco.setInt(5, empregador.getIdEmpregador());
            int linhasAtualizadasEndereco = pstEndereco.executeUpdate();

            pstContato = conexao.prepareStatement(sqlContato);
            pstContato.setString(1, jTel.getText());
            pstContato.setString(2, jCel.getText());
            pstContato.setString(3, jEmail.getText());
            pstContato.setInt(4, empregador.getIdEmpregador());
            int linhasAtualizadasContato = pstContato.executeUpdate();

            if (linhasAtualizadasEmpregador > 0 && linhasAtualizadasEndereco > 0 && linhasAtualizadasContato > 0) {
                conexao.commit();
                JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso!");
                limpar_campos();
            } else {
                conexao.rollback();
                JOptionPane.showMessageDialog(null, "Falha na atualização dos dados!");
            }
        } catch (SQLException e) {
            try {
                conexao.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao desfazer alterações: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public void editar() {
        jNomeFantasia.setEnabled(true);
        jCNPJ.setEnabled(true);
        jIE.setEnabled(true);
        jCEP.setEnabled(true);
        jRua.setEnabled(true);
        jEstado.setEnabled(true);
        jCidade.setEnabled(true);
        jTel.setEnabled(true);
        jCel.setEnabled(true);
        jEmail.setEnabled(true);
        jSenha.setEnabled(true);
    }

    public void limpar_campos() {
        jNomeFantasia.setEnabled(false);
        jCNPJ.setEnabled(false);
        jIE.setEnabled(false);
        jCEP.setEnabled(false);
        jRua.setEnabled(false);
        jEstado.setEnabled(false);
        jCidade.setEnabled(false);
        jTel.setEnabled(false);
        jCel.setEnabled(false);
        jEmail.setEnabled(false);
        jSenha.setEnabled(false);
    

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jidEmpregador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jNomeFantasia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jRazaoSocial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCNPJ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jIE = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jCel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jRua = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jEstado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jCidade = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCEP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Perfil do empregador");
        setPreferredSize(new java.awt.Dimension(730, 480));

        jLabel1.setText("Id");

        jLabel2.setText("Nome fantasia");

        jLabel3.setText("Razão Social");

        jLabel4.setText("CNPJ");

        jLabel5.setText("I.E.");

        jLabel6.setText("Telefone");

        jLabel7.setText("Celular");

        jCel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCelActionPerformed(evt);
            }
        });

        jLabel8.setText("Email");

        jLabel9.setText("Rua");

        jLabel10.setText("Estado");

        jEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEstadoActionPerformed(evt);
            }
        });

        jLabel11.setText("Cidade");

        jLabel12.setText("CEP");

        jButton1.setText("Editar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel13.setText("Senha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jEstado)
                            .addComponent(jCEP))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                            .addComponent(jSenha)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCel))
                                    .addComponent(jNomeFantasia)
                                    .addComponent(jRazaoSocial)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jIE, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jidEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jRua, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jidEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jCelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCelActionPerformed

    private void jEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEstadoActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        salvar();
        editar();
        limpar_campos();
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCEP;
    private javax.swing.JTextField jCNPJ;
    private javax.swing.JTextField jCel;
    private javax.swing.JTextField jCidade;
    private javax.swing.JTextField jEmail;
    private javax.swing.JTextField jEstado;
    private javax.swing.JTextField jIE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jNomeFantasia;
    private javax.swing.JTextField jRazaoSocial;
    private javax.swing.JTextField jRua;
    private javax.swing.JPasswordField jSenha;
    private javax.swing.JTextField jTel;
    private javax.swing.JTextField jidEmpregador;
    // End of variables declaration//GEN-END:variables
}
