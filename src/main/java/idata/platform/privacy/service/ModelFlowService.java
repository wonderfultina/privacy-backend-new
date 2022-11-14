package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.ProcessToFlowRecord;

import java.util.Map;

public interface ModelFlowService extends IService<ProcessToFlowRecord> {
    Map<String, String> createModelFlow(String projectId, String modelId, String flowId);

}
