package com.professions.professions.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.commons.contant.CollegeName;
import com.professions.professions.commons.contant.ConstantUtils;
import com.professions.professions.entity.TbSchool;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbSchoolService;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Predicate;

@Controller
public class LoginController {

    @Autowired
    private TbSchoolService tbSchoolService;

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String login(ModelMap map) {
        TbSchool school = tbSchoolService.getById(CollegeName.NAME);
        String name = school.getName();
        map.addAttribute("school",name);
        return "login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(required = true) String usernumber, @RequestParam(required = true) String password,
                              HttpServletRequest httpServletRequest) {
        ModelAndView mv = new ModelAndView();

        TbUser user = tbUserService.getByUsername(usernumber, password);

        // 登录失败
        if (user == null) {
            mv.addObject("message","用户名或者密码错误，请重新输入");
            mv.setViewName("login");
            return mv;
        }
        // 登录成功
        else {
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            if (user.getAuthority() == AdminPermission.SystemAdmin) {
                mv.setViewName("redirect:/system/main");
            } else if(user.getAuthority() == AdminPermission.CollegeAdmin) {
                mv.setViewName("redirect:/college/main");
            } else if (user.getAuthority() == AdminPermission.ProfessionAdmin){
                mv.setViewName("redirect:/profession/main");
            }
            return mv;
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest, ModelMap map){
        httpServletRequest.getSession().invalidate();
        return login(map);
    }
}
