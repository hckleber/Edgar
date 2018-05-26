/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milene
 */
public class TableModelEditado extends DefaultTableModel{

    public TableModelEditado() {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}