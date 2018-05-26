/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Milene
 */
public class CriarTabela {

    private String[] nomeColunas;
    private ConexaoCSV fonteDados;
    private JTable tabela;
    private DefaultTableModel modelo;

    public CriarTabela(JTable tabela, String[] nomeColunas, ConexaoCSV fonteDados) {
        this.nomeColunas = nomeColunas;
        this.fonteDados = fonteDados;
        this.tabela = tabela;
    }

    private void criarColunas() {
        modelo = new TableModelEditado();

        for (int i = 0; i < nomeColunas.length; i++) {
            modelo.addColumn(nomeColunas[i]);
        }
    }

    public void criarTabelaServicoPrimario() {
        criarColunas();
        ArrayList<String> lista;
        String[] linha;

        lista = fonteDados.readCsvFile();

        for (int i = 1;
                i < lista.size();
                i++) {

            linha = lista.get(i).split(";");

            //Item;Código;Serviços;Unid;Quant;
            modelo.addRow(new String[]{linha[0], linha[1], linha[3], linha[6], linha[8]});
        }

        tabela.setModel(modelo);
        resizeColumns();
    }

    public void criarTabelaServicoSecundario(String idServicoPrincipal) {
//ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;SUBITEM;
//SUBITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;
        criarColunas();
        ArrayList<String> lista;
        String[] servico;
        String[] linha;

        
        if (idServicoPrincipal.equals("") || idServicoPrincipal.equals("DESCRIÇÃO")) {
            
        } else {

            servico = idServicoPrincipal.split(";");

            lista = fonteDados.readCsvFile();

            for (int i = 1; i < lista.size(); i++) {
                linha = lista.get(i).split(";");

                if (linha[0].substring(0, linha[0].indexOf(".")).equals(servico[0].substring(0, servico[0].indexOf(".")))) {
                    modelo.addRow(new String[]{linha[0], linha[1], linha[4], linha[5], linha[6], linha[8], linha[9], linha[10], 
                        linha[11], linha[12], linha[13], linha[14], linha[15], linha[16], linha[17], linha[18], linha[19], linha[20], linha[21]});
                }
            }
        }
        
        tabela.setModel(modelo);

    }

    public void resizeColumns() {

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(126);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(1050);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(44);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(94);
    }
}
