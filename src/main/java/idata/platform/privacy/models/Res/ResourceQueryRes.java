package idata.platform.privacy.models.Res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ResourceQueryRes")
public class ResourceQueryRes {
    @ApiModelProperty(value = "资源id")
    private String resourceId;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源简介")
    private String resourceDesc;

    @ApiModelProperty(value = "资源信息")
    private String resourceInfo;

    @ApiModelProperty(value = "所属公司")
    private String companyName;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    @ApiModelProperty(value = "可见成员")
    private String memberList;

    @ApiModelProperty(value = "参与项目数")
    private Integer projectNum;

    @ApiModelProperty(value = "可见性")
    private String visible;

    @ApiModelProperty(value = "资源路径")
    private String resourcePath;

    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    @ApiModelProperty(value = "创建人")
    private String createUser;
}
