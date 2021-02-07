package com.taro.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @File: ImportExcelUtil.java
 * @Description: TODO
 * @Author: 张宇唯
 * @Date: 2017年2月6日
 * @Copyright taro(c)2010-2017
 */
public class ImportExcelUtil {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

	/**
	 * 获取IO流中的数据，组装成List<List<Object>>对象
	 * 
	 * @param in
	 *            ,fileName
	 * @return
	 * @throws IOException
	 */
	public static List<List<Object>> getDataListByExcel(InputStream in, String fileName)
			throws Exception {
		List<List<Object>> list = null;

		// 创建Excel工作薄
		Workbook work = getWorkbook(in, fileName);
		if (null == work) {
			throw new Exception("创建Excel工作薄为空！");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;

		list = new ArrayList<List<Object>>();
		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}

			// 遍历当前sheet中的所有行
			for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
				row = sheet.getRow(j);
				if (row == null) {
					continue;
				}

				// 遍历所有的列
				List<Object> li = new ArrayList<Object>();
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					li.add(getCellValue(cell));
				}
				list.add(li);
			}
		}
		work.close();
		return list;
	}
	
	/**
	 * 根据文件后缀，自适应上传文件的版本
	 * 
	 * @param inStr
	 *            ,fileName
	 * @return
	 * @throws Exception
	 */
	public static Workbook getWorkbook(InputStream inStr, String fileName)
			throws Exception {
		Workbook wb = null;
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		if (excel2003L.equals(fileType)) {
			wb = new HSSFWorkbook(inStr); // 2003-
		} else if (excel2007U.equals(fileType)) {
			wb = new XSSFWorkbook(inStr); // 2007+
		} else {
			throw new Exception("解析的文件格式有误！");
		}
		return wb;
	}

	/**
	 * 对表格中数值进行格式化
	 * 
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell) {
		
		Object value = null;
		if(cell==null){
			value = "";
			return value;
		}
		DecimalFormat df = new DecimalFormat("0.0"); // 格式化number String字符
		DecimalFormat df1 = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if ("General".equals(cell.getCellStyle().getDataFormatString())) {
				BigDecimal bd = new BigDecimal(cell.getNumericCellValue()); 
				if(new BigDecimal(bd.intValue()).compareTo(bd)==0){
					value =df1.format(bd);
				}else{
					value = df.format(bd);
				}
			} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
				value = sdf.format(cell.getDateCellValue());
			}else if("yyyymmdd".equals(cell.getCellStyle().getDataFormatString())){
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df2.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			value = "";
			break;
		default:
			break;
		}
		if(null == value){
			value = "";
		}
		return value;
	}
	
	public static void main(String[] args) {
		/*BigDecimal bd = new BigDecimal(1); 
		BigDecimal bd1 = new BigDecimal(1.1); 
		System.out.println(bd);
		System.out.println(bd1);
		if(new BigDecimal(bd.intValue()).compareTo(bd)==0){
		System.out.println("b 是整数");
		}else{
		System.out.println("b 是小数");
		}
		
		if(new BigDecimal(bd1.intValue()).compareTo(bd1)==0){
			System.out.println("b 是整数");
			}else{
			System.out.println("b 是小数");
			}*/
		Map<String,List<List<Object>>> tempMap = new HashMap<String,List<List<Object>>>();
		List<List<Object>> objectList1 = new ArrayList<List<Object>>();
		List<List<Object>> objectList2 = new ArrayList<List<Object>>();
		List<Object> list1 = new ArrayList<Object>();
		list1.add("列1");
		list1.add("列2");
		list1.add("列3");
		list1.add("列4");
		objectList1.add(list1);
		List<Object> list2 = new ArrayList<Object>();
		list2.add("值1");
		list2.add("值2");
		list2.add("值3");
		list2.add("值4");
		objectList1.add(list2);
		List<Object> list3 = new ArrayList<Object>();
		list3.add("值11");
		list3.add("值22");
		list3.add("值33");
		list3.add("值44");
		objectList1.add(list3);
		tempMap.put("变压器", objectList1);
		
		List<Object> list11 = new ArrayList<Object>();
		list11.add("列11111");
		list11.add("列22222");
		list11.add("列33333");
		list11.add("列44444");
		objectList2.add(list11);
		List<Object> list22 = new ArrayList<Object>();
		list22.add("值11111");
		list22.add("值22222");
		list22.add("值33333");
		list22.add("值44444");
		objectList2.add(list22);
		List<Object> list33 = new ArrayList<Object>();
		list33.add("值11111111");
		list33.add("值22222222");
		list33.add("值33333333");
		list33.add("值44444444");
		objectList2.add(list33);
		tempMap.put("断路器", objectList2);
		String realPath = "c:/abdc";
		String fileName = "abc.xls";
		createExcel(tempMap,realPath,fileName);
		
		System.out.println("2222222222222");
	}
	
	public static void createExcel(Map<String,List<List<Object>>> tempMap,String realPath,String fileName){
		HSSFWorkbook workbook = new HSSFWorkbook();  
		int tempNum = 0;
		for(Iterator<String> iterator = tempMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();//sheet名称
			List<List<Object>> list = tempMap.get(key);//sheet数据
			List<Object> lastHead = list.get(0);
    		List<List<Object>> lastData = new ArrayList<List<Object>>();
    		for(int i=1;i<list.size();i++){//取数据进行封装
    			lastData.add(list.get(i));
    		}
			try {
				exportExcel(workbook,tempNum,key,lastHead,lastData);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tempNum++;
		}
		//String realPath = request.getRealPath("home") + File.separator + "exceldata" + File.separator+ "abc.xls";
		try {
			File file = new File(realPath);
			if  (!file .exists()  && !file .isDirectory()) {       
			    System.out.println("//不存在");  
			    file .mkdir();    
			} 
			realPath = realPath + File.separator + fileName;
			OutputStream out = new FileOutputStream(realPath);  
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static void exportExcel(HSSFWorkbook workbook, int sheetNum,  
            String sheetTitle, List<Object> headers, List<List<Object>> result) throws Exception {  
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet();  
        workbook.setSheetName(sheetNum, sheetTitle);  
        // 设置表格默认列宽度为20个字节  
        sheet.setDefaultColumnWidth((short) 20);  
        // 生成一个样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        // 设置这些样式  
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);  
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setFontHeightInPoints((short) 12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        // 把字体应用到当前的样式  
        style.setFont(font);  
  
        // 指定当单元格内容显示不下时自动换行  
        style.setWrapText(true);  
  
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);  
        for (int i = 0; i < headers.size(); i++) {  
            HSSFCell cell = row.createCell((short) i);  
          
            cell.setCellStyle(style);  
            HSSFRichTextString text = new HSSFRichTextString(String.valueOf(headers.get(i)));  
            cell.setCellValue(text.toString());  
        }  
        // 遍历集合数据，产生数据行  
        if (result != null) {  
            int index = 1;  
            for (List<Object> m : result) {  
                row = sheet.createRow(index);  
                int cellIndex = 0;  
                for (Object str : m) {  
                    HSSFCell cell = row.createCell((short) cellIndex);  
                    cell.setCellValue(String.valueOf(str));  
                    cellIndex++;  
                }  
                index++;  
            }  
        }  
    }
}
