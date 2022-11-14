package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ProcessToFlowRecordMapper;
import idata.platform.privacy.models.project.ProcessToFlowRecord;
import idata.platform.privacy.service.ProcessToFlowRecordService;
import org.springframework.stereotype.Service;

@Service
public class ProcessToFlowRecordServiceImpl extends ServiceImpl<ProcessToFlowRecordMapper, ProcessToFlowRecord> implements ProcessToFlowRecordService {
    @Override
    public ProcessToFlowRecord createProcessToFlowRecord(String projectId, String flowId, String flowRecordId) {
        ProcessToFlowRecord record = new ProcessToFlowRecord();
        record.setFlowId(flowId);
        record.setFlowRecordId(flowRecordId);
        record.setProjectId(projectId);
        baseMapper.insert(record);
        return record;
    }
}
