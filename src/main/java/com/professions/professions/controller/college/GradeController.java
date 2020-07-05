package com.professions.professions.controller.college;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AdminPermission;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbGrade;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 年级管理
 */
@Controller
@RequestMapping("college/grade")
public class GradeController {

    @Autowired
    private TbGradeService tbGradeService;

    @Autowired
    private TbCollegeService tbCollegeService;

    /**
     * 获取学院
     * @param request
     * @return
     */
    private TbCollege getCollege(HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");
        QueryWrapper<TbCollege> wrapper = new QueryWrapper<>();
        wrapper.eq("name", tbUser.getCollege());
        return tbCollegeService.getOne(wrapper);
    }

    /**
     * 年级列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "college/grade_list";
    }

    /**
     * 创建年级
     * @param map
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(ModelMap map) {
        map.addAttribute("tbGrade", new TbGrade());
        return "college/grade_create";
    }

    /**
     * 保存年级
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ModelMap map, HttpServletRequest request, @ModelAttribute @Valid TbGrade tbGrade) {
        TbCollege tbCollege = getCollege(request);
        tbGrade.setCollegeId(tbCollege.getId());

        tbGradeService.save(tbGrade);
       return "redirect:/college/grade/list";
    }

    /**
     * 删除年级
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        tbGradeService.removeById(id);
        return "redirect:/college/grade/list";
    }

    /**
     * 分页查询
     * @param request
     * @param tbGrade
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Map<String, Object> page(HttpServletRequest request, TbGrade tbGrade) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");


        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 4 : Integer.parseInt(strLength);

        QueryWrapper<TbGrade> wrapper = new QueryWrapper<>();

        TbCollege tbCollege =  getCollege(request);

        wrapper.eq("college_id", tbCollege.getId());
        int cont = tbGradeService.count(wrapper);
        List<TbGrade> userList = tbGradeService.list(wrapper);
        result.put("draw", draw);
        result.put("recordsTotal",cont);
        result.put("recordsFiltered",  cont);
        result.put("data", userList);
        result.put("error","");
        return  result;
    }
}
