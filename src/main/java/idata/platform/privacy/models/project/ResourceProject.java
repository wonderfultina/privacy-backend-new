package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ResourceProject")
@TableName("ResourceProject")
public class ResourceProject extends BaseEntity {
    @ApiModelProperty(value = "项目id")
    @TableField("project_id")
    private String projectId;

    @ApiModelProperty(value = "资源id")
    @TableField("resource_id")
    private String resourceId;

    @ApiModelProperty(value = "公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "是否是发起方")
    @TableField("is_active")
    private Integer isActive;

}
