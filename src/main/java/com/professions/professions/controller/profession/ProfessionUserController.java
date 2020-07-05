package com.professions.professions.controller.profession;


import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Controller
@RequestMapping("profession")
public class ProfessionUserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 返回主页
     */
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main() {
        return "profession/main";
    }

    /**
     * 个人信息页面
     *
     * @return
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile(ModelMap map, HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        map.addAttribute("tbUser", tbUser);
        return "profession/profile";
    }

    /**
     * 修改个人信息
     * @param map
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "profile/update", method = RequestMethod.POST)
    public String profileUpdate(ModelMap map, @Valid TbUser tbUser, HttpServletRequest request) {
        TbUser tbUser1 = (TbUser) request.getSession().getAttribute("user");

        tbUser1.setUsername(tbUser.getUsername());
        tbUser1.setPhone(tbUser.getPhone());
        tbUser1.setEmail(tbUser.getEmail());

        tbUserService.updateById(tbUser1);
        return "redirect:/profession/profile";
    }

    /**
     * 修改个人密码
     * @param oldPassword
     * @param newPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "update/password", method = RequestMethod.POST)
    public String updatePassword(@RequestParam("oldPassword") String  oldPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        // 查询原密码是否符合
        if (tbUser.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            tbUser.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
            tbUserService.updateById(tbUser);
            request.getSession().invalidate();
            return "login";
        }
        return "redirect:/profession/profile";
    }

}

