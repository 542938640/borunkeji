package com.taro.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.taro.constants.Constant;
import org.apache.commons.lang3.StringUtils;

/**
* @Package: com.taro.tdevice.common
* @File: Message.java
* @Description: 消息返回对象
* @Author: tanquanlong
* @Date: 2016年12月9日
* @Copyright taro(c)2010-2016
*/

public class Message {
	
	//状态标识0为成功
	protected int status= Constant.STATUS_OK;
	
	//错误消息
	protected String msg; 

	//错误堆栈信息
	protected String errorMsg;

    public Message() {
    }
    
	public Message(int status) {
		this.status=status;
	}
	
	public Message(int status,String msg) {
		this.status=status;
		this.msg=msg;
	}

	public Message(int status,String msg,String errorMsg){
		this.status=status;
		this.msg=msg;
		this.errorMsg=errorMsg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String toJson(){
		String json = JSON.toJSONString(this,new SerializeConfig(),SerializerFeature.DisableCircularReferenceDetect);
		return json;
	}
	
	public String toJson(PropertyPreFilter filter){
		if (filter==null){
			return toJson();
		}
		String json = JSON.toJSONString(this,new SerializeConfig(),filter);
		return json;
	}
	
	public String toJson(String jsonpCallback){
		if (StringUtils.isBlank(jsonpCallback)){
			return toJson();
		}
		return String.format(Constant.FORMAT, jsonpCallback, toJson());
	}
	
	public String toJson(String jsonpCallback,PropertyPreFilter filter){
		if (StringUtils.isBlank(jsonpCallback)){
			return toJson();
		}
		return String.format(Constant.FORMAT, jsonpCallback, toJson(filter));
	}

	@Override
	public String toString() {
		return toJson();
	}
	
	/**
	 * convertStr:(HTML脚本转义   &、<、>、"、'、(、)、%、+、\). <br/>
	 * 目前只转义了script标签
	 * @author gavin
	 * @param str
	 * @return
	 */
	/*private String convertStr(String str) {
        return str.replace("<script>", "&lt;script&gt;")
            .replace("</script>", "&lt;/script&gt;")
            .replace("(", "&#40;")
            .replace(")", "&#41;");
        
		return str.replace("&", "&amp;")
		        .replace("<", "&lt;")
		        .replace(">", "&gt;")
		        .replace("\"", "&quot;")
		        .replace("'", "&#x27;")
		        .replace("(", "&#40;")
		        .replace(")", "&#41;")
		        .replace("%", "&#37;")
		        .replace("+", "&#43;")
		        .replace("\\", "&#92;");
    }*/
}