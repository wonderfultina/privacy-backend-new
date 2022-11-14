package idata.platform.privacy.models.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "DataIo")
public class DataIo {
    @ApiModelProperty(value = "参与方角色")
    private String role;

    @ApiModelProperty(value = "数据集绝对路径")
    private String abs_path;

}
