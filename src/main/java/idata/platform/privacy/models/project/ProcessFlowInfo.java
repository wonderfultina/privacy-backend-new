package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ProcessFlowInfo")
@TableName("ProcessFlowInfo")
public class ProcessFlowInfo {
    @ApiModelProperty(value = "项目id")
    private float batch_size;

    @ApiModelProperty(value = "学习率")
    private float learn_rate;

}

