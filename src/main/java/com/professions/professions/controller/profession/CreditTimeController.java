package com.professions.professions.controller.profession;

import com.professions.professions.service.TbCreditTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 学分学时管理
 */
@Controller
@RequestMapping("creditTime")
public class CreditTimeController {

    @Autowired
    private TbCreditTimeService tbCreditTimeService;

    /**
     * 学分学时列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {

        return "profession/credit_time_list";
    }
}
