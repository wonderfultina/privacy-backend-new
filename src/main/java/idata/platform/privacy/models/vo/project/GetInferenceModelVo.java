package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "getInferenceModelVo")
public class GetInferenceModelVo {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "处理流程id")
    private String flowId;

    @ApiModelProperty(value = "处理流程记录id")
    private String flowRecordId;

    @ApiModelProperty(value = "模型id")
    private String modelId;
}
