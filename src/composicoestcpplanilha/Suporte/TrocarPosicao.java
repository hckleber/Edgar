/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Suporte;

import composicoestcpplanilha.Classes.ConexaoCSV;
import composicoestcpplanilha.Classes.Constantes;
import java.util.ArrayList;

/**
 *
 * @author Milene
 */
public class TrocarPosicao {

    public static void Principal(int id1, int id2) {
        ConexaoCSV principal = new ConexaoCSV(Constantes.DESPESAS_DIRETASP);
        ArrayList<String> listPrincipal = principal.readCsvFile();
        String principalId1;
        String principalId2;

        principalId1 = listPrincipal.get(id1);
        principalId2 = listPrincipal.get(id2);

        principalId1 = id2 + ".0" + principalId1.substring(principalId1.indexOf(";"));
        principalId2 = id1 + ".0" + principalId2.substring(principalId2.indexOf(";"));

        listPrincipal.set(id1, principalId2);
        listPrincipal.set(id2, principalId1);

        String dadosPrincipal = "";

        for (String a : listPrincipal) {
            dadosPrincipal = dadosPrincipal + a + "\n";
        }

        principal.writeCsvFile(dadosPrincipal, true);

    }

    public static void Secundario(int id1, int id2) {
        ConexaoCSV secundario = new ConexaoCSV(Constantes.DESPESAS_DIRETASS);
        ArrayList<String> listSecundaria = secundario.readCsvFile();

        String dados = "";
        for (String a : listSecundaria) {
            if (a.subSequence(0, a.indexOf(".")).equals(Integer.toString(id1))) {
                dados = dados + id2 + a.substring(a.indexOf(".")) + "\n";
            } else if (a.subSequence(0, a.indexOf(".")).equals(Integer.toString(id2))) {
                dados = dados + id1 + a.substring(a.indexOf(".")) + "\n";
            } else {
                dados = dados + a + "\n";
            }
        }
        System.out.println(dados);
//        secundario.writeCsvFile(dados, true);

    }

    public static void Secundario(String idPrinc, int id1, int id2) {
        ConexaoCSV secundario = new ConexaoCSV(Constantes.DESPESAS_DIRETASS);
        ArrayList<String> listSecundaria = secundario.readCsvFile();

        String dados = "";
        for (String a : listSecundaria) {
            if (!a.subSequence(0, a.indexOf(".")).equals(idPrinc) || a.subSequence(0, a.indexOf(";")).equals("ITEM")) {
                dados = dados + a + "\n";
            } else {
                if (a.subSequence(a.indexOf(".") + 1, a.indexOf(";")).equals(Integer.toString(id1))) {
                    dados = dados + idPrinc + "." + id2 + a.substring(a.indexOf(";")) + "\n";
                } else if (a.subSequence(a.indexOf(".") + 1, a.indexOf(";")).equals(Integer.toString(id2))) {
                    dados = dados + idPrinc + "." + id1 + a.substring(a.indexOf(";")) + "\n";
                } else {
                    dados = dados + a + "\n";
                }

            }
        }
        secundario.writeCsvFile(dados, true);

    }

}
