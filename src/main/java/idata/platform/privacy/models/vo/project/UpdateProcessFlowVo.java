package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "CreateProcessFlowVo")
//修改数据处理流程
public class UpdateProcessFlowVo {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程id")
    private String flowId;

    @ApiModelProperty(value = "数据处理流程")
    private String processFlow;

    @ApiModelProperty(value = "数据处理流程原始json")
    private String processFlowJson;

}
