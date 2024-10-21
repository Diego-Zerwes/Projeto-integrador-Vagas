

package Screens;

import java.sql.*;
import dao.ConexaoBanco;
import java.text.ParseException;
import javax.swing.JOptionPane;
import entities.Empregador;
import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaCadastroEmpregador extends javax.swing.JFrame {
    Connection conexao = null;
    PreparedStatement pstEmpregador = null;
    PreparedStatement pstEndereco = null;
    PreparedStatement pstContato = null;
    ResultSet rs = null;
    private JTextField jNomeFantasia;
    private JTextField jCNPJ;
    private JPasswordField jSenha;
    private JTextField jCEP;
    private JTextField jCel;
    private JTextField jEmail;
    private JTextField jIE;
    private JTextField jRazaoSocial;
    private JTextField jTel;
    private JTextField jRua;
    private JTextField jEstado;
    private JTextField jCidade;
    private JTextField jCep;



    public TelaCadastroEmpregador() {
        initComponents();

        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection();
            //JOptionPane.showMessageDialog(null, "Conexão com o banco estabelecida com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Conexão com o banco falhou");
        }
    }

    public void cadastrar(int jidEmpregador) {
        String sqlEmpregador = "INSERT INTO Empregador (nomeFantasia, razaoSocial, CNPJ, IE, tipoUsuario, senha) VALUES (?,?,?,?,?,?)";
        String sqlEndereco = "INSERT INTO endereco (rua, estado, cidade, CEP, idEmpregador) VALUES (?,?,?,?,?)";
        String sqlContato = "INSERT INTO contato (telefone, celular, email, idEmpregador) VALUES (?,?,?,?)";

        try {
            if (jNomeFantasia.getText().isEmpty() || jRazaoSocial.getText().isEmpty() || jCNPJ.getText().isEmpty() || jIE.getText().isEmpty() || jCEP.getText().isEmpty() || jTel.getText().isEmpty() || jCel.getText().isEmpty() || jEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                return;
            }
            conexao.setAutoCommit(false);
            // Cadastrando empregador
            pstEmpregador = conexao.prepareStatement(sqlEmpregador, Statement.RETURN_GENERATED_KEYS);
            pstEmpregador.setString(1, jNomeFantasia.getText());
            pstEmpregador.setString(2, jRazaoSocial.getText());
            pstEmpregador.setString(3, jCNPJ.getText());
            pstEmpregador.setString(4, jIE.getText());
            pstEmpregador.setString(5, "Empregador");
            pstEmpregador.setString(6, new String(jSenha.getPassword()));
            int empregadorCadastrado = pstEmpregador.executeUpdate();
            ResultSet rsId = pstEmpregador.getGeneratedKeys();
            int idEmpregador = 0;
            if (rsId.next()) {
                idEmpregador = rsId.getInt(1);
            }
            if (idEmpregador > 0) {
                // Cadastrando Endereço do empregador
                pstEndereco = conexao.prepareStatement(sqlEndereco);
                pstEndereco.setString(1, jRua.getText());
                pstEndereco.setString(2, jEstado.getText());
                pstEndereco.setString(3, jCidade.getText());
                pstEndereco.setString(4, jCEP.getText());
                pstEndereco.setInt(5, idEmpregador);
                int enderecoCadastrado = pstEndereco.executeUpdate();
                // Cadastrando contatos do empregador
                pstContato = conexao.prepareStatement(sqlContato);
                pstContato.setString(1, jTel.getText());
                pstContato.setString(2, jCel.getText());
                pstContato.setString(3, jEmail.getText());
                pstContato.setInt(4, idEmpregador);
                int contatoCadastrado = pstContato.executeUpdate();
                if (empregadorCadastrado > 0 && enderecoCadastrado > 0 && contatoCadastrado > 0) {
                    conexao.commit();
                    limpar_campos();
                    JOptionPane.showMessageDialog(null, "Empregador cadastrado com sucesso");
                } else {
                    conexao.rollback();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o empregador e suas informações");
                }
            } else {
                conexao.rollback();
                JOptionPane.showMessageDialog(null, "Erro ao recuperar o ID do empregador");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir no banco de dados: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public void limpar_campos() {
        jNomeFantasia.setText("");
        jRazaoSocial.setText("");
        jCNPJ.setText("");
        jIE.setText("");
        jCEP.setText("");
        jRua.setText("");
        jCidade.setText("");
        jEstado.setText("");
        jTel.setText("");
        jCel.setText("");
        jEmail.setText("");
        jSenha.setText("");
        jNomeFantasia.requestFocus();
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jidEmpregador = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jNomeFantasia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jRazaoSocial = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCNPJ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jIE = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRua = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jEstado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jCidade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jCEP = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Insira os dados");

        jLabel2.setText("Id");

        jLabel3.setText("Nome fantasia *");

        jNomeFantasia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNomeFantasiaActionPerformed(evt);
            }
        });

        jLabel4.setText("Razão Social *");

        jLabel5.setText("CNPJ *");

        jLabel6.setText("I.E.");

        jLabel7.setText("Telefone *");

        jLabel8.setText("Cel.");

        jLabel9.setText("Email *");

        jLabel10.setText("Rua");

        jRua.setEditable(false);

        jLabel11.setText("Estado");

        jEstado.setEditable(false);

        jLabel12.setText("Cidade");

        jCidade.setEditable(false);

        jLabel13.setText("CEP *");

        jCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCEPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCEPKeyTyped(evt);
            }
        });

        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCadastrarKeyPressed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel14.setText("Senha *");

        jSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnCancelar)
                            .addGap(18, 18, 18)
                            .addComponent(btnCadastrar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel2)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jidEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRua, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTel)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jCel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jRazaoSocial)
                                        .addComponent(jEmail)
                                        .addComponent(jCEP)
                                        .addComponent(jCidade)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jIE, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))
                                    .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jidEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNomeFantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jRazaoSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCadastrar))
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        this.setVisible(false)
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jNomeFantasiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNomeFantasiaActionPerformed

    }//GEN-LAST:event_jNomeFantasiaActionPerformed

    private void btnCadastrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCadastrarKeyPressed
        // TODO add your handling code here:
        cadastrar()
        TelaLogin tl = new TelaLogin()
        tl.setVisible(true)
        this.dispose()
    }//GEN-LAST:event_btnCadastrarKeyPressed

    private void jCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCEPKeyPressed

        if (evt.getExtendedKeyCode() == evt.VK_ENTER) {
            ViaCEP viaCep = new ViaCEP()
            try {
                viaCep.buscar(jCep.getText())
                jRua.setText(viaCep.getLogradouro())
                jEstado.setText(viaCep.getUf())
                jCidade.setText(viaCep.getLocalidade())
            } catch (ViaCEPException ex) {
                JOptionPane.showMessageDialog(null,
                        "Ocorreu um erro inesperado:\n" + ex.getMessage(), "ERRO!", ERROR_MESSAGE)
            }

        }
    }//GEN-LAST:event_jCEPKeyPressed

    private void jCEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCEPKeyTyped
        char c = evt.getKeyChar()
        if (!Character.isDigit((c))) {
            evt.consume()
        }
    }//GEN-LAST:event_jCEPKeyTyped

    private void jSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSenhaActionPerformed

    public static void main(String args[]){
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
            java.util.logging.Logger.getLogger(TelaCadastroEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroEmpregador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroEmpregador().setVisible(true)
            }
        })
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
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
    private javax.swing.JLabel jLabel14;
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
