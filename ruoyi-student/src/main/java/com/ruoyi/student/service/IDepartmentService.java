package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.Department;

/**
 * 院系Service接口
 *
 * @author ruoyi
 * @date 2022-03-16
 */
public interface IDepartmentService
{
    /**
     * 查询院系
     *
     * @param id 院系主键
     * @return 院系
     */
    public Department selectDepartmentById(Long id);

    /**
     * 查询院系列表
     *
     * @param department 院系
     * @return 院系集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增院系
     *
     * @param department 院系
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改院系
     *
     * @param department 院系
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 批量删除院系
     *
     * @param ids 需要删除的院系主键集合
     * @return 结果
     */
    public int deleteDepartmentByIds(Long[] ids);

    /**
     * 删除院系信息
     *
     * @param id 院系主键
     * @return 结果
     */
    public int deleteDepartmentById(Long id);
}
