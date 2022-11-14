package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.ResourceProject;
import idata.platform.privacy.models.vo.project.CreateResourceProjectVo;
import idata.platform.privacy.models.vo.project.ProjectAddResourceVo;

import java.util.List;
import java.util.Map;

public interface ResourceProjectService extends IService<ResourceProject> {
    Map<String, Object> addResource(ProjectAddResourceVo projectAddResourceVo);
    Map<String, Object> addResourceProject(CreateResourceProjectVo createResourceProjectVo);
    List<ResourceProject> queryResourceProject(String projectId);
    List<ResourceProject> queryResProjectByResId(String resourceId);

    List<ResourceProject> queryResProjectById(String resourceId, String projectId);

}
