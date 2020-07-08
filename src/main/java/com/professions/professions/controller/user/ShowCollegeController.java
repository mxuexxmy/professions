package com.professions.professions.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.professions.professions.commons.dto.ProfessionInfo;
import com.professions.professions.entity.TbCollege;
import com.professions.professions.entity.TbGrade;
import com.professions.professions.entity.TbProfession;
import com.professions.professions.service.TbCollegeService;
import com.professions.professions.service.TbGradeService;
import com.professions.professions.service.TbProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示学院
 */
@Controller
@RequestMapping("show")
public class ShowCollegeController {

    @Autowired
    private TbCollegeService tbCollegeService;

    @Autowired
    private TbProfessionService tbProfessionService;

    @Autowired
    private TbGradeService tbGradeService;

    @RequestMapping(value = "college/{id}",method = RequestMethod.GET)
    public String list(ModelMap map, @PathVariable Long id) {
        TbCollege tbCollege = tbCollegeService.getById(id);

        map.addAttribute("college", tbCollege);

        QueryWrapper<TbProfession> wrapper = new QueryWrapper<>();
        wrapper.eq("college", tbCollege.getId());

        List<TbProfession> tbProfessions = tbProfessionService.list(wrapper);

        List<ProfessionInfo> professionInfos = new ArrayList<>();
        // 转换专业信息
        for (TbProfession p : tbProfessions) {
            ProfessionInfo professionInfo = new ProfessionInfo();
            professionInfo.setCollege(tbCollege.getName());
            professionInfo.setProfession(p.getName());
            professionInfo.setGrade(getGrade(p.getGradeId()).getName());
            professionInfo.setId(p.getId());
            professionInfo.setUpdated(p.getUpdated());
            professionInfos.add(professionInfo);
        }

        map.addAttribute("professionInfos", professionInfos);
        return "user/college";
    }

    /**
     * 查询年级
     * @param gradeID
     * @return
     */
    private TbGrade getGrade(Long gradeID) {
        QueryWrapper<TbGrade> wrapper = new QueryWrapper<>();
        wrapper.eq("id", gradeID);
        return  tbGradeService.getOne(wrapper);
    }


}
