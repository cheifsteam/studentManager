package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.Student;
import com.ruoyi.student.domain.vo.StudentVo;

/**
 * 学生Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-17
 */
public interface StudentMapper
{
    /**
     * 查询学生
     *
     * @param id 学生主键
     * @return 学生
     */
    public StudentVo selectStudentById(Long id);

    /**
     * 查询学生列表
     *
     * @param student 学生
     * @return 学生集合
     */
    public List<Student> selectStudentList(Student student);

    /**
     * 新增学生
     *
     * @param student 学生
     * @return 结果
     */
    public int insertStudent(Student student);

    /**
     * 修改学生
     *
     * @param student 学生
     * @return 结果
     */
    public int updateStudent(Student student);

    /**
     * 删除学生
     *
     * @param id 学生主键
     * @return 结果
     */
    public int deleteStudentById(Long id);

    /**
     * 批量删除学生
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStudentByIds(Long[] ids);

    /**
     * 联接查询学生列表
     *
     * @param studentVo 学生
     * @return 学生集合
     */
    public List<StudentVo> selectStudentVoList(StudentVo studentVo);

    /**
     * 通过学号查询学生
     * @param  studentId
     * @return
     */
    public Student selectStudentByStudentId(Long studentId);
}
