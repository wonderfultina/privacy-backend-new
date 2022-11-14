package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "GetProjectByIdVo")
public class GetProjectByIdVo {
    @ApiModelProperty(value = "项目名称")
    private String ProjectId;
}
