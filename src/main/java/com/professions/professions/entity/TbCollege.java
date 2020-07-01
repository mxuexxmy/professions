package com.professions.professions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学院信息表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbCollege implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 学院表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学院名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;


}
