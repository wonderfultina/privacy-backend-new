package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.common.util.IdRandomUtils;
import idata.platform.privacy.mapper.ModelMapper;
import idata.platform.privacy.models.project.Model;
import idata.platform.privacy.models.project.ModelToFlowRecord;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.models.vo.project.GetModelVo;
import idata.platform.privacy.service.ModelService;
import idata.platform.privacy.service.ModelToFlowRecordService;
import idata.platform.privacy.service.ProcessFlowService;
import idata.platform.privacy.models.vo.project.ModelCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {

    @Autowired
    private ModelToFlowRecordService modelToFlowRecordService;
    @Autowired
    private ProcessFlowService processFlowService;
    @Override
    public Map<String, String> createModel(ModelCreateVo createModelVo) {

        String projectId= createModelVo.getProjectId();
        String flowId = createModelVo.getFlowId();
        String flowRecordId = createModelVo.getFlowRecordId();
        String createUser = createModelVo.getCreateUser();
        Long begin = createModelVo.getTrainBegin();
        Long end = createModelVo.getTrainEnd();
        Date timeBegin = IdRandomUtils.timeStamp2Date(begin);
        Date timeEnd = IdRandomUtils.timeStamp2Date(end);
        String metric = createModelVo.getModelMetric();
        String modelId = "mo" + IdRandomUtils.getRandomID().toString();
        String flowName = "";
        //由数据处理流程id,查询数据处理流程名称
        List<ProcessFlow> processFlowList = processFlowService.queryProcessFlowByFlowId(flowId);
        if(processFlowList.size() == 0){
            //抛出异常
        }else{
              flowName = processFlowList.get(0).getProcessName();
        }
        String modelName = flowName + "-" +flowRecordId;

        Model model = new Model();
        model.setModelName(modelName);
        model.setModelMetric(metric);
        model.setFlowId(flowId);
        model.setCreateUser(createUser);
        model.setFlowRecordId(flowRecordId);
        model.setTrainBegin(timeBegin);
        model.setTrainEnd(timeEnd);
        model.setProjectId(projectId);
        //插入模型表
        baseMapper.insert(model);

        //插入数据处理流程记录、模型映射表
        ModelToFlowRecord record = new ModelToFlowRecord();
        record.setFlowId(flowId);
        record.setFlowRecordId(flowRecordId);
        record.setModelId(modelId);
        record.setProjectId(projectId);
        Map<String, String> mapTo = modelToFlowRecordService.createModelToFlowRecord(record);

        HashMap<String, String> map= new HashMap<>();
        map.put("modelId", modelId);
        return map;
    }

    @Override
    public List<Model> getModelList(GetModelVo getModelVo) {
        QueryWrapper<Model> wrapper = new QueryWrapper<>();

        String projectId = getModelVo.getProjectId();
        String companyId = getModelVo.getCompanyId();
        String flowRecordId = getModelVo.getFlowRecordId();
        String flowId = getModelVo.getFlowId();
        String modelId = getModelVo.getModelId();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        if(!StringUtils.isEmpty(companyId)){
            wrapper.eq("company_id", companyId);
        }
        if(!StringUtils.isEmpty(companyId)){
            wrapper.eq("company_id", companyId);
        }
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }
        if(!StringUtils.isEmpty(modelId)){
            wrapper.eq("model_id", modelId);
        }
        if(!StringUtils.isEmpty(flowRecordId)){
            wrapper.eq("flow_record_id", flowRecordId);
        }

        List<Model> modelList = baseMapper.selectList(wrapper);
        return modelList;
    }
}
