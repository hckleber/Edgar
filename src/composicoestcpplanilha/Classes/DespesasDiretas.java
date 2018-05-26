/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Milene
 */
public class DespesasDiretas {

    private ArrayList<String> lista;
    private float producao;
    private float prod;
    private float constante;

    public DespesasDiretas() {
    }

    public DespesasDiretas(float producao, float prod) {
        this.producao = producao;
        this.prod = prod;
        constante = this.producao * this.prod;
    }

    public ArrayList preencherListBox(String selecao) {
        ConexaoCSV arquivo;

        switch (selecao) {
            case "--TIPO--":
                lista = null;
                break;
            case "EQ":
                arquivo = new ConexaoCSV(Constantes.EQUIPAMENTOS_CUSTO);
                lista = arquivo.readColunaCsvFile(2);
                break;
            case "MAT":
                arquivo = new ConexaoCSV(Constantes.MATERIAIS_CUSTO);
                lista = arquivo.readColunaCsvFile(2);
                break;
            case "MOD":
                arquivo = new ConexaoCSV(Constantes.MAODEOBRA_CUSTO);
                lista = arquivo.readColunaCsvFile(2);
                break;
            case "SET/MOE":
                arquivo = new ConexaoCSV(Constantes.SERVICOS_CUSTO);
                lista = arquivo.readColunaCsvFile(2);
                break;
            default:
                break;
        }
        return lista;
    }

    public String lerLinhaServicoSecundario(String selecao, String localizar) {
        ConexaoCSV arquivo;
        String dadosLinha = null;

        arquivo = new ConexaoCSV(Constantes.PRINCIPAL);
        dadosLinha = arquivo.retornarLinha(localizar);
        switch (selecao) {
            case "--TIPO--":
                break;
            case "EQ":
                arquivo = new ConexaoCSV(Constantes.EQUIPAMENTOS_CUSTO);
                dadosLinha = arquivo.retornarLinha(localizar);
                break;
            case "MAT":
                arquivo = new ConexaoCSV(Constantes.MATERIAIS_CUSTO);
                dadosLinha = arquivo.retornarLinha(localizar);
                break;
            case "MOD":
                arquivo = new ConexaoCSV(Constantes.MAODEOBRA_CUSTO);
                dadosLinha = arquivo.retornarLinha(localizar);
                break;
            case "SET/MOE":
                arquivo = new ConexaoCSV(Constantes.SERVICOS_CUSTO);
                dadosLinha = arquivo.retornarLinha(localizar);
                break;
            default:
                break;
        }
        return dadosLinha;
    }

