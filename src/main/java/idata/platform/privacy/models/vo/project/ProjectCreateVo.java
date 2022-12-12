package idata.platform.privacy.models.vo.project;

import idata.platform.privacy.models.resource.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(description = "ProjectCreate")
public class ProjectCreateVo {

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目简介")
    private String projectDesc;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "发起方公司")
    private String sponsorCompany;

    @ApiModelProperty(value = "发起方公司")
    private String sponsorCompanyId;

    @ApiModelProperty(value = "发起方公司资源")
    private List<String> sponsorResId;

    @ApiModelProperty(value = "发起方公司资源")
    private List<Resource> sponsorRes;

    @ApiModelProperty(value = "协作方id公司")
    private List<Company> passiveCompanyList;

    @ApiModelProperty(value = "协作方公司资源")
    private Map<String, List<String>> passiveCompanyMap;

//    @ApiModelProperty(value = "模型名称")
//    private String ModelName;
//
//    @ApiModelProperty(value = "数据处理流程")
//    private String processFlow;

    @ApiModelProperty(value = "创建人")
    private String createUser;

}

