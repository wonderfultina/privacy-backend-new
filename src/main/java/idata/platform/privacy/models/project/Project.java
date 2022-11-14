package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Resource")
@TableName("Project")
public class Project extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    @TableField("project_name")
    private String projectName;

    @ApiModelProperty(value = "项目简介")
    @TableField("project_desc")
    private String projectDesc;

    @ApiModelProperty(value = "项目类型")
    @TableField("project_type")
    private String projectType;


    @ApiModelProperty(value = "发起方")
    @TableField("sponsor_company")
    private String sponsorCompany;

    @ApiModelProperty(value = "发起方公司id")
    @TableField("sponsor_compid")
    private String sponsorCompid;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;

}
