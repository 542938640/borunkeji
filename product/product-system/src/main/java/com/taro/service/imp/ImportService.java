package com.taro.service.imp;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taro.entity.imp.ImportEntity;


/**
 * ClassName:ImportService公共导入 <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2017年12月13日 上午9:47:57 <br/>
 * @author   张宇唯
 * @since    JDK 1.6
 */

public interface ImportService{
    ImportEntity importExcel (InputStream in, String fileName, Map<String, Object> paramete);
    void downloadTemplate (HttpServletRequest request, HttpServletResponse response, Map<String, Object> paramete);
}
