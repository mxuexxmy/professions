package com.professions.professions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 年级表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbGrade implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 年级ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 年级
     */
    private String name;

    /**
     * 学院ID
     */
    private Integer collegeId;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;


}
