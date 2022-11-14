package idata.platform.privacy.models.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "FlowExecuteResultRes")
public class FlowExecuteResultRes {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程id")
    private String flowId;

    @ApiModelProperty(value = "数据处理流程记录名称")
    private String flowRecordId;

    @ApiModelProperty(value = "联邦类型")
    private String federationType;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainBegin;

    @ApiModelProperty(value = "结束执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainEnd;

    @ApiModelProperty(value = "运行时长")
    private Long duration;

    @ApiModelProperty(value = "组件详情")
    private List<Component> componentList;

    @ApiModelProperty(value = "日志详情")
    private String logInfo;
}
