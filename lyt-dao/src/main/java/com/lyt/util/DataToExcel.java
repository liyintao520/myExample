package com.lyt.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lyt.module.org.entity.OrgInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DataToExcel
 * @Description TODO
 * @Author liyintao
 * @Date 2020/6/5 11:08
 */
public class DataToExcel {

    private static Log log = LogFactory.getLog(DataToExcel.class);

    // 1    公证机构查询   notarization
    private final static String NOTARY_ORG_URL = "http://nx.12348.gov.cn/flfw-xt/portDeptGzc/queryGzcByTwo";
    private final static String NOTARY_ORG_URL_POST = null;


    // 2    人民调解机构查询 mediation
    private final static String MEDIATION_ORG_URL = "http://nx.12348.gov.cn/flfw-xt/portDeptRmtj/queryRmtj";
    // 2    获取人民调解委员会名称代码等信息
    private final static String MEDIATION_ORG_URL_POST = "http://nx.12348.gov.cn/flfw-xt/exApi/listRmtjwyh";

    // 5    法援机构查询   legalAid
    private final static String LEGALAID_ORG_URL = "http://nx.12348.gov.cn/flfw-xt/portDeptFy/queryFyzx";
    // 5    获取法援中心名称及编号
    private final static String LEGALAID_ORG_URL_POST = "http://nx.12348.gov.cn/flfw-xt/exApi/listFyzx";

