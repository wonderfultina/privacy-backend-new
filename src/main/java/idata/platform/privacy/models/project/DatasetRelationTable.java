package idata.platform.privacy.models.project;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "关联表")
@TableName("dataset_relation_table_tb")
public class DatasetRelationTable {
    @ApiModelProperty(value = "数据集id")
    @TableField("data_set_id")
    private String dataSetId;

    @ApiModelProperty(value = "表di")
    @TableField("table_id")
    private String tableId;

    @ApiModelProperty(value = "数据源id")
    @TableField("data_source_id")
    private String dataSourceId;


}
