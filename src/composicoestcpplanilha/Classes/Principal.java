/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import composicoestcpplanilha.Janelas.JanelaAtualizacaoInicial;
import composicoestcpplanilha.Janelas.JanelaPrincipal;
import composicoestcpplanilha.Janelas.JanelaQuantitativos;
import composicoestcpplanilha.Janelas.JanelaSerialKey;
import composicoestcpplanilha.Suporte.LerXlsx;
import composicoestcpplanilha.Suporte.NetworkInfo;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Milene
 */
public class Principal {

    public static String path;

    public static void programa() throws SocketException, UnknownHostException {

        File filePath = new File(".");
        try {
            path = filePath.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        File file = new File(Constantes.MISC_HASH);

        if (!file.exists()) {

            File fileHash = new File(Constantes.HASH_CREATED);
            if (fileHash.exists()) {

                ConexaoCSV arqHash = new ConexaoCSV(Constantes.HASH_CREATED);
                String hash = arqHash.readLastLine();

                JanelaSerialKey janelaSerial = new JanelaSerialKey();
                janelaSerial.setTxtBoxHash(hash);
                janelaSerial.setLocationRelativeTo(null);
                janelaSerial.setVisible(true);

            } else {
                String hash = NetworkInfo.criarHash().toUpperCase();

                try {

                    FileWriter StrW = new FileWriter(new File(Constantes.HASH_CREATED));

                    StrW.write(hash);

                    StrW.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                JanelaSerialKey janelaSerial = new JanelaSerialKey();
                janelaSerial.setTxtBoxHash(hash);
                janelaSerial.setLocationRelativeTo(null);
                janelaSerial.setVisible(true);

            }

        } else {

            String[] serial = new ConexaoCSV(Constantes.MISC_HASH).readLastLine().split("-");

            if (NetworkInfo.verificarSerial(serial)) {

                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(JanelaQuantitativos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(JanelaQuantitativos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(JanelaQuantitativos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(JanelaQuantitativos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                JanelaAtualizacaoInicial novaJanela = new JanelaAtualizacaoInicial();
                novaJanela.setLocationRelativeTo(null);
                novaJanela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                novaJanela.setVisible(true);

                /////////////////////////////////////////////
                atualizarBase(novaJanela);
                novaJanela.dispose();

                ////////////////////////////////////////////
                JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
                janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                janelaPrincipal.setVisible(true);
                janelaPrincipal.setExtendedState(MAXIMIZED_BOTH);

            } else {
                new File(Constantes.HASH_CREATED).delete();
                new File(Constantes.MISC_HASH).delete();

                String hash = NetworkInfo.criarHash().toUpperCase();

                JanelaSerialKey janelaSerial = new JanelaSerialKey();
                janelaSerial.setTxtBoxHash(hash);
                janelaSerial.setLocationRelativeTo(null);
                janelaSerial.setVisible(true);

            }

        }

    }

    private static void atualizarBase(JanelaAtualizacaoInicial novaJanela) {
        try {

            // Atualizar arquivos base
            LerXlsx arquivo = new LerXlsx(Constantes.PLA_COMPOSICOES);
            ConexaoCSV arqQuantitativos = new ConexaoCSV(Constantes.QUANTITATIVOS);
            ConexaoCSV arqServCusto = new ConexaoCSV(Constantes.SERVICOS_CUSTO);
            ConexaoCSV arqMatCusto = new ConexaoCSV(Constantes.MATERIAIS_CUSTO);
            ConexaoCSV arqMaoObraCuto = new ConexaoCSV(Constantes.MAODEOBRA_CUSTO);
            ConexaoCSV arqEquipCusto = new ConexaoCSV(Constantes.EQUIPAMENTOS_CUSTO);
            ConexaoCSV arqPrincipal = new ConexaoCSV(Constantes.PRINCIPAL);
            String[] header = {"Item;Código;Serviços;Unid;Quant",
                "codigo;Tipo;Serviços contratados;unid;quant;Preço de compra total;Preço de compra unitario;Outras Despesas;Custo unitario;SERV.TERC.",
                "codigo;Tipo;Materiais;unid;quant;  Preço de compra total;  Preço de compra unitario;Frete;Custo unitario;Consumo",
                "codigo;Tipo;Profissão;Salario fator;Salario mensal;Alimentação mensal;Alimentação diaria;Hospedagem mensal;Hospedagem diaria;Viagens;Custo  mão-de-obra mensal;Custo  mão-de-obra hora;HOMEM- HORA;SERV. TERC.",
                "codigo;Tipo;Equipamentos;unid;C u s t o Produtivo;C u s t o Improdutivo;C u s t o Parado;C u s t o Aluguel;HORA-MAQUINA",
                "ITEM;CÓDIGO;;;TIPO;DESCRIÇÃO;UNID.;;QUANT. TOTAL;CONSUMO TOTAL;PRODUÇÃO;PROD*FATOR;CUSTO TOTAL;CONSUMO;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO;MATERIAL;HOMEM- HORA;HORA-MAQUINA;SERV.TERC.;CUSTO TOTAL;BDI: 47,1%;PREÇO TOTAL"};

            novaJanela.setLblEtapa("1 de 5");
            novaJanela.repaint();
            arqQuantitativos.writeCsvFile(arquivo.LerArquivo("Quantitativos", 10, 1, header[0]));
            novaJanela.setLblEtapa("2 de 5");
            novaJanela.repaint();
            arqServCusto.writeCsvFile(arquivo.LerArquivo("Serviços - custo", 12, 1, header[1]));
            novaJanela.setLblEtapa("3 de 5");
            novaJanela.repaint();
            arqMatCusto.writeCsvFile(arquivo.LerArquivo("Materiais - custos", 11, 1, header[2]));
            novaJanela.setLblEtapa("4 de 5");
            novaJanela.repaint();
            arqMaoObraCuto.writeCsvFile(arquivo.LerArquivo("Mão de Obra - custos", 11, 1, header[3]));
            novaJanela.setLblEtapa("5 de 5");
            novaJanela.repaint();
            arqEquipCusto.writeCsvFile(arquivo.LerArquivo("Equipamentos - custos", 299, 1, header[4]));
            novaJanela.setLblEtapa("Iniciando...");
            novaJanela.repaint();
            arqPrincipal.writeCsvFile(arquivo.LerArquivo("Despesas Diretas", 11, 1, header[5]));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(novaJanela, "O arquivo \"COMPOSICOES_TCPO14.xls\" está sendo usado em outro processo.\nEncerre o processo para prosseguir.");
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        programa();
    }
}
