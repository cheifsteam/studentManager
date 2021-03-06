<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程编号" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程名称" prop="courseName">
        <el-input
          v-model="queryParams.courseName"
          placeholder="请输入课程名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开课院系" prop="departmentId">
        <el-select
          v-model="queryParams.departmentId"
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
          v-hasPermi="['student:course:add']"
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
          v-hasPermi="['student:course:edit']"
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
          v-hasPermi="['student:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="课程编号" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName" />
      <el-table-column label="课程学分" align="center" prop="courseCredit" />
      <el-table-column label="理论学时" align="center" prop="courseTheoreticalHours" />
      <el-table-column label="实践学时" align="center" prop="coursePracticalHours" />
      <el-table-column label="考核方式" align="center" prop="courseAssessmentMethods">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_course_exam" :value="scope.row.courseAssessmentMethods"/>
        </template>
      </el-table-column>
      <el-table-column label="开课院系" align="center" prop="departmentId">
        <template slot-scope="scope">
          <span   v-for="depart in departAll"
                  v-if="depart.departmentId===scope.row.departmentId">
            {{depart.departmentName}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:course:remove']"
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

    <!-- 添加或修改课程信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程编号" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程编号" />
        </el-form-item>
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="form.courseName" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程学分" prop="courseCredit">
          <el-input v-model="form.courseCredit" placeholder="请输入课程学分" />
        </el-form-item>
        <el-form-item label="理论学时" prop="courseTheoreticalHours">
          <el-input v-model="form.courseTheoreticalHours" placeholder="请输入理论学时" />
        </el-form-item>
        <el-form-item label="实践学时" prop="coursePracticalHours">
          <el-input v-model="form.coursePracticalHours" placeholder="请输入实践学时" />
        </el-form-item>
        <el-form-item label="考核方式" prop="courseAssessmentMethods">
          <el-select v-model="form.courseAssessmentMethods" placeholder="请选择考核方式"
                     clearable
                     size="small"
                     style="width: 240px">
          <el-option
            v-for="dict in dict.type.sys_course_exam"
            :key="dict.value"
            :label="dict.label"
            :value="parseInt(dict.value)"
          ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课院系" prop="departmentId">
          <el-select
            v-model="form.departmentId"
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse,getCourseListByDepartmentId} from "@/api/student/course";
import {listDepartment} from "@/api/student/department";

export default {
  name: "Course",
  dicts:['sys_course_exam'],
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
      // 课程信息表格数据
      courseList: [],
      departAll : [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        courseName: null,
        courseCredit: null,
        courseTheoreticalHours: null,
        coursePracticalHours: null,
        courseAssessmentMethods: null,
        departmentId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        courseId: [
          {required: true, message: "请输入课程编号", trigger: "blur"},
          {
            pattern: /^\d{10}$/,
            message: "请输入10位课程编号"
          }
        ],
        departmentId:[
          { required:true,message:"请选择院系",trigger:"change"},
        ],
        courseAssessmentMethods:[
          { required:true,message:"请选择考核方式",trigger:"change"},
        ],
        courseName: [
          {required: true, message: "请输入课程名称", trigger: "blur"},
          {
            pattern: /^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$/,
            message: "请输入正确格式课程名称"

          }
        ],
        courseCredit :[
          {required: true, message: "请输入课程学分", trigger: "blur"},
          {
            pattern: /^\d{1,2}$/,
            message: "请输入正确格式的学分"
          }
        ],
        courseTheoreticalHours:[
          {required: true, message: "请输入理论学时", trigger: "blur"},
          {
            pattern: /^\d{1,2}$/,
            message: "请输入正确格式的理论学时"
          }
        ],
        coursePracticalHours:[
          {required: true, message: "请输入实践学时", trigger: "blur"},
          {
            pattern: /^\d{1,2}$/,
            message: "请输入正确格式的实践学时"
          }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getAllDepartment();
  },
  methods: {
    /** 查询课程信息列表 */
    getList() {
      this.loading = true;
        listCourse(this.queryParams).then(response => {
          this.courseList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
    },
    getAllDepartment() {
      this.loading = true
      listDepartment().then(response => {
        this.departAll = response.rows
      })
    },
    getCourseListByDepartmentId() {
      getCourseListByDepartmentId(this.queryParams.departmentId).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        courseId: null,
        courseName: null,
        courseCredit: null,
        courseTheoreticalHours: null,
        coursePracticalHours: null,
        courseAssessmentMethods: null,
        departmentId :null
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
      this.title = "添加课程信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除课程信息编号为"' + ids + '"的数据项？').then(function() {
        return delCourse(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
