package idata.platform.privacy.service;
import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.Res.*;
import idata.platform.privacy.models.project.*;
import idata.platform.privacy.models.vo.project.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface ProjectService extends IService<Project> {
    Map<String, String> createProject(ProjectCreateVo projectCreateVo);

    Map<String, String> addResource(ProjectAddResourceVo projectAddResourceVo);

    Map<String, Object> addResourceProject(CreateResourceProjectVo createResourceProjectVo);

    Map<String, String> createProcessFlow(CreateProcessFlowVo createProcessFlowVo);

    String updateProcessFlow(UpdateProcessFlowVo updateProcessFlowVo);

    ProcessFlow getProcessFlowByFlowId(String flowId, String projectId);

    ProcessFlowRecord getProcessFlowByFlowRecordId(String flowId, String projectId);

    String deleteProcessFlow(String flowRecordId,String projectId);

    List<ProcessFlowRecordMetric> getFlowRecords(String projectId);

    Map<String, String> createProcessFlowRecord(ProcessFlowRecordVo processFlowRecordVo);

    Project getProjectByIdList(String projectId);

    ProjectInfoRes selectProjectById(String projectId);

    List<ProjectListRes> getProjectListByResId(String resourceId);

    List<ProjectListRes> getProjectByComId(String companyId);

    List<ProcessFlowRecordRes> queryProcessFlowRecord(String flowId, String projectId);

    FlowExecuteResultRes getFlowExecuteResult(String flowId, String flowRecordId, String projectId);

    Map<String, String> createModel(ModelCreateVo modelCreateVo);

    List<Model> getModelList(GetModelVo getModelVo);

    List<Inference> getInferenceModelList(GetInferenceModelVo getInferenceModelVo);


}
