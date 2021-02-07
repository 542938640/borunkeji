package com.taro.service.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taro.common.DataSet;
import com.taro.dao.file.FileManageDao;
import com.taro.entity.file.FileManageEntity;
import com.taro.exception.BusinessException;
import com.taro.service.AbstractService;
import com.taro.service.file.FileManageService;
import com.taro.utils.MyStringUtil;
import com.taro.utils.UuidUtil;

/**
 *<p>Title:FileManageServiceImpl.java</p>
 *<p>Description:文件管理ServiceImpl</p>
 *@author 张宇唯
 *@version 1.0
 *@Automatically generate by Coder in 2019-07-31 10:57
 */
@Service
public class FileManageServiceImpl extends AbstractService<FileManageEntity> implements FileManageService{
	@Autowired
	private FileManageDao FileManageDao;
	
    @Override
    protected FileManageDao getDao () {
        return FileManageDao;
    }
    
    @Value("${fileManage.uploadPath}")
    private String uploadPath;
	
	@Override
	public void viewImage(HttpServletResponse response, String id) {
		if(MyStringUtil.isNotBlank(id)) {
			FileManageEntity model = getDao().get(id);
			if(null == model) {
				Map<String, Object> queryMap = Maps.newHashMap();
				queryMap.put("sysFlag", "1");
				queryMap.put("busi_key", id);
				List<FileManageEntity> queryList = super.list(queryMap);
				if(CollectionUtils.isNotEmpty(queryList)) {
					model = queryList.get(0);
				}
			}
			if(null != model && MyStringUtil.isNotBlank(model.getFile_path())) {
				response.setContentType("text/html;charset=UTF-8");
				try(InputStream in = getInputStream(model);
						OutputStream out = response.getOutputStream();){
					if(null != in) {
						// 把图片的输入流程写入resp的输出流中
			            byte[] b = new byte[1024];
			            for (int len = -1; (len= in.read(b))!=-1; ) {
			                out.write(b, 0, len);
			            }
					}
				}catch(IOException e) {
		            e.printStackTrace();
				}
			}
		}
	}
	
