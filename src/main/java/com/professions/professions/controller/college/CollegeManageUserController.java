package com.professions.professions.controller.college;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbGrade;
import com.professions.professions.entity.TbProfession;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbGradeService;
import com.professions.professions.service.TbProfessionService;
import com.professions.professions.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping("college/user")
public class CollegeManageUserController {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbProfessionService tbProfessionService;

    @Autowired
    private TbGradeService tbGradeService;

    @Autowired
    private TbCollegeService tbCollegeService;

    /**
     * 用户列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "college/user_list";
    }

    /**
     * 显示创建用户表单
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String createUserForm(ModelMap map, HttpServletRequest request) {
        TbCollege tbCollege = getCollege(request);

        map.addAttribute("professions", getProfessions(tbCollege.getId()));
        map.addAttribute("grades", getGrades(tbCollege.getId()));
        map.addAttribute("tbUser", new TbUser());
        map.addAttribute("actions", "form");
        map.addAttribute("inf", "新建用户");
        return "college/user_form";
    }

    /**
     *  创建用户
     *    处理 "/users" 的 POST 请求，用来获取用户列表
     *    通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String postUser(ModelMap map, HttpServletRequest request,
                           @ModelAttribute @Valid TbUser tbUser,
                           @RequestParam("profession") String profession,
                           @RequestParam("grade") String grade,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("actions", "form");
            map.addAttribute("inf", "新建用户");
            return "college/user_form";
        }
        TbUser tbUser1 = (TbUser) request.getSession().getAttribute("user");
        tbUser.setCollege(tbUser1.getCollege());
        tbUser.setTitle(grade + profession);
        tbUser.setAuthority(AdminPermission.ProfessionAdmin);
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        System.out.println(tbUser);
        tbUserService.save(tbUser);
        return "redirect:/college/user/list";
    }

    /**
     * 显示需要更新用户表单
     *    处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map, HttpServletRequest request) {
        map.addAttribute("tbUser", tbUserService.getById(id));
        map.addAttribute("actions", "update");
        map.addAttribute("inf", "修改用户");
        return "college/user_form";
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
            return "college/user_form";
        }
        tbUserService.saveOrUpdate(tbUser);
        return "redirect:/college/user/list";
    }

    /**
     * 显示用户详情
     *
     * @return
     */
    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Long id, ModelMap map) {
        map.addAttribute("detail", tbUserService.getById(id));
        return "college/detail";
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
        return "redirect:/college/user/list";
    }

    /**
     * 查询学院
     * @param request
     * @return
     */
    private TbCollege getCollege(HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");

        QueryWrapper<TbCollege> wrapper = new QueryWrapper<>();
        wrapper.eq("name", tbUser.getCollege());

        return  tbCollegeService.getOne(wrapper);
    }

    /**
     * 根据学院 ID 获取专业
     * @param collegeId
     * @return
     */
    private List<TbProfession> getProfessions(Long collegeId) {
      QueryWrapper<TbProfession> wrapper = new QueryWrapper<>();
      wrapper.eq("college", collegeId);

      return tbProfessionService.list(wrapper);
    }

    private List<TbGrade> getGrades(Long collegeId) {
      QueryWrapper<TbGrade> wrapper = new QueryWrapper<>();
      wrapper.eq("college_id", collegeId);

      return tbGradeService.list(wrapper);
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

        TbUser tbUser1 = (TbUser) request.getSession().getAttribute("user");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("authority", AdminPermission.ProfessionAdmin)
                .eq("college", tbUser1.getCollege());

        int cont = tbUserService.count(wrapper);
        List<TbUser> userList = tbUserService.list(wrapper);
        result.put("draw", draw);
        result.put("recordsTotal",cont);
        result.put("recordsFiltered",  cont);
        result.put("data", userList);
        result.put("error","");
        return  result;
    }

}
