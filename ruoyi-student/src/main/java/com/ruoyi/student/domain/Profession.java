package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 专业对象 sys_profession
 *
 * @author ruoyi
 * @date 2022-03-16
 */
public class Profession extends BaseEntity
{
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

    /** 院系ID */
    @Excel(name = "院系ID")
    private Long departmentId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProfessionId(Long professionId)
    {
        this.professionId = professionId;
    }

    public Long getProfessionId()
    {
        return professionId;
    }
    public void setProfessionName(String professionName)
    {
        this.professionName = professionName;
    }

    public String getProfessionName()
    {
        return professionName;
    }
    public void setProfessionDescription(String professionDescription)
    {
        this.professionDescription = professionDescription;
    }

    public String getProfessionDescription()
    {
        return professionDescription;
    }
    public void setDepartmentId(Long departmentId)
    {
        this.departmentId = departmentId;
    }

    public Long getDepartmentId()
    {
        return departmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("professionId", getProfessionId())
                .append("professionName", getProfessionName())
                .append("professionDescription", getProfessionDescription())
                .append("departmentId", getDepartmentId())
                .toString();
    }
}
