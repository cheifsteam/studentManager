package com.ruoyi.student.domain.vo;

import com.ruoyi.common.annotation.Excel;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class CourseVo {
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

    private  Long departmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Long courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Long getCourseTheoreticalHours() {
        return courseTheoreticalHours;
    }

    public void setCourseTheoreticalHours(Long courseTheoreticalHours) {
        this.courseTheoreticalHours = courseTheoreticalHours;
    }

    public Long getCoursePracticalHours() {
        return coursePracticalHours;
    }

    public void setCoursePracticalHours(Long coursePracticalHours) {
        this.coursePracticalHours = coursePracticalHours;
    }

    public Integer getCourseAssessmentMethods() {
        return courseAssessmentMethods;
    }

    public void setCourseAssessmentMethods(Integer courseAssessmentMethods) {
        this.courseAssessmentMethods = courseAssessmentMethods;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
