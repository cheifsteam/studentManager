package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.student.domain.vo.ClazzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.ClazzMapper;
import com.ruoyi.student.domain.Clazz;
import com.ruoyi.student.service.IClazzService;

/**
 * 班级信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-16
 */
@Service
public class ClazzServiceImpl implements IClazzService
{
    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public Clazz selectClazzById(Long id)
    {
        return clazzMapper.selectClazzById(id);
    }

    /**
     * 查询班级信息列表
     *
     * @param clazz 班级信息
     * @return 班级信息
     */
    @Override
    public List<Clazz> selectClazzList(Clazz clazz)
    {
        return clazzMapper.selectClazzList(clazz);
    }

    /**
     * 新增班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    @Override
    public int insertClazz(Clazz clazz)
    {
        return clazzMapper.insertClazz(clazz);
    }

    /**
     * 修改班级信息
     *
     * @param clazz 班级信息
     * @return 结果
     */
    @Override
    public int updateClazz(Clazz clazz)
    {
        return clazzMapper.updateClazz(clazz);
    }

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClazzByIds(Long[] ids)
    {
        return clazzMapper.deleteClazzByIds(ids);
    }

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClazzById(Long id)
    {
        return clazzMapper.deleteClazzById(id);
    }

    /**
     * 根据院系ID查找班级
     * @param departmentId  院系ID
     * @return 结果
     */
    public List<Clazz>  selectClazzListByDepartmentId(Long departmentId){
        return clazzMapper.selectClazzListByDepartmentId(departmentId);
    }

    /**
     * 导出班级信息
     * @param clazzVo 班级信息
     * @return
     */
    @Override
    public List<ClazzVo> selectClazzVoList(ClazzVo clazzVo) {
        return clazzMapper.selectClazzVoList(clazzVo);
    }

    /**
     * 通过专业ID 查找班级
     * @param  professionId 专业ID
     * @return
     */
    @Override
    public List<Clazz> selectClazzListByProfessionId(Long professionId) {
        return clazzMapper.selectClazzListByProfessionId(professionId);
    }


}
