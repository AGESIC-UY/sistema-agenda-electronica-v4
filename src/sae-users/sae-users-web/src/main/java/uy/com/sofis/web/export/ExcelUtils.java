/*
 * Sofis Solutions
 */
package uy.com.sofis.web.export;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import uy.com.sofis.constantes.ConstantesComponentesId;
import uy.com.sofis.web.presentacion.mensajes.Mensajes;
import uy.com.sofis.web.utils.JSFUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author USUARIO
 */
public class ExcelUtils {

    private static final Logger LOGGER = Logger.getLogger(ExcelUtils.class.getName());


    public static byte[] generarReporteDonaciones(List<Object[]> donaciones, List<Object[]> donacionesPorIns, Integer anio, String insNombre) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Donaciones " + anio);

        try {

            Integer rowCount = 0;
            Row row = sheet.createRow(rowCount);
            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue(anio);
            cell.setCellStyle(createTitleStyle(workbook));

            if (!StringUtils.isBlank(insNombre)) {
                cell = row.createCell(1);
                cell.setCellValue(insNombre);
                cell.setCellStyle(createTitleStyle(workbook));
            }

            rowCount = 2;
            row = sheet.createRow(rowCount);
            cell = row.createCell(0);
            cell.setCellValue("RUT");
            cell.setCellStyle(createColumnTitleStyle(workbook));

            cell = row.createCell(1);
            cell.setCellValue("Razón social");
            cell.setCellStyle(createColumnTitleStyle(workbook));

            cell = row.createCell(2);
            cell.setCellValue("Monto donado");
            cell.setCellStyle(createColumnTitleStyle(workbook));

            BigDecimal totalDonado = BigDecimal.ZERO;

            for (Object[] ob : donaciones) {

                row = sheet.createRow(++rowCount);
                cell = row.createCell(0);
                cell.setCellValue(ob[0].toString());

                cell = row.createCell(1);
                cell.setCellValue(ob[1].toString());

                cell = row.createCell(2);
                BigDecimal montoDonado = (BigDecimal) ob[2];
                cell.setCellValue(montoDonado.doubleValue());

                totalDonado = totalDonado.add(montoDonado);

            }

            row = sheet.createRow(++rowCount);

            cell = row.createCell(1);
            cell.setCellValue("Total");
            cell.setCellStyle(createColumnTitleStyle(workbook));

            cell = row.createCell(2);
            cell.setCellValue(totalDonado.doubleValue());
            cell.setCellStyle(createColumnTitleStyle(workbook));

            ///Sheet 2
            if (donacionesPorIns != null && !donacionesPorIns.isEmpty()) {
                sheet = workbook.createSheet("Donaciones por institución " + anio);

                rowCount = 0;
                row = sheet.createRow(rowCount);

                cell = row.createCell(0);
                cell.setCellValue(anio);
                cell.setCellStyle(createTitleStyle(workbook));

                rowCount = 2;
                row = sheet.createRow(rowCount);
                cell = row.createCell(0);
                cell.setCellValue("Beneficiario");
                cell.setCellStyle(createColumnTitleStyle(workbook));

                cell = row.createCell(1);
                cell.setCellValue("RUT donante");
                cell.setCellStyle(createColumnTitleStyle(workbook));

                cell = row.createCell(2);
                cell.setCellValue("Razón social donante");
                cell.setCellStyle(createColumnTitleStyle(workbook));

                cell = row.createCell(3);
                cell.setCellValue("Monto donado");
                cell.setCellStyle(createColumnTitleStyle(workbook));

                String institucionActual = "";
                totalDonado = null;

                for (Object[] ob : donacionesPorIns) {

                    String institucionBeneficiaria = ob[0].toString();

                    if (!institucionActual.equals(institucionBeneficiaria)) {

                        if (totalDonado != null) {
                            //Comienza a ejecutarse a partir de la segunda institución
                            //Se agrega el total de la institución anterior
                            row = sheet.createRow(++rowCount);

                            cell = row.createCell(2);
                            cell.setCellValue("Total");
                            cell.setCellStyle(createColumnTitleStyle(workbook));

                            cell = row.createCell(3);
                            cell.setCellValue(totalDonado.doubleValue());
                            cell.setCellStyle(createColumnTitleStyle(workbook));

                            totalDonado = BigDecimal.ZERO;
                        }

                        row = sheet.createRow(++rowCount);
                        cell = row.createCell(0);
                        cell.setCellValue(institucionBeneficiaria);

                        cell = row.createCell(1);
    
                        cell = row.createCell(2);
     
                        cell = row.createCell(3);


                        institucionActual = institucionBeneficiaria;
                    }

                    row = sheet.createRow(++rowCount);
                    cell = row.createCell(1);
                    cell.setCellValue(ob[1].toString());

                    cell = row.createCell(2);
                    cell.setCellValue(ob[2].toString());

                    cell = row.createCell(3);
                    BigDecimal montoDonado = (BigDecimal) ob[3];
                    cell.setCellValue(montoDonado.doubleValue());

                    if (totalDonado == null) {
                        totalDonado = BigDecimal.ZERO;
                    }
                    totalDonado = totalDonado.add(montoDonado);

                }

                if (totalDonado != null) {
                    row = sheet.createRow(++rowCount);

                    cell = row.createCell(2);
                    cell.setCellValue("Total");
                    cell.setCellStyle(createColumnTitleStyle(workbook));

                    cell = row.createCell(3);
                    cell.setCellValue(totalDonado.doubleValue());
                    cell.setCellStyle(createColumnTitleStyle(workbook));
                }

            }

            autoSizeColumns(workbook, 2);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarError(ConstantesComponentesId.ID_MSG_TEMPLATE, Mensajes.obtenerMensaje(Mensajes.ERROR_GENERAL), "");
        }

        return null;

    }

    
    public static void autoSizeColumns(XSSFWorkbook workbook, Integer rowNumber) {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(rowNumber);
                for (int f = 0; f < row.getLastCellNum(); f++) {
                    sheet.autoSizeColumn(f);
                }
            }
        }
    }

    public static CellStyle createYearStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        //font.setFontHeightInPoints((short) 10);
        //font.setFontName("Arial");
        font.setBold(true);
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setFont(font);
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        return cs;
    }

    public static CellStyle createColumnTitleStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#33a5b3")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createTitleStyle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setFont(font);
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#60ddec")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnTitleWithBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#33a5b3")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnGroupN1Style(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#78b2e8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnGroupN1WithBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#78b2e8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnGroupN1WithoutBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.NONE);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#78b2e8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnProjectCategoryWithBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#F0F4F8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnProjectCategoryWithoutBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.NONE);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#F0F4F8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cs;
    }

    public static CellStyle createColumnProjectWithBorderStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.MEDIUM);
        cs.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        cs.setFillForegroundColor(new XSSFColor(Color.decode("#F0F4F8")));
        cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cs.setAlignment(HorizontalAlignment.RIGHT);
        return cs;
    }

    public static CellStyle createBorderLeftStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setLeftBorderColor(new XSSFColor(Color.decode("#FFBE00")));
        return cellStyle;
    }

}
