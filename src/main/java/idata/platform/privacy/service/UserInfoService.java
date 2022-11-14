package idata.platform.privacy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import idata.platform.privacy.models.user.UserInfo;
import idata.platform.privacy.models.vo.user.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {

    //用户手机号登录
    Map<String, Object> loginUser(LoginVo loginVo);

    Map<String, Object> registerUser(RegisterVo RegisterVo);

    List<Map<String, Object>> selectCompany();

    String uploadCompanyLogo(MultipartFile file, HttpServletRequest request);

    UserInfo  getCompanyById(String companyId);

    String addConfigureInfo(ConfigureInfoVo configureInfoVo);

    List<CompanyStatus> getCompanyStatus(List<CompanyStatus> federatedStateVo);

}
