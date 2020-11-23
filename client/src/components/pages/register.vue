<template>
  <div>
  <div>
  <el-input placeholder="" v-model="phone">
    <template slot="prepend"><div style="width:40px">手机号</div></template>
  </el-input>
</div>
  <div  style="margin-top: 15px;"> 
   
    <el-input placeholder="" v-model="password" type="password">
    <template slot="prepend" ><div style="width:40px">密码</div></template>
  </el-input>
  </div>
 <div  id="register_but_div"> 
   
   <el-button type="primary" round id="register_but" @click="toRegister">注册</el-button>
  </div>
  </div>
</template>
<script>
// import {register} from "../../api/user"
export default {
  name: "register",
  components: {},
  props: {},
  data() {
    return {
    phone:'',
    password:''
    };
  },
  created() {},
  mounted() {

    
    
  },
  computed: {},
  watch: {},

  methods: {
   async toRegister(){
   
    //  await register({
    //    password:this.password,
    //    phone:this.phone
    //  });
console.log(  localStorage.token);

    }
  }
};
</script>
<style >

  .el-select .el-input {
    width: 130px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  #register_but_div{
    text-align: center;
    margin:15px auto;
  }
    #register_but{
    width: 200px;
  }
</style>
