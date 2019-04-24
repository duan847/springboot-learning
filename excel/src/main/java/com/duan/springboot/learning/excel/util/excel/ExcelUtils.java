package com.duan.springboot.learning.excel.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * excel操作公共类-提供excel按照模板输出
 * @author duanjw
 *
 */
@Slf4j
public class ExcelUtils {


    /**
     * Sheet复制
     * @param fromSheet
     * @param toSheet
     * @param copyValueFlag
     */
    public static void copySheet(Workbook wb, Sheet fromSheet, Sheet toSheet,
                                 boolean copyValueFlag) {
        //合并区域处理

        mergerRegion(fromSheet, toSheet);
        int index = 0;
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            Row tmpRow =  rowIt.next();
            Row newRow = toSheet.createRow(tmpRow.getRowNum());

            CellStyle style = tmpRow.getRowStyle();
            if(style != null)
                newRow.setRowStyle(tmpRow.getRowStyle());

            newRow.setHeight(tmpRow.getHeight());

            //针对第一行设置行宽
            if(index == 0) {
                int first = tmpRow.getFirstCellNum();
                int last = tmpRow.getLastCellNum();
                for(int i = first ; i < last ; i++) {
                    int w = fromSheet.getColumnWidth(i);
                    toSheet.setColumnWidth(i, w + 1);
                }
                toSheet.setDefaultColumnWidth(fromSheet.getDefaultColumnWidth());
            }

            //行复制
            copyRow(wb,tmpRow,newRow,copyValueFlag);

            index++ ;
        }
    }
    /**
     * 行复制功能
     * @param fromRow
     * @param toRow
     */
    static void copyRow(Workbook wb,Row fromRow,Row toRow,boolean copyValueFlag){
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            Cell tmpCell = cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb,tmpCell, newCell, copyValueFlag);
        }
    }
    /**
     * 复制原有sheet的合并单元格到新创建的sheet
     *
     * @param toSheet 新创建sheet
     * @param fromSheet      原有的sheet
     */
    static void mergerRegion(Sheet fromSheet, Sheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {

            CellRangeAddress cra = fromSheet.getMergedRegion(i);

            toSheet.addMergedRegion(cra);
        }
    }
    /**
     * 复制单元格
     *
     * @param srcCell
     * @param distCell
     * @param copyValueFlag
     *            true则连同cell的内容一起复制
     */
    public static void copyCell(Workbook wb,Cell srcCell, Cell distCell,
                                boolean copyValueFlag) {



        CellStyle newstyle=wb.createCellStyle();
        //copyCellStyle(srcCell.getCellStyle(), newstyle);
        //distCell.setEncoding(srcCell.);
        newstyle.cloneStyleFrom(srcCell.getCellStyle());
        //样式
        distCell.setCellStyle(newstyle);
        //批注
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        CellType srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);


        if (copyValueFlag) {
            if (srcCellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == CellType.STRING ) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == CellType.BLANK ) {
                // nothing21
            } else if (srcCellType == CellType.BOOLEAN  ) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == CellType.ERROR ) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == CellType.FORMULA  ) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else { // nothing29
            }
        }
    }


    /**
     * 写入excel数据
     * @param path 采用的模板 位置在 src/下 模板第一个sheet页必须是模板sheet
     * @param sheetDatas 模板数据
     */

    public static void writeData(String path , OutputStream out, SheetData... sheetDatas ) {
        Workbook wb;
        try(InputStream input = ExcelUtils.class.getResourceAsStream( path)) {
            if(input == null) {
                throw new RuntimeException("Excel文件不存在：" + path);
            }

            //Excel2007的版本，扩展名是.xlsx
            if(path.endsWith(".xlsx")) {
                wb = new XSSFWorkbook(input);
            }
            //Excel2003以前（包括2003）的版本，扩展名是.xls
            else if(path.endsWith(".xls")) {
                wb = new HSSFWorkbook(input);
            }
            else {
                throw new RuntimeException("非Excel文件：" + path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Excel读取失败：" + path);
        }

        Sheet source =  wb.getSheetAt(0);

        //sheet数据
        int size = sheetDatas.length ;
        for(int i = 0 ; i < size  ; i++) {
            if(i == 0) {
                wb.setSheetName(0, sheetDatas[0].getName());
            } else {
                //如果sheet数据下标大于shell数，说明有sheet数据但没有sheet，复制第一个sheet
                if(i >= wb.getNumberOfSheets() ) {
                    Sheet toSheet = wb.createSheet(sheetDatas[i].getName());
                    //复制sheet
                    copySheet(wb, source, toSheet, true);
                }
                //直接使用sheet
                else {
                    wb.setSheetName(i, sheetDatas[i].getName());
                }
            }
        }

        for(int i = 0 ; i < size  ; i++) {
            //写数据
            writeData(sheetDatas[i], wb.getSheetAt(i));
        }

        try {
            wb.write(out);
            out.flush();
            wb.close();
            out.close();
        } catch (IOException e) {
            log.error("写入或关闭文件资源失败", e);
        }


    }

    /**
     * 向sheet页中写入数据
     * @param sheetData shell数据
     * @param sheet sheet
     */
    public static void writeData(SheetData sheetData , Sheet sheet) {
        //从sheet中找到匹配符 #{}表示单个 , ${}表示集合,从该单元格开始向下追加
        for(Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
            Row row = rowIt.next();
            //取cell
            for(int j = row.getFirstCellNum() ; j < row.getLastCellNum() ; j++) {
                Cell cell = row.getCell(j);
                if(cell == null) {
                    continue;
                }
                String cellValue = cell.getStringCellValue();
                //判断cell的内容是否包含 $ 或者#
                if(cell.getCellType() == CellType.STRING && cellValue != null
                        && (cellValue.contains("$") || cellValue.contains("#") )) {
                    //剥离# $
                    String[] winds = CommonUtils.getWildcard(cellValue.trim());
                    for(String wind : winds) {
                        writeData(sheetData, wind , cell , sheet);
                    }
                }

            }

        }
    }

    /**
     * 填充数据
     * @param sheetData
     * @param keyWind #{name}只替换当前 or ${names} 从当前行开始向下替换
     */
    static void writeData(SheetData sheetData , String keyWind , Cell cell , Sheet sheet) {
        String key = keyWind.substring(2 , keyWind.length() - 1);

        if(keyWind.startsWith("#")) {

            //简单替换

            Object value = sheetData.get(key);
            //为空则替换为空字符串
            if(value == null)
                value = "" ;

            String cellValue = cell.getStringCellValue();
            cellValue = cellValue.replace(keyWind, value.toString());

            cell.setCellValue(cellValue);

        } else  if(keyWind.startsWith("$")) {

            //从list中每个实体开始解,行数从当前开始
            int rowindex = cell.getRowIndex();
            int columnindex = cell.getColumnIndex();

            List<? extends Object> listdata = sheetData.getDatas();

            //不为空的时候开始填充
            if(listdata != null && !listdata.isEmpty()){
                for(Object o : listdata) {
                    Object cellValue = CommonUtils.getValue(o, key);




                    Row row = sheet.getRow(rowindex);
                    if(row == null) {
                        row = sheet.createRow(rowindex);
                    }


                    //取出cell
                    Cell c = row.getCell(columnindex);
                    if(c == null)
                        c = row.createCell(columnindex);
                    if(cell.getCellStyle() != null){
                        c.setCellStyle(cell.getCellStyle());

                    }
                    if(cell.getCellType() != null) {
                        System.out.println(cell.getCellType());
                        c.setCellType(cell.getCellType());

                    }

                    if(cellValue != null) {
                        if((cellValue instanceof Number || CommonUtils.isNumber(cellValue)) && cellValue.toString().length() < 15 )
                            c.setCellValue( Double.valueOf(cellValue.toString()));
                        else if(cellValue instanceof Boolean)
                            c.setCellValue((Boolean)cellValue);
                        else if(cellValue instanceof Date){
                            c.setCellValue((Date)cellValue);
                        }
                        else
                            c.setCellValue(cellValue.toString());
                    } else {

                        //数据为空 如果当前单元格已经有数据则重置为空
                        if(c.getStringCellValue() != null) {
                            c.setCellValue("");
                        }

                    }

                    //错误消息，如果有，添加到批注
                    Map errorMap = (Map)CommonUtils.getValue(o, "errorMap");
                    if(null !=errorMap && null != errorMap.get(key)){
                        Drawing draw1 = sheet.createDrawingPatriarch();
                        Comment ct1 = draw1.createCellComment(new XSSFClientAnchor(0, 0, 0,
                                0, (short) 3, 3, (short) 9, 10));
                        ct1.setString(new XSSFRichTextString(errorMap.get(key).toString()));
                        c.setCellComment(ct1);
                    }
                    rowindex++ ;
                }
            } else {
                //list数据为空则将$全部替换空字符串
                String cellValue = "" ;

                cell.setCellValue(cellValue);
            }
        }
    }

}
