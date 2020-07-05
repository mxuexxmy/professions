package com.professions.professions.controller.profession;

import com.professions.professions.service.TbTeachAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 教学模块汇总
 */
@Controller
@RequestMapping("teachAll")
public class TeachAllController {

    @Autowired
    private TbTeachAllService tbTeachAllService;

    /**
     * 教学模块列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {

        return "profession/teach_all_list";
    }
}
