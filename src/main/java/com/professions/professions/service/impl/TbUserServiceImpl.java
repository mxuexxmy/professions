package com.professions.professions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.commons.dto.BaseResult;
import com.professions.professions.commons.dto.PageInfo;
import com.professions.professions.entity.TbUser;
import com.professions.professions.mapper.TbUserMapper;
import com.professions.professions.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TbUser getByUsername(String usernumber, String password) {
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("usernumber",usernumber);
        TbUser user=  userMapper.selectOne(wrapper);
        if (user != null) {
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            // 判断密码是否相等
            if (md5Password.equals(user.getPassword())) {
                return user;
            }
        }
        return  null;
    }

    @Override
    public List<TbUser> selectByAuthority(int authority) {
        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("authority", authority);
        return userMapper.selectList(wrapper);
    }

    @Override
    public BaseResult saveUser(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);

        // 通过验证
        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS) {
            tbUser.setUpdated(new Date());

            if(tbUser.getId() == null) {
                //加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUser.setAuthority(AdminPermission.CollegeAdmin);
                tbUser.setTitle("学院管理员");
                userMapper.insert(tbUser);
            } else {
                userMapper.updateById(tbUser);
            }
            baseResult.setMessage("添加用户成功"); ;
        }
        return baseResult;
    }

    /**
     * 用户信息的有效性验证
     */
    private BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();
        if (StringUtils.isBlank(tbUser.getUsername())){
            baseResult =  BaseResult.fail("姓名不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getCollege())) {
            baseResult =  BaseResult.fail("院系不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getUsernumber())) {
            baseResult =  BaseResult.fail("职工号不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult =  BaseResult.fail("电话不能为空,请重新输入");
        } else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult =  BaseResult.fail("密码不能为空,请重新输入");
        }
        return baseResult;
    }
}
