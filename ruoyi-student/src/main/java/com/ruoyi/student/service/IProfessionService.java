package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.domain.vo.ProfessionVo;

/**
 * 专业Service接口
 *
 * @author ruoyi
 * @date 2022-03-16
 */
public interface IProfessionService
{
    /**
     * 查询专业
     *
     * @param id 专业主键
     * @return 专业
     */
    public Profession selectProfessionById(Long id);

    /**
     * 查询专业列表
     *
     * @param profession 专业
     * @return 专业集合
     */
    public List<Profession> selectProfessionList(Profession profession);

    /**
     * 新增专业
     *
     * @param profession 专业
     * @return 结果
     */
    public int insertProfession(Profession profession);

    /**
     * 修改专业
     *
     * @param profession 专业
     * @return 结果
     */
    public int updateProfession(Profession profession);

    /**
     * 批量删除专业
     *
     * @param ids 需要删除的专业主键集合
     * @return 结果
     */
    public int deleteProfessionByIds(Long[] ids);

    /**
     * 删除专业信息
     *
     * @param id 专业主键
     * @return 结果
     */
    public int deleteProfessionById(Long id);

    /**
     * 根据院系id 查询专业
     * @param departmentId 院系ID
     * @return 结果
     */
    public  List<Profession> selectProfessionByDepartmentId(Long departmentId);


    /**
     * 导出专业
     * @param profession
     * @return
     */
    public List<ProfessionVo> selectProfessionVoList(Profession profession);

    /**
     * 根据专业id查询专业
     * @param professionId
     * @return
     */
    public List<Profession> selectProfessionByProfessionId(Long professionId);


}
