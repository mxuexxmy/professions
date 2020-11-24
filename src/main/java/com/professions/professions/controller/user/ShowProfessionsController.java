package com.professions.professions.controller.user;

import com.professions.professions.entity.TbContentCategory;
import com.professions.professions.service.TbContentCategoryService;
import com.professions.professions.service.TbProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("profession")
public class ShowProfessionsController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @Autowired
    private TbProfessionService tbProfessionService;

    /**
     * 展示专业内容
     * @param map
     * @return
     */
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showProfession(ModelMap map){

        return "user/professions";
    }

}