	private InputStream getInputStream(FileManageEntity model) {
		if(null == model) {
			return null;
		}
		File file = new File(uploadPath + "/upload/" + model.getFile_path() + "/" + model.getFile_savename() + model.getFile_extname());

		if(!file.exists()) {
			return null;
		}
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new BusinessException("系统错误!");
		}
	}
	
	private void downloadFile(HttpServletResponse response, FileManageEntity model, String fileName) {
    	if(null != model) {
    		try(ServletOutputStream sos = response.getOutputStream();
    				InputStream is = getInputStream(model);) {
    			if(null != is) {
        			response.setCharacterEncoding("UTF-8");
        			response.setContentType("application/octet-stream; charset=UTF-8");
        			if(MyStringUtil.isBlank(fileName)) {
            			fileName = model.getFile_name();
        			}
        			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        			IOUtils.copy(is, sos);
        			sos.flush();
    			}
    		} catch (Exception e) {
    			throw new BusinessException("下载出错!");
    		}
    	}
	}
	
	private void downloadZip(HttpServletResponse response, List<FileManageEntity> list, String fileName) {
        try(ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			if(MyStringUtil.isBlank(fileName)) {
				fileName = new Date().getTime() + ".zip";
			}
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			for(FileManageEntity model : list) {
				zipOut.putNextEntry(new ZipEntry(model.getFile_name()));
				try(InputStream is = getInputStream(model);) {
        			IOUtils.copy(is, zipOut);
        			zipOut.flush();
				}
			}
			zipOut.flush();
		} catch (IOException e) {
			throw new BusinessException("下载Zip出错!");
		}
	}
	
	@Override
	public void download(HttpServletResponse response, Map<String, Object> param) {
		if(null == param || (!param.containsKey("id") && !param.containsKey("busi_key"))) {
			throw new BusinessException("参数错误！");
		}
		String id = String.valueOf(param.get("id"));
		String busi_key = String.valueOf(param.get("busi_key"));
		String file_name = String.valueOf(param.get("file_name"));
		String type = String.valueOf(param.get("type"));
		Map<String, Object> queryParam = new HashMap<>();
		if(MyStringUtil.isNotBlank(id)) {
			queryParam.put("id", id);
		}
		if(MyStringUtil.isNotBlank(busi_key)){
			queryParam.put("busi_key", busi_key);
		}
		queryParam.put("sysFlag", "1");
		queryParam.put("busi_flag", "1");
		List<FileManageEntity> queryList = getDao().list(queryParam);
		if(CollectionUtils.isEmpty(queryList)) {
			return;
		}
		if(queryList.size() == 1 && !"zip".equals(type)) {
			downloadFile(response, queryList.get(0), file_name);
		}else {
			downloadZip(response, queryList, file_name);
		}
	}
	
    @Override
    public List<FileManageEntity> queryFile (Map<String, Object> parameter) {
    	if(null == parameter || !parameter.containsKey("busi_key")) {
    		return null;
    	}
    	
    	//先删除上次未保存的文件
    	String busi_key = String.valueOf(parameter.get("busi_key"));
    	Map<String, Object> queryMap = new HashMap<>();
    	queryMap.put("busi_key", busi_key);
    	queryMap.put("sysFlag", "1");
    	queryMap.put("busi_flag", "0");
    	List<FileManageEntity> queryList = getDao().list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
    		String ids = "";
    		for(FileManageEntity fileManage : queryList) {
    			if(StringUtils.isNotBlank(ids)) {
    				ids += ",";
    			}
    			ids += fileManage.getId();
    		}
//    		this.deleteFile(ids);
    	}
    	
        return getDao().list(parameter);
    }

    @Override
    public void activation(String busi_key) {
    	FileManageEntity updateBean = new FileManageEntity();
    	updateBean.setBusi_key(busi_key);
    	updateBean.setBusi_flag("1");
    	getDao().updateByBusiKey(updateBean);
    }

    @Override
    public int deleteFile (String ids) {
    	
    	if(StringUtils.isBlank(ids)) {
    		return 0;
    	}
    	
    	String[] fileIds = ids.split(",");
    	if(null != fileIds) {        	
        	FileManageEntity fileManage = null;
			File file = null;
    		for(String id : fileIds) {
    			fileManage = getDao().get(id);
    			if(null != fileManage) {
    				//删除文件
    				file = new File(uploadPath + "/upload/" + fileManage.getFile_path() + "/" + fileManage.getFile_savename() + fileManage.getFile_extname());
    				if(file.exists() && file.isFile()) {
    					file.delete();
    				}
    			}
    		}
    	}
    	
        return super.deleteAll(ids);
    }
    
    @Override
    public int deleteFileByBusiKey (String busi_key) {
    	if(StringUtils.isBlank(busi_key)) {
    		return 0;
    	}

    	String ids = "";
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("busi_key", busi_key);
    	queryMap.put("sysFlag", "1");
    	List<FileManageEntity> queryList = super.list(queryMap);
    	if(CollectionUtils.isNotEmpty(queryList)) {
    		for(FileManageEntity file : queryList) {
    			if(MyStringUtil.isNotBlank(ids)) {
    				ids += ",";
    			}
    			ids += file.getId();
    		}
    	}
    	
    	return deleteFile(ids);
    }

    @Override
    public String uploadFile(MultipartFile[] files, FileManageEntity fileParam) {
		List<FileManageEntity> list = Lists.newArrayList();
		
		if(null == files || files.length == 0){
			throw new BusinessException("没有上传文件！");
		}else if(files.length > 9){
			throw new BusinessException("上传文件数量超过9个！");
		}
		
		if(null == fileParam) {
			throw new BusinessException("上传数据错误！");
		}
		if(StringUtils.isBlank(fileParam.getType())) {
			fileParam.setType("public");
		}
		if(StringUtils.isBlank(fileParam.getBusi_key())) {
			fileParam.setBusi_key(UuidUtil.get32UUID());
		}
		if(StringUtils.isBlank(fileParam.getBusi_flag())) {
			fileParam.setBusi_flag("0");
		}
		
		// 当天日期
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		File localFile = null;
		FileManageEntity saveBean = null;
		for(MultipartFile file : files) {
			if(file.isEmpty()) {
				continue;
			}
			// 文件名称
			String fileName = file.getOriginalFilename();
			if(fileName.indexOf(".") == -1) {
				throw new BusinessException("[" + fileName + "]文件格式错误，上传失败！");
			}
			// 文件后缀名
			String fileExtName = fileName.substring(fileName.lastIndexOf("."));
			
			// 文件保存名称
			String fileSaveName = UuidUtil.get32UUID();
			
			// 文件保存路径
			String filePath = fileParam.getType();
			switch (fileExtName) {
				case ".jpg":
				case ".png":
				case ".gif":
				case ".jpeg":
				case ".bmp":
					filePath += "/photo";
					break;
				case ".docx":
				case ".xlsx":
				case ".pptx":
				case ".pdf":
				case ".txt":
				case ".doc":
				case ".xls":
				case ".ppt":
					filePath += "/file";
					break;
				default:
					filePath += "/other";
					break;
			}
			filePath += "/" + dateStr;

			//生成服务器保存路径
			localFile = new File(uploadPath + "/upload/" + filePath + "/" + fileSaveName + fileExtName);
			
			//复制文件至服务器
			if(!localFile.getParentFile().exists()){
				localFile.getParentFile().mkdirs();//创建父级文件路径
			}
			try {
				localFile.createNewFile();//创建文件
				file.transferTo(localFile);//复制上传文件
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 保存数据
			saveBean = new FileManageEntity();
			saveBean.setType(fileParam.getType());
			saveBean.setBusi_key(fileParam.getBusi_key());
			saveBean.setBusi_type(fileParam.getBusi_type());
			saveBean.setBusi_flag(fileParam.getBusi_flag());
			saveBean.setFile_name(fileName);
			saveBean.setFile_extname(fileExtName);
			saveBean.setFile_savename(fileSaveName);
			saveBean.setFile_path(filePath);
			saveBean.setFile_size(new BigDecimal(file.getSize()).divide(new BigDecimal(1024)).intValue() + " KB");//文件大小
			super.save(saveBean);
			list.add(saveBean);
		}
		
		return new DataSet<FileManageEntity>(list).toJson();
    }

    @Override
    public FileManageEntity copyFileByBusiKey(Map<String, Object> param) {
    	if(null == param
    			|| !param.containsKey("busi_key")
    			|| !param.containsKey("copy_key")) {
			throw new BusinessException("参数错误！");
    	}
    	String busi_key = String.valueOf(param.get("busi_key"));
    	String copy_key = String.valueOf(param.get("copy_key"));
    	
    	Map<String, Object> queryMap = Maps.newHashMap();
    	queryMap.put("sysFlag", "1");
    	queryMap.put("busi_flag", "1");
    	queryMap.put("busi_key", copy_key);
    	List<FileManageEntity> queryList = super.list(queryMap);
    	if(CollectionUtils.isEmpty(queryList)) {
			throw new BusinessException("copy_key查询为空！");
    	}
    	
    	// 删除现有数据
    	this.deleteFileByBusiKey(busi_key);
    	
    	FileManageEntity copyModel = queryList.get(0);
    	FileManageEntity model = new FileManageEntity();
    	
    	File copyFile = new File(uploadPath + "/upload/" + copyModel.getFile_path() + "/" + copyModel.getFile_savename() + copyModel.getFile_extname());
    
    	if(copyFile.exists()) {
    		// 文件保存名称
    		String fileSaveName = UuidUtil.get32UUID();
        	
        	model.setType(copyModel.getType());
        	model.setBusi_key(busi_key);
        	model.setBusi_type(copyModel.getBusi_type());
        	model.setBusi_flag("0");
        	model.setFile_name(copyModel.getFile_name());
        	model.setFile_extname(copyModel.getFile_extname());
        	model.setFile_savename(fileSaveName);
        	model.setFile_path(copyModel.getFile_path());
        	model.setFile_size(copyModel.getFile_size());
        	
        	File file = new File(uploadPath + "/upload/" + model.getFile_path() + "/" + model.getFile_savename() + model.getFile_extname());
        	try {
				FileUtils.copyFile(copyFile, file);
			} catch (IOException e) {
				throw new BusinessException("copy_key查询为空！");
			}

        	super.save(model);
    	}
    	
    	return model;
    }
    
}