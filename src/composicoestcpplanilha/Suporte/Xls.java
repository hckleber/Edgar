/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composicoestcpplanilha.Suporte;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;
import jxl.write.Number;
import composicoestcpplanilha.Classes.Composicoes;
import composicoestcpplanilha.Classes.ConexaoCSV;
import composicoestcpplanilha.Classes.Constantes;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Milene
 */
public class Xls {

    private int controleAba;
    private String nomePlan;

    private WritableFont fonte = new WritableFont(WritableFont.TIMES, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
//    private WritableFont fonteBold = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
    private WritableCellFormat formatCell;

    private WritableWorkbook workbook;

    public Xls(String nomePlan) {
        this.nomePlan = nomePlan;
    }

    public void CriarPlanilha() {
        try {
            CopiarArquivo.copiarArquivo(Constantes.path + "\\src\\composicoestcpplanilha\\Modelo\\Cabecalho.xls",
                    Constantes.path + "\\" + nomePlan + ".xls");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "O arquivo " + nomePlan + " está sendo usado em outro processo.\nEncerre o processo para prosseguir.");
            Logger.getLogger(Xls.class.getName()).log(Level.SEVERE, null, ex);
        }

//        {
//            try {
//                String filename = nomePlan + ".xls";
//                formatCell = new WritableCellFormat(fonte);
//                formatCell.setWrap(true);
//                ws = new WorkbookSettings();
//                ws.setLocale(new Locale("pt", "BR"));
//                workbook = Workbook.createWorkbook(new File(filename), ws);
//                sheet = workbook.createSheet(nomeAba, this.controleAba);
//
//                // escrever na planilha
//                this.workbook.write();
//                this.workbook.close();
//            } catch (IOException e) {
//                JOptionPane.showMessageDialog(null, "Feche o arquivo " + nomePlan + " e tente novamente");
////                e.printStackTrace();
//            } catch (WriteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        this.controleAba++;
        System.out.println("Criado");
    }