    // 地区编码
    private static String city = "640";
    private static String apiSec = "640000";
    public static void main(String[] args) {
        try {
//            createExcel("1");
//            createExcel("2");
            createExcel("5");
//            List<OrgInfo> list1 = doGetDataList(city, null, 1, 100000, "2");
//            List<OrgInfo> list2 = doPostDataList(city, null, 1, 100000, "2");
//            if (JsonUtil.toJson(list1).equals(JsonUtil.toJson(list2))) {
//                System.err.println("2 一样");
//            }

//            List<OrgInfo> list3 = doGetDataList(city, null, 1, 100000, "5");
//            List<OrgInfo> list4 = doPostDataList(city, null, 1, 100000, "5");
//            if (JsonUtil.toJson(list3).equals(JsonUtil.toJson(list4))) {
//                System.err.println("5 一样");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 法援机构查询
     * @param city
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List<OrgInfo> doGetDataList(String city, String name, int pageNum, int pageSize, String type) {
        JSONObject setJson = new JSONObject();
        List<OrgInfo> listInfo = new ArrayList<>();
        try {
            // 请求地址
            long start = System.currentTimeMillis();
            StringBuilder url = null;
            if ("1".equals(type)) {
                url = new StringBuilder(NOTARY_ORG_URL);
            } else if ("2".equals(type)) {
                url = new StringBuilder(MEDIATION_ORG_URL);
            } else if ("5".equals(type)) {
                url = new StringBuilder(LEGALAID_ORG_URL);
            }
            url.append("?city=").append(city);
//            url.append("&name=").append(name);
            url.append("&pageNum=").append(pageNum);
            url.append("&pageSize=").append(pageSize);
            setJson = HttpUtil.doGet(url.toString());
            long end = System.currentTimeMillis();
            int total = (int)setJson.get("total");
            if (0 == total) {
                return listInfo;
            } else {
                String content = setJson.get("content").toString();
                listInfo = (List<OrgInfo>) JSONArray.parseArray(content, OrgInfo.class);
            }
            System.err.println("获取数据总值：" + total + "，list长度：" + listInfo.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("数据：" + JsonUtil.toJson(listInfo));
        return listInfo;
    }


    /**
     * 填充数据
     * @throws IOException
     */
    public static void createExcel(String type) throws IOException {
        // 错误记录数
        List<OrgInfo> list = doGetDataList(city, "", 1, 100000, type);
        // 输出错误日志
        if (null != list && list.size() > 0) {
            // 工程路径读取文件 获取模板格式
            String excelPath = "D:/excel/机构导入模板.xlsx";
            String writeUrlFiles = "D:/excel/";
            XSSFWorkbook wb = null;
            FileOutputStream os = null;
            InputStream in = null;
            try {
//                createSheet(wb, in, list, excelPath);
                int cellIndex = 0;
                in = new FileInputStream(excelPath);
                wb = new XSSFWorkbook(in);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow row = null;
                XSSFCell cell = null;
                XSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
                cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
                cellStyle.setBorderTop(BorderStyle.THIN);//上边框
                cellStyle.setBorderRight(BorderStyle.THIN);//右边框
                // Excel数据实体bean
                OrgInfo bean = null;
                int sheetnum = 0;
                int index = 1;  //
                String[] headers = new String[]{"ID", "用户名", "密码","创建时间","修改时间"};
                for (int i = 0; i < list.size(); i++) {
                    bean = list.get(i);
                    index++;
                    // 如果数据大于100行，生成下一个sheet
                    if (index > 100) {
                        // TODO 创建sheet分页，创建行和标题。
                        index = 1;  //  从第几行开始（坐标是从0开始的）
                        ++sheetnum;
                        sheet = wb.createSheet("李银涛" + sheetnum);
                        // 创建第一行
                        row = sheet.createRow(0);
                        cellIndex = 0;
                        // 创建标题
                        for (int n = 0; n < headers.length; n++) {
                            XSSFCell cell1 = row.createCell(cellIndex++);
                            cell1.setCellStyle(cellStyle);
                            XSSFRichTextString text = new XSSFRichTextString(headers[n]);
                            cell1.setCellValue(text);
                        }
                    }
                    row = sheet.createRow(index);
                    cellIndex = 0;
//                   地区编码 	机构名称	机构类型	业务范围	所属区域（省）	所属区域（市）	所属区域（区/县）	机构地址	统一社会信用代码	官方网址	主管司法局	成立时间	负责科室	负责人	联系电话	传真号码	机构编码	简介	错误提示
                    cell = row.createCell(cellIndex++);
                    // TODO 等待接口返回一个地区编码的属性，然后替换这里
                    cell.setCellValue("");  // 地区编码
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getName());  //  机构名称
                    cell.setCellStyle(cellStyle);
//                    公证处、法律援助中心、调解委员会
                    cell = row.createCell(cellIndex++);
                    if ("1".equals(type)) {
                        cell.setCellValue("公证处");  //  机构类型
                    } else if ("2".equals(type)) {
                        cell.setCellValue("调解委员会");  //  机构类型
                    } else if ("5".equals(type)) {
                        cell.setCellValue("法律援助中心");  //  机构类型
                    }
                    cell.setCellStyle(cellStyle);
                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  业务范围
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  所属区域（省）
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  所属区域（市）
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  所属区域（区/县）
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getNameOfPath());  //  机构地址
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  统一社会信用代码
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  官方网址
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  主管司法局
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  成立时间
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  负责科室
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getLeader());  //  负责人
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getTel());  //  联系电话
                    cell.setCellStyle(cellStyle);
// 地区编码 	机构名称	机构类型	业务范围	所属区域（省）	所属区域（市）	所属区域（区/县）	机构地址	统一社会信用代码	官方网址	主管司法局	成立时间	负责科室	负责人	联系电话	传真号码	机构编码	简介	错误提示

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  传真号码
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getEncNo());  //  机构编码
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(bean.getIntroduce());  //  简介
                    cell.setCellStyle(cellStyle);

                    cell = row.createCell(cellIndex++);
                    cell.setCellValue("");  //  错误提示
                    cell.setCellStyle(cellStyle);
                }
                // 如果文件夹不存在，则创建
                File dstFile = new File(writeUrlFiles);
                if (!dstFile.exists()) {
                    dstFile.mkdirs();
                }
                // 输出文件名为时间戳
                Long soure = new Date().getTime();
                String fileName = StringUtils.EMPTY;
                if ("1".equals(type)) {
                    fileName = "公证处" + soure;
                } else if ("2".equals(type)) {
                    fileName = "调解委员会" + soure;
                } else if ("5".equals(type)) {
                    fileName = "法律援助中心" + soure;
                }
                os = new FileOutputStream(writeUrlFiles + fileName + ".xlsx");
                wb.write(os);
                System.err.println("存储位置：" + writeUrlFiles + fileName + ".xlsx");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != wb) {
                    wb.close();
                }
                if (null != in) {
                    in.close();
                }
                if (null != os) {
                    os.close();
                }
            }
        }
    }

    /**
     * 法援机构查询
     * @param city
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static List<OrgInfo> doPostDataList(String city, String name, int pageNum, int pageSize, String type) {
        JSONObject setJson = new JSONObject();
        List<OrgInfo> listInfo = new ArrayList<>();
        try {
            // 请求地址
//            long start = System.currentTimeMillis();
            StringBuilder url = null;
            if ("1".equals(type)) {
                url = new StringBuilder(NOTARY_ORG_URL_POST);
            } else if ("2".equals(type)) {
                url = new StringBuilder(MEDIATION_ORG_URL_POST);
            } else if ("5".equals(type)) {
                url = new StringBuilder(LEGALAID_ORG_URL_POST);
            }
            Map<String, String> setMap = Maps.newHashMap();
            setMap.put("city", city);   //  模糊匹配
            setMap.put("apiSec", apiSec);   //  模糊匹配
            setMap.put("pageNum", pageNum + "");    // 精确查找
            setMap.put("pageSize", pageSize + "");    // 精确查找
            setJson = HttpUtil.doHttpsPost(url.toString(), setMap,"UTF-8");
//            long end = System.currentTimeMillis();
            int total = (int)setJson.get("total");
            if (0 == total) {
                return listInfo;
            } else {
                String content = setJson.get("content").toString();
                listInfo = (List<OrgInfo>) JSONArray.parseArray(content, OrgInfo.class);
            }
            System.err.println("获取数据总值：" + total + "，list长度：" + listInfo.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("数据：" + JsonUtil.toJson(listInfo));
        return listInfo;
    }
}
