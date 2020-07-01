package com.professions.professions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 学校表
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TbSchool implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 学校ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 学校名
     */
    private String name;


}
