/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author Milene
 */
public class ConexaoCSV {

    private String arquivoCSV;
    private BufferedReader StrR;
    private String Str;
    private String[] TableLine;
    private ArrayList<String> servicos = new ArrayList();

    public ConexaoCSV(String arquivoCSV) {
        this.arquivoCSV = arquivoCSV;
    }

    public void writeCsvFile(ArrayList<String> dadosCSV) {
//A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
//excessões sejam tratadas
        try {

//Criação de um buffer para a escrita em uma stream
            OutputStreamWriter StrW = new OutputStreamWriter(new FileOutputStream(arquivoCSV), "UTF-8");
//            BufferedWriter StrW = new BufferedWriter(new FileWriter(arquivoCSV, false));

//Escrita dos dados da tabela
            for (String a : dadosCSV) {
                StrW.write(a + "\n");
            }
//Fechamos o buffer
            StrW.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeCsvFile(String dadosCSV, boolean sobrescrever) {
        boolean x = !sobrescrever;

//A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
//excessões sejam tratadas
        try {

//Criação de um buffer para a escrita em uma stream

//            BufferedWriter StrW = new BufferedWriter(new FileWriter(arquivoCSV, x));
            OutputStreamWriter StrW = new OutputStreamWriter(new FileOutputStream(arquivoCSV, x),"UTF-8");

//Escrita dos dados da tabela
            if (x) {
                StrW.write(dadosCSV + "\n");
            } else {
                StrW.write(dadosCSV);
            }
//Fechamos o buffer
            StrW.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList readColunaCsvFile(int coluna_CSV) {

//A estrutura try-catch é usada pois o objeto BufferedWriter exige que as
//excessões sejam tratadas
        try {

//Criação de um buffer para a ler de uma stream
//            StrR = new BufferedReader(new FileReader(arquivoCSV));
            StrR = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));

//Essa estrutura do looping while é clássica para ler cada linha
//do arquivo 
            while ((Str = StrR.readLine()) != null) {

//Aqui usamos o método split que divide a linha lida em um array de String
//passando como parametro o divisor ";".
                TableLine = Str.split(";");

//O foreach é usadao para imprimir cada célula do array de String.
//                for (String cell : TableLine) {
//                    System.out.print(cell + " ");
//                }
                servicos.add(TableLine[coluna_CSV]);
            }
//Fechamos o buffer
            StrR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return servicos;
    }

    public String readLastLine() {
        String ultimo = "";

        try {
            InputStream is = new FileInputStream(arquivoCSV);
            InputStreamReader isr = new InputStreamReader(is);
            StrR = new BufferedReader(isr);

            String line = "";
            while (line != null) {
                line = StrR.readLine();
                if (line != null) {
                    ultimo = line;
                }
            }
            StrR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ultimo;
    }

    public int getLastID() {

        String ultimaLinha = readLastLine();
        int id;

        TableLine = ultimaLinha.split(";");

        if (TableLine[0].toUpperCase().equals("ITEM")) {
            id = 0;
        } else {
            id = Integer.valueOf(TableLine[0].replace(".0", ""));
        }

        return id;
    }

    public String retornarLinha(String localizar) {
        String dadosLinha = "";
        try {

//            StrR = new BufferedReader(new FileReader(arquivoCSV));
            StrR = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));

            while ((Str = StrR.readLine()) != null) {

                if (Str.contains(localizar + ";")) {
                    dadosLinha = Str;
                }
            }

            StrR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (dadosLinha == null) {
            dadosLinha = "Objeto não encontrado";
        }
        return dadosLinha;
    }

    public ArrayList retornarBloco(String localizar) {
        String dadosLinha[] = null;
        ArrayList<String> arquivo = new ArrayList();
        boolean teste = false;

        try {

            //StrR = new BufferedReader(new FileReader(arquivoCSV));
            StrR = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));

            while ((Str = StrR.readLine()) != null) {

                dadosLinha = Str.split(";");

                if (dadosLinha[3].contains(localizar)) {
                    arquivo.add(Str + ";");
                    teste = true;
                }
            }

            StrR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (dadosLinha == null) {
            dadosLinha[0] = "Objeto não encontrado";
        }

        return arquivo;

    }

    public ArrayList readCsvFile() {

        try {

//            StrR = new BufferedReader(new FileReader(arquivoCSV));
            StrR = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));

            while ((Str = StrR.readLine()) != null) {

                servicos.add(Str);
            }
            StrR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return servicos;
    }

    public void clearAll() {
        try {

//            BufferedWriter StrW = new BufferedWriter(new FileWriter(arquivoCSV));
            OutputStreamWriter StrW = new OutputStreamWriter(new FileOutputStream(arquivoCSV), "UTF-8");

            StrW.write("ITEM;CÓDIGO;TIPO;DESCRIÇÃO;UNID.;QUANT.TOTAL;CONSUMO TOTAL;PRODUÇÃO;PROD*FATOR;CUSTO TOTAL;"
                    + "CONSUMO;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM-HORA;HORA-MAQUINA;SERV.TERC.;"
                    + "CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL;SUBID" + "\n");

            StrW.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adicionarSubItem(String dadosEscrita) {
        int id = 1;
        String[] linhaAntiga;
        String[] linhaNova;
        String dadosCSV;

        linhaNova = dadosEscrita.split(";");

        try {
            StrR = new BufferedReader(new FileReader(arquivoCSV));
            while ((Str = StrR.readLine()) != null) {
                linhaAntiga = Str.split(";");
                if (linhaNova[0].equals(linhaAntiga[0])) {
                    id++;
                }
            }
            StrR.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        dadosCSV = dadosEscrita + linhaNova[0].replace(".0", "") + "." + String.valueOf(id) + ";";

        writeCsvFile(dadosCSV, false);

    }

}
