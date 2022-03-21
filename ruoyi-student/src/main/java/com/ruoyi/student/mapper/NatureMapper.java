package com.ruoyi.student.mapper;

import java.util.List;
import com.ruoyi.student.domain.Nature;

/**
 * 课程性质Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public interface NatureMapper
{
    /**
     * 查询课程性质
     *
     * @param id 课程性质主键
     * @return 课程性质
     */
    public Nature selectNatureById(Long id);

    /**
     * 查询课程性质列表
     *
     * @param nature 课程性质
     * @return 课程性质集合
     */
    public List<Nature> selectNatureList(Nature nature);

    /**
     * 新增课程性质
     *
     * @param nature 课程性质
     * @return 结果
     */
    public int insertNature(Nature nature);

    /**
     * 修改课程性质
     *
     * @param nature 课程性质
     * @return 结果
     */
    public int updateNature(Nature nature);

    /**
     * 删除课程性质
     *
     * @param id 课程性质主键
     * @return 结果
     */
    public int deleteNatureById(Long id);

    /**
     * 批量删除课程性质
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNatureByIds(Long[] ids);

    /**
     * 通过课程性质编号查看课程性质
     *
     * @param natureId
     * @return 结果
     */
    public Nature selectNatureByNatureId(Long natureId);
}
