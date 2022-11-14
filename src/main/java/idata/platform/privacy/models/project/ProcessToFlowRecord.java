package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//数据处理流程-数据处理流程记录(1:N)
@Data
@ApiModel(description = "ProcessToFlowRecord")
@TableName("ProcessToFlowRecord")
public class ProcessToFlowRecord extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程记录id")
    @TableField("flow_record_id")
    private String flowRecordId;

    @ApiModelProperty(value = "数据处理流程id")
    @TableField("flow_id")
    private String flowId;
}
