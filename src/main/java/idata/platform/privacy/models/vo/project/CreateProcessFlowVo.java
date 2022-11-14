package idata.platform.privacy.models.vo.project;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "CreateProcessFlowVo")
public class CreateProcessFlowVo {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "流程类型")
    private String processType;

    @ApiModelProperty(value = "流程描述")
    private String processDesc;

    @ApiModelProperty(value = "训练名称")
    private String processName;

    @ApiModelProperty(value = "数据流程算法名称")
    private String algorithmName;

    @ApiModelProperty(value = "数据处理流程")
    private String processFlow;

    @ApiModelProperty(value = "是否是SBT模型")
    private Integer isSBT;

    @ApiModelProperty(value = "数据处理流程josn")
    private String processFlowJson;

    @ApiModelProperty(value = "创建人")
    private String createUser;
}
