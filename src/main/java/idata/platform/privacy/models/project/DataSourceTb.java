package idata.platform.privacy.models.project;

import java.util.Date;
import lombok.Data;
@Data
public class DataSourceTb {

  private String dataSourceId;
  private String dataSourceName;
  private String dataLibraryType;
  private String dataLibraryName;
  private String interfaceIp;
  private String interfacePort;
  private String companyName;
  private String companyId;
  private String userId;
  private String configName;
}
