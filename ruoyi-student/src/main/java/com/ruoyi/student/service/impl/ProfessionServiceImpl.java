package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.student.domain.vo.ProfessionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.ProfessionMapper;
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.service.IProfessionService;

/**
 * 专业Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-16
 */
@Service
public class ProfessionServiceImpl implements IProfessionService
{
    @Autowired
    private ProfessionMapper professionMapper;

    /**
     * 查询专业
     *
     * @param id 专业主键
     * @return 专业
     */
    @Override
    public Profession selectProfessionById(Long id)
    {
        return professionMapper.selectProfessionById(id);
    }

    /**
     * 查询专业列表
     *
     * @param profession 专业
     * @return 专业
     */
    @Override
    public List<Profession> selectProfessionList(Profession profession)
    {
        return professionMapper.selectProfessionList(profession);
    }

    /**
     * 新增专业
     *
     * @param profession 专业
     * @return 结果
     */
    @Override
    public int insertProfession(Profession profession)
    {
        return professionMapper.insertProfession(profession);
    }

    /**
     * 修改专业
     *
     * @param profession 专业
     * @return 结果
     */
    @Override
    public int updateProfession(Profession profession)
    {
        return professionMapper.updateProfession(profession);
    }

    /**
     * 批量删除专业
     *
     * @param ids 需要删除的专业主键
     * @return 结果
     */
    @Override
    public int deleteProfessionByIds(Long[] ids)
    {
        return professionMapper.deleteProfessionByIds(ids);
    }

    /**
     * 删除专业信息
     *
     * @param id 专业主键
     * @return 结果
     */
    @Override
    public int deleteProfessionById(Long id)
    {
        return professionMapper.deleteProfessionById(id);
    }
    /**
     * 根据院系id 查询专业
     * @param departmentId 院系ID
     * @return 结果
     */
    @Override
    public List<Profession> selectProfessionByDepartmentId(Long departmentId) {
        return professionMapper.selectProfessionByDepartmentId(departmentId);
    }
    /**
     * 导出专业
     * @param profession
     * @return
     */
    @Override
    public List<ProfessionVo> selectProfessionVoList(Profession profession){
        return professionMapper.selectProfessionVoList(profession);
    }
    /**
     * 根据专业id查询专业
     * @param professionId
     * @return
     */
    @Override
    public List<Profession> selectProfessionByProfessionId(Long professionId) {
        return professionMapper.selectProfessionByProfessionId(professionId);
    }

}
