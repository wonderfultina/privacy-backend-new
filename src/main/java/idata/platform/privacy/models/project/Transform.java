package idata.platform.privacy.models.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Transform")
public class Transform {
    @ApiModelProperty(value = "特征工程类型")
    private List<String> type;

    @ApiModelProperty(value = "归一化类型")
    private String norm;
}
