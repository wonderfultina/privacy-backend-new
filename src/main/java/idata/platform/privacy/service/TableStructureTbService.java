package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.DataSourceTb;
import idata.platform.privacy.models.project.TableStructureTb;

import java.util.List;

public interface TableStructureTbService extends IService<TableStructureTb> {
    List<TableStructureTb> getTableStructureById(List<String> tableIdList, String dataSourceId);
}
