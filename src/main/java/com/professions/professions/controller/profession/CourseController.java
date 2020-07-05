package com.professions.professions.controller.profession;


import com.professions.professions.service.TbCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程信息管理
 */
@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private TbCourseService tbCourseService;

    /**
     * 课程信息列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {

        return "profession/course_list";
    }

}
