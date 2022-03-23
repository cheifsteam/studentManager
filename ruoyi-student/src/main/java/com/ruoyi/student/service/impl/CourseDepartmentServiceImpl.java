package com.ruoyi.student.service.impl;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.CourseDepartmentMapper;
import com.ruoyi.student.domain.CourseDepartment;
import com.ruoyi.student.service.ICourseDepartmentService;

/**
 * 院系开课Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class CourseDepartmentServiceImpl implements ICourseDepartmentService
{
    @Autowired
    private CourseDepartmentMapper courseDepartmentMapper;

    /**
     * 查询院系开课
     *
     * @param courseId 院系开课主键
     * @return 院系开课
     */
    @Override
    public CourseDepartment selectCourseDepartmentByCourseId(Long courseId)
    {
        return courseDepartmentMapper.selectCourseDepartmentByCourseId(courseId);
    }

    /**
     * 查询院系开课列表
     *
     * @param courseDepartment 院系开课
     * @return 院系开课
     */
    @Override
    public List<CourseDepartment> selectCourseDepartmentList(CourseDepartment courseDepartment)
    {
        return courseDepartmentMapper.selectCourseDepartmentList(courseDepartment);
    }

    /**
     * 新增院系开课
     *
     * @param courseDepartment 院系开课
     * @return 结果
     */
    @Override
    public int insertCourseDepartment(CourseDepartment courseDepartment)
    {
        return courseDepartmentMapper.insertCourseDepartment(courseDepartment);
    }

    /**
     * 修改院系开课
     *
     * @param courseDepartment 院系开课
     * @return 结果
     */
    @Override
    public int updateCourseDepartment(CourseDepartment courseDepartment)
    {
        return courseDepartmentMapper.updateCourseDepartment(courseDepartment);
    }

    /**
     * 批量删除院系开课
     *
     * @param courseIds 需要删除的院系开课主键
     * @return 结果
     */
    @Override
    public int deleteCourseDepartmentByCourseIds(Long[] courseIds)
    {
        return courseDepartmentMapper.deleteCourseDepartmentByCourseIds(courseIds);
    }

    /**
     * 删除院系开课信息
     *
     * @param courseId 院系开课主键
     * @return 结果
     */
    @Override
    public int deleteCourseDepartmentByCourseId(Long courseId)
    {
        return courseDepartmentMapper.deleteCourseDepartmentByCourseId(courseId);
    }
    /**
     * 通过院系ID 查询院系开课列表
     * @param departmentId
     * @return
     */
    public List<CourseDepartment> selectCourseDepartmentByDepartmentId(Long departmentId){
        return courseDepartmentMapper.selectCourseDepartmentByDepartmentId(departmentId);
    }
}