    public String criarDadosEscrita(String selecao, String item, String dados, Double qtd, float prodHora) {
        String linhaDados = null;
        String[] dadosEntrada;

        dadosEntrada = dados.split(";");

        NumberFormat formatarFloat = new DecimalFormat("#.########");

        switch (selecao) {
            //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;SUBITEM;

            case "--TIPO--":
                break;
            case "EQ":
                //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;;;HORA-MAQUINA;;CUSTO;;;HORA-MAQUINA;;;;; (17)
                //codigo;Tipo;Equipamentos;unid;C u s t o Produtivo;C u s t o Improdutivo;C u s t o Parado;C u s t o Aluguel;HORA-MAQUINA
                float calc;
                calc = Float.parseFloat(dadosEntrada[7]) * Float.parseFloat(dadosEntrada[8]) / constante;

                linhaDados = item + ";" + dadosEntrada[0] + ";" + dadosEntrada[1] + ";" + dadosEntrada[2] + ";" + dadosEntrada[3] + ";;;" + dadosEntrada[8].replace(".", ",")
                        + ";;" + dadosEntrada[7] + ";;;" + String.valueOf(formatarFloat.format(calc)) + ";;;;;";
                break;
            case "MAT":
                //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;CONSUMO;;;;CUSTO;MATERIAL;;;;;;; (17)
                //codigo;Tipo;Materiais;unid;quant;  Preço de compra total;  Preço de compra unitario;Frete;Custo unitario;Consumo
                float material;
                material = Float.parseFloat(dadosEntrada[8]) * Float.parseFloat(dadosEntrada[9]);

                linhaDados = item + ";" + dadosEntrada[0] + ";" + dadosEntrada[1] + ";" + dadosEntrada[2] + ";" + dadosEntrada[3] + ";" + dadosEntrada[9].replace(".", ",") + ";;;;"
                        + dadosEntrada[8].replace(".", ",") + ";" + String.valueOf(formatarFloat.format(material)) + ";;;;;;;";
                break;
            case "MOD":
                //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;;HOMEM- HORA;;;CUSTO;;HOMEM- HORA;;;;;; (17)
                //codigo;Tipo;Profissão;Salario fator;Salario mensal;Alimentação mensal;Alimentação diaria;Hospedagem mensal;Hospedagem diaria;
                //Viagens;Custo  mão-de-obra mensal;Custo  mão-de-obra hora;HOMEM- HORA;SERV. TERC.
                float homemHora;
                homemHora = Float.parseFloat(dadosEntrada[11]) * Float.parseFloat(dadosEntrada[12]) / constante;

                linhaDados = item + ";" + dadosEntrada[0] + ";" + dadosEntrada[1] + ";" + dadosEntrada[2] + ";H" + ";;"
                        + dadosEntrada[12].replace(".", ",") + ";;;" + dadosEntrada[11].replace(".", ",") + ";;" + String.valueOf(formatarFloat.format(homemHora)) + ";;;;;;";
                break;
            case "SET/MOE":
                //ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;;;;SERV.TERC.;CUSTO;;;;SERV.TERC.;;;; (17)
                //codigo;Tipo;Serviços contratados;unid;quant;Preço de compra total;Preço de compra unitario;Outras Despesas;Custo unitario;SERV.TERC.
                float servTerc;
                servTerc = Float.parseFloat(dadosEntrada[8]) * Float.parseFloat(dadosEntrada[9]);

                linhaDados = item + ";" + dadosEntrada[0] + ";" + dadosEntrada[1] + ";" + dadosEntrada[2] + ";" + dadosEntrada[3] + ";;;;" + dadosEntrada[9].replace(".", ",")
                        + ";" + dadosEntrada[8] + ";;;;" + String.valueOf(formatarFloat.format(servTerc)) + ";;;;";
                break;
            case "interno":
                linhaDados = item + ";";
                for (int j = 1; j <= 6; j++) {
                    linhaDados = linhaDados + dadosEntrada[j] + ";";
                }

                linhaDados = linhaDados + ";" + qtd.toString().replace(".", ",") + ";";

                Double consumoTotal;

//                consumoTotal = qtd * Double.valueOf(dadosEntrada[9].replace(",", ".")) / Double.valueOf(dadosEntrada[8].replace(",", "."));
                consumoTotal = CalcSecundario(dadosEntrada[4], qtd, dadosEntrada[13], dadosEntrada[14], dadosEntrada[15], dadosEntrada[16]);

                float prodFator = prodHora * producao;

                System.out.println(consumoTotal + "    " + dadosEntrada[4]);

                
                Double custoTotal = consumoTotal * Double.valueOf(dadosEntrada[17].replace(",", "."));

                linhaDados = linhaDados + consumoTotal.toString().replace(".", ",") + ";" + Float.toString(prodHora).replace(".", ",") + ";" + Float.toString(prodFator).replace(".", ",")
                        + ";" + custoTotal.toString().replace(".", ",") + ";";

                for (int j = 13; j < dadosEntrada.length; j++) {
                    linhaDados = linhaDados + dadosEntrada[j] + ";";
                }

                for (int j = dadosEntrada.length; j <= 24; j++) {
                    linhaDados = linhaDados + ";";
                }

                break;
            default:
                break;
        }

        return linhaDados;
    }

