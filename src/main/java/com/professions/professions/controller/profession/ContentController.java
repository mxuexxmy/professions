package com.professions.professions.controller.profession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.contant.AuditUtils;
import com.professions.professions.commons.contant.ContentUtils;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbContent;
import com.professions.professions.entity.TbContentCategory;
import com.professions.professions.entity.TbUser;
import com.professions.professions.service.TbContentCategoryService;
import com.professions.professions.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String list(ModelMap map, HttpServletRequest request) {
        // 获取用户
        TbUser tbUser = (TbUser) request.getSession().getAttribute("user");

        // 查询目录列表
        QueryWrapper<TbContentCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", tbUser.getId());
        List<TbContentCategory> tbContentCategories = tbContentCategoryService.list(wrapper);

        // 查询内容列表
        List<TbContent> tbContents = new ArrayList<>();
        for (TbContentCategory contentCategory : tbContentCategories) {
            TbContent content = new TbContent();
            content = getContent(contentCategory.getId());

            if (content != null)
             tbContents.add(content);
        }
        map.addAttribute("contents", tbContents);
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
        map.addAttribute("content", new TbContent());
        return "profession/content_create";
    }

    /**
     * 保存内容描述信息
     * @param map
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveContent(ModelMap map, @ModelAttribute @Valid TbContent tbContent,
                              @RequestParam("name") Long name,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/content/create";
        }
        tbContent.setCategoryId(name);
        tbContentService.save(tbContent);
        return "redirect:/content/list";
    }

    /**
     * 查看内容详情
     * @param map
     * @return
     */
    @RequestMapping(value = "showContent/{id}", method = RequestMethod.GET)
    public String showContent(ModelMap map,@PathVariable Long id) {
         map.addAttribute("content", tbContentService.getById(id));
         return "profession/content_show";
    }

    /**
     * 编辑内容
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "contentUpdate/{id}", method = RequestMethod.GET)
    public String update(ModelMap map,@PathVariable Long id) {
        map.addAttribute("content", tbContentService.getById(id));
        return "profession/content_update";
    }

    /**
     * 保存编辑内容
     * @param map
     * @param content
     * @return
     */
    @RequestMapping(value = "contentUpdate", method = RequestMethod.GET)
    public String update(ModelMap map, @ModelAttribute @Valid TbContent content) {
        tbContentService.updateById(content);
        return "redirect:/content/list";
    }

    /**
     * 删除内容信息
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "contentDelete/{id}", method = RequestMethod.GET)
    public String contentDelete(ModelMap map, @PathVariable Long id) {
         tbContentService.removeById(id);
        return "redirect:/content/list";
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
     * 获取内容描述
     * @param id
     * @return
     */
    private TbContent getContent(Long id) {
        return tbContentService.getById(id);
    }

}