    public void escreverPlan(ArrayList linhasCSV, String nomeAba) throws WriteException {
//        formatCell = new WritableCellFormat(fonte);
        formatCell = new WritableCellFormat(fonte, new NumberFormat("0.00"));
        formatCell.setWrap(false);

        String[] linha;
        {
            try {
                WorkbookSettings conf = new WorkbookSettings();
                conf.setEncoding("ISO-8859-1");
                Workbook pastaExcel = Workbook.getWorkbook(new File(Constantes.path + "\\" + nomePlan + ".xls"), conf);
                WritableWorkbook copia = Workbook.createWorkbook(new File(Constantes.path + "\\" + nomePlan + ".xls"), pastaExcel);
                WritableSheet planilhaExcel = copia.getSheet(nomeAba);

                Cell cellExcel1 = planilhaExcel.getCell(0, 5);
                Cell cellExcel2 = planilhaExcel.getCell(18, 5);
                Cell cellExcel3 = planilhaExcel.getCell(0, 10); // Celula com pontilhos

                WritableCellFormat formatCellBase1 = new WritableCellFormat(cellExcel1.getCellFormat());
                WritableCellFormat formatCellBase2 = new WritableCellFormat(cellExcel2.getCellFormat());
                WritableCellFormat formatCellBase3 = new WritableCellFormat(cellExcel3.getCellFormat());

                //Escreve as linhas na planilha modelo 
                for (int nRow = 1; nRow < linhasCSV.size(); nRow++) {
                    int nColumn = 1;
                    linha = linhasCSV.get(nRow).toString().split(";");
                    for (String conteudoLinha : linha) {
//                        if (nColumn < 26) {
//                            Label lbl = new Label(nColumn, nRow + 9, conteudoLinha, formatCell);
//                            planilhaExcel.addCell(lbl);
//                            lbl = new Label(0, nRow + 9, "", formatCellBase1);
//                            planilhaExcel.addCell(lbl);
//                            lbl = new Label(18, nRow + 9, "", formatCellBase2);
//                            planilhaExcel.addCell(lbl);
//                            nColumn++;
//                        }
                        if (nColumn < 8) {
                            Label lbl = new Label(nColumn, nRow + 9, conteudoLinha, formatCell);
                            planilhaExcel.addCell(lbl);
                            lbl = new Label(0, nRow + 9, "", formatCellBase1);
                            planilhaExcel.addCell(lbl);
                            lbl = new Label(18, nRow + 9, "", formatCellBase2);
                            planilhaExcel.addCell(lbl);
                            nColumn++;
                        } else if (nColumn < 25 && !conteudoLinha.equals("Produção horária da equipe:")) {
                            if(!conteudoLinha.isEmpty()){
                                Number numero;
                                numero = new Number(nColumn, nRow + 9, Float.parseFloat(conteudoLinha.replace(",", ".")), formatCell);
                                planilhaExcel.addCell(numero);
                            }else{
                                Number numero;
                                numero = new Number(nColumn, nRow + 9, 0f, formatCell);
                                planilhaExcel.addCell(numero);
                            }                            
                            nColumn++;
                        } else if (conteudoLinha.equals("Produção horária da equipe:")) {
                            Label lbl = new Label(nColumn, nRow + 9, conteudoLinha, formatCell);
                            planilhaExcel.addCell(lbl);
                            lbl = new Label(0, nRow + 9, "", formatCellBase1);
                            planilhaExcel.addCell(lbl);
                            lbl = new Label(18, nRow + 9, "", formatCellBase2);
                            planilhaExcel.addCell(lbl);
                            nColumn++;
                        }
                    }
                }

                int nRows = planilhaExcel.getRows();

                Label lbl = new Label(0, nRows, "", formatCellBase3);
                planilhaExcel.addCell(lbl);

                lbl = new Label(planilhaExcel.getColumns() - 1, nRows, "", formatCellBase3);
                planilhaExcel.addCell(lbl);

                for (int nColumn = 1; nColumn < planilhaExcel.getColumns() - 1; nColumn++) {
                    lbl = new Label(nColumn, nRows, "", formatCellBase3);
                    planilhaExcel.addCell(lbl);
                    addCell2(planilhaExcel, Pattern.PATTERN13, Border.TOP, BorderLineStyle.MEDIUM, nColumn, nRows, "");
                }

                for (int nRow = 10; nRow < planilhaExcel.getRows() - 1; nRow++) {
                    lbl = new Label(26, nRow, "", formatCellBase3);
                    planilhaExcel.addCell(lbl);
                    addCell2(planilhaExcel, Pattern.PATTERN13, Border.LEFT, BorderLineStyle.MEDIUM, 26, nRow, "");
                }

//                CellView cell = new CellView();
//                cell.setSize(16 * 20);
//                fonte = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
//                formatCell = new WritableCellFormat(fonte);
//                cell.setFormat(formatCell);
//                for (int l = 0; l < planilha.getRows(); l++) {
//                    planilha.setRowView(l, cell);
//                }
//                for (int x = 0; x < planilha.getColumns(); x++) {
//                    cell = sheet.getColumnView(x);
//                    cell.setAutosize(true);
//                    planilha.setColumnView(x, cell);
//                }
                copia.write();
                copia.close();
                pastaExcel.close();
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (BiffException ex) {
                Logger.getLogger(Xls.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void CriarNovaAba(String nomeAba) {
        workbook.createSheet(nomeAba, this.controleAba);
        this.controleAba++;
    }

    private static void addCell2(WritableSheet sheet, Pattern preenchimento, Border border, BorderLineStyle borderLineStyle,
            int col, int row, String desc) throws WriteException {

        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setBorder(border, borderLineStyle);
        cellFormat.setBackground(Colour.DEFAULT_BACKGROUND, preenchimento);
        Label label = new Label(col, row, desc, cellFormat);
        sheet.addCell(label);
    }

}
