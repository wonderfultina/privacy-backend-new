package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.DatasetRelationTableMapper;
import idata.platform.privacy.models.project.DatasetRelationTable;
import idata.platform.privacy.models.project.ProcessFlowRecord;
import idata.platform.privacy.service.DatasetRelationTableService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DatasetRelationTableServiceImpl extends ServiceImpl<DatasetRelationTableMapper, DatasetRelationTable> implements DatasetRelationTableService {

    @Override
    public List<DatasetRelationTable> getDatasetRelationTableByResId(String resourceId) {
        QueryWrapper<DatasetRelationTable> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(resourceId)){
            wrapper.eq("data_set_id", resourceId);
        }
        List<DatasetRelationTable> datasetRelationTable = baseMapper.selectList(wrapper);

        return datasetRelationTable;
    }
}
