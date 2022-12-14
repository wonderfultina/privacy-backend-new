package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.DataSourceTbMapper;
import idata.platform.privacy.models.project.DataSourceTb;
import idata.platform.privacy.models.project.DatasetRelationTable;
import idata.platform.privacy.service.DataSourceTbService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DataSourceTbServiceImpl extends ServiceImpl<DataSourceTbMapper, DataSourceTb> implements DataSourceTbService {

    @Override
    public DataSourceTb getDataSourceBySourceId(String sourceId) {
        QueryWrapper<DataSourceTb> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(sourceId)){
            wrapper.eq("data_source_id", sourceId);
        }
        DataSourceTb datasetRelationTable = baseMapper.selectOne(wrapper);

        return datasetRelationTable;
    }
}
