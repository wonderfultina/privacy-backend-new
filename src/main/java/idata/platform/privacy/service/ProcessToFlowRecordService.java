package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.ProcessToFlowRecord;

public interface ProcessToFlowRecordService extends IService<ProcessToFlowRecord> {
    ProcessToFlowRecord createProcessToFlowRecord(String projectId, String flowId, String flowRecordId);
}
