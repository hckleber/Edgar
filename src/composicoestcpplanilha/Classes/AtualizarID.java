/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Classes;

import java.util.ArrayList;

/**
 *
 * @author Milene
 */
public class AtualizarID {


    public static void ordenarLista(ArrayList<String> arquivo) {
        int id = 0;
        int subId = 0;
        String comparar = null;
        String linhaOld;
        String linhaNew;
        String linha[];
        
        for (int i = 0; i < arquivo.size(); i++) {
            linha = arquivo.get(i).split(";");
            linhaOld = arquivo.get(i);
            
            
            if (linha[4].isEmpty()) {
                id++;
                subId = 1;
                comparar = linha[3];
                linhaNew = id + linhaOld.substring(linhaOld.indexOf(";"));
                
                arquivo.set(i, linhaNew);
            } else if (comparar.equals(linha[3])) {
                linhaNew = id + "." + subId  + arquivo.get(i).substring(arquivo.get(i).indexOf(";"));
                arquivo.set(i, linhaNew);
                subId++;
            }

        }

    }

}
