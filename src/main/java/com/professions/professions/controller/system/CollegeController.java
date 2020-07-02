package com.professions.professions.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbCollegeService;
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
 * 学院管理
 */
@Controller
@RequestMapping("system/college")
public class CollegeController {

    @Autowired
    private TbCollegeService tbCollegeService;

    /**
     * 学院列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "system/college_list";
    }

    /**
     * 新建学院
     * @param map
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(ModelMap map) {
        map.addAttribute("tbCollege", new TbCollege());
        map.addAttribute("actions", "create");
        map.addAttribute("inf", "新建学院");
        return "system/college_form";
    }

    /**
     * 保存学院
     * @param map
     * @param tbCollege
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(ModelMap map, @ModelAttribute @Valid TbCollege tbCollege, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/system/college/form";
        }

        tbCollegeService.save(tbCollege);
        map.addAttribute("message", "添加学院成功");

        return "redirect:/system/college/list";
    }

    /**
     * 删除学院
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
     public String delete(@PathVariable Long id, ModelMap map) {
        tbCollegeService.removeById(id);
        return "redirect:/system/college/list";
     }


    /**
     * 显示需要更新用户表单
     *    处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("tbCollege", tbCollegeService.getById(id));
        map.addAttribute("actions", "update");
        map.addAttribute("inf", "修改学院");
        return "system/college_form";
    }

    /**
     * 更新学院
     * @param map
     * @param tbCollege
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String putUser(ModelMap map,
                          @ModelAttribute @Valid TbCollege tbCollege,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            map.addAttribute("actions", "update");
            map.addAttribute("inf", "修改学院");
            return "system/college_form";
        }

        tbCollegeService.saveOrUpdate(tbCollege);
        return "redirect:/system/college/list";
    }

    /**
     * 分页查询
     * @param request
     * @param tbCollege
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Map<String, Object> page(HttpServletRequest request, TbCollege tbCollege) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 5 : Integer.parseInt(strLength);

        QueryWrapper<TbUser> wrapper = new QueryWrapper<>();
        wrapper.eq("authority", AdminPermission.CollegeAdmin);


        int cont = tbCollegeService.count();
        List<TbCollege> tbColleges = tbCollegeService.list();
        result.put("draw", draw);
        result.put("recordsTotal",cont );
        result.put("recordsFiltered",  cont);
        result.put("data", tbColleges);
        result.put("error","");
        return  result;
    }

}
