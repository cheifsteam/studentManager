<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班级名称" prop="clazzName">
        <el-input
          v-model="queryParams.clazzName"
          placeholder="请输入班级名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="院系名称" prop="departId">
        <el-select
          v-model="departId"
          placeholder="选择院系"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="depart in departAll"
            :key="depart.departmentId"
            :label="depart.departmentName"
            :value="depart.departmentId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="专业名称" prop="professionId">
        <el-select
          v-model="queryParams.professionId"
          placeholder="选择专业"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="profess in professions"
            :key="profess.professionId"
            :label="profess.professionName"
            :value="profess.professionId"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['student:clazz:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['student:clazz:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['student:clazz:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['student:clazz:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:clazz:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clazzList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="班级编号" align="center" prop="clazzId" />
      <el-table-column label="班级名称" align="center" prop="clazzName" />
      <el-table-column label="专业编号" align="center" prop="professionId" >
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:clazz:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:clazz:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 专业导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px">
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改班级信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="院系名称" prop="departId">
          <el-select
            v-model="departId"
            placeholder="选择院系"
            clearable
            size="small"
            style="width: 240px"

          >
            <el-option
              v-for="depart in departAll"
              :key="depart.departmentId"
              :label="depart.departmentName"
              :value="depart.departmentId"
              :disabled="choose"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="班级编号" prop="clazzId">
          <el-input v-model="form.clazzId" placeholder="请输入班级编号" />
        </el-form-item>
        <el-form-item label="班级名称" prop="clazzName">
          <el-input v-model="form.clazzName" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="专业名称" prop="professionId">
          <el-select
            v-model="form.professionId"
            placeholder="选择专业"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="profess in professions"
              :key="profess.professionId"
              :label="profess.professionName"
              :value="profess.professionId"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClazz, getClazz, delClazz, addClazz, updateClazz } from "@/api/student/clazz";
import {getClazzByDepartId} from "@/api/student/common";
import {listDepartment} from "@/api/student/department";
import {listProfession} from "@/api/student/profession";
import {getToken} from "@/utils/auth";

export default {
  name: "Clazz",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      //所有院系
      departAll: {},
      departId: null,
      //所在院系的所有专业
      professions: {},
      // 班级信息表格数据
      clazzList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //是否可选
      choose: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        clazzId: null,
        clazzName: null,
        professionId: null,
        departmentId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        clazzId: [
          {required: true, message: "请输入班级编号", trigger: "blur"},
          {
            pattern: /^\d{10}$/,
            message: "请输入10位班级编号"
          }
        ],
        professionId: [
          {required: true, message: "请选择专业，若专业无数据，请选择院系", trigger: "change"},
        ],
        clazzName: [
          {required: true, message: "请输入班级名称", trigger: "blur"},
          {
            pattern: /^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/,
            message: "请输入正确格式班级名称"

          }
        ],
      },
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/student/clazz/importData"
      }

    };
  },
  created() {
    this.getList();
    this.getAllDepartment();
  },
  watch: {
    departId: function () {
      this.professions = null;
      this.queryParams.professionId = null;
      if (this.departId === null || this.departId.length === 0) {
        this.queryParams.professionId = null;
        this.professions = null;
      } else {
        this.getProfessionByDepart();
      }

    }
  },
  methods: {
    /** 查询班级信息列表 */
    getList() {
      this.loading = true;
      if (this.departId === null || this.departId.length === 0 || this.queryParams.professionId !== null) {
        listClazz(this.queryParams).then(response => {
          this.clazzList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      } else {
        getClazzByDepartId(this.departId).then(response => {
          this.clazzList = response.rows;
          this.total = response.total;
          this.loading = false;
        })
      }
    },
    /**查询所有院系 */
    getAllDepartment() {
      listDepartment().then(response => {
        this.departAll = response.rows
      })
    },
    // getAllProfession() {
    //   listAllProfession().then(response=> {
    //     this.professions=response.data
    //   })
    // },
    /** 通过院系ID查询专业*/
    getProfessionByDepart() {
      let data = {departmentId: this.departId};
      listProfession(data).then(response => {
        this.professions = response.rows;
        console.log(response.msg);
      })
    },


    // 取消按钮
    cancel() {
      this.open = false;
      this.professions = null;
      this.departId = null;
      this.choose = true;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        clazzId: null,
        clazzName: null,
        professionId: null
      };

      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.departId = null;
      this.professions = null;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.getAllDepartment();
      this.choose = false;
      this.reset();
      this.open = true;
      this.title = "添加班级信息";
    },
    /** 修改按钮操作 */
    async handleUpdate(row) {
      this.choose = true
      this.reset();
      const id = row.id || this.ids;
      let data = {};
      let professionId = 0;
      await getClazz(id).then(response => {
        this.form = response.data;
        professionId = this.form.professionId;
        console.log("nml" + professionId);
      });
      data = {professionId: professionId}
      await listProfession(data).then(response => {
        data = response.rows;
        console.log(data)
        this.departId = data[0].departmentId;
        this.open = true;
        this.title = "修改班级信息";
      });

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClazz(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClazz(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除班级信息编号为"' + ids + '"的数据项？').then(function () {
        return delClazz(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.queryParams.departmentId = this.departId;
      this.download('student/clazz/export', {
        ...this.queryParams
      }, `clazz_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "班级导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('student/clazz/importTemplate', {}, `clazz_template_${new Date().getTime()}.xlsx`)
    },
// 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
// 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", {dangerouslyUseHTMLString: true});
      this.getList();
    },
// 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
}
</script>
