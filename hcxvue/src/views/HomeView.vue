<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button class="ml-5" type="primary" @click="handleSearch">查询</el-button>
    </div>
    <el-row>
      <el-button class="ml-5" type="primary" @click="handleAdd">增加</el-button>
    </el-row>
    <el-container style="height: 100%;    ">
      <el-container>
        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="id" label="id" width="140"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="age" label="年龄" width="120"></el-table-column>
            <el-table-column prop="sex" label="性别" width="120"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column prop="phone" label="电话"></el-table-column>
            <el-table-column label="操作"  width="500" align="center">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                <el-popconfirm
                    class="ml-5"
                    confirm-button-text='确定'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="green"
                    title="您确定删除吗？"
                    @confirm="handleDel(scope.row.id)"
                >
                  <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>

              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                layout="prev, pager, next"
                :total="total">
            </el-pagination>
          </div>
          <el-dialog title="人物信息" :visible.sync="dialogFormVisible">
            <el-form label-width="50px">
              <el-form-item label="姓名">
                <el-input v-model="form.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="年龄">
                <el-input v-model="form.age" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-input v-model="form.sex" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

import request from "@/utils/request";

export default {
  name: 'home',
  data(){
    return {
      tableData: [],
      total: 0,
      msg: "hello,hcx",
      id: 7,
      // 输入框中的字段需要初始化？
      name: "",
      objectId: "",
      dialogFormVisible: false,
      form: {}
    }
  },
  created() {
    // 查询全部
    this.load()
  },
  methods: {
    load(){
      fetch("http://localhost:9090/user").then(res => res.json()).then(res =>{
        console.log(res)
        this.tableData=res
      })
    },
    handleSearch(){
      this.request.get("http://localhost:9090/user/search/"+this.name).then(res => {
        console.log(res)
        this.tableData=[res]
      })
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    save() {
      request.post("http://localhost:9090/user/add",this.form).then(res =>{
        if (res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    handleEdit(row){
      // 往弹窗中赋予数据，并打开弹窗
      this.form=row
      this.dialogFormVisible=true
    },
    handleDel(id){
      request.delete("http://localhost:9090/user/"+id).then(res => {
        if (res){

          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
  }
}
</script>
