package com.taro.service.pub.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taro.dao.pub.PubExportConfigDao;
import com.taro.entity.pub.PubExportConfigEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.pub.PubExportConfigService;
import com.taro.utils.ApacheHttpUtil;

/**
 *<p>Title:ExportCofigServiceImpl.java</p>
 *<p>Description:公共导出配置表ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-08-16 15:33
 */
@Service
public class PubExportConfigServiceImpl extends AbstractService<PubExportConfigEntity> implements PubExportConfigService{
	@Autowired
	private PubExportConfigDao pubExportCofigDao;

	@Value("${server.port}")
	private String port;
	
	@Value("${server.servlet.context-path}")
	private String context_path;
	
    @Override
    protected PubExportConfigDao getDao () {
        return pubExportCofigDao;
    }
    
    //设置合并单元格样式
    private static void setRegionStyle(HSSFSheet sheet, CellRangeAddress region, HSSFCellStyle cs) {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            HSSFCell cell = null;
            //循环设置单元格样式
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                cell = HSSFCellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }

    //获取表头单元格样式
    private HSSFCellStyle getHeaderStyle(HSSFWorkbook wb) {
        //生成字体对象  
        HSSFFont font = wb.createFont();  
        font.setFontHeightInPoints((short) 14);  
        font.setFontName("新宋体");
        font.setBold(true);
        //生成样式对象，这里的设置居中样式和版本有关，我用的poi用HSSFCellStyle.ALIGN_CENTER会报错，所以用下面的
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font); // 调用字体样式对象  
        style.setWrapText(true);  
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
        style.setAlignment(HorizontalAlignment.CENTER);//设置居中样式
        // 背景色
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
	    // 设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        return style;
    }

    //获取表头单元格样式
    private HSSFCellStyle getDataStyle(HSSFWorkbook wb) {
        //生成字体对象  
        HSSFFont font = wb.createFont();  
        font.setFontHeightInPoints((short) 12);  
        font.setFontName("新宋体");
        //生成样式对象，这里的设置居中样式和版本有关，我用的poi用HSSFCellStyle.ALIGN_CENTER会报错，所以用下面的
        HSSFCellStyle style = wb.createCellStyle();
        style.setFont(font); // 调用字体样式对象  
        style.setWrapText(true);  
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//水平居中
        style.setAlignment(HorizontalAlignment.CENTER);//设置居中样式
	    // 设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
        return style;
    }
    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportTypeCode, 
    		String fileName, String sheetName, String isQueryData, Map<String, String> paramete) {
		if(StringUtils.isBlank(exportTypeCode)){
            throw new BusinessException("请传入类型参数!");
		}
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("code", exportTypeCode);
		queryMap.put("sysFlag", "1");
		List<PubExportConfigEntity> configList = getDao().list(queryMap);
		if(CollectionUtils.isEmpty(configList)) {
            throw new BusinessException("类型参数查询失败!");
		}
		
		PubExportConfigEntity config = configList.get(0);//即使查出多个也只取第一个
		
		fileName = StringUtils.isBlank(fileName) ? config.getName() : fileName;
		sheetName = StringUtils.isBlank(sheetName) ? "sheet" : sheetName;
		
		String column_list = config.getColumn_list();
		if(StringUtils.isBlank(column_list)) {
            throw new BusinessException("请配置表头信息!");
		}
		column_list = "[" + column_list + "]";
		
		JSONArray columnArr = JSON.parseArray(column_list);
		if(null == columnArr) {
            throw new BusinessException("表头信息配置错误!");
		}
		
		//创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet(sheetName);
        //在sheet里创建行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row = null;
        int rowNum = 0;
        int rowspan = 0;
        Map<Integer, Integer> rowsMap = new HashMap<Integer, Integer>();
        //创建excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = null;
        int cellNum = 0;
        int colspan = 0;
        CellRangeAddress region = null;
        //获取表头样式
        HSSFCellStyle headerStyle = getHeaderStyle(wb);
        
        Map<Integer, JSONObject> fieldMap = new HashMap<Integer, JSONObject>();
        JSONObject headerColumn = null;
        //循环表头
        for(Iterator i = columnArr.iterator(); i.hasNext();) {
        	row = sheet.createRow(rowNum);
        	row.setHeight((short)500);
        	JSONArray column = (JSONArray)i.next();
        	cellNum = 0;
        	for(Iterator it = column.iterator(); it.hasNext();) {
        		if(rowsMap.containsKey(cellNum) && rowNum == rowsMap.get(cellNum)) {
                    cellNum++;
        			continue;
        		}
            	JSONObject columnObj = (JSONObject)it.next();
            	fieldMap.put(cellNum, columnObj);
        		cell = row.createCell(cellNum);
                sheet.setColumnWidth(cellNum, columnObj.getString("column").getBytes().length * 2 * 256);
        		//单元格应用样式
                cell.setCellStyle(headerStyle);
                //设置单元格内容
                cell.setCellValue(columnObj.getString("column"));
                //处理合并单元格
                rowspan = columnObj.getIntValue("rowspan") == 0 ? 1 : columnObj.getIntValue("rowspan");
                colspan = columnObj.getIntValue("colspan") == 0 ? 1 : columnObj.getIntValue("colspan");
                if(rowspan > 1 || colspan > 1) {
                	if(rowspan > 1) {
                		for(int ii = 1; ii < rowspan; ii++) {
                        	rowsMap.put(cellNum, rowNum + ii);
                		}
                	}
                	region = new CellRangeAddress(rowNum, rowNum + --rowspan, cellNum, cellNum + --colspan);
                    //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
                    sheet.addMergedRegion(region);
                    setRegionStyle(sheet, region, headerStyle);
                    cellNum = cellNum + colspan;
                }
                cellNum++;
        	}
        	rowNum++;
        }

        //是否查询数据
        if(StringUtils.isBlank(isQueryData) || "1".equals(isQueryData)) {
            //获取数据样式
            HSSFCellStyle dataStyle = getDataStyle(wb);
            //处理数据
            String query_interface = config.getQuery_interface();
            if(StringUtils.isNotBlank(query_interface)) {
            	String query_method = config.getQuery_method();
            	
            	InetAddress address = null;
                try {
                    address = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                String url = "http://"+address.getHostAddress() +":" + port + context_path + query_interface;
                paramete.put("rows", "9999");
                
                String cookie = request.getHeader("Cookie");
                
                String data = "";
                if(StringUtils.isBlank(query_method) || query_method.equals(query_method)) {
        			data = ApacheHttpUtil.get(url, paramete, cookie);
                }else {
        			data = ApacheHttpUtil.post(url, paramete, "UTF-8", cookie);
                }
    			if(StringUtils.isNotBlank(data)) {
    				JSONObject jsonData = JSON.parseObject(data);
    				if(jsonData.containsKey("status") && "1".equals(jsonData.getString("status")) && jsonData.containsKey("content")) {
    					JSONArray dataList = jsonData.getJSONArray("content");
    					if(null != dataList) {
    						for(Iterator i = dataList.iterator(); i.hasNext();) {
    				        	row = sheet.createRow(rowNum);
    				        	JSONObject column = (JSONObject)i.next();
    				        	for(Integer num : fieldMap.keySet()) {
    				        		cell = row.createCell(num);
    				        		headerColumn = fieldMap.get(num);
    				        		//单元格应用样式
    				                cell.setCellStyle(dataStyle);
    				                cell.setCellValue(column.getString(headerColumn.getString("field")));

    				                if(headerColumn.containsKey("colspan")) {
    				                	colspan = headerColumn.getIntValue("colspan");
    				                	if(colspan != 1) {
    				                		region = new CellRangeAddress(rowNum, rowNum, num,  num + colspan - 1);
    					                    sheet.addMergedRegion(region);
    					                    setRegionStyle(sheet, region, dataStyle);
    				                	}
    				                }
    				        	}
    				        	rowNum++;
    						}
    					}
    				}
    			}
            }
        }
        
        //输出Excel文件
        OutputStream output = null;
		try {
			output = response.getOutputStream();
	        response.reset();
	        response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName + ".xlsx", "UTF-8"));//文件名这里可以改
	        response.setContentType("application/octet-stream; charset=utf-8");
	        wb.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public PubExportConfigEntity saveExportConfig (PubExportConfigEntity model) {

    	Map<String, Object> queryMap = new HashMap<String, Object>();
    	queryMap.put("not_id", model.getId());
    	queryMap.put("sysFlag", "1");
    	queryMap.put("codeEq", model.getCode());
    	List<PubExportConfigEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
            throw new BusinessException("编码已存在,请重新编辑!");
    	}
    	
    	if(StringUtils.isBlank(model.getId())) {
    		super.save(model);
    	}else {
    		super.update(model);
    	}
    	
        return model;
    }

}