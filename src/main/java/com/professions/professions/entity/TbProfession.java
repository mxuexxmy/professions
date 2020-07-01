package com.professions.professions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 专业表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbProfession implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 专业ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院名
     */
    private Long college;

    /**
     * 专业名
     */
    private String name;

    /**
     * 年级ID
     */
    private Long gradeId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;


}
