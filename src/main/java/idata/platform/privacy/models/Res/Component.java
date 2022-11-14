package idata.platform.privacy.models.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Component")
public class Component {
    @ApiModelProperty(value = "算子名称")
    private String name;

    @ApiModelProperty(value = "开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @ApiModelProperty(value = "开始执行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "算子运行时长")
    private Long duration;

    @ApiModelProperty(value = "任务执行状态")
    private String status;

}
