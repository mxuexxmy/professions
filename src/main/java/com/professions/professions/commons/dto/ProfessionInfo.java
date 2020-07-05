package com.professions.professions.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业信息类
 */
@Data
public class ProfessionInfo implements Serializable {
    private Long id;
    private String college;
    private String profession;
    private String grade;
    private Date updated;
}
