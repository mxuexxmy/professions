package com.professions.professions.controller.system;


import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Controller
@RequestMapping("system")
public class SystemUserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 返回主页
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "system/main";
    }

    /**
     * 个人信息页面
     * @return
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile() {
        return "system/profile";
    }


}

