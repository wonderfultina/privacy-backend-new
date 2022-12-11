package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "getPublishModelVo")
public class GetPublishModelVo {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "处理流程id")
    private String flowId;

    @ApiModelProperty(value = "模型id")
    private String modelId;

    @ApiModelProperty(value = "算法类型")
    private String algorithmType;

    @ApiModelProperty(value = "训练类型")
    private String processType;

}
