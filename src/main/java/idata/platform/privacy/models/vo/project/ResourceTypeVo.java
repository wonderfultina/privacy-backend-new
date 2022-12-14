package idata.platform.privacy.models.vo.project;


import com.baomidou.mybatisplus.annotation.TableField;
import idata.platform.privacy.models.resource.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel(description = "ProjectInfoRes")
public class ResourceTypeVo {
    //一个资源id对应一个文件、对应多个表
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

    @ApiModelProperty(value = "公司id")
    private String companyId;

    @ApiModelProperty(value = "关键词")
    private String keywords;

    @ApiModelProperty(value = "训练资源路径")
    private String resourceTrainPath;

    @ApiModelProperty(value = "测试资源路径")
    private String resourceTestPath;

    @ApiModelProperty(value = "表名")
    private List<String> tableList;

    @ApiModelProperty(value = "配置文件名称key")
    private String configName;

}
