package idata.platform.privacy.models.vo.project;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ModelCreate")
public class ModelCreateVo {

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程记录id")
    private String flowRecordId;

    @ApiModelProperty(value = "数据处理流程id")
    private String flowId;

    @ApiModelProperty(value = "模型指标")
    private String modelMetric;

    @ApiModelProperty(value = "模型开始执行时间")
    private Long trainBegin;

    @ApiModelProperty(value = "模型结束执行时间")
    private Long trainEnd;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;
}
