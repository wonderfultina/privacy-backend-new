package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ResourceProjectMapper;
import idata.platform.privacy.models.project.ResourceProject;
import idata.platform.privacy.service.ResourceProjectService;
import idata.platform.privacy.service.ResourceService;
import idata.platform.privacy.models.vo.project.CreateResourceProjectVo;
import idata.platform.privacy.models.vo.project.ProjectAddResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResourceProjectServiceImpl extends ServiceImpl<ResourceProjectMapper, ResourceProject> implements ResourceProjectService {
    @Autowired
    private ResourceService resourceService;

    @Override
    public Map<String, Object> addResource(ProjectAddResourceVo projectAddResourceVo) {
        List<String> resourceIdList = projectAddResourceVo.getResourceIdList();
        String projectId = projectAddResourceVo.getProjectId();
        String companyId = projectAddResourceVo.getCompanyId();
        Integer isActive = projectAddResourceVo.getIsActive();
        ResourceProject resourceProject = new ResourceProject();
        for(String resourceId : resourceIdList){
            //查看该资源id以及项目id是否存在
            List<ResourceProject> list = queryResProjectById(resourceId, projectId);
            if (list.size() > 0){
                continue;
            }
            resourceProject.setCompanyId(companyId);
            resourceProject.setIsActive(isActive);
            resourceProject.setProjectId(projectId);
            resourceProject.setResourceId(resourceId);
            baseMapper.insert(resourceProject);
        }
        return null;
    }

    @Override
    public Map<String, Object> addResourceProject(CreateResourceProjectVo createResourceProjectVo) {
        String pid = createResourceProjectVo.getProjectId();
       Map<String, List<String>> comResourceList = createResourceProjectVo.getCompanyResourceList();
        String activeComId = createResourceProjectVo.getActiveComId();
        List<String> resIdList = new ArrayList<>();
        for (String key : comResourceList.keySet()){
            for (String resId : comResourceList.get(key)){
                ResourceProject resourceProject = new ResourceProject();
                if(key.equals(activeComId)){
                    resourceProject.setIsActive(1);
                }else{
                    resourceProject.setIsActive(0);
                }
                resourceProject.setCompanyId(key);
                resourceProject.setResourceId(resId);
                resIdList.add(resId);
                resourceProject.setProjectId(pid);
                baseMapper.insert(resourceProject);
            }

        }


        //更新资源表中该资源被使用的次数
        for(String resId : resIdList){
            List<ResourceProject> list = queryResProjectByResId(resId);
            resourceService.updateResourceCount(resId, list.size());
        }
        return null;
    }

    @Override
    public List<ResourceProject> queryResourceProject(String projectId) {
        QueryWrapper<ResourceProject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }

        List<ResourceProject> resList = baseMapper.selectList(wrapper);
        return resList;
    }

    @Override
    public List<ResourceProject> queryResProjectByResId(String resourceId) {
        QueryWrapper<ResourceProject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(resourceId)){
            wrapper.eq("resource_id", resourceId);
        }

        List<ResourceProject> resList = baseMapper.selectList(wrapper);
        return resList;
    }

    @Override
    public List<ResourceProject> queryResProjectById(String resourceId, String projectId) {
        QueryWrapper<ResourceProject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(resourceId)){
            wrapper.eq("resource_id", resourceId);
        }
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }

        List<ResourceProject> resList = baseMapper.selectList(wrapper);
        return resList;
    }


}
