package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Company")
public class Company {
    @ApiModelProperty(value = "公司")
    private String companyName;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "是否是发起方")
    private Integer isActive;

}
