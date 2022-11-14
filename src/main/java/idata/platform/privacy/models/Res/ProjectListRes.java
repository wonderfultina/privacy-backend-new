package idata.platform.privacy.models.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import idata.platform.privacy.models.user.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "ProjectListRes")
public class ProjectListRes {
    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目简介")
    private String projectDesc;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "项目发起方")
    private String activeCompany;

    @ApiModelProperty(value = "项目协作方")
    private String passiveCompany;

    @ApiModelProperty(value = "当前查询公司是否是主动方")
    private Integer isActive;

    @ApiModelProperty(value = "项目参与方")
    private List<UserInfo> cooperationCompanyList;

    @ApiModelProperty(value = "训练次数")
    private Integer trainNum;

    @ApiModelProperty(value = "流程次数")
    private Integer flowNum;

    @ApiModelProperty(value = "模型次数")
    private Integer modelNum;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
