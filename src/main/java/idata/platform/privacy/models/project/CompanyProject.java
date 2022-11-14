package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "CompanyProject")
@TableName("CompanyProject")
public class CompanyProject extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "是否发起方")
    @TableField("is_active")
    private Integer isActive;

    @ApiModelProperty(value = "公司")
    @TableField("company_name")
    private String companyName;

    @ApiModelProperty(value = "项目名称")
    @TableField("project_name")
    private String projectName;

}
