package com.professions.professions.controller.user;

import com.professions.professions.commons.contant.CollegeName;
import com.professions.professions.commons.contant.ConstantUtils;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 展示 学校 首页
 */
@Controller
public class ShowSchoolController {

    @Autowired
    private TbSchoolService tbSchoolService;

    @Autowired
    private TbCollegeService tbCollegeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String show(ModelMap map, HttpServletRequest request) {
        request.getSession().setAttribute(ConstantUtils.SCHOOL_NAME, tbSchoolService.getById(CollegeName.NAME));
        map.addAttribute("colleges", tbCollegeService.list());
        return "user/school";
    }

}
