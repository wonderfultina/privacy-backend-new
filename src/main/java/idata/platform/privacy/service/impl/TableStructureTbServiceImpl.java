package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.DataSourceTbMapper;
import idata.platform.privacy.mapper.TableStructureTbMapper;
import idata.platform.privacy.models.project.DataSourceTb;
import idata.platform.privacy.models.project.TableStructureTb;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.service.TableStructureTbService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableStructureTbServiceImpl  extends ServiceImpl<TableStructureTbMapper, TableStructureTb>  implements TableStructureTbService {

    @Override
    public List<TableStructureTb> getTableStructureById(List<String> tableIdList, String dataSourceId) {
        QueryWrapper<TableStructureTb> wrapper = new QueryWrapper<>();
        List<TableStructureTb> tableList = null;
        if(tableIdList.size() > 0 ) {
            wrapper.in("table_id",tableIdList);
            wrapper.eq("data_source_id", dataSourceId);
            tableList = baseMapper.selectList(wrapper);
        }


        return tableList;
    }
}
