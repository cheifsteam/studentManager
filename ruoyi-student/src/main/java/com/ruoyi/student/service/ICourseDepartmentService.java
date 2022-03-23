package com.ruoyi.student.service;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */

import java.util.List;
import com.ruoyi.student.domain.CourseDepartment;

/**
 * 院系开课Service接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface ICourseDepartmentService
{
    /**
     * 查询院系开课
     *
     * @param courseId 院系开课主键
     * @return 院系开课
     */
    public CourseDepartment selectCourseDepartmentByCourseId(Long courseId);

    /**
     * 查询院系开课列表
     *
     * @param courseDepartment 院系开课
     * @return 院系开课集合
     */
    public List<CourseDepartment> selectCourseDepartmentList(CourseDepartment courseDepartment);

    /**
     * 新增院系开课
     *
     * @param courseDepartment 院系开课
     * @return 结果
     */
    public int insertCourseDepartment(CourseDepartment courseDepartment);

    /**
     * 修改院系开课
     *
     * @param courseDepartment 院系开课
     * @return 结果
     */
    public int updateCourseDepartment(CourseDepartment courseDepartment);

    /**
     * 批量删除院系开课
     *
     * @param courseIds 需要删除的院系开课主键集合
     * @return 结果
     */
    public int deleteCourseDepartmentByCourseIds(Long[] courseIds);

    /**
     * 删除院系开课信息
     *
     * @param courseId 院系开课主键
     * @return 结果
     */
    public int deleteCourseDepartmentByCourseId(Long courseId);

    /**
     * 通过院系ID 查询院系开课列表
     * @param departmentId
     * @return
     */
    public List<CourseDepartment> selectCourseDepartmentByDepartmentId(Long departmentId);
}
