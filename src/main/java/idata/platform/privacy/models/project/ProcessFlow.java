package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//数据处理流程
@Data
@ApiModel(description = "ProcessFlow")
@TableName("ProcessFlow")
public class ProcessFlow extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "是否是SBT模型")
    @TableField("is_SBT")
    private Integer isSBT;

    @ApiModelProperty(value = "训练类型")
    @TableField("process_type")
    private String processType;

    @ApiModelProperty(value = "流程描述")
    @TableField("process_desc")
    private String processDesc;

    @ApiModelProperty(value = "训练名称")
    @TableField("process_name")
    private String processName;

    @ApiModelProperty(value = "数据流程算法名称")
    @TableField("algorithm_name")
    private String algorithmName;

    @ApiModelProperty(value = "数据处理流程")
    @TableField("process_flow")
    private String processFlow;

    @ApiModelProperty(value = "数据处理流程")
    @TableField("process_flow_json")
    private String processFlowJson;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;
}
