package com.ruoyi.web.controller.student;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.student.domain.vo.ProfessionVo;
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
import com.ruoyi.student.domain.Profession;
import com.ruoyi.student.service.IProfessionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 专业Controller
 *
 * @author ruoyi
 * @date 2022-03-16
 */
@RestController
@RequestMapping("/student/profession")
public class ProfessionController extends BaseController
{
    @Autowired
    private IProfessionService professionService;

    /**
     * 查询专业列表
     */
    @PreAuthorize("@ss.hasPermi('student:profession:list')")
    @GetMapping("/list")
    public TableDataInfo list(Profession profession)
    {
        startPage();
        List<Profession> list = professionService.selectProfessionList(profession);
        return getDataTable(list);
    }
    /**
     * 查询所有专业的列表
     *
     */
    @PreAuthorize("@ss.hasPermi('student:profession:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(Profession profession)
    {
        List <Profession> list =professionService.selectProfessionList(profession);
        return AjaxResult.success(list);
    }

    /**
     * 导出专业列表
     */
    @PreAuthorize("@ss.hasPermi('student:profession:export')")
    @Log(title = "专业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Profession profession)
    {
        List<ProfessionVo> list = professionService. selectProfessionVoList(profession);
        ExcelUtil<ProfessionVo> util = new ExcelUtil<ProfessionVo>(ProfessionVo.class);
        util.exportExcel(response, list, "专业数据");
    }

    /**
     * 获取专业详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:profession:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(professionService.selectProfessionById(id));
    }

    /**
     * 新增专业
     */
    @PreAuthorize("@ss.hasPermi('student:profession:add')")
    @Log(title = "专业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Profession profession)
    {
        return toAjax(professionService.insertProfession(profession));
    }

    /**
     * 修改专业
     */
    @PreAuthorize("@ss.hasPermi('student:profession:edit')")
    @Log(title = "专业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Profession profession)
    {
        return toAjax(professionService.updateProfession(profession));
    }

    /**
     * 删除专业
     */
    @PreAuthorize("@ss.hasPermi('student:profession:remove')")
    @Log(title = "专业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(professionService.deleteProfessionByIds(ids));
    }

    /**
     * 根据院系id 查询专业
     *
     */
    @PreAuthorize("@ss.hasPermi('student:profession:query')")
    @PostMapping(value = {"/{departmentId}"})
    public AjaxResult getInfoByDepartmentId (@PathVariable("departmentId") Long departmentId){
        return AjaxResult.success(professionService.selectProfessionByDepartmentId(departmentId));
    }
    /**
     * 根据专业id查询专业
     *
     */
    @PreAuthorize("@ss.hasPermi('student:profession:query')")
    @GetMapping(value = "/info/{professionId}")
    public AjaxResult getInfoByProfessionId (@PathVariable("professionId") Long professionId){
        return AjaxResult.success(professionService.selectProfessionByProfessionId(professionId));
    }
}
