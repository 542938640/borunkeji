package com.taro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.servlet.http.Cookie;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;  

/**
 * ClassName: ApacheHttpUtil <br/>
 * Function: apche httpclient请求相关工具. <br/>
 * date: 2017年7月26日 上午11:16:15 <br/>
 * @author gavin
 * @version
 */
public class ApacheHttpUtil {
	/**
	 * ssl:(HttpClient连接SSL ). <br/>
	 * @author gavin
	 */
	public static void ssl() {  
		CloseableHttpClient httpclient = null;  
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
			FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));  // 加载keyStore d:\\tomcat.keystore 
			try {  
				trustStore.load(instream, "123456".toCharArray());  
			} catch (CertificateException e) {  
				e.printStackTrace();  
			} finally {  
				try {  
					instream.close();  
				} catch (Exception ignore) {  
				}  
			}  
			// 相信自己的CA和所有自签名的证书  
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();  
			// 只允许使用TLSv1协议  
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
																			SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
			// 创建http请求(get方式)  
			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");  
			System.out.println("executing request" + httpget.getRequestLine());  
			CloseableHttpResponse response = httpclient.execute(httpget);  
			try {  
				HttpEntity entity = response.getEntity();  
				System.out.println(response.getStatusLine());  
				if (entity != null) {  
					EntityUtils.consume(entity);  
				}  
			} finally {  
				response.close();  
			}  
		} catch (ParseException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} catch (KeyManagementException e) {  
			e.printStackTrace();  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
		} catch (KeyStoreException e) {  
			e.printStackTrace();  
		} finally {  
			if (httpclient != null) {  
				try {  
					httpclient.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
	}  
	/**
	 * post方式请求
	 */
	public static void postForm(String url,Map<String, String> paramMap) {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if(null != paramMap){
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			for (Entry<String, String> entry : entrySet){
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}else{
			
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					//System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送 post请求访问应用并根据传递参数不同返回不同结果  
	 * 这个是原来方法 保持一致 让它还存在
	 */
	public static String post(String url,Map<String, String> paramMap) {
		return post(url, paramMap, "UTF-8");
	}

	public static String post(String url,Map<String, String> paramMap,String encodStr) {
		return post(url, paramMap, encodStr, null);
	}
	/**
	 * post请求数据
	 * @param url  请求url
	 * @param paramMap 请求参数
	 * @param encodStr 请求编码，参数编码，默认UTF-8
	 * @return
	 */
	public static String post(String url,Map<String, String> paramMap,String encodStr, String cookie) {
		String response_content = "";
		if(encodStr == null || "".equals(encodStr)){
			encodStr = "UTF-8";
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if(null != paramMap){
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			for (Entry<String, String> entry : entrySet){
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}else{
			
		}
		UrlEncodedFormEntity uefEntity;
		CloseableHttpResponse response = null;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, encodStr);
			httppost.setEntity(uefEntity);
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				response_content = EntityUtils.toString(entity, encodStr);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response_content;
	}
	public static String get(String url, Map<String, String> paramMap) {
		return get(url, paramMap, null);
	}
	/**
	 * 发送 get请求
	 */
	public static String get(String url, Map<String, String> paramMap, String cookie) {
		String response_content = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.  
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			if(null != paramMap){
				Set<Entry<String, String>> entrySet = paramMap.entrySet();
				for (Entry<String, String> entry : entrySet){
					params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			URI uri = new URI( url + "?" + URLEncodedUtils.format( params, "utf-8" ));
			HttpGet httpget = new HttpGet(uri);
			if(StringUtils.isNotBlank(cookie)) {
				httpget.setHeader("Cookie", cookie);
			}
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					response_content = EntityUtils.toString(entity);
					System.out.println("Response content: " + response_content);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response_content;
	}

	/**
	 * 上传文件
	 */
	public static String upload(String url,File file,String fileParamName,Map<String, String> paramMap) {
		String response_content = "";
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		try {
			HttpPost httppost = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
	        httppost.setConfig(requestConfig);
			
			FileBody fileBody = new FileBody(file);
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().addPart(fileParamName, fileBody);
			
			for(String key : paramMap.keySet()) {
				StringBody param = new StringBody(paramMap.get(key),ContentType.TEXT_PLAIN);
				multipartEntityBuilder.addPart(key, param);
			}
			
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httppost.setEntity(reqEntity);
			
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
					response_content = EntityUtils.toString(resEntity,"UTF-8");
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response_content;
	}
}
