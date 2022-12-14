package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.mapper.ProjectCompanyMapper;
import idata.platform.privacy.models.project.CompanyProject;
import idata.platform.privacy.service.ProjectCompanyService;
import idata.platform.privacy.models.vo.project.Company;
import idata.platform.privacy.models.vo.project.CreateProjectCompanyVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProjectCompanyServiceImpl extends ServiceImpl<ProjectCompanyMapper, CompanyProject> implements ProjectCompanyService {
    @Override
    public Map<String, Object> createProjectCompany(CreateProjectCompanyVo createProjectCompanyVo) {
        String projectId= createProjectCompanyVo.getProjectId();
        String projectName = createProjectCompanyVo.getProjectName();

        for (Company m : createProjectCompanyVo.getCompanyMap()) {
            CompanyProject projectCompany = new CompanyProject();
            System.out.println(projectId);
            System.out.println(m.getCompanyId());
            projectCompany.setProjectId(projectId);
            projectCompany.setProjectName(projectName);
            projectCompany.setCompanyId(m.getCompanyId());
            projectCompany.setCompanyName(m.getCompanyName());
            projectCompany.setIsActive(m.getIsActive());
            baseMapper.insert(projectCompany);
        }
        return null;
    }

    @Override
    public List<CompanyProject> queryProjectCompanyById(String projectId) {
        QueryWrapper<CompanyProject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(projectId)){
            wrapper.eq("project_id", projectId);
        }

        List<CompanyProject> comList = baseMapper.selectList(wrapper);
        return comList;
    }

    @Override
    public List<CompanyProject> queryProjectCompanyByComId(String companyId) {
        QueryWrapper<CompanyProject> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(companyId)){
            wrapper.eq("company_id", companyId);
        }

        List<CompanyProject> comList = baseMapper.selectList(wrapper);
        return comList;
    }
}
