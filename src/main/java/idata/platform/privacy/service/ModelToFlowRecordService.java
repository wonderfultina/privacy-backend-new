package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.ModelToFlowRecord;

import java.util.Map;

public interface ModelToFlowRecordService extends IService<ModelToFlowRecord> {
    Map<String, String> createModelToFlowRecord(ModelToFlowRecord record);

}
