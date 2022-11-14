package idata.platform.privacy.models.Res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "ResourceQueryPage")
public class ResourceQueryPage {
    @ApiModelProperty(value = "资源list")
    private List<ResourceQueryRes> records;

    @ApiModelProperty(value = "size")
    private long size;

    @ApiModelProperty(value = "total")
    private long total;

    @ApiModelProperty(value = "pages")
    private long pages;

    @ApiModelProperty(value = "current")
    private long current;

}
