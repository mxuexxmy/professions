package com.professions.professions.controller.college;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.dto.ProfessionInfo;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbGrade;
import com.professions.professions.entity.TbProfession;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbGradeService;
import com.professions.professions.service.TbProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业管理
 */
@Controller
@RequestMapping("college/profession")
public class ProfessionController {

    @Autowired
    private TbProfessionService tbProfessionService;

    @Autowired
    private TbCollegeService tbCollegeService;

    @Autowired
    private TbGradeService tbGradeService;

    /**
     * 专业列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap  map) {
        return "college/profession_list";
    }

    /**
     * 创建专业
     * @param map
     * @param request
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(ModelMap map, HttpServletRequest request) {
        TbCollege tbCollege = getCollege(request);
        map.addAttribute("grades", getGrades(tbCollege.getId()));
        map.addAttribute("profession", new TbProfession());
        return "college/profession_create";
    }

    /**
     * 保存专业信息
     * @param map
     * @param request
     * @param tbProfession
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(ModelMap map, HttpServletRequest request,
                       @ModelAttribute @Valid TbProfession tbProfession,
                       @RequestParam("gradeId") Long gradeId) {

       tbProfession.setCollege(getCollege(request).getId());
       tbProfession.setGradeId(gradeId);
       tbProfessionService.save(tbProfession);
       return "redirect:/college/profession/list";
    }

    /**
     * 删除专业
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete (@PathVariable Long id) {
        tbProfessionService.removeById(id);
        return "redirect:/college/profession/list";
    }

    /**
     * 分页查询
     * @param request
     * @param tbProfession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public Map<String, Object> page(HttpServletRequest request, TbProfession tbProfession) {
        Map<String, Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");


        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        TbUser user = (TbUser) request.getSession().getAttribute("user");

        // 查询学院
        TbCollege tbCollege = getCollege(request);

        // 统计专业数
        QueryWrapper<TbProfession> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("college", tbCollege.getId());
        int cont = tbProfessionService.count(wrapper1);

        // 查询专业
        List<TbProfession> professions = tbProfessionService.list(wrapper1);

        List<ProfessionInfo> professionInfos = new ArrayList<>();
        // 转换专业信息
        for (TbProfession p : professions) {
            ProfessionInfo professionInfo = new ProfessionInfo();
            professionInfo.setCollege(tbCollege.getName());
            professionInfo.setProfession(p.getName());
            professionInfo.setGrade(getGrade(p.getGradeId()).getName());
            professionInfo.setId(p.getId());
            professionInfo.setUpdated(p.getUpdated());
            professionInfos.add(professionInfo);
        }

        result.put("draw", draw);
        result.put("recordsTotal", cont);
        result.put("recordsFiltered",  cont);
        result.put("data", professionInfos);
        result.put("error","");
        return  result;
    }

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
     * 查询年级
     * @param gradeID
     * @return
     */
    private TbGrade getGrade(Long gradeID) {
        QueryWrapper<TbGrade> wrapper = new QueryWrapper<>();
        wrapper.eq("id", gradeID);
        return  tbGradeService.getOne(wrapper);
    }

    /**
     * 获取学院年级
     * @param collegeID
     * @return
     */
    private List<TbGrade> getGrades(Long collegeID) {
        QueryWrapper<TbGrade> wrapper = new QueryWrapper<>();
        wrapper.eq("college_id", collegeID);
        return tbGradeService.list(wrapper);
    }

}
