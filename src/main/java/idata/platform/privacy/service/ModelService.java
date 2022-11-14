package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.Model;
import idata.platform.privacy.models.vo.project.ModelCreateVo;

import java.util.List;
import java.util.Map;

public interface ModelService extends IService<Model> {
    Map<String, String> createModel(ModelCreateVo createModelVo);
    List<Model> queryModel(String projectId);
}
