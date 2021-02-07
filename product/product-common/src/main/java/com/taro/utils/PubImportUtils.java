
package com.taro.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.taro.constants.Constant;
import com.taro.exception.BusinessException;

/**
 * ClassName:PubImportUtils公共导入的公共方法 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2017年12月13日 上午9:47:57 <br/>
 * @author   张宇唯
 * @since    JDK 1.6
 */
public class PubImportUtils {
	private final static String excel2003L = ".xls"; // 2003- 版本的excel
	private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel
	
	public static void main(String[] args) throws IOException {
//		analysisZipFile(new File("D:\\新建文件夹\\test.zip"), "D:\\新建文件夹\\");
		System.out.println(isMatch(Constant.REGEX_YYYYMM,"asdas"));
	}
	
	public static boolean isMatch(String regex, String orginal){ 
	    if (StringUtils.isBlank(orginal)) { 
	    	return false; 
	    }
	    Pattern pattern = Pattern.compile(regex); 
	    Matcher isNum = pattern.matcher(orginal); 
	    return isNum.matches(); 
	} 

    /**
     * traverseFolder:遍历文件夹,返回文件夹下面所有文件的路径
     * @param ier
     * @return
     * @throws IOException 
     */
	public static List<String> traverseFolder(String path){
		List<String> fileAddress = new ArrayList<String>();
		File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        fileAddress.addAll(traverseFolder(file2.getAbsolutePath()));
                    } else {
                    	fileAddress.add(file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        if(CollectionUtils.isNotEmpty(fileAddress)){
    		List<String> xlsArr = new ArrayList<String>();
        	for(String str : fileAddress){
        		if(StringUtils.isNotBlank(str) 
        			&& (str.substring(str.lastIndexOf(".")).equals(excel2003L)
        				|| str.substring(str.lastIndexOf(".")).equals(excel2007U))){
        			xlsArr.add(str);
        		}
        	}
        	return xlsArr;
        }
		return fileAddress;
	}
    /**
     * analysisZipFile:解析zip压缩包,返回里面所有excel文件地址
     * @param ier
     * @return
     * @throws IOException 
     */
	public static List<String> analysisZipFile(File file,String descDir) throws IOException{
		List<String> fileAddress = new ArrayList<String>();
		File dir = new File(descDir);
		if(!dir .exists() && !dir .isDirectory()){       
			dir .mkdir();//如果文件夹不存在,则创建    
		} 
		ZipUtil.unZipFiles(file,descDir.substring(0,descDir.lastIndexOf("/"))+"/");//解压到指定文件夹
		fileAddress = traverseFolder(descDir.substring(0,descDir.lastIndexOf(".")));//遍历文件夹,返回文件夹下面所有文件的路径
		return fileAddress;
	}
	
    /**
     * firstSetSessionAttr:第一次设置session内容
     * @param code
     * @param str
     * @return
     */
	public static void firstSetSessionAttr(String code,String str){
		Session session = SecurityUtils.getSubject().getSession();
    	session.setAttribute("import"+code+"Str", str);
	}

    /**
     * setSessionAttr:设置session内容
     * @param code
     * @param str
     * @return
     */
	public static void setSessionAttr(String code,String str){
		Session session = SecurityUtils.getSubject().getSession();
		String importStr = String.valueOf(session.getAttribute("import"+code+"Str"));
		str = importStr + "----" + str;
    	session.setAttribute("import"+code+"Str", str);
	}
    /**
     * getExcel:解析Excel 以每一个sheet名称做为键,值就是当前sheet内容
     * @param in
     * @param fileName
     * @return
     */
    public static Map<String,List<List<Object>>> getExcel(InputStream in,String fileName){
    	Map<String,List<List<Object>>> sheetList = new HashMap<String,List<List<Object>>>();//所有sheet的数据
    	// 创建Excel工作薄
		Workbook work = null;
		try {
			work = ImportExcelUtil.getWorkbook(in, fileName);//获得Excel的数据
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			//遍历Excel中所有的sheet
			for (int i = 0; i < work.getNumberOfSheets(); i++) {
				List<List<Object>> dataList = new ArrayList<List<Object>>();
				sheet = work.getSheetAt(i);
				if (sheet == null) {
					continue;
				}
				String sheetName = sheet.getSheetName();
				// 遍历当前sheet中的所有行
				for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
					row = sheet.getRow(j);
					if (row == null) {
						continue;
					}
					// 遍历所有的列
					List<Object> li = new ArrayList<Object>();
					for (int y = 0; y < row.getLastCellNum(); y++) {
						cell = row.getCell(y);
						li.add(ImportExcelUtil.getCellValue(cell));//对表格中数值进行格式化
					}
					for(Object object : li){
						if(null == object){
							object = "";
						}
					}
					if(j != sheet.getFirstRowNum() && li.size() < dataList.get(sheet.getFirstRowNum()).size()){
						for(int ii = li.size(); ii < dataList.get(sheet.getFirstRowNum()).size(); ii++){
							li.add("");
						}
					}
					
					dataList.add(li);
				}
		        sheetList.put(sheetName, dataList);
			}
			work.close();
		} catch (Exception e1) {
			e1.printStackTrace();
            throw new BusinessException("Excel解析错误!");
		}
		return sheetList;
    }
}

