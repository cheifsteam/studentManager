package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程性质对象 sys_nature
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public class Nature extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 课程性质编号 */
    @Excel(name = "课程性质编号")
    private Long natureId;

    /** 课程性质名称 */
    @Excel(name = "课程性质名称")
    private String natureName;

    /** 课程属性(0为必修，1为选修) */
    @Excel(name = "课程属性(0为必修，1为选修)")
    private Integer natureType;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setNatureId(Long natureId)
    {
        this.natureId = natureId;
    }

    public Long getNatureId()
    {
        return natureId;
    }
    public void setNatureName(String natureName)
    {
        this.natureName = natureName;
    }

    public String getNatureName()
    {
        return natureName;
    }
    public void setNatureType(Integer natureType)
    {
        this.natureType = natureType;
    }

    public Integer getNatureType()
    {
        return natureType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("natureId", getNatureId())
                .append("natureName", getNatureName())
                .append("natureType", getNatureType())
                .append("remark", getRemark())
                .toString();
    }
}
