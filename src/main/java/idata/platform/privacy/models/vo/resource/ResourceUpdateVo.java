package idata.platform.privacy.models.vo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "ResourceUpdateVo")
public class ResourceUpdateVo implements Serializable {
    @ApiModelProperty(value = "资源id")
    private String resourceId;

    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源简介")
    private String resourceDesc;

    @ApiModelProperty(value = "资源信息")
    private String resourceInfo;

    @ApiModelProperty(value = "关键词")
    private String resourceKeyword;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    @ApiModelProperty(value = "可见性")
    private Integer visible;

    @ApiModelProperty(value = "可见联邦成员列表")
    private String memberList;

    @ApiModelProperty(value = "训练资源路径")
    private String resourceTrainPath;

    @ApiModelProperty(value = "测试资源路径")
    private String resourceTestPath;

}
