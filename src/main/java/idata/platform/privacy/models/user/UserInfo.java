package idata.platform.privacy.models.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "UserInfo")
@TableName("userInfo")
public class UserInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "公司")
    @TableField("company_name")
    private String companyName;

    @ApiModelProperty(value = "公司")
    @TableField("company_logo")
    private String companyLogo;

    @ApiModelProperty(value = "公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "公司服务器ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "公司状态")
    @TableField("federated_status")
    private Integer federatedStatus;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;
}
