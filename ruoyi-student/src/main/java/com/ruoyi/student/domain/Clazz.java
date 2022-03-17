package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级信息对象 sys_clazz
 *
 * @author ruoyi
 * @date 2022-03-16
 */
public class Clazz extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long clazzId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String clazzName;

    /** 专业编号 */
    @Excel(name = "专业编号")
    private Long professionId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setClazzId(Long clazzId)
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId()
    {
        return clazzId;
    }
    public void setClazzName(String clazzName)
    {
        this.clazzName = clazzName;
    }

    public String getClazzName()
    {
        return clazzName;
    }
    public void setProfessionId(Long professionId)
    {
        this.professionId = professionId;
    }

    public Long getProfessionId()
    {
        return professionId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("clazzId", getClazzId())
                .append("clazzName", getClazzName())
                .append("professionId", getProfessionId())
                .toString();
    }
}
