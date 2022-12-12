package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(description = "CreateResourceProject")
public class CreateResourceProjectVo {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "发起方公司id")
    private String activeComId;

    @ApiModelProperty(value = "公司资源")
    private Map<String, List<String>> companyResourceList;

}
