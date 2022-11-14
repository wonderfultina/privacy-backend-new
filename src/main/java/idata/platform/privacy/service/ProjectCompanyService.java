package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.project.CompanyProject;
import idata.platform.privacy.models.vo.project.CreateProjectCompanyVo;

import java.util.List;
import java.util.Map;

public interface ProjectCompanyService extends IService<CompanyProject> {
    Map<String, Object> createProjectCompany(CreateProjectCompanyVo createProjectCompanyVo);
    List<CompanyProject>  queryProjectCompanyById(String projectId);

    List<CompanyProject>  queryProjectCompanyByComId(String companyId);

}
