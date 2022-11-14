package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ModelFlowMapper;
import idata.platform.privacy.models.project.ProcessToFlowRecord;
import idata.platform.privacy.service.ModelFlowService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ModelFlowServiceImpl extends ServiceImpl<ModelFlowMapper, ProcessToFlowRecord> implements ModelFlowService {
    @Override
    public Map<String, String> createModelFlow(String projectId, String modelId, String flowID) {

        return null;
    }
}
