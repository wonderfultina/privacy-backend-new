package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.DataSourceTb;
import idata.platform.privacy.models.project.ProcessFlowRecord;

public interface DataSourceTbService extends IService<DataSourceTb> {
    DataSourceTb getDataSourceBySourceId(String sourceId);
}
