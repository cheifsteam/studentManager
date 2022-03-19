package com.ruoyi.student.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.DepartmentMapper;
import com.ruoyi.student.domain.Department;
import com.ruoyi.student.service.IDepartmentService;

/**
 * 院系Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-16
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询院系
     *
     * @param id 院系主键
     * @return 院系
     */
    @Override
    public Department selectDepartmentById(Long id)
    {
        return departmentMapper.selectDepartmentById(id);
    }

    /**
     * 查询院系列表
     *
     * @param department 院系
     * @return 院系
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增院系
     *
     * @param department 院系
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改院系
     *
     * @param department 院系
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除院系
     *
     * @param ids 需要删除的院系主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByIds(Long[] ids)
    {
        return departmentMapper.deleteDepartmentByIds(ids);
    }

    /**
     * 删除院系信息
     *
     * @param id 院系主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentById(Long id)
    {
        return departmentMapper.deleteDepartmentById(id);
    }
    /**
     * 通过院系ID 查找院系
     * @param departmentId 院系ID
     * @return
     */
    public Department selectDepartmentByDepartmentId(Long departmentId){
        return departmentMapper.selectDepartmentByDepartmentId(departmentId);
    }
}
