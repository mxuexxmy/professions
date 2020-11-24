package com.professions.professions.controller.college;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AuditUtils;
import com.professions.professions.commons.contant.ContentUtils;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbContentCategory;
import com.professions.professions.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业培养方案管理
 */
@Controller
@RequestMapping("college/professions")
public class CollegeProfessionsController {

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "college/professions_list";
    }

    /**
     * 展示专业培养方案数据
     * @param map
     * @return
     */
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String showProfessions(ModelMap map) {

        return "system/professions_show";
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
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        QueryWrapper<TbContentCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", ContentUtils.PROFESSION_TITLE)
                .ge("audit", AuditUtils.UNCHECKED);



        int cont = tbContentCategoryService.count(wrapper);
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryService.list(wrapper);
        result.put("draw", draw);
        result.put("recordsTotal",cont );
        result.put("recordsFiltered",  cont);
        result.put("data", tbContentCategoryList);
        result.put("error","");
        return  result;
    }

}
