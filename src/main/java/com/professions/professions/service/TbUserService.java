package com.professions.professions.service;

import com.professions.professions.commons.dto.BaseResult;
import com.professions.professions.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 根据职工号查询 用户
     * @param usernumber
     * @return
     */
    TbUser getByUsername(String usernumber,String password);

    /**
     * 根据权限查询用户列表
     * @param authority
     * @return
     */
    List<TbUser> selectByAuthority(int authority);

    /**
     * 保持用户信息
     * @param tbUser
     * @return
     */
    BaseResult saveUser(TbUser tbUser);
}
