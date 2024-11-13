package Screens;

import dao.ConexaoBanco;
import entities.Candidato;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class TelaCandidaturasCandidato extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    Candidato candidato = new Candidato();

    public TelaCandidaturasCandidato(Candidato candidato) {
        initComponents();
        this.candidato = candidato;

        ConexaoBanco conn = new ConexaoBanco();
        if (conn.conectar()) {
            conexao = conn.getConnection();
            visualizarVagasCandidatadas();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void visualizarVagasCandidatadas() {
        String sql = "SELECT v.idVagas, v.descricao, v.remuneracao, v.requisitos, v.idEmpregador, v.dataVaga "
                + "FROM Vagas v "
                + "INNER JOIN CandidatoVagas cv ON (v.idVagas = cv.idVagas) "
                + "INNER JOIN candidato c ON (cv.idCandidato = c.idCandidato) "
                + "WHERE c.idCandidato=? ";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            rs = pst.executeQuery();
            tblVagasCandidatadas.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void pesquisar_vaga() {
        String sql = "SELECT v.idVagas AS Id, v.descricao AS Descrição, v.remuneracao AS Remuneração, v.requisitos AS Requisitos, v.idEmpregador, v.dataVaga as DataVaga "
                + "FROM CandidatoVagas AS cv "
                + "INNER JOIN Vagas AS v ON (v.idVagas = cv.idVagas) "
                + "INNER JOIN candidato AS c ON (c.idCandidato = cv.idCandidato) "
                + "WHERE c.idCandidato = ? AND v.descricao like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.candidato.getIdCandidato());
            pst.setString(2, "%" + txtPesquisarVaga.getText() + "%");
            rs = pst.executeQuery();
            tblVagasCandidatadas.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar as vagas! " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPesquisarVaga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagasCandidatadas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Candidaturas");
        setPreferredSize(new java.awt.Dimension(730, 480));

        txtPesquisarVaga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarVagaKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_search_icon.png"))); // NOI18N

        tblVagasCandidatadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Remuneração", "Requisitos", "idEmpregador", "Data"
            }
        ));
        jScrollPane1.setViewportView(tblVagasCandidatadas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisarVaga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(txtPesquisarVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisarVagaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarVagaKeyReleased
        // pesquisando uma vaga em específico
        pesquisar_vaga();
    }//GEN-LAST:event_txtPesquisarVagaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVagasCandidatadas;
    private javax.swing.JTextField txtPesquisarVaga;
    // End of variables declaration//GEN-END:variables
}
