package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.models.project.Inference;
import idata.platform.privacy.models.project.ProcessFlowRecord;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.vo.project.GetInferenceModelVo;
import idata.platform.privacy.models.vo.project.GetPublishModelVo;
import idata.platform.privacy.models.vo.project.PublishModelVo;
import idata.platform.privacy.service.FlowRecordService;
import idata.platform.privacy.service.InferenceService;
import idata.platform.privacy.mapper.InferenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class InferenceServiceImpl extends ServiceImpl<InferenceMapper, Inference> implements InferenceService {

    @Autowired
    private FlowRecordService flowRecordService;

//    @Override
//    public void publishModel(PublishModelVo publishModelVo) {
//        String algorithmType = publishModelVo.getAlgorithmType();
//        String modelId = publishModelVo.getModelId();
//        String projectId = publishModelVo.getProjectId();
//        String flowId = publishModelVo.getFlowId();
//        //根据数据处理流程id查看数据处理流程记录
//        ProcessFlowRecord flowRecord = flowRecordService.getProcessFlowRecord(modelId, projectId);
//        String processType = flowRecord.getProcessType();
//
//        //根据模型modelId看推理表中是否有数据
//        QueryWrapper<InferenceModel> wrapper = new QueryWrapper<>();
//
//        if(!isEmpty(projectId)){
//            wrapper.eq("model_id", modelId);
//        }
//        List<InferenceModel>  modelList = baseMapper.selectList(wrapper);
//        if(modelList.size() >0 ){
//            return;
//        }
//        //不存在的话插入数据
//        InferenceModel inferenceModel = new InferenceModel();
//        inferenceModel.setAlgorithmType(algorithmType);
//        inferenceModel.setProcessType(processType);
//        inferenceModel.setProjectId(projectId);
//        inferenceModel.setModelId(modelId);
//        inferenceModel.setFlowId(flowId);
//
//        baseMapper.insert(inferenceModel);
//    }

    @Override
    public List<Inference> getPublishModel(GetPublishModelVo getPublishModelVo) {
        String algorithmType = getPublishModelVo.getAlgorithmType();
        String modelId = getPublishModelVo.getModelId();
        String projectId = getPublishModelVo.getProjectId();
        String flowId = getPublishModelVo.getFlowId();
        String processType= getPublishModelVo.getProcessType();

        //对条件值进行非空判断
        QueryWrapper<Inference> wrapper = new QueryWrapper<>();

        if(!isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        if(!isEmpty(modelId)) {
            wrapper.eq("model_id",modelId);
        }
        if(!isEmpty(flowId)) {
            wrapper.eq("flow_id",flowId);
        }
        if(!isEmpty(algorithmType)) {
            wrapper.eq("algorithm_type",algorithmType);
        }
        if(!isEmpty(processType)) {
            wrapper.eq("process_type",processType);
        }
        List<Inference>  inferenceModel = baseMapper.selectList(wrapper);

        return inferenceModel;
    }

    @Override
    public List<Inference> getInferenceModelList(GetInferenceModelVo getInferenceModelVo) {
        String companyId = getInferenceModelVo.getCompanyId();
        String modelId = getInferenceModelVo.getModelId();
        String projectId = getInferenceModelVo.getProjectId();
        String flowId = getInferenceModelVo.getFlowId();
        String flowRecordId = getInferenceModelVo.getFlowRecordId();

        //对条件值进行非空判断
        QueryWrapper<Inference> wrapper = new QueryWrapper<>();

        if(!isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        if(!isEmpty(modelId)) {
            wrapper.eq("model_id",modelId);
        }
        if(!isEmpty(flowId)) {
            wrapper.eq("flow_id",flowId);
        }
        if(!isEmpty(companyId)) {
            wrapper.eq("company_id",companyId);
        }
        if(!isEmpty(flowRecordId)) {
            wrapper.eq("flow_record_id",flowRecordId);
        }

        List<Inference>  inferenceModel = baseMapper.selectList(wrapper);

        return inferenceModel;
    }
}
