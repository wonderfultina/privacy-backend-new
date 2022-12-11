package idata.platform.privacy.controller;

import idata.platform.privacy.common.Result;
import idata.platform.privacy.models.vo.user.*;
import idata.platform.privacy.service.UserInfoService;
import io.swagger.annotations.Api;
import idata.platform.privacy.models.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import idata.platform.privacy.common.exception.PrivacyException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags="用户接口")
public class UserController{
    //用户手机号登录
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        try{
            Map<String, Object> map = userInfoService.loginUser(loginVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "登录失败");
        }
    }

    @Autowired
    private UserInfoService userInfoService;
    //上传图片
    @PostMapping("/upload/CompanyLogo")
    public Result uploadCompanyLogo(@RequestParam(name = "files", required = false) MultipartFile file, HttpServletRequest request){
        String url = userInfoService.uploadCompanyLogo(file, request);
        return Result.ok(null);
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterVo registerVo){
        try{
            Map<String,Object> map = userInfoService.registerUser(registerVo);
            return Result.ok(map);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "注册失败");
        }

    }

    //查找公司
    @GetMapping("/select/company")
    public Result selectCompany(){
        List<Map<String, Object>> map = userInfoService.selectCompany();
        return Result.ok(map);
    }

    @GetMapping("/getCompanyById")
    public Result getCompanyById(String companyId){
        UserInfo company = userInfoService.getCompanyById(companyId);
        return Result.ok(company);
    }


    @PostMapping("/addConfigureInfo")
    public Result addConfigureInfo(@RequestBody ConfigureInfoVo configureInfoVo){
        try {
            String companyId = userInfoService.addConfigureInfo(configureInfoVo);
            return Result.ok(companyId);
        }catch (PrivacyException p){
            return Result.build(p.getCode(), p.getMessage());
        }catch (Exception e){
            return Result.build(500, "配置ip");
        }
    }

    //获取公司的状态信息
    @PostMapping("/getCompanyStatus")
    public Result getCompanyStatus(@RequestBody List<CompanyStatus> federatedStateVo){
        List<CompanyStatus> companyStatusList = userInfoService.getCompanyStatus(federatedStateVo);
        return  Result.ok(companyStatusList);
    }

}