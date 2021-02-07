/*     */ package com.taro.activiti;
/*     */ 
/*     */ import com.fasterxml.jackson.core.JsonProcessingException;
/*     */ import com.fasterxml.jackson.databind.ObjectMapper;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.activiti.engine.HistoryService;
/*     */ import org.activiti.engine.IdentityService;
/*     */ import org.activiti.engine.ProcessEngine;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.RuntimeService;
/*     */ import org.activiti.engine.TaskService;
/*     */ import org.activiti.engine.history.HistoricActivityInstance;
/*     */ import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
/*     */ import org.activiti.engine.impl.identity.Authentication;
/*     */ import org.activiti.engine.impl.javax.el.ExpressionFactory;
/*     */ import org.activiti.engine.impl.javax.el.ValueExpression;
/*     */ import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
/*     */ import org.activiti.engine.impl.juel.SimpleContext;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.PvmActivity;
/*     */ import org.activiti.engine.impl.pvm.PvmTransition;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.impl.task.TaskDefinition;
/*     */ import org.activiti.engine.runtime.Execution;
/*     */ import org.activiti.engine.runtime.ExecutionQuery;
import org.apache.commons.lang3.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public class ActivitiUtil
/*     */ {
/*     */   @Autowired
/*     */   private ProcessEngine processEngine;
/*     */   @Autowired
/*     */   private TaskService taskService;
/*     */   @Autowired
/*     */   private RuntimeService runtimeService;
/*     */   @Autowired
/*     */   private RepositoryService repositoryService;
/*     */   @Autowired
/*     */   private HistoryService historyService;
/*     */   @Autowired
/*     */   private IdentityService identityService;
/*     */   
/*     */   public List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances)
/*     */   {
/*  70 */     List<String> highFlows = new ArrayList();
/*  71 */     for (int i = 0; i < historicActivityInstances.size() - 1; i++)
/*     */     {
/*  72 */       ActivityImpl activityImpl = processDefinitionEntity.findActivity(((HistoricActivityInstance)historicActivityInstances.get(i)).getActivityId());
/*  73 */       List<ActivityImpl> sameStartTimeNodes = new ArrayList();
/*  74 */       ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(((HistoricActivityInstance)historicActivityInstances.get(i + 1)).getActivityId());
/*     */       
/*  76 */       sameStartTimeNodes.add(sameActivityImpl1);
/*     */       HistoricActivityInstance activityImpl2;
/*  77 */       for (int j = i + 1; j < historicActivityInstances.size() - 1; j++)
/*     */       {
/*  78 */         HistoricActivityInstance activityImpl1 = (HistoricActivityInstance)historicActivityInstances.get(j);
/*  79 */         activityImpl2 = (HistoricActivityInstance)historicActivityInstances.get(j + 1);
/*  80 */         if (!activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
/*     */           break;
/*     */         }
/*  82 */         ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
/*  83 */         sameStartTimeNodes.add(sameActivityImpl2);
/*     */       }
/*  89 */       List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();
/*  90 */       for (PvmTransition pvmTransition : pvmTransitions)
/*     */       {
/*  92 */         ActivityImpl pvmActivityImpl = (ActivityImpl)pvmTransition.getDestination();
/*  94 */         if (sameStartTimeNodes.contains(pvmActivityImpl)) {
/*  95 */           highFlows.add(pvmTransition.getId());
/*     */         }
/*     */       }
/*     */     }
/*  99 */     return highFlows;
/*     */   }
/*     */   
/*     */   public TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString, String processInstanceId)
/*     */   {
/* 114 */     PvmActivity ac = null;
/* 115 */     Object s = null;
/* 117 */     if (("userTask".equals(activityImpl.getProperty("type"))) && (!activityId.equals(activityImpl.getId())))
/*     */     {
/* 119 */       TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
/* 120 */       return taskDefinition;
/*     */     }
/* 123 */     List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
/* 124 */     List<PvmTransition> outTransitionsTemp = null;
/* 125 */     for (PvmTransition tr : outTransitions)
/*     */     {
/* 126 */       ac = tr.getDestination();
/* 128 */       if ("exclusiveGateway".equals(ac.getProperty("type")))
/*     */       {
/* 129 */         outTransitionsTemp = ac.getOutgoingTransitions();
/* 131 */         if (StringUtils.isBlank(elString)) {
/* 133 */           elString = getGatewayCondition(ac.getId(), processInstanceId);
/*     */         }
/* 136 */         if (outTransitionsTemp.size() == 1) {
/* 137 */           return nextTaskDefinition((ActivityImpl)((PvmTransition)outTransitionsTemp.get(0)).getDestination(), activityId, elString, processInstanceId);
/*     */         }
/* 138 */         if (outTransitionsTemp.size() > 1) {
/* 139 */           for (PvmTransition tr1 : outTransitionsTemp)
/*     */           {
/* 140 */             s = tr1.getProperty("conditionText");
/* 141 */             if (s != null) {
/* 143 */               if (isCondition(ac.getId(), s.toString().trim(), elString))
/*     */               {
/* 144 */                 if (activityId.equals(tr1.getDestination().getId()))
/*     */                 {
/* 145 */                   TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
/* 146 */                   return taskDefinition;
/*     */                 }
/* 148 */                 return nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId, elString, processInstanceId);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 154 */       else if ("userTask".equals(ac.getProperty("type")))
/*     */       {
/* 155 */         return ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();
/*     */       }
/*     */     }
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   public String getGatewayCondition(String gatewayId, String processInstanceId)
/*     */   {
/* 171 */     Execution execution = (Execution)this.runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
/* 172 */     Object obj = this.runtimeService.getVariable(execution.getId(), gatewayId);
/* 173 */     if (obj != null) {
/* 174 */       return obj.toString();
/*     */     }
/* 176 */     return "";
/*     */   }
/*     */   
/*     */   public boolean isCondition(String key, String el, String value)
/*     */   {
/* 188 */     ExpressionFactory factory = new ExpressionFactoryImpl();
/* 189 */     SimpleContext context = new SimpleContext();
/* 190 */     context.setVariable(key, factory.createValueExpression(value, String.class));
/*     */     
/* 192 */     ValueExpression e = factory.createValueExpression(context, el, Boolean.TYPE);
/* 193 */     return ((Boolean)e.getValue(context)).booleanValue();
/*     */   }
/*     */   
/*     */   private boolean isCondition(Map<String, Object> variables, String el)
/*     */   {
/* 204 */     if (variables != null)
/*     */     {
/* 205 */       Set<String> keySet = variables.keySet();
/* 206 */       String elKey = "";
/* 207 */       String elValue = "";
/* 208 */       for (String key : keySet)
/*     */       {
/* 209 */         elKey = key;
/* 210 */         elValue = (String)variables.get(key);
/*     */       }
/* 212 */       ExpressionFactory factory = new ExpressionFactoryImpl();
/* 213 */       SimpleContext context = new SimpleContext();
/* 214 */       context.setVariable(elKey, factory.createValueExpression(elValue, String.class));
/* 215 */       ValueExpression e = factory.createValueExpression(context, el, Boolean.TYPE);
/* 216 */       return ((Boolean)e.getValue(context)).booleanValue();
/*     */     }
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   public Set<String> nextGatewayVariables(ActivityImpl activityImpl, String activityId, String processInstanceId)
/*     */   {
/* 232 */     Set<String> set = new HashSet();
/* 233 */     PvmActivity ac = null;
/* 234 */     if ((!"userTask".equals(activityImpl.getProperty("type"))) || (activityId.equals(activityImpl.getId())))
/*     */     {
/* 238 */       List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
/* 239 */       List<PvmTransition> outTransitionsTemp = null;
/* 240 */       for (PvmTransition tr : outTransitions)
/*     */       {
/* 241 */         ac = tr.getDestination();
/* 245 */         if ("exclusiveGateway".equals(ac.getProperty("type")))
/*     */         {
/* 246 */           outTransitionsTemp = ac.getOutgoingTransitions();
/* 248 */           if ((outTransitionsTemp != null) && (outTransitionsTemp.size() > 0)) {
/* 249 */             for (PvmTransition tr1 : outTransitionsTemp)
/*     */             {
/* 250 */               Object s = tr1.getProperty("conditionText");
/* 251 */               if (null != s) {
/* 252 */                 set.add(s.toString());
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 259 */     return set;
/*     */   }
/*     */   
/*     */   
/*     */   public void addComment(String task_id, String inst_id, String act_user, Map<String, Object> variables)
/*     */   {
/* 317 */     Authentication.setAuthenticatedUserId(act_user);
/*     */     
/* 319 */     Map<String, String> commentMap = new HashMap();
/* 320 */     commentMap.put("idea", (String)variables.get("idea"));
/* 321 */     commentMap.put("idea_message", (String)variables.get("idea_message"));
/* 322 */     ObjectMapper json = new ObjectMapper();
/*     */     try
/*     */     {
/* 325 */       String message = json.writeValueAsString(commentMap);
/* 326 */       this.taskService.addComment(task_id, inst_id, "task_comment", message);
/* 327 */       variables.remove("idea_message");
/*     */     }
/*     */     catch (JsonProcessingException e)
/*     */     {
/* 329 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public List<TaskDefinition> listNextTaskDefinition(ActivityImpl activityImpl, String activityId, String processInstanceId, Map<String, Object> variables)
/*     */   {
/* 343 */     List<TaskDefinition> taskDefList = new ArrayList();
/* 344 */     PvmActivity ac = null;
/* 346 */     if (("userTask".equals(activityImpl.getProperty("type"))) && (!activityId.equals(activityImpl.getId())))
/*     */     {
/* 348 */       TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
/* 349 */       taskDefList.add(taskDefinition);
/*     */     }
/*     */     else
/*     */     {
/* 352 */       List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
/* 353 */       List<PvmTransition> outTransitionsTemp = null;
/* 354 */       for (PvmTransition tr : outTransitions)
/*     */       {
/* 355 */         ac = tr.getDestination();
/* 357 */         if ("exclusiveGateway".equals(ac.getProperty("type")))
/*     */         {
/* 358 */           if ((variables == null) || (variables.size() == 0))
/*     */           {
/* 359 */             outTransitionsTemp = ac.getOutgoingTransitions();
/* 360 */             for (PvmTransition tr1 : outTransitionsTemp)
/*     */             {
/* 361 */               TaskDefinition taskDefinition = ((UserTaskActivityBehavior)((ActivityImpl)tr1.getDestination()).getActivityBehavior()).getTaskDefinition();
/* 362 */               taskDefList.add(taskDefinition);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 365 */             outTransitionsTemp = ac.getOutgoingTransitions();
/* 367 */             if (outTransitionsTemp.size() == 1) {
/* 368 */               return listNextTaskDefinition((ActivityImpl)((PvmTransition)outTransitionsTemp.get(0)).getDestination(), activityId, processInstanceId, variables);
/*     */             }
/* 369 */             if (outTransitionsTemp.size() > 1) {
/* 370 */               for (PvmTransition tr1 : outTransitionsTemp)
/*     */               {
/* 371 */                 String s = (String)tr1.getProperty("conditionText");
/* 372 */                 if (s != null) {
/* 374 */                   if (isCondition(variables, s.toString().trim()))
/*     */                   {
/* 375 */                     if (activityId.equals(tr1.getDestination().getId()))
/*     */                     {
/* 376 */                       TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
/* 377 */                       taskDefList.add(taskDefinition);
/* 378 */                       break;
/*     */                     }
/* 380 */                     return listNextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId, processInstanceId, variables);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 387 */         else if ("userTask".equals(ac.getProperty("type")))
/*     */         {
/* 388 */           if ((variables == null) || (variables.size() == 0))
/*     */           {
/* 389 */             taskDefList.add(((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition());
/*     */           }
/*     */           else
/*     */           {
/* 391 */             String s = (String)tr.getProperty("conditionText");
/* 392 */             System.out.println(s);
/* 393 */             if (s != null) {
/* 395 */               if (isCondition(variables, s.toString().trim()))
/*     */               {
/* 396 */                 taskDefList.add(((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition());
/* 397 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 401 */         else if ("endEvent".equals(ac.getProperty("type")))
/*     */         {
/* 402 */           TaskDefinition taskDefinition = new TaskDefinition(null);
/* 403 */           taskDefinition.setKey("end");
/* 404 */           taskDefList.add(taskDefinition);
/*     */         }
/*     */       }
/*     */     }
/* 410 */     return taskDefList;
/*     */   }
/*     */ }
