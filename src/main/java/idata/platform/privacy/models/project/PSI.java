package idata.platform.privacy.models.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "PSI")
public class PSI {
    @ApiModelProperty(value = "通信组件类型")
    private String messenger_type;

    @ApiModelProperty(value = "参与方角色")
    private String role;

    @ApiModelProperty(value = "主动方IP地址")
    private String active_ip;

    @ApiModelProperty(value = "主动方通信端口")
    private String active_port;

    @ApiModelProperty(value = "被动方IP地址")
    private String passive_ip;

    @ApiModelProperty(value = "被动方通信端口")
    private String passive_port;

    @ApiModelProperty(value = "RSA秘钥长度")
    private Integer ras_key_size;

    @ApiModelProperty(value = "RSA公钥")
    private Integer e;

    @ApiModelProperty(value = "并行线程数")
    private Integer num_workers;

}
