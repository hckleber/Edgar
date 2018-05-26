/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Suporte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

/**
 *
 * @author Milene
 */
public class LerXlsx {

    private String inputArquivo;

    public LerXlsx(String inputArquivo) {
        this.inputArquivo = inputArquivo;

    }

    public ArrayList LerArquivo(String aba, int starRow, int StartColumn, String header) throws IOException {
        ArrayList<String> dadosSaida = new ArrayList();
        dadosSaida.add(header);
        WorkbookSettings conf = new WorkbookSettings();
        conf.setEncoding("ISO-8859-1");
        File inputWorkbook = new File(inputArquivo);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook, conf);

            // Ler aba
            Sheet sheet = w.getSheet(aba);

            for (int nRow = starRow; nRow < sheet.getRows(); nRow++) {
                String linha = "";
                Cell teste = sheet.getCell(2, nRow);
                if (!teste.getContents().isEmpty()) {
                    for (int nColumn = StartColumn; nColumn < sheet.getColumns(); nColumn++) {
                        Cell cell = sheet.getCell(nColumn, nRow);
                        //CellType type = cell.getType();
                        if (cell.getType() == CellType.LABEL || cell.getType() == CellType.STRING_FORMULA) {
                            linha = linha + cell.getContents() + ";";
                        } else if (cell.getType() == CellType.NUMBER || cell.getType() == CellType.NUMBER_FORMULA) {
                            linha = linha + cell.getContents().replace(".", ",") + ";";
                        }else{
                            linha = linha + ";";
                        }
                    }

                    dadosSaida.add(linha);
                }
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
        return dadosSaida;
    }
}
