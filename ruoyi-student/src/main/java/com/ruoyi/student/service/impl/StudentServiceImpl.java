package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Regexp;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.validator.RegexValidator;
import com.ruoyi.student.domain.Clazz;
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.domain.vo.StudentVo;
import com.ruoyi.student.mapper.ClazzMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.StudentMapper;
import com.ruoyi.student.domain.Student;
import com.ruoyi.student.service.IStudentService;

import javax.validation.Validator;

/**
 * 学生Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-17
 */
@Service
public class StudentServiceImpl implements IStudentService
{
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ClazzServiceImpl.class);


    /**
     * 查询学生
     *
     * @param id 学生主键
     * @return 学生
     */
    @Override
    public StudentVo selectStudentById(Long id)
    {
        return studentMapper.selectStudentById(id);
    }

    /**
     * 查询学生列表
     *
     * @param student 学生
     * @return 学生
     */
    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    /**
     * 新增学生
     *
     * @param student 学生
     * @return 结果
     */
    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    /**
     * 修改学生
     *
     * @param student 学生
     * @return 结果
     */
    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    /**
     * 批量删除学生
     *
     * @param ids 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteStudentByIds(Long[] ids)
    {
        return studentMapper.deleteStudentByIds(ids);
    }

    /**
     * 删除学生信息
     *
     * @param id 学生主键
     * @return 结果
     */
    @Override
    public int deleteStudentById(Long id)
    {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    public List<StudentVo> selectStudentVoList(StudentVo studentVo) {
        return studentMapper.selectStudentVoList(studentVo);
    }

    @Override
    public String importStudent(List<Student> studentList,boolean isUpdateSupport , String s) {
        if (StringUtils.isNull(studentList) || studentList.size() == 0) {
            throw new SecurityException("导入的数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Student student : studentList) {
            String str;
            //验证是否存在这个班级编号是否合法
            if ((str=validStudent(student))!=null) {

                failureNum++;
                String msg = "<br/>" + failureNum + "  " ;
                failureMsg.append(msg).append(str);
            }
            else {
                try {
                    //  验证是否存在这个学生
                    Student stu = studentMapper.selectStudentByStudentId(student.getStudentId());
                    //验证专业编号是否为正确格式
                    Clazz c = clazzMapper.selectClazzByClazzId(student.getClazzId());
                    if (StringUtils.isNull(c)) {
                        failureNum++;
                        String msg = "<br/>" + failureNum + "  " + "学生姓名" + student.getStudentName() + " 导入专业编号非法格式：";
                        failureMsg.append(msg);
                    } else {
                        if (StringUtils.isNull(stu)) {
                            BeanValidators.validateWithException(validator, student);
                            insertStudent(student);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "学生姓名" + student.getStudentName() + "导入成功");
                        } else if (isUpdateSupport) {
                            BeanValidators.validateWithException(validator, student);
                            updateStudent(student);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "学生姓名" + student.getStudentName() + "更新成功");

                        } else {
                            failureNum++;
                            failureMsg.append("<br/>" + failureNum + "  " + "学生姓名" + student.getStudentName() + " 已存在");
                        }
                    }
                } catch (Exception e) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "  " + "学生姓名" + student.getStudentName() + " 导入失败：";
                    failureMsg.append(msg + e.getMessage());
                    log.error(msg, e);
                }
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }

    @Override
    public Student selectStudentByStudentId(Long studentId) {
        return studentMapper.selectStudentByStudentId(studentId);
    }

    public String validStudent (Student student){
        String msg=null;
        if (!RegexValidator.isValid(student.getStudentPhonenumber(),Regexp.MOBILE_REG)){
            msg =  "学生姓名" + student.getStudentName() + " 导入学生手机号非法格式：";
            return msg;
        }
        if (!RegexValidator.isValid(student.getStudentKeyContactsPhonenumber(),Regexp.MOBILE_REG)){
            msg=  "学生姓名" + student.getStudentName() + " 导入关键人手机号非法格式：";
            return msg;
        }
        if (!RegexValidator.isValid(String.valueOf(student.getStudentId()), Regexp.StudentID_REG)){
             msg= "学生姓名" + student.getStudentName() + " 导入学号非法格式：";
            return msg;
        }
        return msg;
    }
}
