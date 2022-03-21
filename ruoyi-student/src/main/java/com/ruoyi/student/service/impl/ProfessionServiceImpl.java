package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Regexp;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.validator.RegexValidator;
import com.ruoyi.student.domain.Department;
import com.ruoyi.student.domain.vo.ProfessionVo;
import com.ruoyi.student.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.ProfessionMapper;
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.service.IProfessionService;

import javax.validation.Validator;

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
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    protected Validator validator;
    private static final Logger log = LoggerFactory.getLogger(ProfessionServiceImpl.class);


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
    public Profession selectProfessionByProfessionId(Long professionId) {
        return professionMapper.selectProfessionByProfessionId(professionId);
    }

    @Override
    public String importProfession(List<Profession> professionList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(professionList) || professionList.size() == 0) {
            throw new SecurityException("导入的数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Profession profession: professionList) {

                //验证是否存在这个专业编号是否合法
                if (!RegexValidator.isValid(String.valueOf(profession.getProfessionId()), Regexp.ID_REG)) {
                    failureNum++;
                    String msg = "<br/>" + failureNum + "  " + "专业名称" + profession.getProfessionName() + " 导入专业编号非法格式：";
                    failureMsg.append(msg );
                } else {
                    try {
                      //  验证是否存在这个专业
                    Profession p = professionMapper.selectProfessionByProfessionId(profession.getProfessionId());
                    Department department = departmentMapper.selectDepartmentByDepartmentId(profession.getDepartmentId());
                    if (department==null){
                        failureNum++;
                        String msg = "<br/>" + failureNum + "  " + "专业名称" + profession.getProfessionName() + " 导入院系编号非法格式：";
                        failureMsg.append(msg );
                    }else {
                        if (StringUtils.isNull(p) ) {
                            BeanValidators.validateWithException(validator, profession);
                            insertProfession(profession);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "专业名称" + profession.getProfessionName() + "导入成功");
                        } else if (isUpdateSupport ) {
                            BeanValidators.validateWithException(validator, profession);
                            updateProfession(profession);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "专业名称" + profession.getProfessionName() + "更新成功");

                        } else {
                            failureNum++;
                            failureMsg.append("<br/>" + failureNum + "  " + "专业名称" + profession.getProfessionName() + " 已存在");
                        }
                    }
                }catch(Exception e){
                    failureNum++;
                    String msg = "<br/>" + failureNum + "  " + "专业名称" + profession.getProfessionName() + " 导入失败：";
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
}
