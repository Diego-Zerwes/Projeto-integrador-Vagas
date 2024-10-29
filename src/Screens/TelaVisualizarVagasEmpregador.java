
package Screens;


public class TelaVisualizarVagasEmpregador extends javax.swing.JInternalFrame {

   
    public TelaVisualizarVagasEmpregador() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBusca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVagas = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Visualizar minhas vagas");
        setPreferredSize(new java.awt.Dimension(730, 480));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ic_search_icon.png"))); // NOI18N

        tblVagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Descrição", "Remuneração", "Requisitos"
            }
        ));
        jScrollPane1.setViewportView(tblVagas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        setBounds(0, 0, 730, 480);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVagas;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
