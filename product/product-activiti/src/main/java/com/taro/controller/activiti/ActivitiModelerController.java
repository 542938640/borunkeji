package com.taro.controller.activiti;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.taro.common.Message;
import com.taro.common.Page;
import com.taro.constants.Constant;
import com.taro.controller.AbstractController;
import com.taro.entity.AbstractEntity;
import com.taro.service.AbstractService;

/** 
 * Activiti模型接口 
 * @author  张宇唯
 * @date  2019-01-14
 */  
@RestController
@RequestMapping("/activitiModeler")
public class ActivitiModelerController extends AbstractController<AbstractEntity>{

	public final static String MODULE = "Activiti模型";

	public final static String ENTITY = "AbstractEntity";
	
    @Autowired
    RepositoryService repositoryService;
    
    @Autowired
    ObjectMapper objectMapper;
    
	@Override
	protected AbstractService<AbstractEntity> getService() {
		return null;
	} 

	@Override
    public String getModule() {
		return MODULE;
	}

    /**
     * 新建一个空模型
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping("createModel")
    public String createModel(@RequestParam Map<String, Object> parameter) throws UnsupportedEncodingException {
        //初始化一个空模型
        Model model = repositoryService.newModel();

        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        
        return new Message(Constant.STATUS_OK,"/editor?modelId="+id).toJson();
    }

    /**
     * 发布模型为流程定义
     * @param id
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/deployment", method = RequestMethod.GET, produces = Constant.JSON_UTF_8)
    public String deployment(@RequestParam String id) throws Exception {

        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

        if (bytes == null) {
            return new Message(Constant.STATUS_ERROR_GENERAL,"模型数据为空，请先设计流程并成功保存，再进行发布!").toJson();
        }

        JsonNode modelNode = new ObjectMapper().readTree(bytes);

        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size()==0){
            return new Message(Constant.STATUS_ERROR_GENERAL, "数据模型不符要求，请至少设计一条主线流程!").toJson();
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return new Message(Constant.STATUS_OK,"部署成功!").toJson();
    }

	@RequestMapping(value = "/list",produces = Constant.JSON_UTF_8)
	public String list(@RequestParam(value = Constant.PAGE_NUM, defaultValue = Constant.PAGE_DEFAULT_VALUE) int pageNum,
			@RequestParam(value = Constant.PAGE_SIZE, defaultValue = Constant.PAGE_SIZE_DEFAULT_VALUE) int pageSize,
			@RequestParam Map<String, Object> parameter){

		listBefore(parameter);
		
		long count = repositoryService.createModelQuery().count();
		
		List<Model> list = repositoryService.createModelQuery().orderByCreateTime().desc().listPage(pageSize * (pageNum - 1), pageSize);
		
		Page<Model> result = new Page<>(list);
		
		result.setTotal(count);

		return result.toJson(ignoreAttr.get(Constant.IGNORE_ATTR_LIST));
	}

	@RequestMapping(value = "/deleteModel", method = RequestMethod.GET, produces = Constant.JSON_UTF_8)
	public String deleteModel(@RequestParam String ids) {
		if(StringUtils.isBlank(ids)) {
            return new Message(Constant.STATUS_ERROR_GENERAL,"请传入id!").toJson();
		}
		String[] idArr = ids.split(",");
		if(null != idArr) {
			for(String id : idArr) {
		        repositoryService.deleteModel(id);
			}
		}
		return new Message(Constant.STATUS_OK,"删除成功!").toJson();
	}

}
