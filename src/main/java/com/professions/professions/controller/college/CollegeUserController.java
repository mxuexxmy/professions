package com.professions.professions.controller.college;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Controller
@RequestMapping("college")
public class CollegeUserController {
    /**
     * 返回主页
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    /**
     * 个人信息页面
     * @return
     */
    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String profile() {
        return "profile";
    }
}

