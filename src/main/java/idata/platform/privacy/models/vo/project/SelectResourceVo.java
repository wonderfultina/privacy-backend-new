package idata.platform.privacy.models.vo.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(description = "SelectResource")
public class SelectResourceVo {

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "发起方公司")
    private Long sponsorCompanyId;


    @ApiModelProperty(value = "发起方资源")
    private Map<Long, Long> sponsorRes;

    @ApiModelProperty(value = "协作方公司")
    private List<Long> partnerCompany;

    @ApiModelProperty(value = "协作方资源")
    private List<Map<Long, Long>> sponsorResource;

}
