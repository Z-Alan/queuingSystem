package edu.niit.danzhao.querySystem.util;
/**
 * Created by home
 * on 2018/4/12.
 */

import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 自己写的一个excel导出
 * @author zhou
 **/
public class MyExcelUtil {
    public void exportExcel(String[]  headers,String filename,List<FunctionQuHao> functionQuHaos){
        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet("考生取号表");

        //第三步创建行row:添加表头0行
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中
        //第四步创建表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i); //第一个单元格
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
        //第五步插入数据
        for (int i = 0; i < functionQuHaos.size(); i++) {
            //创建行
            row = sheet.createRow(i+1);
            //创建单元格并且添加数据
            row.createCell(0).setCellValue(functionQuHaos.get(i).getQuhaoId());
            row.createCell(1).setCellValue(functionQuHaos.get(i).getKsName());
            row.createCell(2).setCellValue(functionQuHaos.get(i).getKsSfzh());
            row.createCell(3).setCellValue(functionQuHaos.get(i).getKsKjh());
            row.createCell(4).setCellValue(functionQuHaos.get(i).getKsZkzh());
            row.createCell(5).setCellValue(functionQuHaos.get(i).getNumber());
            row.createCell(6).setCellValue(functionQuHaos.get(i).getNumberType());
            row.createCell(7).setCellValue(timeToString(functionQuHaos.get(i).getQhTime()));
            row.createCell(8).setCellValue(timeToString(functionQuHaos.get(i).getEnterTime()));
            row.createCell(9).setCellValue(functionQuHaos.get(i).getQhTimeFlag());
        }
        //第六步将生成excel文件保存到指定路径下
        try {
            FileOutputStream fout = new FileOutputStream(
                    "c:\\exportExcel"
                    + filename + ".xls");
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 日期转换
     * */
    private String timeToString(Timestamp sqlTime){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //可以灵活的设置字符串的形式。
        String tsStr = sdf.format(sqlTime);

        return tsStr;

    }
}
