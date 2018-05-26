/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import composicoestcpplanilha.Suporte.OrdenarLista;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Milene
 */
public class Composicoes {

    private ConexaoCSV arquivoServPrincipal;
    private ConexaoCSV arquivoServSecundario;
    private ArrayList<String> listaSaida = new ArrayList();
    private String listaSaidaString;

    public Composicoes(ConexaoCSV arquivoServPrincipal, ConexaoCSV arquivoServSecundario) {
        this.arquivoServPrincipal = arquivoServPrincipal;
        this.arquivoServSecundario = arquivoServSecundario;
        listaSaidaString = "ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;"
                + "CUSTO;MATERIAL;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;";
    }

    public ArrayList criarComposicoes() {
        ArrayList<String> listaPrincipal;
        ArrayList<String> listaSecundaria;

        listaPrincipal = arquivoServPrincipal.readCsvFile();
        listaSecundaria = arquivoServSecundario.readCsvFile();

//        OrdenarLista.Ordenar(listaSecundaria);
        for (int a = 1; a < listaPrincipal.size(); a++) {
            //18, 19, 20, 21, 22
            //10, 11, 12, 13, 14
            Double material = 0d;
            Double homemHora = 0d;
            Double horaMaquina = 0d;
            Double servTer = 0d;
            Double custoTotal = 0d;

            String[] primeiraPosicao1 = listaPrincipal.get(a).split(";");

            for (String b : listaSecundaria) {
                if (!b.substring(0, 4).equals("ITEM")) {
                    String[] primeiraPosicao2 = b.split(";");
                    if (primeiraPosicao1[0].substring(0, primeiraPosicao1[0].indexOf(".")).equals(primeiraPosicao2[0].substring(0, primeiraPosicao2[0].indexOf(".")))) {
//                    String saida;
                        if (primeiraPosicao2[18] != null && !primeiraPosicao2[18].isEmpty()) {
                            material = material + Double.parseDouble(primeiraPosicao2[18].replace(",", "."));
                        }
                        if (primeiraPosicao2[19] != null && !primeiraPosicao2[19].isEmpty()) {
                            homemHora = homemHora + Double.parseDouble(primeiraPosicao2[19].replace(",", "."));
                        }
                        if (primeiraPosicao2[20] != null && !primeiraPosicao2[20].isEmpty()) {
                            horaMaquina = horaMaquina + Double.parseDouble(primeiraPosicao2[20].replace(",", "."));
                        }
                        if (primeiraPosicao2[21] != null && !primeiraPosicao2[21].isEmpty()) {
                            servTer = servTer + Double.parseDouble(primeiraPosicao2[21].replace(",", "."));
                        }

//                    saida = primeiraPosicao2[17] + ";";
//                    for (int i = 1; i < (primeiraPosicao2.length - 1); i++) {
//                        saida = saida + primeiraPosicao2[i] + ";";
//                    }
                        listaSaida.add(b);
                    }
                }
            }

            custoTotal = material + homemHora + horaMaquina + servTer;

            Double precoTotal = custoTotal / (1 - Constantes.BDI);
            Double bdi = precoTotal - custoTotal;

            String[] strg = listaPrincipal.get(a).split(";");
            String novaStrg = strg[0] + ";";

            for (int j = 1; j <= 6; j++) {
                novaStrg = novaStrg + strg[j] + ";";
            }

            for (int j = 7; j <= 17; j++) {
                novaStrg = novaStrg + strg[j].replace(".", ",") + ";";
            }

            novaStrg = novaStrg + String.valueOf(material).replace(".", ",") + ";" + String.valueOf(homemHora).replace(".", ",") + ";" + String.valueOf(horaMaquina).replace(".", ",")
                    + ";" + String.valueOf(servTer).replace(".", ",") + ";" + String.valueOf(custoTotal).replace(".", ",")
                    + ";" + String.valueOf(bdi).replace(".", ",") + ";" + String.valueOf(precoTotal).replace(".", ",") + ";";
            listaSaida.add(novaStrg);
        }

        return listaSaida;
    }

    public void moverArrayList(int posicaoOrigem, int posicaoDestino) {
        String a = null;

        if (posicaoOrigem > posicaoDestino) {
            for (int i = 0; i < listaSaida.size(); i++) {
                if (i == posicaoOrigem) {
                    a = listaSaida.get(i);
                    i = listaSaida.size();
                }
            }

            for (int i = posicaoOrigem; i > posicaoDestino; i--) {
                listaSaida.set(i, listaSaida.get(i - 1));
            }
            listaSaida.set(posicaoDestino, a);

        }
        if (posicaoOrigem < posicaoDestino) {
            for (int i = 0; i < listaSaida.size(); i++) {
                if (i == posicaoOrigem) {
                    a = listaSaida.get(i);
                    i = listaSaida.size();
                }
            }
            listaSaida.set(posicaoDestino, a);

            for (int i = posicaoOrigem; i < posicaoDestino; i++) {
                listaSaida.set(i, listaSaida.get(i + 1));
            }

        }
    }

    public String getListaSaidaString() {

        for (String a : listaSaida) {
            listaSaidaString = listaSaidaString + "\n" + a;
        }

        return listaSaidaString;
    }

    public ArrayList<String> getListaSaida() {
        String titulo = "ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;"
                + "CUSTO;MATERIAL;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;";

        listaSaida.add(0, titulo);
        return listaSaida;
    }

    public ArrayList AtualizarArquivoServPrincipal() {
        ArrayList<String> saida = new ArrayList();
        saida.add("ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;"
                + "HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;");
        for (String a : listaSaida) {
            if(!a.substring(0, a.indexOf(";")).equals("ITEM")){    
            if (a.substring(a.indexOf("."), a.indexOf(";")).equals(".0") ) {
                    saida.add(a);
                }
            }
        }

        return saida;
    }

    public ArrayList CriarPrecos() {
        ArrayList<String> dadosBase = AtualizarArquivoServPrincipal();
        ArrayList<String> saida = new ArrayList();
        saida.add(dadosBase.get(0));
        dadosBase.remove(0);

        for (String a : dadosBase) {
            String[] b = a.split(";");
            String linha;
            float qtd = Float.valueOf(b[7].replace(",", "."));
            float calc = Float.valueOf(b[18].replace(",", ".")) + Float.valueOf(b[19].replace(",", ".")) + Float.valueOf(b[20].replace(",", ".")) + Float.valueOf(b[21].replace(",", "."));
            linha = b[0] + ";" + b[1] + ";" + b[5] + ";" + b[6] + ";" + b[7] + ";" + b[18] + ";" + String.valueOf(qtd * Float.parseFloat(b[18].replace(",", "."))).replace(".", ",") + ";"
                    + b[19] + ";" + String.valueOf(qtd * Float.parseFloat(b[19].replace(",", "."))).replace(".", ",") + ";"
                    + b[20] + ";" + String.valueOf(qtd * Float.parseFloat(b[20].replace(",", "."))).replace(".", ",") + ";" + b[21] + ";"
                    + String.valueOf(qtd * Float.valueOf(b[21].replace(",", "."))).replace(".", ",") + ";"
                    + b[23] + ";" + String.valueOf(qtd * Float.valueOf(b[23].replace(",", "."))).replace(".", ",") + ";" + String.valueOf(calc).replace(".", ",") + ";"
                    + String.valueOf(qtd * calc).replace(".", ",") + ";" + b[24] + ";" + String.valueOf(qtd * Float.valueOf(b[24].replace(",", "."))).replace(".", ",")
                    + ";;;;;";
            saida.add(linha);
        }

        return saida;
    }

}
