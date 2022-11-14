package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ProcessFlowMapper;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.service.ProcessFlowService;
import idata.platform.privacy.models.vo.project.UpdateProcessFlowVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProcessFlowServiceImpl extends ServiceImpl<ProcessFlowMapper, ProcessFlow> implements ProcessFlowService {


    @Override
    public Map<String, Object> createProcessFlow(ProcessFlow flow) {
        int row = baseMapper.insert(flow);
        return null;
    }

    @Override
    public String updateProcessFlow(UpdateProcessFlowVo vo) {
        String flowId = vo.getFlowId();
        String projectId = vo.getProjectId();
        QueryWrapper<ProcessFlow> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        List<ProcessFlow> processFlow = baseMapper.selectList(wrapper);
        if(processFlow.size() < 1){
            //抛出异常

        }else {
            QueryWrapper<ProcessFlow> updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("flow_id", flowId);
            updateWrapper.eq("project_id", projectId);
            ProcessFlow processFlowUpdate = new ProcessFlow();
            processFlowUpdate.setProcessFlow(vo.getProcessFlow());
            processFlowUpdate.setProcessFlowJson(vo.getProcessFlowJson());
            baseMapper.update(processFlowUpdate, updateWrapper);

        }
        return flowId;
    }

    @Override
    public ProcessFlow getProcessFlowByFlowId(String flowId, String projectId) {
        QueryWrapper<ProcessFlow> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        ProcessFlow processFlow = baseMapper.selectOne(wrapper);
        return processFlow;
    }



    @Override
    public String deleteProcessFlow(String flowId, String projectId) {
        QueryWrapper<ProcessFlow> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        Integer effectRow = baseMapper.delete(wrapper);

        return null;
    }


    @Override
    public List<ProcessFlow> queryProcessFlow(String projectId) {
        QueryWrapper<ProcessFlow> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        List<ProcessFlow> processFlowList = baseMapper.selectList(wrapper);

        return processFlowList;
    }

    @Override
    public List<ProcessFlow> queryProcessFlowByFlowId(String flowId) {
        QueryWrapper<ProcessFlow> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }
        List<ProcessFlow> processFlowList = baseMapper.selectList(wrapper);

        return processFlowList;
    }
}
