/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Screens;

import dao.ConexaoBanco;
import entities.Empregador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;




public class TelaCandidatosInscritosEmpregador extends javax.swing.JFrame { 
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private Empregador empregador;

    public TelaCandidatosInscritosEmpregador(Empregador empregador) {
        initComponents();
        this.empregador = empregador; 
        ConexaoBanco conn = new ConexaoBanco();
        if (conn.conectar()) {
            conexao = conn.getConnection();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    visualizarVagasCandidatadas();
                }
            }).start();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
        }
    }

    public void visualizarVagasCandidatadas() {
        String sql = "SELECT v.idVagas AS Vagas, v.descricao AS Descrição, " 
                + "c.idCandidato AS Candidato "
                + "FROM Vagas v "
                + "INNER JOIN CandidatoVagas cv ON v.idVagas = cv.idVagas "
                + "INNER JOIN candidato c ON cv.idCandidato = c.idCandidato "
                + "WHERE v.idEmpregador = ?";
        try { 
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, this.empregador.getIdEmpregador());
            rs = pst.executeQuery(); 
            tblVagas.setModel(DbUtils.resultSetToTableModel(rs)); 
        } catch (Exception e) { 
            JOptionPane.showMessageDialog(null, "Erro ao listar os candidatos! " + e.getMessage()); 
        }
    }



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblVagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Candidato", "Vagas", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblVagas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(Empregador empregador) {
                new TelaCandidatosInscritosEmpregador(empregador).setVisible(true);
            }

            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           }
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVagas;
    // End of variables declaration//GEN-END:variables

}