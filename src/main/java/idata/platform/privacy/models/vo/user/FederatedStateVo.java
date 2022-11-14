package idata.platform.privacy.models.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "查看公司状态")
public class FederatedStateVo {

    @ApiModelProperty(value = "公司信息")
    private List<CompanyStatus> companyStatusList;
}
