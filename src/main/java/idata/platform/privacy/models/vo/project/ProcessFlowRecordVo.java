package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ProcessFlowRecord")
public class ProcessFlowRecordVo {

    @ApiModelProperty(value = "数据处理流程id")
    private String flowId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "创建人")
    private String createUser;
}
