/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Janelas;

import composicoestcpplanilha.Classes.ConexaoCSV;
import composicoestcpplanilha.Classes.Constantes;
import composicoestcpplanilha.Classes.Quantitativos;
import composicoestcpplanilha.Classes.TableModelEditado;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milene
 */
public class JanelaQuantitativos extends javax.swing.JFrame {

    /**
     * Creates new form JanelaQuantitativos
     */
    public JanelaQuantitativos() {
        initComponents();

        // Preenche jcomboBox com a lista Quantitativos
        ConexaoCSV arquivo = new ConexaoCSV(Constantes.QUANTITATIVOS);
        cboxServico.setModel(new DefaultComboBoxModel(new Vector(arquivo.readColunaCsvFile(2))));
        
        
        
        

        carregarJTable();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnADD = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnRemoveAll = new javax.swing.JButton();
        cboxServico = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtQtdServico = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quantitativos");
        setResizable(false);

        btnADD.setText("ADD");
        btnADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDActionPerformed(evt);
            }
        });

        btnRemove.setText("Remover");
        btnRemove.setMaximumSize(new java.awt.Dimension(56, 25));
        btnRemove.setMinimumSize(new java.awt.Dimension(56, 25));

        btnRemoveAll.setText("Remover Tudo");
        btnRemoveAll.setMaximumSize(new java.awt.Dimension(56, 25));
        btnRemoveAll.setMinimumSize(new java.awt.Dimension(56, 25));
        btnRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveAllActionPerformed(evt);
            }
        });

        cboxServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4", "A", "B", "C" }));

        jLabel2.setText("Serviço");

        jLabel3.setText("Qtd.");

        txtQtdServico.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#"))));
        txtQtdServico.setText("0");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1252, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboxServico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQtdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnADD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoveAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnADD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnRemoveAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboxServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQtdServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDActionPerformed
        // TODO add your handling code here:

        String servico;
        Double qtd;
        servico = (String) cboxServico.getSelectedItem();
        qtd = Double.parseDouble(txtQtdServico.getText());

        if (servico.toUpperCase().equals("SERVIÇOS")) {
            JOptionPane.showMessageDialog(null, "Selecione um serviço para inserir!");

        } else {
            Quantitativos inserir = new Quantitativos(servico, qtd);
            inserir.inserirLinhaQuantitativo();
            carregarJTable();

        }


    }//GEN-LAST:event_btnADDActionPerformed

    private void btnRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveAllActionPerformed
        // TODO add your handling code here:
        ConexaoCSV despesasDiretas = new ConexaoCSV(Constantes.DESPESAS_DIRETASP);
        despesasDiretas.clearAll();
        carregarJTable();

    }//GEN-LAST:event_btnRemoveAllActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnADD;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnRemoveAll;
    private javax.swing.JComboBox<String> cboxServico;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField txtQtdServico;
    // End of variables declaration//GEN-END:variables

    private void carregarJTable() {

//Item;Código;Serviços;Unid;Quant;
        DefaultTableModel modelo = new TableModelEditado();
        ArrayList<String> lista;

        modelo.addColumn("Item");
        modelo.addColumn("Código");
        modelo.addColumn("Serviço");
        modelo.addColumn("Unid");
        modelo.addColumn("Qtd");

        ConexaoCSV despesasDiretas = new ConexaoCSV(Constantes.DESPESAS_DIRETASP);

        lista = despesasDiretas.readCsvFile();

        for (int i = 1; i < lista.size(); i++) {

            String[] linha;
            linha = lista.get(i).split(";");
            modelo.addRow(new String[]{linha[0], linha[1], linha[3], linha[4], linha[5]});
        }

        jTable1.setModel(modelo);

        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(126);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(1500);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(44);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(94);

    }

}
