package com.professions.professions.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.commons.dto.BaseResult;
import com.professions.professions.commons.dto.PageInfo;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        tbUser.setTitle("学院管理员");
        tbUser.setAuthority(AdminPermission.CollegeAdmin);
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
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
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUserService.saveOrUpdate(tbUser);
        return "redirect:/system/user/list";
    }

    /**
     * 分页查询
     * @param request
     * @param tbUser
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
     public Map<String, Object> page(HttpServletRequest request, TbUser tbUser) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("authority", AdminPermission.CollegeAdmin);


        int cont = tbUserService.count(wrapper);
        List<TbUser> userList = tbUserService.list(wrapper);
        result.put("draw", draw);
        result.put("recordsTotal",cont);
        result.put("recordsFiltered",  cont);
        result.put("data", userList);
        result.put("error","");
        return  result;
     }


    /**
     * 显示用户详情
     *
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Long id, ModelMap map) {
        map.addAttribute("detail", tbUserService.getById(id));
        return "system/detail";
    }


    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping( value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable  Long id) {
        tbUserService.removeById(id);
        return "redirect:/system/user/list";
    }

}
