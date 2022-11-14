package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.models.vo.project.UpdateProcessFlowVo;

import java.util.List;
import java.util.Map;

public interface ProcessFlowService extends IService<ProcessFlow> {
    Map<String, Object> createProcessFlow(ProcessFlow flow);

    String updateProcessFlow(UpdateProcessFlowVo vo);

    ProcessFlow getProcessFlowByFlowId(String flowId,String projectId);

    String deleteProcessFlow(String flowId,String projectId);

    List<ProcessFlow> queryProcessFlow(String projectId);

    List<ProcessFlow> queryProcessFlowByFlowId(String flowId);
}
