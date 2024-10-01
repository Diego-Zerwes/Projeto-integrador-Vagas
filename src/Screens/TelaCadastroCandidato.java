package Screens;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import java.sql.*;
import dao.ConexaoBanco;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class TelaCadastroCandidato extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pstCandidato = null;
    PreparedStatement pstEndereco = null;
    PreparedStatement pstContato = null;
    ResultSet rs = null;

    public TelaCadastroCandidato() {
        initComponents();

        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection();
            JOptionPane.showMessageDialog(null, "Conexão com o banco estabelecida com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Conexão com o banco falhou");

        }
    }

    public void cadastrar() {
        String sqlCandidato = "INSERT INTO candidato (nome, RG, dataNascimento) VALUES (?,?,?)";
        String sqlEndereco = "INSERT INTO endereco (rua, estado, cidade, CEP, idCandidato) VALUES (?,?,?,?,?)";
        String sqlContato = "INSERT INTO contato (telefone, celular, email, idCandidato) VALUES (?,?,?,?)";

        try {
            if (txtNome.getText().isEmpty() || txtRg.getText().isEmpty() || txtDatNasc.getText().isEmpty() || txtCep.getText().isEmpty() || txtTel.getText().isEmpty() || txtCel.getText().isEmpty() || txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                return;
            }

            conexao.setAutoCommit(false);
            //Cadastrando candidato
            pstCandidato = conexao.prepareStatement(sqlCandidato, Statement.RETURN_GENERATED_KEYS);

            pstCandidato.setString(1, txtNome.getText());
            pstCandidato.setString(2, txtRg.getText());

            String dataDeNascimentoTexto = txtDatNasc.getText();
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
            Date dataNascimento = formatoEntrada.parse(dataDeNascimentoTexto);
            String dataFormatada = formatoBanco.format(dataNascimento);
            pstCandidato.setString(3, dataFormatada);

            int candidatoCadastrado = pstCandidato.executeUpdate();

            ResultSet rsId = pstCandidato.getGeneratedKeys();
            int idCandidato = 0;

            if (rsId.next()) {
                idCandidato = rsId.getInt(1);
            }

            if (idCandidato > 0) {
                //cadastrando Endereço do candidato
                pstEndereco = conexao.prepareStatement(sqlEndereco);
                pstEndereco.setString(1, txtRua.getText());
                pstEndereco.setString(2, txtEstado.getText());
                pstEndereco.setString(3, txtCidade.getText());
                pstEndereco.setString(4, txtCep.getText());
                pstEndereco.setInt(5, idCandidato);

                int enderecoCadastrado = pstEndereco.executeUpdate();

                //cadastrando contatos do candidato
                pstContato = conexao.prepareStatement(sqlContato);

                pstContato.setString(1, txtTel.getText());
                pstContato.setString(2, txtCel.getText());
                pstContato.setString(3, txtEmail.getText());
                pstContato.setInt(4, idCandidato);

                int contatoCadastrado = pstContato.executeUpdate();

                if (candidatoCadastrado > 0 && enderecoCadastrado > 0 && contatoCadastrado > 0) {
                    conexao.commit();
                    limpar_campos();
                    JOptionPane.showMessageDialog(null, "Candidato cadastrado com sucesso");
                } else {
                    conexao.rollback();
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar o candidato e suas informações");
                }

            } else {
                conexao.rollback();
                JOptionPane.showMessageDialog(null, "Erro ao recuperar o ID do candidato");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir no banco de dados: " + e.getMessage());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar a data de nascimento. Verifique o formato (dd/MM/yyyy).");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public void limpar_campos() {
        txtNome.setText("");
        txtRg.setText("");
        txtDatNasc.setText("");
        txtCep.setText("");
        txtRua.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        txtTel.setText("");
        txtCel.setText("");
        txtEmail.setText("");
        txtNome.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDatNasc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro (candidato)");
        setPreferredSize(new java.awt.Dimension(700, 512));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Insira os dados");

        jLabel2.setText("*Nome");

        jLabel3.setText("*RG");

        jLabel4.setText("*Data de nascimento (dd/mm/aaaa)");

        jLabel5.setText("Rua");

        txtCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCepFocusLost(evt);
            }
        });
        txtCep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCepKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCepKeyTyped(evt);
            }
        });

        jLabel6.setText("Estado");

        txtRua.setEnabled(false);

        jLabel7.setText("Cidade");

        txtEstado.setEnabled(false);

        jLabel8.setText("*CEP");

        txtCidade.setEnabled(false);
        txtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCidadeKeyPressed(evt);
            }
        });

        jLabel9.setText("*Telefone");

        jLabel10.setText("*Celular");

        jLabel11.setText("*Email");

        btnCadastrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel12.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel12)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(btnCadastrar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtRua)
                            .addComponent(txtCidade)
                            .addComponent(txtTel)
                            .addComponent(txtCel)
                            .addComponent(txtEmail)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCep, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRg))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtDatNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtDatNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCadastrar))
                .addGap(118, 118, 118))
        );

        setSize(new java.awt.Dimension(716, 503));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // fechando a tela de cadastro do candidato
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        // chamando o método cadastrar
        cadastrar();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyPressed

    }//GEN-LAST:event_txtCidadeKeyPressed

    private void txtCepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit((c))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCepKeyTyped

    private void txtCepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCepKeyPressed
        // completando o endereço através da api viacep
        if (evt.getExtendedKeyCode() == evt.VK_ENTER) {
            ViaCEP viaCep = new ViaCEP();
            try {
                viaCep.buscar(txtCep.getText());
                txtRua.setText(viaCep.getLogradouro());
                txtEstado.setText(viaCep.getUf());
                txtCidade.setText(viaCep.getLocalidade());
            } catch (ViaCEPException ex) {
                JOptionPane.showMessageDialog(null,
                        "Ocorreu um erro inesperado:\n" + ex.getMessage(), "ERRO!", ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_txtCepKeyPressed

    private void txtCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCepFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCepFocusLost

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroCandidato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCel;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDatNasc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtTel;
    // End of variables declaration//GEN-END:variables
}
