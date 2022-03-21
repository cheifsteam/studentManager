package com.ruoyi.student.service;

import java.util.List;
import com.ruoyi.student.domain.Nature;

/**
 * 课程性质Service接口
 *
 * @author ruoyi
 * @date 2022-03-21
 */
public interface INatureService
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
     * 批量删除课程性质
     *
     * @param ids 需要删除的课程性质主键集合
     * @return 结果
     */
    public int deleteNatureByIds(Long[] ids);

    /**
     * 删除课程性质信息
     *
     * @param id 课程性质主键
     * @return 结果
     */
    public int deleteNatureById(Long id);

    /**
     * 课程性质的导入
     * @param professionList
     * @param updateSupport
     * @param s
     * @return
     */
    public String importNature(List<Nature> professionList, boolean updateSupport, String s);

    /**
     * 通过课程性质编号查看课程性质
     *
     * @param natureId
     * @return 结果
     */
    public Nature selectNatureByNatureId(Long natureId);
}
