package idata.platform.privacy.models.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="注册对象")
public class RegisterVo {
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "公司")
    private String companyName;

    @ApiModelProperty(value = "公司logo")
    private String companyLogo;
}
