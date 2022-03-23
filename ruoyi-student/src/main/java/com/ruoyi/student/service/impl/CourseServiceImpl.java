package com.ruoyi.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.student.domain.CourseDepartment;
import com.ruoyi.student.mapper.CourseDepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.CourseMapper;
import com.ruoyi.student.domain.Course;
import com.ruoyi.student.service.ICourseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-21
 */
@Service
public class CourseServiceImpl implements ICourseService
{
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDepartmentMapper courseDepartmentMapper;

    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
    @Override
    public Course selectCourseById(Long id)
    {

        Course course= courseMapper.selectCourseById(id);
        CourseDepartment courseDepartment = courseDepartmentMapper.selectCourseDepartmentByCourseId(course.getCourseId());
        course.setDepartmentId(courseDepartment.getDepartmentId());
        return course;
    }

    /**
     * 查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {

        List<Course> courses = courseMapper.selectCourseList(course);
        return courses;
    }

    public List<Course> selectCourseByDepartmentId(Long departmentId){
         CourseDepartment courseDepartment = new CourseDepartment();
         courseDepartment.setDepartmentId(departmentId);
         List<CourseDepartment> courseDepartments = courseDepartmentMapper.selectCourseDepartmentByDepartmentId(departmentId);
         List<Course> courses=new ArrayList<>();
        for (CourseDepartment courseDepart:courseDepartments) {
            Course course = courseMapper.selectCourseByCourseId(courseDepart.getCourseId());
            course.setDepartmentId(departmentId);
            courses.add(course);
        }
        return courses;
    }
    /**
     * 通过CourseId查询课程信息
     * @param courseId
     * @return
     */
    public Course  selectCourseByCourseId(Long courseId){
      return   courseMapper.selectCourseByCourseId(courseId);
    }

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCourse(Course course)
    {
         int rows= courseMapper.insertCourse(course);
         insertCourseDepartment(course);
         return rows;

    }

    /**
     * 院系开课表
     * @param course
     */
    public void   insertCourseDepartment(Course course){
        CourseDepartment courseDepartment=new CourseDepartment();
        courseDepartment.setCourseId(course.getCourseId());
        courseDepartment.setDepartmentId(course.getDepartmentId());
        courseDepartmentMapper.insertCourseDepartment(courseDepartment);

    }

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] ids)
    {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程信息信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id)
    {
        return courseMapper.deleteCourseById(id);
    }
}
