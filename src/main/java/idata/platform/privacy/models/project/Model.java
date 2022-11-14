package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Model")
@TableName("Model")
public class Model extends BaseEntity {
    @ApiModelProperty(value = "模型id")
    @TableField("model_id")
    private String modelId;

    @ApiModelProperty(value = "模型名称")
    //以数据处理流程名称-记录id命名
    @TableField("model_name")
    private String modelName;

    @ApiModelProperty(value = "我方角色")
    //以数据处理流程名称-记录id命名
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程记录id")
    @TableField("flow_record_id")
    private String flowRecordId;

    @ApiModelProperty(value = "数据处理流程id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "模型指标")
    @TableField("model_metric")
    private String modelMetric;

    @ApiModelProperty(value = "模型开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("train_begin")
    private Date trainBegin;

    @ApiModelProperty(value = "模型结束执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("train_end")
    private Date trainEnd;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;
}
