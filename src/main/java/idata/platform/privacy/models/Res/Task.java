package idata.platform.privacy.models.Res;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Task")
public class Task {
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "参与成员")
    private String taskComList;

    @ApiModelProperty(value = "创建人-时间")
    private String createInfo;

    @ApiModelProperty(value = "模型个数")
    private Integer modelNum;

    @ApiModelProperty(value = "历史最佳训练效果")
    private String bestMetric;

}
