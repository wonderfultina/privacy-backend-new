package idata.platform.privacy.models.Res;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "ProcessFlowRecordMetric")
@TableName("ProcessFlowRecordMetric")
public class ProcessFlowRecordMetric {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "数据处理流程记录id")
    private String flowRecordId;

    @ApiModelProperty(value = "数据处理流程id")
    private String flowId;

    @ApiModelProperty(value = "联邦类型")
    private String federationType;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "训练名称")
    private String processName;

    @ApiModelProperty(value = "流程进度")
    private Float progressbar;

    @ApiModelProperty(value = "开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainBegin;

    @ApiModelProperty(value = "该记录对应的数据处理流程")
    private String processFlow;

    @ApiModelProperty(value = "结束执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainEnd;

    @ApiModelProperty(value = "指标数据")
    private List<Object> metricData;

    @ApiModelProperty(value = "模型的状态 0:未开始 1:进行中 2:已完成 3:失败")
    private Long status;

    @ApiModelProperty(value = "失败原因")
    private String failReason;

    @ApiModelProperty(value = "创建人")
    private String createUser;

}
