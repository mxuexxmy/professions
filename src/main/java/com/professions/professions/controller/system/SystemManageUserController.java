package com.professions.professions.controller.system;

import com.professions.professions.commons.dto.BaseResult;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 系统管理员；用户管理控制
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Controller
@RequestMapping("system/user")
public class SystemManageUserController {

    @Autowired
    private TbUserService tbUserService;


    /**
     * 用户列表页面
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String detail(HttpSession httpSession, ModelMap map) {
        TbUser user = (TbUser) httpSession.getAttribute("user");
        map.addAttribute("users",tbUserService.selectByAuthority(user.getAuthority() + 1));
        return "system/user_list";
    }

    /**
     * 显示创建用户表单
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        map.addAttribute("tbUser", new TbUser());
        map.addAttribute("actions", "form");
        map.addAttribute("inf", "新建用户");
        return "system/user_form";
    }

    /**
     *  创建用户
     *    处理 "/users" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String postUser(ModelMap map,
                           @ModelAttribute @Valid TbUser tbUser,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("actions", "form");
            map.addAttribute("inf", "新建用户");
            return "system/user_form";
        }

        tbUserService.save(tbUser);

        return "redirect:/system/user/list";
    }

    /**
     * 显示需要更新用户表单
     *    处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("tbUser", tbUserService.getById(id));
        map.addAttribute("actions", "update");
        map.addAttribute("inf", "修改用户");
        return "system/user_form";
    }

    /**
     * 处理 "/users/{id}" 的 PUT 请求，用来更新 User 信息
     *
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(ModelMap map,
                          @ModelAttribute @Valid TbUser tbUser,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("actions", "update");
            map.addAttribute("inf", "修改用户");
            return "system/user_form";
        }

        tbUserService.saveOrUpdate(tbUser);
        return "redirect:/system/user/list";
    }



//    /**
//     * 新建用户或编辑用户
//     */
//    @RequestMapping(value ="form", method = RequestMethod.GET)
//    public ModelAndView user_form( HttpServletRequest request, BaseResult baseResult) {
//        ModelAndView mv  = new ModelAndView();
//        mv.addObject(new TbUser());
//        mv.addObject("baseResult",baseResult);
//        mv.setViewName("system/user_form");
//        return mv;
//    }
//
//    /**
//     * 新建用户或编辑用户
//     */
//    @RequestMapping(value ="form/{id}", method = RequestMethod.GET)
//    public ModelAndView user_form(@PathVariable("id") Long id, HttpServletRequest request, BaseResult baseResult) {
//        ModelAndView mv  = new ModelAndView();
//        System.out.println(tbUserService.getById(id));
//        mv.addObject("tbUser",  tbUserService.getById(id) );
//        mv.addObject("baseResult",baseResult);
//        mv.setViewName("system/user_form");
//        return mv;
//    }
//
//    /**
//     * 用户信息保持
//     */
//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute TbUser tbUser){
//        ModelAndView mv = new ModelAndView();
//        BaseResult baseResult=  tbUserService.saveUser(tbUser);
//        // 保存成功
//        if (BaseResult.STATUS_SUCCESS == baseResult.getStatus()) {
//            mv.addObject("baseResult", baseResult);
//            mv.setViewName("redirect:/system/user/list");
//            return mv;
//        }
//        // 保持失败
//        else {
//            mv.addObject("baseResult",baseResult);
//            mv.setViewName("system/user_form");
//            return mv;
//        }
//    }


    /**
     * 用户信息详情
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(ModelMap map) {
        return "detail";
    }



}
