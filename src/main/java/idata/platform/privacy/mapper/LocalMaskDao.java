package idata.platform.privacy.mapper;

import org.apache.ibatis.annotations.Select;

public interface LocalMaskDao {

    @Select("select * from resource,user where dataset_relation_table_tb.table_id = table_structure_tb.table_id" +
            " and dataset_relation_table_tb.data_set_id = #{resource_id}  and is_delete = 0 ")
    int getRecordCountByUserId(int user_id);



}