    public String criarDadosEscrita(String selecao, String item, String[] servicoPrincipal, String dados, Double qtd) {
        String linhaDados = null;
        String[] dadosEntrada;
        int subitem = Integer.parseInt(item.substring(item.indexOf(".") + 1)) + 1;
        float calc;

        dadosEntrada = dados.split(";");

        // NumberFormat formatarFloat = new DecimalFormat("#.########");
        switch (selecao) {
            case "--TIPO--":
                break;
            case "EQ":

                calc = this.producao * Float.valueOf(servicoPrincipal[7].replace(",", "."));

                linhaDados = item.substring(0, item.indexOf(".") + 1) + subitem + ";" + dadosEntrada[0] + ";" + servicoPrincipal[2] + ";" + servicoPrincipal[3] + ";"
                        + dadosEntrada[1].replace(".", ",") + ";" + dadosEntrada[2].replace(".", ",") + ";" + dadosEntrada[3] + ";;" + servicoPrincipal[7].replace(".", ",") + ";" + String.valueOf(calc).replace(".", ",") + ";"
                        + servicoPrincipal[15] + ";" + String.valueOf(this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";"
                        + String.valueOf(calc * Float.parseFloat(dadosEntrada[7].replace(",", "."))).replace(".", ",") + ";";

                linhaDados = linhaDados + ";;" + String.valueOf(this.producao).replace(".", ",") + ";;"
                        + dadosEntrada[7].replace(".", ",") + ";"
                        + ";;" + String.valueOf(Float.parseFloat(dadosEntrada[7]) * this.producao / this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";;;;;";
                break;
            case "MAT":
                calc = this.producao * Float.valueOf(servicoPrincipal[7].replace(",", "."));

                linhaDados = item.substring(0, item.indexOf(".") + 1) + subitem + ";" + dadosEntrada[0] + ";" + servicoPrincipal[2] + ";" + servicoPrincipal[3] + ";"
                        + dadosEntrada[1].replace(".", ",") + ";" + dadosEntrada[2].replace(".", ",") + ";" + dadosEntrada[3] + ";;" + servicoPrincipal[7].replace(".", ",") + ";" + String.valueOf(calc).replace(".", ",") + ";"
                        + servicoPrincipal[15] + ";" + String.valueOf(this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";"
                        + String.valueOf(calc * Float.parseFloat(dadosEntrada[8].replace(",", "."))).replace(".", ",") + ";";

                linhaDados = linhaDados + String.valueOf(this.producao).replace(".", ",") + ";;;;"
                        + dadosEntrada[8].replace(".", ",") + ";"
                        + String.valueOf(this.producao * Float.parseFloat(dadosEntrada[8].replace(",", "."))).replace(".", ",") + ";;;;;;;";
                break;
            case "MOD":
                calc = this.producao * Float.valueOf(servicoPrincipal[7].replace(",", "."));
                
                linhaDados = item.substring(0, item.indexOf(".") + 1) + subitem + ";" + dadosEntrada[0] + ";" + servicoPrincipal[2] + ";" + servicoPrincipal[3] + ";"
                        + dadosEntrada[1].replace(".", ",") + ";" + dadosEntrada[2].replace(".", ",") + ";" + "H" + ";;" + servicoPrincipal[7].replace(".", ",") + ";" + String.valueOf(calc).replace(".", ",") + ";"
                        + servicoPrincipal[15] + ";" + String.valueOf(this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";"
                        + String.valueOf(calc * Float.parseFloat(dadosEntrada[11].replace(",", "."))).replace(".", ",") + ";";

                linhaDados = linhaDados + ";" + String.valueOf(this.producao).replace(".", ",") + ";;;"
                        + dadosEntrada[11].replace(".", ",") + ";"
                        + ";" + String.valueOf(Float.parseFloat(dadosEntrada[11].replace(",", "."))* this.producao / this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";;;;;;";
                break;
            case "SET/MOE":
                calc = this.producao * Float.valueOf(servicoPrincipal[7].replace(",", ","));
                
                linhaDados = item.substring(0, item.indexOf(".") + 1) + subitem + ";" + dadosEntrada[0] + ";" + servicoPrincipal[2] + ";" + servicoPrincipal[3] + ";"
                        + dadosEntrada[1].replace(".", ",") + ";" + dadosEntrada[2].replace(".", ",") + ";" + dadosEntrada[3] + ";;" + servicoPrincipal[7].replace(".", ",") + ";" + String.valueOf(calc).replace(".", ",") + ";"
                        + servicoPrincipal[15] + ";" + String.valueOf(this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";"
                        + String.valueOf(calc * Float.parseFloat(dadosEntrada[8].replace(",", "."))).replace(".", ",") + ";";

                linhaDados = linhaDados + ";;;" + String.valueOf(this.producao).replace(".", ",") + ";"
                        + dadosEntrada[8].replace(".", ",") + ";"
                        + ";;;" + String.valueOf(Float.parseFloat(dadosEntrada[8])* this.producao / this.prod * Float.parseFloat(servicoPrincipal[15].replace(",", "."))).replace(".", ",") + ";;;;";
                break;
            case "interno":
                linhaDados = item + ";";
                for (int j = 1; j <= 6; j++) {
                    linhaDados = linhaDados + dadosEntrada[j] + ";";
                }

                linhaDados = linhaDados + ";" + qtd.toString().replace(".", ",") + ";";

                Double consumoTotal;
                consumoTotal = CalcSecundario(dadosEntrada[4], qtd, dadosEntrada[13], dadosEntrada[14], dadosEntrada[15], dadosEntrada[16]);

                float prodFator = this.prod * producao;
                Double custoTotal = consumoTotal * Double.valueOf(dadosEntrada[17].replace(",", "."));

                linhaDados = linhaDados + consumoTotal.toString().replace(".", ",") + ";" + Float.toString(this.prod).replace(".", ",") + ";" + Float.toString(prodFator).replace(".", ",")
                        + ";" + custoTotal.toString().replace(".", ",") + ";";

                for (int j = 13; j < dadosEntrada.length; j++) {
                    linhaDados = linhaDados + dadosEntrada[j] + ";";
                }

                for (int j = dadosEntrada.length; j <= 24; j++) {
                    linhaDados = linhaDados + ";";
                }

                break;
            default:
                break;
        }

        System.out.println(linhaDados);

        return linhaDados;
    }

    private Double CalcSecundario(String tipo, Double qtd, String consumo, String hh, String hm, String st) {
        Double calc = 0d;

        switch (tipo) {
            case "MAT":
                calc = qtd * Double.valueOf(consumo.replace(",", "."));
                break;
            case "MOD":
                calc = qtd * Double.valueOf(hh.replace(",", "."));
                break;
            case "EQA":
                calc = qtd * Double.valueOf(hm.replace(",", "."));
                break;
            case "EQL":
                calc = qtd * Double.valueOf(hm.replace(",", "."));
                break;
            case "SET":
                calc = qtd * Double.valueOf(st.replace(",", "."));
                break;
            case "MOE":
                calc = qtd * Double.valueOf(st.replace(",", "."));
                break;
            case "SER":
                break;

        }
        return calc;
    }

}
