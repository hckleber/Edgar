/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import composicoestcpplanilha.Classes.ConexaoCSV;

/**
 *
 * @author Milene
 */
public class Quantitativos {

    private String servicos;
    private Double unid;
    private int lastID;

    public Quantitativos(String servicos, Double unid) {
        this.servicos = servicos;
        this.unid = unid;
    }

    public void inserirLinhaQuantitativo() {
//        int lastID;
        ConexaoCSV despesasDiretas = new ConexaoCSV(Constantes.DESPESAS_DIRETASP);

        String[] linhaDadosOrigem;
        String linhaDadosDestino;
//        ConexaoCSV quantitativo = new ConexaoCSV(Constantes.QUANTITATIVOS);

        //Item[0];Código[1];Serviços[2];Unid[3];Quant[4];
//        linhaDadosOrigem = quantitativo.retornarLinha(servicos).split(";");
        linhaDadosOrigem = servicos.split(";");

        lastID = despesasDiretas.getLastID() + 1;

        //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;SUBITEM;
//        linhaDadosDestino = String.valueOf(lastID)+".0" + ";" + linhaDadosOrigem[1] + ";" + ";" + linhaDadosOrigem[2] + ";" + linhaDadosOrigem[3] + ";" + Double.toString(unid).replace(".", ",") + ";;;;;;;;;;;;;";
//      linhaDadosDestino = +";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";"+";";


    linhaDadosDestino = String.valueOf(lastID) + ".0" + ";";

    for(int i = 1; i<7; i++){
        linhaDadosDestino = linhaDadosDestino + linhaDadosOrigem[i] + ";";
    }
    
    linhaDadosDestino = linhaDadosDestino + unid + ";" + unid + ";";
    
    for(int i = 9; i< linhaDadosOrigem.length; i++){
        linhaDadosDestino = linhaDadosDestino + linhaDadosOrigem[i] + ";";
    }

// linhaDadosDestino = String.valueOf(lastID) + ".0" + servicos;

        despesasDiretas.writeCsvFile(linhaDadosDestino, false);
    }

    public int getLastID() {
        ConexaoCSV despesasDiretas = new ConexaoCSV(Constantes.DESPESAS_DIRETASP);
        lastID = despesasDiretas.getLastID();
        return lastID;
    }

}
