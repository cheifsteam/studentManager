package com.ruoyi.student.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class StudentVo {
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 学号 */
    @Excel(name = "学号")
    private Long studentId;
    /** 所属院系    */
    @Excel(name = "所属院系")
    private String  departmentName;
    /** 所属专业 */
    @Excel(name = "所属专业")
    private String professionName;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String clazzName;
    private Long professionId;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String studentName;

    /** 学生年龄 */
    @Excel(name = "学生年龄")
    private Integer studentAge;

    /** 性别(0为男，1为女) */
    @Excel(name = "性别(0为男，1为女)")
    private String studentSex;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date studentBirthday;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy")
    private Date studentEnrollmentTime;

    /** 学生电话 */
    @Excel(name = "学生电话")
    private String studentPhonenumber;

    /** 学生家庭地址 */
    @Excel(name = "学生家庭地址")
    private String studentAddress;

    /** 关键联系人 */
    @Excel(name = "关键联系人")
    private String studentKeyContacts;

    /** 关键联系人号码 */
    @Excel(name = "关键联系人号码")
    private String studentKeyContactsPhonenumber;

    private Long clazzId;
    private Long departmentId;

    private Long userId;
    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public Date getStudentBirthday() {
        return studentBirthday;
    }

    public void setStudentBirthday(Date studentBirthday) {
        this.studentBirthday = studentBirthday;
    }

    public Date getStudentEnrollmentTime() {
        return studentEnrollmentTime;
    }

    public void setStudentEnrollmentTime(Date studentEnrollmentTime) {
        this.studentEnrollmentTime = studentEnrollmentTime;
    }

    public String getStudentPhonenumber() {
        return studentPhonenumber;
    }

    public void setStudentPhonenumber(String studentPhonenumber) {
        this.studentPhonenumber = studentPhonenumber;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentKeyContacts() {
        return studentKeyContacts;
    }

    public void setStudentKeyContacts(String studentKeyContacts) {
        this.studentKeyContacts = studentKeyContacts;
    }

    public String getStudentKeyContactsPhonenumber() {
        return studentKeyContactsPhonenumber;
    }

    public void setStudentKeyContactsPhonenumber(String studentKeyContactsPhonenumber) {
        this.studentKeyContactsPhonenumber = studentKeyContactsPhonenumber;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
