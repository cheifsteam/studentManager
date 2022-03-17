package com.ruoyi.student.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 sys_student
 * 
 * @author ruoyi
 * @date 2022-03-16
 */
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private Long studentId;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
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

    /** 用户id */
    private Long userId;

    /** 班级编号 */
    private Long clazzId;

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setStudentAge(Integer studentAge) 
    {
        this.studentAge = studentAge;
    }

    public Integer getStudentAge() 
    {
        return studentAge;
    }
    public void setStudentSex(String studentSex) 
    {
        this.studentSex = studentSex;
    }

    public String getStudentSex() 
    {
        return studentSex;
    }
    public void setStudentBirthday(Date studentBirthday) 
    {
        this.studentBirthday = studentBirthday;
    }

    public Date getStudentBirthday() 
    {
        return studentBirthday;
    }
    public void setStudentEnrollmentTime(Date studentEnrollmentTime) 
    {
        this.studentEnrollmentTime = studentEnrollmentTime;
    }

    public Date getStudentEnrollmentTime() 
    {
        return studentEnrollmentTime;
    }
    public void setStudentPhonenumber(String studentPhonenumber) 
    {
        this.studentPhonenumber = studentPhonenumber;
    }

    public String getStudentPhonenumber() 
    {
        return studentPhonenumber;
    }
    public void setStudentAddress(String studentAddress) 
    {
        this.studentAddress = studentAddress;
    }

    public String getStudentAddress() 
    {
        return studentAddress;
    }
    public void setStudentKeyContacts(String studentKeyContacts) 
    {
        this.studentKeyContacts = studentKeyContacts;
    }

    public String getStudentKeyContacts() 
    {
        return studentKeyContacts;
    }
    public void setStudentKeyContactsPhonenumber(String studentKeyContactsPhonenumber) 
    {
        this.studentKeyContactsPhonenumber = studentKeyContactsPhonenumber;
    }

    public String getStudentKeyContactsPhonenumber() 
    {
        return studentKeyContactsPhonenumber;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setClazzId(Long clazzId) 
    {
        this.clazzId = clazzId;
    }

    public Long getClazzId() 
    {
        return clazzId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("studentAge", getStudentAge())
            .append("studentSex", getStudentSex())
            .append("studentBirthday", getStudentBirthday())
            .append("studentEnrollmentTime", getStudentEnrollmentTime())
            .append("studentPhonenumber", getStudentPhonenumber())
            .append("studentAddress", getStudentAddress())
            .append("studentKeyContacts", getStudentKeyContacts())
            .append("studentKeyContactsPhonenumber", getStudentKeyContactsPhonenumber())
            .append("userId", getUserId())
            .append("clazzId", getClazzId())
            .toString();
    }
}
