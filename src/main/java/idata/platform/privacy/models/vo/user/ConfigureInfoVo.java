package idata.platform.privacy.models.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="配置信息")
public class ConfigureInfoVo {

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "服务器ip")
    private String ip;

}
