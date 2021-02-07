package com.taro.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import com.taro.common.DataSetObject;
import com.taro.common.Message;
import com.taro.constants.Constant;

/**
 * 公共接口控制器，此控制器一般写跟业务无关的公共接口
 * 
 * @author zouxixi
 */
@Controller
public class CommonController {

	private static List<Map<String, String>> dataSource = null;

	/**
	 * 获取spring配置文件中包含dataSource的bean。此方法不需要登录也可以访问。
	 */
	@RequestMapping(value = "/getDataSources", produces = Constant.JSON_UTF_8)
	@ResponseBody
	public String getDataSources(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (dataSource != null) {
			return new DataSetObject<List<Map<String, String>>>(dataSource).toJson();
		}
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		try {
			String beanId = null;
			String beanName = null;
			Map<String, String> datasourceMap = null;
			List<Node> dataSourceNodes = getDataSourceElements();
			for (Node n : dataSourceNodes) {
				beanId = n.getAttributes().getNamedItem("id") == null ? ""
						: n.getAttributes().getNamedItem("id").getNodeValue();
				beanName = n.getAttributes().getNamedItem("name") == null ? ""
						: n.getAttributes().getNamedItem("name").getNodeValue();
				if (StringUtils.isNotBlank(beanId) && beanId.contains("dataSource") && !"dataSource".equals(beanId)) {
					datasourceMap = new HashMap<String, String>();
					datasourceMap.put("id", beanId);
					datasourceMap.put("name", beanName);
					result.add(datasourceMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Constant.STATUS_ERROR_GENERAL, e.getMessage()).toJson();
		}
		dataSource = result;
		return new DataSetObject<List<Map<String, String>>>(result).toJson();
	}

	/**
	 * java原生api解析xml
	 */
	public static List<Node> getDataSourceElements() throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream resource = CommonController.class.getResourceAsStream("/applicationContext-dataSource.xml");
		Document document = db.parse(resource);
		Element root = document.getDocumentElement();
		NodeList nl = root.getElementsByTagName("beans");
		List<Node> list = new ArrayList<Node>();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			NodeList childList = n.getChildNodes();
			for (int x = 0; x < childList.getLength(); x++) {
				Node childNode = childList.item(x);
				if (childNode instanceof DeferredElementImpl) {
					if (childNode.getAttributes() == null || childNode.getAttributes().getLength() == 0
							|| childNode.getAttributes().getNamedItem("class") == null) {
						continue;
					}
					String nodeClassName = childNode.getAttributes().getNamedItem("class").getNodeValue();
					if (!"com.alibaba.druid.pool.DruidDataSource".equals(nodeClassName)) {
						continue;
					}
					list.add(childNode);
				}
			}
		}
		return list;
	}

}
