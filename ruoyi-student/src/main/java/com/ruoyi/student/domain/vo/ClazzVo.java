package com.ruoyi.student.domain.vo;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class ClazzVo {
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long clazzId;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String clazzName;
    /** 专业编号*/
    @Excel(name =  "专业编号")
    private String professionId;
    /** 所属专业 */
    @Excel(name = "所属专业")
    private String professionName;
    /** 所属院系    */
    @Excel(name = "所属院系")
    private String  departmentName;

    private Long departmentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

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

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "ClazzVo{" +
                "id=" + id +
                ", clazzId=" + clazzId +
                ", clazzName='" + clazzName + '\'' +
                ", professionId='" + professionId + '\'' +
                ", professionName='" + professionName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
