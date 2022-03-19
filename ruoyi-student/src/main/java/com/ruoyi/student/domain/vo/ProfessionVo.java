package com.ruoyi.student.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class ProfessionVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 专业ID */
    @Excel(name = "专业ID")
    private Long professionId;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String professionName;

    /** 专业描述 */
    @Excel(name = "专业描述")
    private String professionDescription;

    /** 所属院系 */
    @Excel(name = "所属院系")
    private String departmentName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getProfessionDescription() {
        return professionDescription;
    }

    public void setProfessionDescription(String professionDescription) {
        this.professionDescription = professionDescription;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
