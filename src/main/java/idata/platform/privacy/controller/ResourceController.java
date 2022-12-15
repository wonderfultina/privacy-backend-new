package idata.platform.privacy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import idata.platform.privacy.common.Result;
import idata.platform.privacy.common.exception.PrivacyException;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.vo.project.ResourceTypeVo;
import idata.platform.privacy.service.ResourceService;
import idata.platform.privacy.models.vo.resource.ResourceQueryVo;
import idata.platform.privacy.models.vo.resource.ResourceUpdateVo;
import idata.platform.privacy.models.vo.resource.ResourceUploadVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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

        try{
            Map<String,Object> map = resourceService.uploadResource(resourceUploadVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "上传资源失败");
        }
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
            return Result.build(500, "查询资源失败");
        }

    }

    //分页查询
    @GetMapping("/{page}/{limit}")
    public Result index(@PathVariable Long page,
                        @PathVariable Long limit,
                        ResourceQueryVo resourceQueryVo){
        try {
            Page<Resource> pageParam = new Page<>(page, limit);
            Page<Resource> pageModel = resourceService.selectResourcePage(pageParam, resourceQueryVo);
            return Result.ok(pageModel);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "分页查询资源失败");
        }

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
            return Result.build(500, "修改资源失败");
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
            return Result.build(500, "删除资源失败");
        }
    }


    @GetMapping("/getResourceType")
    public Result update(@RequestParam("resourceIdList") List<String> resourceIdList){
        System.out.println(12345);
        List<ResourceTypeVo> resTypeList = resourceService.getResourceTypeList(resourceIdList);
        //一个公司多个资源
        Map<String, List<ResourceTypeVo>> companyResTypeMap = new HashMap<>();
        for(ResourceTypeVo res : resTypeList){
            //如果已经有了该公司，直接放资源
            if(companyResTypeMap.containsKey(res.getCompanyId())){
                List<ResourceTypeVo> list = companyResTypeMap.get(res.getCompanyId());
                list.add(res);
            }else{
                List<ResourceTypeVo> list = new ArrayList<>();
                list.add(res);
                companyResTypeMap.put(res.getCompanyId(), list);
            }
        }
        System.out.println(companyResTypeMap);
        return Result.ok(companyResTypeMap);
//        try{
//            System.out.println(12345);
//            List<ResourceTypeVo> resTypeList = resourceService.getResourceTypeList(resourceIdList);
//            //一个公司多个资源
//            Map<String, List<ResourceTypeVo>> companyResTypeMap = new HashMap<>();
//            for(ResourceTypeVo res : resTypeList){
//                //如果已经有了该公司，直接放资源
//                if(companyResTypeMap.containsKey(res.getCompanyId())){
//                    List<ResourceTypeVo> list = companyResTypeMap.get(res.getCompanyId());
//                    list.add(res);
//                }else{
//                    List<ResourceTypeVo> list = new ArrayList<>();
//                    list.add(res);
//                    companyResTypeMap.put(res.getCompanyId(), list);
//                }
//            }
//            System.out.println(companyResTypeMap);
//            return Result.ok(companyResTypeMap);
//
//        }catch (PrivacyException p){
//            return Result.build(p.getCode(), p.getMessage());
//        }catch (Exception e){
//            return Result.build(500, "删除资源失败");
//        }
    }

}
