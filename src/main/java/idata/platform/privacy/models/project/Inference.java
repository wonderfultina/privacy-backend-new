package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "推理表")
@TableName("Inference")
public class Inference extends BaseEntity {

    @ApiModelProperty(value = "推理id")
    @TableField("inference_id")
    private String inferenceId;


    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "项目id")
    @TableField("flow_record_id")
    private String flowRecordId;

    @ApiModelProperty(value = "模型id")
    @TableField("model_id")
    private String modelId;

    @ApiModelProperty(value = "数据处理流程id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "数据类型")
    @TableField("process_type")
    private String processType;

    @ApiModelProperty(value = "流程名称")
    @TableField("process_name")
    private String processName;

    @ApiModelProperty(value = "流程名称")
    @TableField("model_name")
    private String modelName;

    @ApiModelProperty(value = "推理文件所在路径")
    @TableField("inference_result_path")
    private String inferenceResultPath;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;

    @ApiModelProperty(value = "模型的状态 0:未开始 1:进行中 2:已完成 3:失败")
    @TableField("status")
    private Long status;

}
