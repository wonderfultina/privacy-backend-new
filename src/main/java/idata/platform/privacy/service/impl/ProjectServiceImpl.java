package idata.platform.privacy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.common.util.HttpRequestHelper;
import idata.platform.privacy.common.util.IdRandomUtils;
import idata.platform.privacy.models.Res.*;
import idata.platform.privacy.mapper.ProjectMapper;
import idata.platform.privacy.models.project.*;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.user.UserInfo;
import idata.platform.privacy.service.*;
import idata.platform.privacy.models.vo.project.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {
    @Autowired
    private ProjectCompanyService projectCompanyService;
    @Autowired
    private ResourceProjectService resourceProjectService;
    @Autowired
    private ModelService modelService;
    @Lazy
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ProcessFlowService processFlowService;
    @Autowired
    private FlowRecordService flowRecordService;
    @Autowired
    private ProcessToFlowRecordService processToFlowRecordService;
    @Autowired
    private UserInfoService userInfoService;

    private static final Map<String, String> constMap1 = new HashMap<String, String>() {
        {
            put("loading dataset", "数据读取");
            put("transforming features", "特征工程");
            put("psi protocol", "集合求交");
            put("collaborative model training", "联合训练");
            put("collaborative inference","联合推理");
            put("End FL","结束");
        }
    };

    @Override
    public Map<String, String> createProject(ProjectCreateVo projectCreateVo) {
        String projectName = projectCreateVo.getProjectName();
        String projectDesc = projectCreateVo.getProjectDesc();
        String createUser = projectCreateVo.getCreateUser();
        String type = projectCreateVo.getProjectType();
//        String processName = projectCreateVo.getModelName();
//        String processFlow = projectCreateVo.getProcessFlow();
        String sponsorCompany = projectCreateVo.getSponsorCompany();
        String sponsorCompanyId = projectCreateVo.getSponsorCompanyId();
        String sponsorResId = projectCreateVo.getSponsorResId();

        List<Map<String, String>> partnerCompanyMap = projectCreateVo.getPassiveCompanyMap();

        List<Company> partnerCompanyList = projectCreateVo.getPassiveCompanyList();
        String projectId = "pro" + IdRandomUtils.getRandomID().toString();
        //插入项目表
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectType(type);
        project.setProjectName(projectName);
        project.setProjectDesc(projectDesc);
        project.setSponsorCompany(sponsorCompany);
        project.setSponsorCompid(sponsorCompanyId);
        project.setCreateUser(createUser);
        baseMapper.insert(project);

        //插入项目公司表
        CreateProjectCompanyVo vo = new CreateProjectCompanyVo();
        vo.setProjectId(projectId);
        vo.setProjectName(projectName);

        Company com = new Company();
        com.setCompanyId(sponsorCompanyId);
        com.setCompanyName(sponsorCompany);
        com.setIsActive(1);
        partnerCompanyList.add(com);
        vo.setCompanyMap(partnerCompanyList);
        projectCompanyService.createProjectCompany(vo);


        //插入项目资源表
        CreateResourceProjectVo resourceProjectVo = new CreateResourceProjectVo();
        resourceProjectVo.setProjectId(projectId);
        resourceProjectVo.setActiveComId(sponsorCompanyId);
        HashMap<String, String> map= new HashMap<>();
        map.put(sponsorCompanyId, sponsorResId);
        partnerCompanyMap.add(map);
        resourceProjectVo.setCompanyResourceList(partnerCompanyMap);
        resourceProjectService.addResourceProject(resourceProjectVo);

        //添加数据处理流程,插入数据处理流程表
//        ProcessFlow flow = new ProcessFlow();
//        String flowId = "fl" + IdRandomUtils.getRandomID().toString();
//        flow.setFlowId(flowId);
//        flow.setProcessFlow(processFlow);
//        flow.setProcessName(processName);
//        flow.setCreateUser(createUser);
//        flow.setProjectId(projectId);
//        processFlowService.createProcessFlow(flow);

        //返回值
        map.put("projectId", projectId);
        return map;
    }

    @Override
    public Map<String, String> addResource(ProjectAddResourceVo projectAddResourceVo) {
        resourceProjectService.addResource(projectAddResourceVo);
        return null;
    }

    @Override
    public Map<String, Object> addResourceProject(CreateResourceProjectVo createResourceProjectVo) {
        resourceProjectService.addResourceProject(createResourceProjectVo);
        return null;
    }

    @Override
    public Map<String, String> createProcessFlow(CreateProcessFlowVo createProcessFlowVo) {
        String user = createProcessFlowVo.getCreateUser();
        String projectId = createProcessFlowVo.getProjectId();
        String processFlow = createProcessFlowVo.getProcessFlow();
        String processFlowJson = createProcessFlowVo.getProcessFlowJson();
        String algorithmName = createProcessFlowVo.getAlgorithmName();

        String type = createProcessFlowVo.getProcessType();
        String processName = createProcessFlowVo.getProcessName();
        String processDesc = createProcessFlowVo.getProcessDesc();

        ProcessFlow flow = new ProcessFlow();
        flow.setProjectId(projectId);
        flow.setCreateUser(user);
        flow.setProcessName(processName);
        flow.setProcessType(type);
        flow.setProcessFlow(processFlow);
        flow.setProcessDesc(processDesc);
        flow.setAlgorithmName(algorithmName);
        flow.setProcessFlowJson(processFlowJson);

        flow.setIsSBT(createProcessFlowVo.getIsSBT());
        String flowId = "pf" + IdRandomUtils.getRandomID().toString();
        flow.setFlowId(flowId);
        processFlowService.createProcessFlow(flow);
        HashMap<String, String> map= new HashMap<>();
        map.put("flowId", flowId);
        return map;
    }

    @Override
    public String updateProcessFlow(UpdateProcessFlowVo updateProcessFlowVo) {
        String flowId = processFlowService.updateProcessFlow(updateProcessFlowVo);
        return flowId;
    }

    @Override
    public ProcessFlow getProcessFlowByFlowId(String flowId, String projectId) {
        ProcessFlow processFlow = processFlowService.getProcessFlowByFlowId(flowId, projectId);
        return processFlow;
    }

    @Override
    public String getProcessFlowByFlowRecordId(String flowRecordId, String projectId) {
        String processFlow = flowRecordService.getProcessFlowByFlowRecordId(flowRecordId, projectId);
        return processFlow;
    }

    @Override
    public String deleteProcessFlow(String flowId, String projectId) {
        //删除数据处理流程
        processFlowService.deleteProcessFlow(flowId, projectId);
        //删除该数据处理流程下的记录
        return null;
    }

    //获取该项目下的数据处理流程记录
    @Override
    public List<ProcessFlowRecordMetric> getFlowRecords(String projectId) {
        List<ProcessFlowRecordMetric> list = flowRecordService.getFlowRecordMetricByProjectId(projectId);
        return list;
    }

    @Override
    public Map<String, String> createProcessFlowRecord(ProcessFlowRecordVo processFlowRecordVo) {
        //创建数据处理流程记录
        ProcessFlowRecord mapRecord = flowRecordService.createProcessFlowRecord(processFlowRecordVo);

        //创建数据处理流程-数据处理流程记录映射
        ProcessToFlowRecord processToFlowRecord = processToFlowRecordService.createProcessToFlowRecord(mapRecord.getProjectId(), mapRecord.getFlowId(), mapRecord.getFlowRecordId());

        HashMap<String, String> map= new HashMap<>();
        map.put("flow_record_id", mapRecord.getFlowRecordId());
        return map;
    }

    @Override
    public Project getProjectByIdList(String projectId) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            System.out.println(projectId);
            wrapper.eq("project_id", projectId);
        }
        Project project = baseMapper.selectOne(wrapper);

        return project;
    }


    @Override
    public ProjectInfoRes selectProjectById(String projectId) {
        Project project = getProjectByIdList(projectId);

        ProjectInfoRes projectInfo = new ProjectInfoRes();
        projectInfo.setProjectId(projectId);
        projectInfo.setProjectType(project.getProjectType());
        projectInfo.setCreateTime(project.getCreateTime());
        projectInfo.setProjectName(project.getProjectName());
        projectInfo.setProjectDesc(project.getProjectDesc());
        projectInfo.setSponsorCompanyId(project.getSponsorCompid());

        projectInfo.setSponsorCompanyName(project.getSponsorCompany());
        //所有公司的信息 id:公司信息
        Map<String,UserInfo> companyInfoList = new HashMap<>();
//        UserInfo infoCom = userInfoService.getCompanyById(project.getSponsorCompid());
//        companyInfoList.add(new HashMap<String, UserInfo>(){{
//            put(infoCom.getCompanyId(),infoCom);
//        }});

        //查公司项目表，查找该项目对应的协作方
        List<Map<String,String>> companyMap = new ArrayList<>();
        List<String> companyIdList = new ArrayList<>();
        List<CompanyProject> proComList = projectCompanyService.queryProjectCompanyById(projectId);
        for(CompanyProject com : proComList){
            UserInfo info = userInfoService.getCompanyById(com.getCompanyId());
            companyInfoList.put(info.getCompanyId(), info);

            //不包含主动方
            if(!com.getCompanyId().equals(project.getSponsorCompid())){
                Map<String, String> map = new HashMap<>();
                companyIdList.add(com.getCompanyId());
                map.put(com.getCompanyId(), com.getCompanyName());
                companyMap.add(map);
            }

        }
        projectInfo.setCompanyIdList(companyIdList);
        projectInfo.setCompanyMap(companyMap);
        projectInfo.setCompanyInfoList(companyInfoList);
        //查找项目资源表
        List<ResourceProject> ResList = resourceProjectService.queryResourceProject(projectId);
        List<String> ResIdList = new ArrayList<>();
        for(ResourceProject res : ResList){
            ResIdList.add(res.getResourceId());
        }

        //查找资源表
        List<Resource> resList = resourceService.queryResourceByBatchId(ResIdList);
        //一个公司多个资源
        Map<String, List<Resource>> companyResMap = new HashMap<>();

        for(Resource res : resList){

            //如果已经有了该公司，直接放资源
            if(companyResMap.containsKey(res.getCompanyId())){
                List<Resource> list = companyResMap.get(res.getCompanyId());
                list.add(res);
            }else{
                List<Resource> list = new ArrayList<>();
                list.add(res);
                companyResMap.put(res.getCompanyId(), list);
            }
        }

        projectInfo.setCompanyResMap(companyResMap);

        //根据项目id查找数据处理流程
        List<ProcessFlow> ProcessFlowList = processFlowService.queryProcessFlow(projectId);
        projectInfo.setProcessFlowList(ProcessFlowList);
        //模型列表

        modelService.queryModel(projectId);
        return projectInfo;
    }

    @Override
    public List<ProjectListRes> getProjectListByResId(String resourceId) {
        List<ProjectListRes> projectListRes = new ArrayList<>();

        //根据资源id查资源项目表
        List<ResourceProject>  resProList = resourceProjectService.queryResProjectByResId(resourceId);
        //对于每一个资源项目
        for(ResourceProject resPro : resProList){
            System.out.println(234);
            System.out.println(resPro);
            String proId = resPro.getProjectId();
            ProjectListRes projectRes = new ProjectListRes();

            //查找该项目下的公司
            List<CompanyProject> list = projectCompanyService.queryProjectCompanyById(resPro.getProjectId());
            projectRes.setProjectId(proId);

            //根据公司id，查找公司详细信息
            List<UserInfo> companyList = new ArrayList<>();
            for(CompanyProject com : list){
                //根据id查找公司信息
                UserInfo company = userInfoService.getCompanyById(com.getCompanyId());
                companyList.add(company);
            }
            projectRes.setCooperationCompanyList(companyList);

            //根据项目id查找项目信息
            Project proInfo = getProjectByIdList(proId);
            projectRes.setCreateUser(proInfo.getCreateUser());
            projectRes.setCreateTime(proInfo.getCreateTime());
            projectRes.setProjectName(proInfo.getProjectName());
            projectRes.setProjectDesc(proInfo.getProjectDesc());

            //根据项目id查找数据处理流程
            List<ProcessFlow> ProcessFlowList = processFlowService.queryProcessFlow(proId);
            //数据处理流程记录的个数
            projectRes.setTrainNum(10);
            //模型的个数
            projectRes.setModelNum(5);
            //
            projectListRes.add(projectRes);

        }
        return projectListRes;
    }

    @Override
    public List<ProjectListRes> getProjectByComId(String companyId) {
        List<ProjectListRes> projectListRes = new ArrayList<>();
//        String companyId = getProjectByComIdVo.getCompanyId();
//        String companyName = getProjectByComIdVo.getCompanyName();
//        String taskName = getProjectByComIdVo.getTaskName();
//        String taskStatus = getProjectByComIdVo.getTaskStatus();
//        String role = getProjectByComIdVo.getRole();
//        Date beginTime  = getProjectByComIdVo.getStartCreateTime();
//        Date endTime = getProjectByComIdVo.getEndCreateTime();

        //查找该公司下的项目
        List<CompanyProject> comProjectList = projectCompanyService.queryProjectCompanyByComId(companyId);
        //对于每一个项目,只针对两方 TODO

        for (CompanyProject pro : comProjectList){
            CompanyProject comPro = new CompanyProject();
            //查找该项目下的公司
            List<CompanyProject> list = projectCompanyService.queryProjectCompanyById(pro.getProjectId());
            ProjectListRes projectRes = new ProjectListRes();
            projectRes.setProjectId(pro.getProjectId());
            projectRes.setProjectName(pro.getProjectName());

            //根据公司id，查找公司详细信息
            List<UserInfo> companyList = new ArrayList<>();
            for(CompanyProject com : list){
                //根据id查找公司信息
                UserInfo company = userInfoService.getCompanyById(com.getCompanyId());
                companyList.add(company);
                if(!com.getCompanyId().equals(pro.getCompanyId())){
                    comPro = com;
                }
            }
            projectRes.setCooperationCompanyList(companyList);
            System.out.println(pro.getIsActive());
            System.out.println(comPro.getIsActive());
            //是主动方
            if(pro.getIsActive() == 1){
                projectRes.setActiveCompany(pro.getCompanyName());
                projectRes.setPassiveCompany(comPro.getCompanyName());
                //当前公司是主动方
                projectRes.setIsActive(1);
            }else if(comPro.getIsActive() == 0){
                projectRes.setPassiveCompany(pro.getCompanyName());
                projectRes.setActiveCompany(comPro.getCompanyName());
                projectRes.setIsActive(0);
            }

            //根据项目id查找项目信息
            Project proInfo = getProjectByIdList(pro.getProjectId());
            if(proInfo.getProjectDesc() != null){
                projectRes.setProjectDesc(proInfo.getProjectDesc());
            }
            projectRes.setCreateUser(proInfo.getCreateUser());
            projectRes.setCreateTime(proInfo.getCreateTime());

            //根据项目id查找数据处理流程
            List<ProcessFlow> ProcessFlowList = processFlowService.queryProcessFlow(pro.getProjectId());
            List<ProcessFlowRecord> flowRecordList = flowRecordService.getFlowRecordByProjectId(pro.getProjectId());
            //数据处理流程记录的个数
            projectRes.setFlowNum(ProcessFlowList.size());
            //训练次数
            projectRes.setTrainNum(flowRecordList.size());
            //模型的个数
            projectRes.setModelNum(flowRecordList.size());

            projectListRes.add(projectRes);
        }
        return projectListRes;
    }

    @Override
    public List<ProcessFlowRecordRes> queryProcessFlowRecord(String flowId, String projectId) {
        List<ProcessFlowRecord> recordList = flowRecordService.queryProcessFlowRecord(flowId, projectId);
        List<ProcessFlowRecordRes> recordListRes = new ArrayList<>();
        for (ProcessFlowRecord record : recordList){
            ProcessFlowRecordRes res = new ProcessFlowRecordRes();
            res.setProjectId(record.getProjectId());
            res.setFlowRecordId(record.getFlowRecordId());
            res.setProcessFlowName(record.getProcessName());
            res.setFederationType(record.getProcessType());
            res.setRole(record.getRole());

            res.setFlowId(record.getFlowId());
            //根据flowId和projectId查询日志
            byte[] respData;
            try {
                respData = HttpRequestHelper.doGet("http://10.10.10.71:9602/" + record.getFlowRecordId() + ".json");
            }catch (Exception e){
                respData = null;
            }

            if(respData != null){
                String result = new String(respData);
                log.info(String.format("--> 应答结果：result data %1s", result));
                //进度
                JSONObject obj = JSON.parseObject(result);
                JSONObject runningLog = obj.getJSONArray("runningLog").getJSONObject(0);

                Float progressbar = runningLog.getFloat("progress");
                log.info(String.format("--> 进度：%1s", progressbar));
                res.setProgress(progressbar);
                //训练开始时间
                Long startTime = runningLog.getLong("start_time");
                log.info(String.format("--> 时间戳：%1s", IdRandomUtils.timeStamp2Date(startTime)));
                res.setTrainBegin(IdRandomUtils.timeStamp2Date(startTime*1000L));
                //训练结束时间
                Long endTime = runningLog.getLong("end_time");
                res.setTrainEnd(IdRandomUtils.timeStamp2Date(endTime*1000L));

                Long timeElapsed = endTime - startTime;
                res.setTimeElapsed(timeElapsed);

            }
            //时间

            recordListRes.add(res);
        }

        return recordListRes;
    }

    //任务执行结果
    @Override
    public FlowExecuteResultRes getFlowExecuteResult(String flowId, String flowRecordId, String projectId) {
        FlowExecuteResultRes res = new FlowExecuteResultRes();
        res.setFlowId(flowId);
        res.setProjectId(projectId);
        res.setFlowRecordId(flowRecordId);
        //根据flowRecordId查询联邦类型，和角色

        //从日志中拿到时间以及组件详情
        List<Component> componentList = new ArrayList<>();
        byte[] respData = HttpRequestHelper.doGet("http://10.10.10.71:9602/" + flowRecordId + ".json");

        if(respData != null){
            String result = new String(respData);
            log.info(String.format("--> 应答结果：result data %1s", result));

            //进度
            JSONObject obj = JSON.parseObject(result);
            JSONObject runningLog = obj.getJSONArray("runningLog").getJSONObject(0);

            //训练开始时间
            Long startTime = runningLog.getLong("start_time");
            res.setTrainBegin(IdRandomUtils.timeStamp2Date(startTime*1000L));
            //训练结束时间
            Long endTime = runningLog.getLong("end_time");
            res.setTrainEnd(IdRandomUtils.timeStamp2Date(endTime*1000L));
            //运行时长
            Long timeElapsed = endTime - startTime;
            res.setDuration(timeElapsed);

            //获取每一个算子的信息
            JSONArray comList = obj.getJSONArray("componentLog");
            for (Object com : comList){
                Component component = new Component();
                JSONObject jsonObject = (JSONObject) com;
//                String name = constMap1.get(jsonObject.getString("name"));
                String name = jsonObject.getString("name");
                Date begin = jsonObject.getDate("begin");
                Date end = jsonObject.getDate("end");
                Long duration = jsonObject.getLong("duration");
//                component.setBeginTime(IdRandomUtils.timeStamp2Date(begin*1000L));
//                component.setEndTime(IdRandomUtils.timeStamp2Date(end*1000L));
                component.setBeginTime(begin);
                component.setEndTime(end);
                component.setDuration(duration);
                component.setName(name);
                componentList.add(component);
            }

            res.setLogInfo(comList.toJSONString());
        }
        res.setComponentList(componentList);


        return res;
    }
    @Override
    public Map<String, String> createModel(ModelCreateVo modelCreateVo) {
        modelService.createModel(modelCreateVo);
        return null;
    }
}
