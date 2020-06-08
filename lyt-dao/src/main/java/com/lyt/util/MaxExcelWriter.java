package com.lyt.util;

import com.lyt.module.user.entity.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaxExcelWriter {

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符合一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataSet 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */

    public <T> byte[] exportExcel(String title, String[] headers, Collection<T> dataSet, String pattern) {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(10000);
        Font font3 = workbook.createFont();
        // 生成一个表格
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);	poi版本是3.15
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());	//	4.x以上
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(IndexedColors.VIOLET.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        Sheet sheet = null;
        Row row = null;
        int index = 0;
        int sheetnum = 0;
        Iterator<T> it = dataSet.iterator();
        // 生成表格标题行
        sheet = workbook.createSheet(title + sheetnum);
        sheet.setDefaultColumnWidth(15);
        row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        ByteArrayOutputStream baos = null;
        try {
            // 生成表格具体数据行
            while (it.hasNext()) {
                index++;
                // 如果数据大于50000行，生成下一个sheet
                if (index > 50000) {
                    index = 0;
                    ++sheetnum;
                    sheet = workbook.createSheet(title + sheetnum);
                    sheet.setDefaultColumnWidth(15);
                    row = sheet.createRow(0);
                    for (int i = 0; i < headers.length; i++) {
                        Cell cell = row.createCell(i);
                        cell.setCellStyle(style);
                        XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                        cell.setCellValue(text);
                    }
                }
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    Field field = fields[i];
                    String fieldName = field.getName();

                    Object value = PropertyUtils.getProperty(t, fieldName);
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        cell.setCellValue(intValue);
                    } else if (value instanceof Float) {
                        float fValue = (Float) value;
                        textValue = new XSSFRichTextString(
                                String.valueOf(fValue)).toString();
                        cell.setCellValue(textValue);
                    } else if (value instanceof Double) {
                        double dValue = (Double) value;
                        textValue = new XSSFRichTextString(
                                String.valueOf(dValue)).toString();
                        cell.setCellValue(textValue);
                    } else if (value instanceof Long) {
                        long longValue = (Long) value;
                        cell.setCellValue(longValue);
                    }
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "true";
                        if (!bValue) {
                            textValue = "false";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
//                        if ("".equals(pattern)) {
                        if (StringUtils.isBlank(pattern)) {
                            pattern = "yyy-MM-dd";
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else {
                        if (null == value || "".equals(value)) {
                            value = "";
                        } else {
                            textValue = value.toString();
                        }
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            XSSFRichTextString richString = new XSSFRichTextString(
                                    textValue);
                            font3.setColor(IndexedColors.BLUE.getIndex());
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }

                }
            }
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("poi处理出错");
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    public static void main(String[] args) {
		try {
			List<User> list = new ArrayList<User>();
			for (int i = 0; i < 100000; i++) {
				User people = new User("admin" + i, "password" + i, new Date(), new Date());
				list.add(people);
			}
			MaxExcelWriter writer = new MaxExcelWriter();
			byte[] buff = writer.exportExcel("测试例子", new String[]{"ID", "用户名", "密码","创建时间","修改时间"}, list, null);
			OutputStream os = new FileOutputStream(new File("D:/excel/蛋蛋老骚逼.xlsx"));
			IOUtils.write(buff, os);
            System.err.println("创建成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
