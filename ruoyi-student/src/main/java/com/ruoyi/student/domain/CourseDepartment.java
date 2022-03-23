package com.ruoyi.student.domain;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 院系开课对象 sys_course_department
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public class CourseDepartment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long courseId;

    /** $column.columnComment */
    private Long departmentId;

    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
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
                .append("courseId", getCourseId())
                .append("departmentId", getDepartmentId())
                .toString();
    }
}
