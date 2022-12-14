package idata.platform.privacy.models.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import idata.platform.privacy.models.project.Model;
import idata.platform.privacy.models.project.ProcessFlow;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.user.UserInfo;
import idata.platform.privacy.models.vo.project.ResourceTypeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(description = "ProjectInfoRes")
public class ProjectInfoRes {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目简介")
    private String projectDesc;

    @ApiModelProperty(value = "发起方公司id")
    private String sponsorCompanyId;

    @ApiModelProperty(value = "发起方公司")
    private String sponsorCompanyName;

    @ApiModelProperty(value = "公司id:公司信息")
    private Map<String, UserInfo> companyInfoList;

    @ApiModelProperty(value = "协作公司id:公司名称")
    private List<Map<String,String>> companyMap;

    @ApiModelProperty(value = "协作公司id集合")
    private List<String> companyIdList;

    @ApiModelProperty(value = "公司id:资源")
    private Map<String, List<ResourceTypeVo>> companyResTypeMap;

    @ApiModelProperty(value = "公司id:资源")
    private Map<String, List<Resource>> companyResMap;

    @ApiModelProperty(value = "数据处理流程")
    private List<ProcessFlow> processFlowList;

    //模型列表
    @ApiModelProperty(value = "模型列表")
    private List<Model> modelList;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
