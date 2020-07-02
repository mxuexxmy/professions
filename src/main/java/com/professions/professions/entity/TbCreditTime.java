package com.professions.professions.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学分学时分配表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbCreditTime implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 内容类目ID
     */
    private Long categoryId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 学分
     */
    private BigDecimal credit;

    /**
     * 总学时
     */
    private Integer time;

    /**
     * 课程教学学时
     */
    private Integer classroom;

    /**
     * 实验、上机学时
     */
    private Integer experiment;

    /**
     * 实习、实践学时
     */
    private Integer practice;

    /**
     * 学期分配：1表示第一学期，2表示第二学期，以此类推
     */
    private Integer session;

    /**
     * 考核方式：考试、考察、证书等
     */
    private String check;

    /**
     * 备注
     */
    private String comment;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date created;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updated;

}
