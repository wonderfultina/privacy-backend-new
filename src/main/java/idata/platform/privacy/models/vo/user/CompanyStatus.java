package idata.platform.privacy.models.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "companyStatus")
public class CompanyStatus {

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "公司服务器ip")
    private String ip;

    @ApiModelProperty(value = "是否是发起方")
    private Integer isActive;

    @ApiModelProperty(value = "公司状态")
    private Integer federatedStatus;

}
