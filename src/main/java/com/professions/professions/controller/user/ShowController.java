package com.professions.professions.controller.user;

import com.professions.professions.entity.TbSchool;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbGradeService;
import com.professions.professions.service.TbSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示 用户 首页
 */
@Controller
@RequestMapping("show")
public class ShowController {

    @Autowired
    private TbSchoolService tbSchoolService;

    @Autowired
    private TbCollegeService tbCollegeService;




}
