package com.ruoyi.student.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Regexp;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.common.validator.RegexValidator;
import com.ruoyi.student.domain.Department;
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.domain.vo.ClazzVo;
import com.ruoyi.student.mapper.ProfessionMapper;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.student.mapper.ClazzMapper;
import com.ruoyi.student.domain.Clazz;
import com.ruoyi.student.service.IClazzService;

import javax.validation.Validator;

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
    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ClazzServiceImpl.class);

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

    /**
     * 根据clazzId 查询 clazz
     * @param clazzId
     * @return
     */
    @Override
    public Clazz selectClazzByClazzId(Long clazzId) {
        return clazzMapper.selectClazzByClazzId(clazzId);
    }
    @Override
    public  String importClazz(List<Clazz> clazzList,Boolean isUpdateSupport, String s){
        if (StringUtils.isNull(clazzList) || clazzList.size() == 0) {
            throw new SecurityException("导入的数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Clazz clazz: clazzList) {

            //验证是否存在这个班级编号是否合法
            if (RegexValidator.isValid(String.valueOf(clazz.getClazzId()), Regexp.ID_REG)) {
                failureNum++;
                String msg = "<br/>" + failureNum + "  " + "班级名称" + clazz.getClazzName() + " 导入班级编号非法格式：";
                failureMsg.append(msg );
            } else {
                try {
                    //  验证是否存在这个班级
                    Clazz c = clazzMapper.selectClazzByClazzId(clazz.getClazzId());
                    //验证专业编号是否为正确格式
                    Profession p =professionMapper.selectProfessionByProfessionId(clazz.getProfessionId());
                    if (StringUtils.isNull(p)){
                        failureNum++;
                        String msg = "<br/>" + failureNum + "  " + "班级名称" + clazz.getClazzName() + " 导入专业编号非法格式：";
                        failureMsg.append(msg );
                    }else {
                        if (StringUtils.isNull(c) ) {
                            BeanValidators.validateWithException(validator, clazz);
                            insertClazz(clazz);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "班级名称" + clazz.getClazzName() + "导入成功");
                        } else if (isUpdateSupport ) {
                            BeanValidators.validateWithException(validator, clazz);
                            updateClazz(clazz);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "  " + "班级名称" + clazz.getClazzName() + "更新成功");

                        } else {
                            failureNum++;
                            failureMsg.append("<br/>" + failureNum + "  " + "班级名称" + clazz.getClazzName() + " 已存在");
                        }
                    }
                }catch(Exception e){
                    failureNum++;
                    String msg = "<br/>" + failureNum + "  " + "班级名称" + clazz.getClazzName() + " 导入失败：";
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
