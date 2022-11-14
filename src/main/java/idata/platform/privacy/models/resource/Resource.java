package idata.platform.privacy.models.resource;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import idata.platform.privacy.models.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Resource")
@TableName("Resources")
public class Resource extends BaseEntity {
    @ApiModelProperty(value = "资源id")
    @TableField("resource_id")
    private String resourceId;

    @ApiModelProperty(value = "资源名称")
    @TableField("resource_name")
    private String resourceName;

    @ApiModelProperty(value = "资源简介")
    @TableField("resource_desc")
    private String resourceDesc;

    @ApiModelProperty(value = "资源信息")
    @TableField("resource_Info")
    private String resourceInfo;

    @ApiModelProperty(value = "所属公司")
    @TableField("company_name")
    private String companyName;

    @ApiModelProperty(value = "公司id")
    @TableField("company_id")
    private String companyId;

    @ApiModelProperty(value = "关键词")
    @TableField("keywords")
    private String keywords;

    @ApiModelProperty(value = "可见成员")
    @TableField("member_list")
    private String memberList;

    @ApiModelProperty(value = "参与项目数")
    @TableField("project_num")
    private Integer projectNum;

    @ApiModelProperty(value = "可见性")
    @TableField("publicLevel")
    private String publicLevel;

    @ApiModelProperty(value = "训练资源路径")
    @TableField("resource_train_path")
    private String resourceTrainPath;

    @ApiModelProperty(value = "测试资源路径")
    @TableField("resource_test_path")
    private String resourceTestPath;

    @ApiModelProperty(value = "资源类型")
    @TableField("resource_type")
    private String resourceType;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private String createUser;

}
