package com.professions.professions.controller.profession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AuditUtils;
import com.professions.professions.commons.contant.ContentUtils;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbContentCategory;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbContentCategoryService;
import com.professions.professions.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专业培养方案管理
 */
@Controller
@RequestMapping("profession/manage")
public class ProfessionsManageController {

    @Autowired
    private TbContentService tbContentService;

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    /**
     * 专业培养方案目录
     * @param map
     * @return
     */
    @RequestMapping(value = "look", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "profession/professions_look";
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
     * 创建专业培养方案目录
     * @param map
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(ModelMap map) {

        return "profession/professions_create";
    }

    /**
     * 专业培养方案详情
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String detail(ModelMap map,HttpServletRequest request) {
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");

        QueryWrapper<TbContentCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", tbUser.getId())
                .orderByAsc("parent_id", "sort_order")
                .orderByDesc("is_parent");

        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = tbContentCategoryService.list(wrapper);

        // 排序
        sortList(sourceList, targetList, 0L);

        map.addAttribute("tbContentCategories",targetList);
        return "/profession/professions_list";
    }


    /**
     * 排序
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后的集合
     * @param parentId   父节点的 ID
     */
    protected void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList, Long parentId) {
        for (TbContentCategory sourceEntity : sourceList) {
            if (sourceEntity.getParentId().equals(parentId)) {
                targetList.add(sourceEntity);

                // 判断有没有子节点，如果有则继续追加
                if (sourceEntity.getIsParent()) {
                    for (TbContentCategory currentEntity : sourceList) {
                        if (currentEntity.getParentId().equals(sourceEntity.getId())) {
                            sortList(sourceList, targetList, sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
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
                .ge("audit", AuditUtils.COLLEGE_CHECKED);


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
