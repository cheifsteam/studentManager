package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.Course;

/**
 * 课程信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public interface CourseMapper
{
    /**
     * 查询课程信息
     *
     * @param id 课程信息主键
     * @return 课程信息
     */
    public Course selectCourseById(Long id);

    /**
     * 查询课程信息列表
     *
     * @param course 课程信息
     * @return 课程信息集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 通过CourseId查询课程信息
     * @param courseId
     * @return
     */
    public Course  selectCourseByCourseId(Long courseId);

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 删除课程信息
     *
     * @param id 课程信息主键
     * @return 结果
     */
    public int deleteCourseById(Long id);

    /**
     * 批量删除课程信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseByIds(Long[] ids);


}
