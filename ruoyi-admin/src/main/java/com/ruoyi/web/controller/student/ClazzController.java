package com.ruoyi.web.controller.student;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.student.domain.vo.ClazzVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.student.domain.Clazz;
import com.ruoyi.student.service.IClazzService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级信息Controller
 *
 * @author ruoyi
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/student/clazz")
public class ClazzController extends BaseController
{
    @Autowired
    private IClazzService clazzService;

    /**
     * 查询班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:list')")
    @GetMapping("/list")
    public TableDataInfo list(Clazz clazz)
    {
        startPage();
        List<Clazz> list = clazzService.selectClazzList(clazz);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:export')")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClazzVo clazzVo)
    {
        List<ClazzVo> list = clazzService.selectClazzVoList(clazzVo);
        ExcelUtil<ClazzVo> util = new ExcelUtil<ClazzVo>(ClazzVo.class);
        util.exportExcel(response, list, "班级信息数据");
    }

    /**
     * 获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(clazzService.selectClazzById(id));
    }

    /**
     * 新增班级信息
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:add')")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Clazz clazz)
    {
        return toAjax(clazzService.insertClazz(clazz));
    }

    /**
     * 修改班级信息
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:edit')")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Clazz clazz)
    {
        return toAjax(clazzService.updateClazz(clazz));
    }

    /**
     * 删除班级信息
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:remove')")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clazzService.deleteClazzByIds(ids));
    }

    /**
     * 通过院系ID获取班级信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:clazz:query')")
    @GetMapping(value = "/queryInfo/{departmentId}")
    public TableDataInfo getInfoByDepartId(@PathVariable("departmentId") Long departmentId)
    {
        startPage();
        List<Clazz> list = clazzService.selectClazzListByDepartmentId(departmentId);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('student:clazz:query')")
    @GetMapping(value = "/query/{professionId}")
    public AjaxResult getInfoByProfessId(@PathVariable("professionId") Long professionId){
        return AjaxResult.success(clazzService.selectClazzListByProfessionId(professionId));
    }

}
