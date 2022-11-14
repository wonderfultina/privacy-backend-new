package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

//数据处理流程记录
@Data
@ApiModel(description = "ProcessFlowRecord")
@TableName("ProcessFlowRecord")
public class ProcessFlowRecord extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程记录id")
    @TableField("flow_record_id")
    private String flowRecordId;

    @ApiModelProperty(value = "数据处理流程id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "联邦类型")
    @TableField("process_type")
    private String processType;

    @ApiModelProperty(value = "角色")
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "训练名称")
    @TableField("process_name")
    private String processName;

    @ApiModelProperty(value = "开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("train_begin")
    private Date trainBegin;

    @ApiModelProperty(value = "该记录对应的数据处理流程")
    @TableField("process_flow")
    private String processFlow;

    @ApiModelProperty(value = "结束执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("train_end")
    private Date trainEnd;

    @ApiModelProperty(value = "模型的状态 0:未开始 1:进行中 2:已完成 3:失败")
    @TableField("status")
    private Long status;

    @ApiModelProperty(value = "失败原因")
    @TableField("fail_reason")
    private String failReason;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;

}
