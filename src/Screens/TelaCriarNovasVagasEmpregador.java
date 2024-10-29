/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Screens;
import entities.Vagas;
import entities.Empregador;
import java.sql.*;
import dao.ConexaoBanco;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 *
 * @author Usuario
 */
public class TelaCriarNovasVagasEmpregador extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pstEmpregador = null;
    PreparedStatement pstVagas = null;
    ResultSet rs = null;
    Empregador empregador = null;
    
    public TelaCriarNovasVagasEmpregador(Empregador empregador) {
        initComponents();
        
        this.empregador = empregador; // Receba o empregador logado
        ConexaoBanco con = new ConexaoBanco();
        if (con.conectar()) {
            conexao = con.getConnection();
            txtEmpregador.setText(String.valueOf(empregador.getIdEmpregador()));
        } else {
            JOptionPane.showMessageDialog(null, "Conexão com o banco falhou");
        }
        
    }
        public void cadastrar() {
        String sqlVagas = "INSERT INTO Vagas (descricao, remuneracao, requisitos, idEmpregador) VALUES (?,?,?,?)";
        try {
            if (txtDescricao.getText().isEmpty() || txtRemuneracao.getText().isEmpty() || txtRequisitos.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                return;
            }
            conexao.setAutoCommit(false);
            pstVagas = conexao.prepareStatement(sqlVagas, Statement.RETURN_GENERATED_KEYS);
            pstVagas.setString(1, txtDescricao.getText());
            pstVagas.setString(2, txtRemuneracao.getText());
            pstVagas.setString(3, txtRequisitos.getText());
            pstVagas.setInt(4, empregador.getIdEmpregador()); // Adiciona o ID do empregador
            int vagasCadastradas = pstVagas.executeUpdate();
            ResultSet rsId = pstVagas.getGeneratedKeys();
            int idVaga = 0;
            if (rsId.next()) {
                idVaga = rsId.getInt(1);
            }
            if (idVaga > 0) {
                // Sucesso ao cadastrar a vaga
                JOptionPane.showMessageDialog(null, "Vaga cadastrada com sucesso!");
                conexao.commit();
                limpar_campos();
            } else {
                // Falha ao cadastrar a vaga
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar a vaga");
                conexao.rollback();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar vaga: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            try {
                conexao.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao reverter transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (pstVagas != null) pstVagas.close();
                if (conexao != null) conexao.setAutoCommit(true);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar recursos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        }

        public void limpar_campos() {
        txtDescricao.setText("");
        txtRemuneracao.setText("");
        txtRequisitos.setText("");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        txtRemuneracao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRequisitos = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmpregador = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de vaga");
        setPreferredSize(new java.awt.Dimension(730, 480));

        jLabel1.setText("Descrição");

        jLabel2.setText("Remuneração");

        jLabel3.setText("Requisitos");

        txtRequisitos.setColumns(20);
        txtRequisitos.setRows(5);
        jScrollPane1.setViewportView(txtRequisitos);

        jButton1.setText("Cadastrar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel4.setText("Empregador");

        txtEmpregador.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addComponent(txtRemuneracao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmpregador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRemuneracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(106, Short.MAX_VALUE))))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        cadastrar();
        limpar_campos();
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEmpregador;
    private javax.swing.JTextField txtRemuneracao;
    private javax.swing.JTextArea txtRequisitos;
    // End of variables declaration//GEN-END:variables
}
