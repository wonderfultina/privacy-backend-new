package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.Inference;
import idata.platform.privacy.models.vo.project.GetInferenceModelVo;
import idata.platform.privacy.models.vo.project.GetPublishModelVo;
import idata.platform.privacy.models.vo.project.PublishModelVo;

import java.util.List;

public interface InferenceService extends IService<Inference> {
    List<Inference> getPublishModel(GetPublishModelVo getPublishModelVo);

    List<Inference> getInferenceModelList(GetInferenceModelVo getInferenceModelVo);

}
