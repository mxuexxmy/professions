package com.professions.professions.controller.profession;

import com.professions.professions.entity.TbContent;
import com.professions.professions.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.xml.ws.Action;

/**
 * 内容描述管理
 */
@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    /**
     * 内容列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "profession/content_list";
    }
}
