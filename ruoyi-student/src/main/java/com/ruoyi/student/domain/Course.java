package com.ruoyi.student.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程信息对象 sys_course
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private Long courseCredit;

    /** 理论学时 */
    @Excel(name = "理论学时")
    private Long courseTheoreticalHours;

    /** 实践学时 */
    @Excel(name = "实践学时")
    private Long coursePracticalHours;

    /** 考核方式(0为考试，1为考查) */
    @Excel(name = "考核方式(0为考试，1为考查)")
    private Integer courseAssessmentMethods;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    private Long departmentId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public void setCourseCredit(Long courseCredit)
    {
        this.courseCredit = courseCredit;
    }

    public Long getCourseCredit()
    {
        return courseCredit;
    }
    public void setCourseTheoreticalHours(Long courseTheoreticalHours)
    {
        this.courseTheoreticalHours = courseTheoreticalHours;
    }

    public Long getCourseTheoreticalHours()
    {
        return courseTheoreticalHours;
    }
    public void setCoursePracticalHours(Long coursePracticalHours)
    {
        this.coursePracticalHours = coursePracticalHours;
    }

    public Long getCoursePracticalHours()
    {
        return coursePracticalHours;
    }
    public void setCourseAssessmentMethods(Integer courseAssessmentMethods)
    {
        this.courseAssessmentMethods = courseAssessmentMethods;
    }

    public Integer getCourseAssessmentMethods()
    {
        return courseAssessmentMethods;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("courseId", getCourseId())
                .append("courseName", getCourseName())
                .append("courseCredit", getCourseCredit())
                .append("courseTheoreticalHours", getCourseTheoreticalHours())
                .append("coursePracticalHours", getCoursePracticalHours())
                .append("courseAssessmentMethods", getCourseAssessmentMethods())
                .toString();
    }
}
