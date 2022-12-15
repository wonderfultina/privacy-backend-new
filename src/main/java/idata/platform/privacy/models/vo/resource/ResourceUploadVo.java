package idata.platform.privacy.models.vo.resource;

import idata.platform.privacy.models.user.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "ResourceUpload")
public class ResourceUploadVo implements Serializable {

    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源简介")
    private String resourceDesc;

    @ApiModelProperty(value = "资源信息")
    private String resourceInfo;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "关键词")
    private List<String> keywords;

    @ApiModelProperty(value = "可见性")
    private String publicLevel;

    @ApiModelProperty(value = "可见联邦成员列表")
    private List<UserInfo> memberList;

//    @ApiModelProperty(value = "训练资源路径")
//    private String resourceTrainPath;
//
//    @ApiModelProperty(value = "测试资源路径")
//    private String resourceTestPath;
    @ApiModelProperty(value = "训练资源路径")
    private String resourcePath;

    @ApiModelProperty(value = "创建人")
    private String createUser;
}
