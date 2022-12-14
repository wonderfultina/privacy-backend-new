package idata.platform.privacy.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.vo.project.ResourceTypeVo;
import idata.platform.privacy.models.vo.resource.ResourceQueryVo;
import idata.platform.privacy.models.vo.resource.ResourceUpdateVo;
import idata.platform.privacy.models.vo.resource.ResourceUploadVo;

import java.util.List;
import java.util.Map;

public interface ResourceService extends IService<Resource> {

    Page<Resource> selectResourcePage(Page<Resource> pageParam, ResourceQueryVo resourceQueryVo);

    Map<String, Object> uploadResource(ResourceUploadVo resourceUploadVo);

    List<Resource> queryResourceByBatchId(List<String> resourceId);

    List<ResourceTypeVo> getResourceTypeList(List<String> resourceId);

    String updateResource(ResourceUpdateVo resourceUpdateVo);

    String deleteResource(String resourceId);

    String updateResourceCount(String resourceId, Integer count);

}
