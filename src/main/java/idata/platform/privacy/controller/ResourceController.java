package idata.platform.privacy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import idata.platform.privacy.common.Result;
import idata.platform.privacy.common.exception.PrivacyException;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.service.ResourceService;
import idata.platform.privacy.models.vo.resource.ResourceQueryVo;
import idata.platform.privacy.models.vo.resource.ResourceUpdateVo;
import idata.platform.privacy.models.vo.resource.ResourceUploadVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Api(tags = "资源接口")
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    //上传资源
    @PostMapping("/upload")
    public  Result upload(@RequestBody ResourceUploadVo resourceUploadVo){

//        try{
//            Map<String,Object> map = resourceService.uploadResource(resourceUploadVo);
//            return Result.ok(map);
//        }catch (PrivacyException p){
//            return Result.build(p.getCode(), p.getMessage());
//        }catch (Exception e){
//            return Result.build(500, "上传资源失败");
//        }
        Map<String,Object> map = resourceService.uploadResource(resourceUploadVo);
        return Result.ok(map);
    }

    //查询某个资源
    @GetMapping("/queryById")
    public Result queryById(@RequestParam("ids") List<String> ids){
        try{
            List<Resource> res = resourceService.queryResourceByBatchId(ids);
            return Result.ok(res);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "查询失败");
        }

    }


    //分页查询
    @GetMapping("/{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        ResourceQueryVo resourceQueryVo){
        System.out.println(resourceQueryVo.getCompanyId());
        Page<Resource> pageParam = new Page<>(page, limit);
        Page<Resource> pageModel = resourceService.selectResourcePage(pageParam, resourceQueryVo);
        List<Resource> rescords = pageModel.getRecords();
        for(Resource res : rescords){
            System.out.println(res.getResourceId());
        }
        return Result.ok(pageModel);
    }

    //修改资源
    @PostMapping("/update")
    public Result update(@RequestBody ResourceUpdateVo resourceUpdateVo){
        try{
            String resId = resourceService.updateResource(resourceUpdateVo);
            return Result.ok(resId);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "更新失败");
        }
    }

    //删除资源
    @GetMapping("/delete")
    public Result update(String resourceId){
        try{
            String resId = resourceService.deleteResource(resourceId);
            return Result.ok(resId);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "更新失败");
        }
    }

}
