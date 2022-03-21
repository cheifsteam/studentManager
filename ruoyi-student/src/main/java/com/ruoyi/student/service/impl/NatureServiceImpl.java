package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Regexp;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.validator.RegexValidator;
import com.ruoyi.student.domain.Department;
import com.ruoyi.student.domain.Profession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.NatureMapper;
import com.ruoyi.student.domain.Nature;
import com.ruoyi.student.service.INatureService;

import javax.validation.Validator;

/**
 * 课程性质Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-21
 */
@Service
public class NatureServiceImpl implements INatureService
{
    @Autowired
    private NatureMapper natureMapper;
    @Autowired
    private Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ClazzServiceImpl.class);


    /**
     * 查询课程性质
     *
     * @param id 课程性质主键
     * @return 课程性质
     */
    @Override
    public Nature selectNatureById(Long id)
    {
        return natureMapper.selectNatureById(id);
    }

    /**
     * 查询课程性质列表
     *
     * @param nature 课程性质
     * @return 课程性质
     */
    @Override
    public List<Nature> selectNatureList(Nature nature)
    {
        return natureMapper.selectNatureList(nature);
    }

    /**
     * 新增课程性质
     *
     * @param nature 课程性质
     * @return 结果
     */
    @Override
    public int insertNature(Nature nature)
    {
        return natureMapper.insertNature(nature);
    }

    /**
     * 修改课程性质
     *
     * @param nature 课程性质
     * @return 结果
     */
    @Override
    public int updateNature(Nature nature)
    {
        return natureMapper.updateNature(nature);
    }

    /**
     * 批量删除课程性质
     *
     * @param ids 需要删除的课程性质主键
     * @return 结果
     */
    @Override
    public int deleteNatureByIds(Long[] ids)
    {
        return natureMapper.deleteNatureByIds(ids);
    }

    /**
     * 删除课程性质信息
     *
     * @param id 课程性质主键
     * @return 结果
     */
    @Override
    public int deleteNatureById(Long id)
    {
        return natureMapper.deleteNatureById(id);
    }

    @Override
    public String importNature(List<Nature> naturelist, boolean updateSupport, String s) {
        if (StringUtils.isNull(naturelist) || naturelist.size() == 0) {
            throw new SecurityException("导入的数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Nature nature : naturelist) {

            //验证是否存在这个课程性质编号是否合法
            if (!RegexValidator.isValid(String.valueOf(nature.getNatureId()), Regexp.ID_REG)) {
                failureNum++;
                String msg = "<br/>" + failureNum + "  " + "课程性质名称" + nature.getNatureName() + " 导入课程性质编号非法格式：";
                failureMsg.append(msg);
            } else {
                try {
                    //  验证是否存在这个专业
                    Nature n = natureMapper.selectNatureByNatureId(nature.getNatureId());
                    if (StringUtils.isNull(n)) {
                        BeanValidators.validateWithException(validator, nature);
                        insertNature(nature);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "  " + "课程性质名称" + nature.getNatureName() + "导入成功");
                    } else if (updateSupport) {
                        BeanValidators.validateWithException(validator, nature);
                        updateNature(nature);
                        successNum++;
                        successMsg.append("<br/>" + successNum + "  " + "课程性质名称" + nature.getNatureName() + "更新成功");

                    } else {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "  " + "课程性质名称" + nature.getNatureName() + " 已存在");
                    }

                } catch (Exception e) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "  " + "课程性质名称" + nature.getNatureName() + " 导入失败：";
                    failureMsg.append(msg + e.getMessage());
                    log.error(msg, e);
                }
            }
        }
            if (failureNum > 0) {
                failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
                throw new ServiceException(failureMsg.toString());
            } else {
                successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
            }
            return successMsg.toString();

    }
    @Override
    public Nature selectNatureByNatureId(Long natureId) {
        return natureMapper.selectNatureByNatureId(natureId);
    }
}

