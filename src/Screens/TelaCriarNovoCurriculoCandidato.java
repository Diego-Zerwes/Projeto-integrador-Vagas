package Screens;

import dao.ConexaoBanco;
import entities.Candidato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class TelaCriarNovoCurriculoCandidato extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Candidato candidato = new Candidato();

    public TelaCriarNovoCurriculoCandidato(Candidato candidato) {
        initComponents();
        this.candidato = candidato;

        ConexaoBanco conn = new ConexaoBanco();
        if (conn.conectar()) {
            conexao = conn.getConnection();
            plotarDadosIniciais();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void plotarDadosIniciais() {
        String sql = "SELECT c.nome, c.RG, c.dataNascimento FROM candidato AS c WHERE c.idCandidato=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString("nome"));
                txtRg.setText(rs.getString("RG"));
                txtDataNasc.setText(rs.getString("dataNascimento"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void criarCurriculo() {
        String sql = "INSERT INTO curriculo (experiencia, graduacao, idCandidato) VALUES (?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtExp.getText());
            pst.setString(2, txtGrad.getText());
            pst.setInt(3, this.candidato.getIdCandidato());
            int cadastrarCurriculo = pst.executeUpdate();
            if (cadastrarCurriculo > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro do currículo realizado com sucesso!");
                txtExp.setEnabled(false);
                txtGrad.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDataNasc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExp = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtGrad = new javax.swing.JTextField();
        btnCriarCur = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Criar currículo");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(730, 480));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Insira os dados");

        jLabel2.setText("Nome");

        txtNome.setEnabled(false);

        jLabel3.setText("RG");

        txtRg.setEnabled(false);

        jLabel4.setText("Data Nasc");

        txtDataNasc.setEnabled(false);

        txtExp.setColumns(20);
        txtExp.setRows(5);
        jScrollPane1.setViewportView(txtExp);

        jLabel5.setText("Experiência");

        jLabel6.setText("Graduação");

        btnCriarCur.setText("Criar");
        btnCriarCur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarCurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCriarCur)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtGrad))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addGap(40, 40, 40)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGrad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCriarCur)
                .addGap(82, 82, 82))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCriarCurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarCurActionPerformed
        // criando o currículo
        criarCurriculo();
    }//GEN-LAST:event_btnCriarCurActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarCur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextField txtDataNasc;
    private javax.swing.JTextArea txtExp;
    private javax.swing.JTextField txtGrad;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRg;
    // End of variables declaration//GEN-END:variables
}
