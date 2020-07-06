package com.professions.professions.controller.profession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.entity.TbContent;
import com.professions.professions.entity.TbContentCategory;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbContentCategoryService;
import com.professions.professions.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容描述管理
 */
@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    private TbContentService tbContentService;

    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    /**
     * 内容列表
     * @param map
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(ModelMap map) {
        return "profession/content_list";
    }

    /**
     * 创建内容描述
     * @param map
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String contentCreate(ModelMap map, HttpServletRequest request) {
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
        return "profession/content_create";
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

}
