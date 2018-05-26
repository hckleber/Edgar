/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Suporte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Milene
 */
public class OrdenarLista {
    
    public static void Ordenar(ArrayList lista, boolean criacao){
        String cabecalho = "";
        if(!criacao){
        cabecalho = lista.get(0).toString();
        lista.remove(0);
        }
        Collections.sort(lista, new Comparator() {
                public int compare(Object o1, Object o2) {
                    String primeira = (String) o1;
                    String segunda = (String) o2;
                    Float um = Float.parseFloat(primeira.substring(0, primeira.indexOf(";")));
                    Float dois = Float.parseFloat(segunda.substring(0, segunda.indexOf(";")));
                    return um < dois ? -1 : um > dois ? +1 : 0;
                }
            });
        if(!criacao){           
            lista.add(0, cabecalho);
        }
    }
}
