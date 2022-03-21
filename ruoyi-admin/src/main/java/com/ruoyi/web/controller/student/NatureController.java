package com.ruoyi.web.controller.student;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.student.domain.Nature;
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
import com.ruoyi.student.domain.Nature;
import com.ruoyi.student.service.INatureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程性质Controller
 *
 * @author ruoyi
 * @date 2022-03-21
 */
@RestController
@RequestMapping("/student/nature")
public class NatureController extends BaseController
{
    @Autowired
    private INatureService natureService;

    /**
     * 查询课程性质列表
     */
    @PreAuthorize("@ss.hasPermi('student:nature:list')")
    @GetMapping("/list")
    public TableDataInfo list(Nature nature)
    {
        startPage();
        List<Nature> list = natureService.selectNatureList(nature);
        return getDataTable(list);
    }

    /**
     * 导出课程性质列表
     */
    @PreAuthorize("@ss.hasPermi('student:nature:export')")
    @Log(title = "课程性质", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Nature nature)
    {
        List<Nature> list = natureService.selectNatureList(nature);
        ExcelUtil<Nature> util = new ExcelUtil<Nature>(Nature.class);
        util.exportExcel(response, list, "课程性质数据");
    }

    /**
     * 获取课程性质详细信息
     */
    @PreAuthorize("@ss.hasPermi('student:nature:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(natureService.selectNatureById(id));
    }

    /**
     * 新增课程性质
     */
    @PreAuthorize("@ss.hasPermi('student:nature:add')")
    @Log(title = "课程性质", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Nature nature)
    {
        return toAjax(natureService.insertNature(nature));
    }

    /**
     * 修改课程性质
     */
    @PreAuthorize("@ss.hasPermi('student:nature:edit')")
    @Log(title = "课程性质", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Nature nature)
    {
        return toAjax(natureService.updateNature(nature));
    }

    /**
     * 删除课程性质
     */
    @PreAuthorize("@ss.hasPermi('student:nature:remove')")
    @Log(title = "课程性质", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(natureService.deleteNatureByIds(ids));
    }

    /**
     * 下载导入模板
     * @param response
     */
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<Nature> util = new ExcelUtil<Nature>(Nature.class);
        util.importTemplateExcel(response, "课程性质数据");
    }

    /**
     * 导入
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "课程性质信息", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:nature:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Nature> util = new ExcelUtil<Nature>(Nature.class);
        List<Nature> natureList = util.importExcel(file.getInputStream());
        String message = natureService.importNature(natureList, updateSupport, "");
        return AjaxResult.success(message);
    }
}
