package idata.platform.privacy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.common.util.HttpRequestHelper;
import idata.platform.privacy.common.util.IdRandomUtils;
import idata.platform.privacy.models.Res.ProcessFlowRecordMetric;
import idata.platform.privacy.mapper.FlowRecordMapper;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.models.project.ProcessFlowRecord;
import idata.platform.privacy.service.FlowRecordService;
import idata.platform.privacy.service.ProcessFlowService;
import idata.platform.privacy.models.vo.project.ProcessFlowRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FlowRecordServiceImpl extends ServiceImpl<FlowRecordMapper, ProcessFlowRecord> implements FlowRecordService {
    @Autowired
    private ProcessFlowService processFlowService;
    @Override
    public List<ProcessFlowRecord> queryProcessFlowRecord(String flowId, String projectId) {
        QueryWrapper<ProcessFlowRecord> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        if(!StringUtils.isEmpty(flowId)){
            wrapper.eq("flow_id", flowId);
        }

        List<ProcessFlowRecord> flowRecordList = baseMapper.selectList(wrapper);

        return flowRecordList;
    }

    @Override
    public ProcessFlowRecord getProcessFlowByFlowRecordId(String flowRecordId, String projectId) {
        QueryWrapper<ProcessFlowRecord> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowRecordId)){
            wrapper.eq("flow_record_id", flowRecordId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        ProcessFlowRecord record = baseMapper.selectOne(wrapper);

        return record;
    }

    @Override
    public ProcessFlowRecord getProcessFlowRecord(String flowRecordId, String projectId) {
        QueryWrapper<ProcessFlowRecord> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(flowRecordId)){
            wrapper.eq("flow_record_id", flowRecordId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        ProcessFlowRecord record = baseMapper.selectOne(wrapper);
        return  record;
    }

    @Override
    public ProcessFlowRecord createProcessFlowRecord(ProcessFlowRecordVo processFlowRecord) {
        String flowId = processFlowRecord.getFlowId();
        //由id查询对应的数据处理流程
        List<ProcessFlow> processFlows = processFlowService.queryProcessFlowByFlowId(flowId);
        if(processFlows.size() < 1){
            //抛出异常
        }
        String processFlow = processFlows.get(0).getProcessFlow();

        String projectId = processFlowRecord.getProjectId();
        String createUser = processFlowRecord.getCreateUser();
        String recordId = "fr" + IdRandomUtils.getRandomID().toString();
        //创建数据处理流程记录
        ProcessFlowRecord record = new ProcessFlowRecord();
        record.setFlowId(flowId);
        record.setProjectId(projectId);
        record.setCreateUser(createUser);
        record.setFlowRecordId(recordId);
        record.setProcessFlow(processFlow);
        record.setTrainBegin(new Date());
        record.setStatus(Long.valueOf(0));
        baseMapper.insert(record);
        return record;
    }

    @Override
    public List<ProcessFlowRecordMetric> getFlowRecordMetricByProjectId(String projectId) {
        QueryWrapper<ProcessFlowRecord> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        List<ProcessFlowRecord> flowRecordList = baseMapper.selectList(wrapper);
        List<ProcessFlowRecordMetric> metricList = new ArrayList<>();
        for(ProcessFlowRecord record : flowRecordList){
            ProcessFlowRecordMetric metric = new ProcessFlowRecordMetric();
            metric.setFlowId(record.getFlowId());
            metric.setFlowRecordId(record.getFlowRecordId());
            metric.setProcessFlow(record.getProcessFlow());
            metric.setProjectId(record.getProjectId());
            metric.setProcessFlow(record.getProcessFlow());
            metric.setFederationType(record.getProcessType());
            metric.setFailReason(record.getFailReason());
            metric.setRole(record.getRole());
            metric.setProcessName(record.getProcessName());
            metric.setStatus(record.getStatus());

            byte[] respData = HttpRequestHelper.doGet("http://10.10.10.71:9602/" + record.getFlowRecordId() + ".json");
            if(respData != null){
                String result = new String(respData);
                //进度
                JSONObject obj = JSON.parseObject(result);
                JSONObject runningLog = obj.getJSONArray("runningLog").getJSONObject(0);

                Float progressbar = runningLog.getFloat("progress");
                metric.setProgressbar(progressbar);

                //训练开始时间
                Long startTime = runningLog.getLong("start_time");
                if(startTime != null){
                    metric.setTrainBegin(IdRandomUtils.timeStamp2Date(startTime*1000L));
                }
                //训练结束时间
                Long endTime = runningLog.getLong("end_time");
                if (endTime != null){
                    metric.setTrainEnd(IdRandomUtils.timeStamp2Date(endTime*1000L));
                }
                //训练过程中的指标信息
                JSONArray comList = obj.getJSONArray("metricLog");
                metric.setMetricData(comList);
            }

            metricList.add(metric);

        }
        return metricList;
    }

    @Override
    public List<ProcessFlowRecord> getFlowRecordByProjectId(String projectId) {
        QueryWrapper<ProcessFlowRecord> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }
        List<ProcessFlowRecord> flowRecordList = baseMapper.selectList(wrapper);

        return flowRecordList;
    }
}
