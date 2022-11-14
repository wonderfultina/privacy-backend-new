package idata.platform.privacy.models.vo.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "ResourceQuery")
public class ResourceQueryVo implements Serializable {

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "公司")
    private String companyName;

//    @ApiModelProperty(value = "可见性")
//    private String visible;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "关键词")
    private String keyWords;


}
