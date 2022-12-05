package idata.platform.privacy.controller;

import idata.platform.privacy.common.Result;
import idata.platform.privacy.common.exception.PrivacyException;
import idata.platform.privacy.common.util.FileUtils;
import idata.platform.privacy.models.Res.*;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.service.ProjectService;
import idata.platform.privacy.models.vo.project.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(tags = "项目接口")
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    //创建项目
    @PostMapping("/create")
    public Result create(@RequestBody ProjectCreateVo projectCreateVo){
        try {
            Map<String, String> map = projectService.createProject(projectCreateVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "创建项目失败");
        }
    }

    //页面上的添加资源到此项目
    @PostMapping("/add/resource")
    public Result addResource(@RequestBody ProjectAddResourceVo projectAddResourceVo){
        try{
            Map<String, String> map = projectService.addResource(projectAddResourceVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "添加资源到项目失败");
        }

    }

    //根据资源id获取使用该资源的项目
    @GetMapping("/getProListByResId")
    public Result getProjectListByResId(String resourceId){
        try{
            List<ProjectListRes> projectList = projectService.getProjectListByResId(resourceId);
            return Result.ok(projectList);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "登录失败");
        }
    }

    //由公司id查看项目-获取全部项目-我创建的-我协作的
    @GetMapping("/getProjectListByComId")
    public Result getProjectByComId(String companyId){
        try {
            List<ProjectListRes> projectList = projectService.getProjectByComId(companyId);
            return Result.ok(projectList);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看项目失败");
        }
    }


    //查看项目
    @GetMapping("/selectProjectById")
    public Result selectProjectById(String projectId){
        try {
            ProjectInfoRes projectInfo = projectService.selectProjectById(projectId);
            return Result.ok(projectInfo);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看项目失败");
        }

    }

    //根据数据处理流程id，查看流程执行记录
    @GetMapping("/getFlowExecuteRecords")
    public Result getFlowExecuteRecords(String flowId, String projectId){
        try {
            List<ProcessFlowRecordRes> list = projectService.queryProcessFlowRecord(flowId, projectId);
            return Result.ok(list);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看流程执行记录失败");
        }

    }

    //点击每一个数据处理流程执行记录，查看详情,从日志中拿
//    TODO
    @GetMapping("/flowExecuteResult")
    public Result getFlowExecuteResult(String flowId, String flowRecordId, String projectId){
        try {
            FlowExecuteResultRes res = projectService.getFlowExecuteResult(flowId, flowRecordId, projectId);
            return Result.ok(res);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看数据处理流程执行记录详情失败");
        }

    }

    //确认添加资源
    @PostMapping("/add/resource/sum")
    public Result AddResourceProject(@RequestBody CreateResourceProjectVo createResourceProjectVo){
        try {
            Map<String, Object> map = projectService.addResourceProject(createResourceProjectVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "添加资源失败");
        }

    }

    //创建数据处理流程
    @PostMapping("/create/processFlow")
    public Result CreateProcessFlow(@RequestBody CreateProcessFlowVo createProcessFlowVo){
        try {
            Map<String, String> map = projectService.createProcessFlow(createProcessFlowVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "创建数据处理流程失败");
        }

    }

    //根据id修改处理流程画布
    @PostMapping("/update/processFlow")
    public Result UpdateProcessFlow(@RequestBody UpdateProcessFlowVo updateProcessFlowVo){
        try {
            String flowId = projectService.updateProcessFlow(updateProcessFlowVo);
            return Result.ok(flowId);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "根据id修改处理流程画布失败");
        }

    }

    //根据数据处理流程id查看最新数据处理流程画布
    @GetMapping("/get/processFlowByFlowId")
    public Result getProcessFlowByFlowId(String flowId, String projectId){
        try {
            ProcessFlow processFlow = projectService.getProcessFlowByFlowId(flowId, projectId);
            return Result.ok(processFlow);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看最新数据处理流程画布");
        }

    }

    //根据数据处理流程记录id查看该记录对用的画布
    @GetMapping("/get/processFlowByFlowRecordId")
    public Result CreateProcessFlow(String flowRecordId, String projectId){
        try {
            String processFlow = projectService.getProcessFlowByFlowRecordId(flowRecordId, projectId);
            return Result.ok(processFlow);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "根据数据处理流程记录id查看画布失败");
        }
    }

    //复制数据处理流程画布
    @GetMapping("/copy/processFlow")
    public Result CopyProcessFlow(String flowId, String projectId){
//        String processFlow = projectService.getProcessFlow(flowId, projectId);
        return Result.ok(null);
    }

    //根据id删除数据处理流程画布
    @GetMapping("/delete/processFlow")
    public Result DeleteProcessFlow(String flowId, String projectId){
        try {
            projectService.deleteProcessFlow(flowId, projectId);
            return Result.ok(null);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "删除数据处理流程画布失败");
        }
    }

    //点击运行，创建数据处理流程记录
    @PostMapping("/createproject/delete/processFlow/processFlowRecord")
    public Result CreateProcessFlowRecord(@RequestBody ProcessFlowRecordVo processFlowRecordVo){
        try {
            Map<String, String> map = projectService.createProcessFlowRecord(processFlowRecordVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "创建数据处理流程记录失败");
        }

    }

    //由projectd 拿到项目id下所有流程记录
    @GetMapping("/get/processFlowRecords")
    public Result GetFlowRecords(String projectId){
        try {
            List<ProcessFlowRecordMetric> list = projectService.getFlowRecords(projectId);
            return Result.ok(list);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查看流程记录失败");
        }
    }


    //数据处理流程完毕，产生模型  TODO 算法回调
    @PostMapping("/create/model")
    public Result CreateModel(@RequestBody ModelCreateVo modelCreateVo){
        Map<String, String> map = projectService.createModel(modelCreateVo);
        return Result.ok(map);
    }

    //日志回调函数
    @PostMapping("/transferLog")
    public Result transferTrainLog(HttpServletRequest request){
        System.out.println(request.getParameter("msg"));
        String msg = request.getParameter("msg");
        System.out.println(msg);
        FileUtils utils = new FileUtils();
        //从msg中获取projectId、flowId、flowRecordId
        utils.saveJsonData(msg, "");
        return Result.ok(null);
    }

}
