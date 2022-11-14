package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.common.ResultCodeEnum;
import idata.platform.privacy.common.exception.PrivacyException;
import idata.platform.privacy.common.util.IdRandomUtils;
import idata.platform.privacy.mapper.UserInfoMapper;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.user.UserInfo;
import idata.platform.privacy.models.vo.user.*;
import idata.platform.privacy.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public Map<String, Object> loginUser(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String password = loginVo.getPassword();

        //参数判断，判断登录信息是否为空
        checkLoginParams(phone, password);

        //根据手机号查询用户信息
        UserInfo userInfo = QueryUserByPhone(phone);

        Map<String, Object> map = new HashMap<String, Object>();

        //判断用户信息是否为空
        if(userInfo == null){
            throw new PrivacyException(ResultCodeEnum.REGISTER_MOBLE_NOTUSE);
        }else{
            //密码是否与登录密码一致
            if(!userInfo.getPassword().equals(password) ){
                throw new PrivacyException(ResultCodeEnum.Password_ERROR);
            }
            map.put("userName", userInfo.getUserName());
            map.put("companyName", userInfo.getCompanyName());
            map.put("companyId", userInfo.getCompanyId());
            map.put("userId", userInfo.getUserId());

            //输出id
            System.out.println("companyId");
            System.out.println(userInfo.getCompanyId());
//            //返回token信息
//            String token = JwtHelper.createToken(userInfo.getId(), name);
//            map.put("token", token);
        }
        return map;
    }

    private UserInfo QueryUserByPhone(String phone) {
        UserInfo userInfo;
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        userInfo = baseMapper.selectOne(wrapper);
        return userInfo;
    }

    private void checkLoginParams( String phone, String password) {
        //从loginVo获取输入的手机号和密码和公司，并判断是否为空
        if( StringUtils.isEmpty(phone) || StringUtils.isEmpty(password) ){
            throw new PrivacyException(ResultCodeEnum.PARAM_ERROR);
        }
    }

    @Override
    public Map<String, Object> registerUser(RegisterVo registerVo) {

        //从loginVo获取输入的手机号和密码和公司，并判断是否为空
        String phone = registerVo.getPhone();
        String password = registerVo.getPassword();
        String company = registerVo.getCompanyName();
        String name = registerVo.getUserName();
        String logo = registerVo.getCompanyLogo();
        //参数判断，判断登录信息是否为空
        checkLoginParams(phone, password);

        //根据手机号查询用户信息
        UserInfo userInfo = QueryUserByPhone(phone);

        if(userInfo == null){
            //添加到数据库
            userInfo = new UserInfo();
            userInfo.setCompanyName(company);
            userInfo.setPhone(phone);
            userInfo.setUserName(name);
            userInfo.setPassword(password);
            String result = "";
            try {
                result = URLDecoder.decode(logo, "utf-8");
                System.out.println(result);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            userInfo.setCompanyLogo(result);
            userInfo.setUserId("us" + IdRandomUtils.getRandomID().toString());
            userInfo.setCompanyId("com" + IdRandomUtils.getRandomID().toString());
            baseMapper.insert(userInfo);
        }else {
            //提示，该手机号已被注册，请登录
            throw new PrivacyException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        return null;
    }

    @Override
    public List<Map<String, Object>> selectCompany() {
        //查询所有公司
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        List<UserInfo> UserInfoList = baseMapper.selectList(wrapper);
        System.out.println(UserInfoList);
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        //判断用户信息是否为空
        if(UserInfoList == null){
            throw new PrivacyException(ResultCodeEnum.REGISTER_MOBLE_NOTUSE);
        }else{
            for(UserInfo user : UserInfoList){
                Map<String, Object> map = new HashMap<>();
                map.put("companyName", user.getCompanyName());
                map.put("companyId", user.getCompanyId());
                map.put("phone", user.getPhone());
                mapList.add(map);
            }
        }

        return mapList;
    }

    @Override
    public String uploadCompanyLogo(MultipartFile file, HttpServletRequest request) {
        String path = "/root/idata/companyLogo";
        System.out.println(path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
            fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
            if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
                targetFile.getParentFile().mkdirs();
            }
            long size = 0;
            //保存
            try {
                file.transferTo(targetFile);
                size = file.getSize();
            } catch (Exception e) {
                e.printStackTrace();
                //return R.error("上传失败！");
            }
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
            String fileUrl="http://10.10.10.71:8151/";
            //文件获取路径
            fileUrl = fileUrl + fileName;
            System.out.println(fileUrl);
            //return R.ok().put("fileUrl", fileUrl);
            return fileUrl;
        }else{
            //
        }

        return null;
    }

    @Override
    public UserInfo getCompanyById(String companyId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(companyId)){
//            System.out.println(companyId);
            wrapper.eq("company_id", companyId);
        }

        UserInfo company= baseMapper.selectOne(wrapper);
        return company;
    }

    @Override
    public String addConfigureInfo(ConfigureInfoVo configureInfoVo) {
        String companyId = configureInfoVo.getCompanyId();
        String userId = configureInfoVo.getUserId();
        String ip = configureInfoVo.getIp();


        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(companyId)){
            wrapper.eq("company_id", companyId);
        }
        if(!StringUtils.isEmpty(userId)){
            wrapper.eq("user_id", userId);
        }

        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setIp(ip);
        baseMapper.update(userInfoUpdate, wrapper);
        return companyId;
    }

    @Override
    public List<CompanyStatus> getCompanyStatus(List<CompanyStatus> federatedStateVo) {
        for(CompanyStatus companyStatus : federatedStateVo){
            String companyId = companyStatus.getCompanyId();
            QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(companyId)){
                wrapper.eq("company_id", companyId);
            }
            UserInfo company= baseMapper.selectOne(wrapper);
            companyStatus.setFederatedStatus(company.getFederatedStatus());
            companyStatus.setCompanyName(company.getCompanyName());
            companyStatus.setIp(company.getIp());
        }

        return federatedStateVo;
    }


}