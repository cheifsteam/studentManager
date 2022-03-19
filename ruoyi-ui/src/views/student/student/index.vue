<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学号" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入学时间" prop="studentEnrollmentTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.studentEnrollmentTime"
                        type="year"
                        value-format="yyyy-MM-dd"
                        :picker-options="pickerOptions"
                        placeholder="选择入学时间">
        </el-date-picker>
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
      <el-form-item label="专业名称" prop="professId">
        <el-select
          v-model="professId"
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
      <el-form-item label="班级名称" prop="clazzId">
        <el-select
          v-model="queryParams.clazzId"
          placeholder="选择班级"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="clazz in clazzs"
            :key="clazz.clazzId"
            :label="clazz.clazzName"
            :value="clazz.clazzId"
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
          v-hasPermi="['student:student:add']"
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
          v-hasPermi="['student:student:edit']"
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
          v-hasPermi="['student:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['student:student:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" width="80" prop="id" />
      <el-table-column label="学号" align="center" width="80" prop="studentId" />
      <el-table-column label="学生姓名" align="center" width="120" prop="studentName" />
      <el-table-column label="学生年龄" align="center" width="120" prop="studentAge" />
      <el-table-column label="性别(0为男，1为女)" align="center" prop="studentSex" width="180" />
      <el-table-column label="出生日期" align="center" prop="studentBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.studentBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="入学时间" align="center" prop="studentEnrollmentTime" width="180">

      </el-table-column>
      <el-table-column label="学生电话" align="center" prop="studentPhonenumber" width="200"/>
      <el-table-column label="学生家庭地址" align="center" prop="studentAddress" width="200"/>
      <el-table-column label="关键联系人" align="center" prop="studentKeyContacts" width="200"/>
      <el-table-column label="关键联系人号码" align="center" prop="studentKeyContactsPhonenumber" width="230"/>
<!--      <el-table-column label="用户id (学号作为userId)" align="center" prop="userId" />-->
      <el-table-column label="班级编号" align="center" prop="clazzId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['student:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['student:student:remove']"
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

    <!-- 添加或修改学生对话框 -->
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
            />
          </el-select>
        </el-form-item>
        <el-form-item label="专业名称" prop="professId">
          <el-select
            v-model="professId"
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
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号" />
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="学生年龄" prop="studentAge">
          <el-input v-model="form.studentAge" placeholder="请输入学生年龄" />
        </el-form-item>
        <el-form-item label="出生日期" prop="studentBirthday">
          <el-date-picker clearable size="small"
                          v-model="form.studentBirthday"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="入学时间" prop="studentEnrollmentTime">
          <el-date-picker clearable size="small"
                          v-model="form.studentEnrollmentTime"
                          type="year"
                          format="yyyy"
                          value-format="yyyy"
                          :picker-options="pickerOptions"
                          placeholder="选择入学时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学生电话" prop="studentPhonenumber">
          <el-input v-model="form.studentPhonenumber" placeholder="请输入学生电话" />
        </el-form-item>
        <el-form-item label="学生家庭地址" prop="studentAddress">
          <el-input v-model="form.studentAddress" placeholder="请输入学生家庭地址" />
        </el-form-item>
        <el-form-item label="关键联系人" prop="studentKeyContacts">
          <el-input v-model="form.studentKeyContacts" placeholder="请输入关键联系人" />
        </el-form-item>
        <el-form-item label="关键联系人号码" prop="studentKeyContactsPhonenumber">
          <el-input v-model="form.studentKeyContactsPhonenumber" placeholder="请输入关键联系人号码" />
        </el-form-item>
<!--        <el-form-item label="用户id (学号作为userId)" prop="userId">-->
<!--          <el-input v-model="form.userId" placeholder="请输入用户id (学号作为userId)" />-->
<!--        </el-form-item>-->
        <el-form-item label="班级名称" prop="clazzId">
          <el-select
            v-model="form.clazzId"
            placeholder="选择班级"
            clearable
            size="small"
            style="width: 240px"
          >
            <el-option
              v-for="clazz in clazzs"
              :key="clazz.clazzId"
              :label="clazz.clazzName"
              :value="clazz.clazzId"
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
import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/student/student";
import {getClazzByProfessId, getProfessionByDepartId, listAllDepartment} from "@/api/student/common";
import {listDepartment} from "@/api/student/department";

export default {
  name: "Student",
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
      // 学生表格数据
      studentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //所有院系
      departAll :{},
      //所有的专业
      professions:{},
      //院系ID
      departId : null,
      //专业ID
      professId :null,
      //班级
      clazzs :{},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentId: null,
        studentName: null,
        studentAge: null,
        studentSex: null,
        studentBirthday: null,
        studentEnrollmentTime: null,
        studentPhonenumber: null,
        studentAddress: null,
        studentKeyContacts: null,
        studentKeyContactsPhonenumber: null,
        userId: null,
        clazzId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      pickerOptions:{
        disabledDate: (time) => {
          let nowYear =new Date().getFullYear();
          return time.getFullYear()>nowYear || time .getFullYear()<nowYear-6
        }
      }
    };
  },
  created() {
    this.getList();
    this.getAllDepartment();
  },
  watch: {
    departId : function (){
      this.professions=null;
      this.professId=null;
      if (!(this.departId ===null||this.departId.length === 0 ) ) {
        this.getProfessionByDepart();
      }
    },
    professId :function () {
      this.clazzs = null ;
      this.queryParams.clazzId =null;
      if (!(this.professId===null||this.professId.length ===0)){
        this.getClazzByProfessionId();
      }
    }
  },
  methods: {
    /** 查询学生列表 */
    getList() {
      this.loading = true;
      listStudent(this.queryParams).then(response => {
        this.studentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /**查询所有院系 */
    getAllDepartment() {
      listDepartment().then(response => {
        this.departAll = response.rows
      })
    },
    /** 通过院系ID查询专业*/
    getProfessionByDepart() {
      getProfessionByDepartId(this.departId).then(response=>{
        this.professions =response.data;
        console.log(response.msg);
      })
    },
    getClazzByProfessionId() {
      getClazzByProfessId(this.professId).then(response =>{
        this.clazzs= response.data;
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
        studentId: null,
        studentName: null,
        studentAge: null,
        studentSex: null,
        studentBirthday: null,
        studentEnrollmentTime: null,
        studentPhonenumber: null,
        studentAddress: null,
        studentKeyContacts: null,
        studentKeyContactsPhonenumber: null,
        userId: null,
        clazzId: null
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
      this.resetChoice();
      this.resetForm("queryForm");
      this.handleQuery();
    },
    resetChoice() {
      this.departId=null;
      this.professId=null;
      this.professions=null;
      this.clazzs=null;
      this.queryParams.clazzId=null;
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
      this.title = "添加学生";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStudent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudent(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除学生编号为"' + ids + '"的数据项？').then(function() {
        return delStudent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('student/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
