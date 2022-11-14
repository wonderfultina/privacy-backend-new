package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ModelToFlowRecordMapper;
import idata.platform.privacy.models.project.ModelToFlowRecord;
import idata.platform.privacy.service.ModelToFlowRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ModelToFlowRecordServiceImpl extends ServiceImpl<ModelToFlowRecordMapper, ModelToFlowRecord> implements ModelToFlowRecordService {
    @Override
    public Map<String, String> createModelToFlowRecord(ModelToFlowRecord record) {

        return null;
    }
}
