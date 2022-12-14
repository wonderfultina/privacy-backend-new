package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.DatasetRelationTable;

import java.util.List;

public interface DatasetRelationTableService extends IService<DatasetRelationTable> {
    List<DatasetRelationTable> getDatasetRelationTableByResId(String resourceId);
 }
