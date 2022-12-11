package idata.platform.privacy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import idata.platform.privacy.common.ResultCodeEnum;
import idata.platform.privacy.common.exception.PrivacyException;
import idata.platform.privacy.common.util.IdRandomUtils;
import idata.platform.privacy.mapper.ResourceMapper;
import idata.platform.privacy.models.resource.Resource;
import idata.platform.privacy.models.user.UserInfo;
import idata.platform.privacy.service.ResourceService;
import idata.platform.privacy.service.UserInfoService;
import idata.platform.privacy.models.vo.resource.ResourceQueryVo;
import idata.platform.privacy.models.vo.resource.ResourceUpdateVo;
import idata.platform.privacy.models.vo.resource.ResourceUploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.*;
import static org.springframework.util.StringUtils.isEmpty;

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Page<Resource> selectResourcePage(Page<Resource> pageParam, ResourceQueryVo resourceQueryVo) {
        String name = resourceQueryVo.getResourceName();
        String type = resourceQueryVo.getResourceType();
        String scene = resourceQueryVo.getScene();
        String createUser = resourceQueryVo.getCreateUser();
        String keywords = resourceQueryVo.getKeyWords();
        String companyId = resourceQueryVo.getCompanyId();
        String companyName = resourceQueryVo.getCompanyName();

        //对条件值进行非空判断
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        //-1表示查询所有公司
        if(!isEmpty(companyId) && !companyId.equals("-1")){
            wrapper.eq("company_id", companyId);
        }

        if(!isEmpty(type)) {
            wrapper.eq("resource_type",type);
        }
        if(!isEmpty(keywords)) {
            wrapper.like("keywords",keywords);
        }
        if(!isEmpty(scene)) {
            wrapper.like("use_scene",scene);
        }

        if(!isEmpty(name)) {
            wrapper.like("resource_name",name);
        }

        if(!isEmpty(createUser)) {
            wrapper.eq("create_user",createUser);
        }

        if(!isEmpty(companyName)) {
            wrapper.eq("company_name",companyName);
        }


        //调用mapper的方法
        Page<Resource> pages = baseMapper.selectPage(pageParam, wrapper);

        return pages;
    }

    @Override
    public Map<String, Object> uploadResource(ResourceUploadVo resourceUploadVo) {
        String companyId = resourceUploadVo.getCompanyId();
        String createUser= resourceUploadVo.getCreateUser();
        String resName = resourceUploadVo.getResourceName();
        String resDesc = resourceUploadVo.getResourceDesc();
        String resInfo = resourceUploadVo.getResourceInfo();
        String publicLevel = resourceUploadVo.getPublicLevel();
        List<String> keywords = resourceUploadVo.getKeywords();

        String trainPath = resourceUploadVo.getResourceTrainPath();
        String testPath = resourceUploadVo.getResourceTestPath();
        List<UserInfo> memberList = resourceUploadVo.getMemberList();

        String type = resourceUploadVo.getResourceType();
        String id = "res" + IdRandomUtils.getRandomID().toString();

        //根据公司id查找公司信息
        UserInfo company = userInfoService.getCompanyById(companyId);
        if(company == null){
            throw new PrivacyException(ResultCodeEnum.COMPANY_NOT_EXIST);
        }

        Resource resource = new Resource();
        List<String> companyList = new ArrayList<>();
        if(memberList.size() > 0){
            for(UserInfo name : memberList){
                companyList.add(name.getCompanyId());
            }
        }

        resource.setMemberList(String.join("、", companyList));
        resource.setCompanyId(companyId);
        resource.setCompanyName(company.getCompanyName());
        resource.setKeywords(String.join("、", keywords));
        resource.setCreateUser(createUser);
        resource.setCompanyId(companyId);
        resource.setResourceName(resName);
        resource.setResourceDesc(resDesc);
        resource.setResourceInfo(resInfo);
        resource.setResourceTrainPath(trainPath);
        resource.setResourceTestPath(testPath);
        resource.setPublicLevel(publicLevel);
        resource.setResourceType(type);
        resource.setResourceId(id);
        baseMapper.insert(resource);
        return null;
    }

    @Override
    public List<Resource> queryResourceByBatchId(List<String> idList) {

        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        List<Resource> resList = null;
        if(idList.size() > 0 ) {
            wrapper.in("resource_id",idList);
            resList = baseMapper.selectList(wrapper);
        }


        return resList;
    }

    @Override
    public String updateResource(ResourceUpdateVo resourceUpdateVo) {
        String resId = resourceUpdateVo.getResourceId();
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id",resId);
        Resource res = baseMapper.selectOne(wrapper);
        if(res == null){
            throw new PrivacyException(ResultCodeEnum.RESOURCE_NOT_EXIST);
        }

        Resource updateRes = new Resource();
        String resName = resourceUpdateVo.getResourceName();
        String resDesc = resourceUpdateVo.getResourceDesc();
        String resInfo = resourceUpdateVo.getResourceInfo();
        String keywords = resourceUpdateVo.getKeywords();

        String trainPath = resourceUpdateVo.getResourceTrainPath();
        String testPath = resourceUpdateVo.getResourceTestPath();

        String member = resourceUpdateVo.getMemberList();
        String type = resourceUpdateVo.getResourceType();

        updateRes.setResourceId(resId);
        updateRes.setKeywords(keywords);
        updateRes.setResourceInfo(resInfo);
        updateRes.setResourceName(resName);
        updateRes.setResourceDesc(resDesc);
        updateRes.setResourceTrainPath(trainPath);
        updateRes.setResourceTestPath(testPath);

        updateRes.setResourceType(type);
        int row = baseMapper.updateById(updateRes);


        return resId;
    }

    @Override
    public String deleteResource(String resourceId) {
//        System.out.println(resourceId);
        if(resourceId.equals("")){
            throw new PrivacyException(ResultCodeEnum.PARAM_ERROR);
        }
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq("resource_id",resourceId);

        int row = baseMapper.delete(wrapper);
//        System.out.println(row);
        return null;
    }

    @Override
    public String updateResourceCount(String resourceId, Integer count) {
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        if(!isEmpty(resourceId)) {
            wrapper.eq("resource_id",resourceId);
        }

        Resource resUpdate = new Resource();
        resUpdate.setProjectNum(count);
        baseMapper.update(resUpdate, wrapper);
        return null;
    }


}
