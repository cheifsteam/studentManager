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
      <el-form-item label="院系名称">
        <el-select
          v-model="departmentId"
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
      <el-table-column label="专业编号" align="center" prop="professionId" />
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

    <!-- 添加或修改班级信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级编号" prop="clazzId">
          <el-input v-model="form.clazzId" placeholder="请输入班级编号" />
        </el-form-item>
        <el-form-item label="班级名称" prop="clazzName">
          <el-input v-model="form.clazzName" placeholder="请输入班级名称" />
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
import {listAllDepartment,getProfessionByDepartId} from "@/api/student/common";

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
      departAll :{},
      departmentId: null,
      //所在院系的所有专业
      professions :{},
      // 班级信息表格数据
      clazzList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        clazzId: null,
        clazzName: null,
        professionId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getAllDepartment();
  },
  watch: {
    departmentId : function (){
      console.log(this.departmentId);
      if (this.departmentId!=null) {

        this.getProfessionByDepart();
      }
    }
  },
  methods: {
    /** 查询班级信息列表 */
    getList() {
      this.loading = true;
      listClazz(this.queryParams).then(response => {
        this.clazzList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getAllDepartment() {
      listAllDepartment().then(response => {
        this.departAll = response.data
      })
    },
    getProfessionByDepart() {
      getProfessionByDepartId(this.departmentId).then(response=>{
        this.professions =response.data;
        console.log(response.msg);
      })
    },

    // 取消按钮
    cancel() {
      this.open = false;
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
      this.departmentId= null;
      this.professions = null;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加班级信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClazz(id).then(response => {
        this.form = response.data;
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
      this.$modal.confirm('是否确认删除班级信息编号为"' + ids + '"的数据项？').then(function() {
        return delClazz(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/clazz/export', {
        ...this.queryParams
      }, `clazz_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
