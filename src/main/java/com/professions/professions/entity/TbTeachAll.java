package com.professions.professions.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 教学模块汇总表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbTeachAll implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 教学模块汇总id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容类目ID
     */
    private Long categoryId;

    /**
     * 课程性质
     */
    private String nature;

    /**
     * 百分比
     */
    private BigDecimal percent;

    /**
     * 学分
     */
    private BigDecimal credit;

    /**
     * 学时
     */
    private Integer time;

    /**
     * 理论学时
     */
    private Integer theoryTime;

    /**
     * 实践、实验学时
     */
    private Integer practiceTime;

    /**
     * 备注
     */
    private String comment;


}
