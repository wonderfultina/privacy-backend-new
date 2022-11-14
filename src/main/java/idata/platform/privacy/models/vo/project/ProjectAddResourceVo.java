package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "ProjectCreate")
public class ProjectAddResourceVo {
    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "资源集合id")
    private List<String> resourceIdList;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "是否是发起方")
    private Integer isActive;
}
