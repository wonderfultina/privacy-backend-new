package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.Res.ProcessFlowRecordMetric;
import idata.platform.privacy.models.project.ProcessFlowRecord;
import idata.platform.privacy.models.vo.project.ProcessFlowRecordVo;

import java.util.List;

public interface FlowRecordService extends IService<ProcessFlowRecord> {
    List<ProcessFlowRecord> queryProcessFlowRecord(String flowId, String projectId);
    String getProcessFlowByFlowRecordId(String flowRecordId,String projectId);
    ProcessFlowRecord createProcessFlowRecord(ProcessFlowRecordVo processFlowRecord);
    List<ProcessFlowRecordMetric> getFlowRecordMetricByProjectId(String projectId);
    List<ProcessFlowRecord> getFlowRecordByProjectId(String projectId);

}
