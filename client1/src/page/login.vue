<template>
  <div id="login">
<div id="login_ner">
      <br>
      <br>
      <br>
     
     <div class="logo">

             <img style="width:80%;height:80%;  " src="@/assets/icon-imgs/logo_white.png" class="icon-img" />
   <p style=" text-align:right;">
         
          <router-link to="/forgot">
  <span>&nbsp; 忘记密码?</span>
 </router-link>
     </p>
             
     </div>
       <br>
      <br>
      <br>
    <div>
      <el-input placeholder="" v-model="phone">
        <template slot="prepend"
          ><div style="width:40px">手机号</div></template
        >
      </el-input>
    </div>
    <div style="margin-top: 15px;">
      <el-input placeholder="" v-model="password" type="password">
        <template slot="prepend"
          ><div style="width:40px">密码</div></template
        >
      </el-input>
    </div>
    <div id="login_but_div">
      <el-button type="primary" round id="login_but" @click="toLogin"
        >登录</el-button
      > 

      
   <br>
       <p style=" text-align:right;">
         
          <router-link to="/reg">
  <span>&nbsp; 没有账号?</span>
 </router-link>
     </p>
    </div>

</div>
  </div>
</template>
<script>
import { login } from "@/api/user";
import {getFriends} from "@/api/friend";
import {getMessage} from "@/api/message";
export default {
  name: "login",
  components: {},
  props: {},
  data() {
    return {
      phone: "",
      password: ""
    };
  },
  created() {},
  mounted() {
  
  },
  computed: {},
  watch: {},

  methods: {
     toLogin() {
       login({
        password: this.password,
        phone: this.phone
      }).then(res=>{
      if(res.code){
     
        this.$router.push("/chat")
      }else{
         this.$message({
          message: "账号或密码错误",
         
          duration: 2000
        });
      }
      });
     
    }
  }
};
</script>
<style>
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
#login_but_div {
  text-align: center;
  margin: 15px auto;
}
#login_but {
  width: 200px;
}
#login{
 
  width: 860px
}
#login_ner{
 width: 400px;
 margin:0 auto;
}
.title{
  font-size: 50px;
  text-align: center;
  color:rgb(67, 120, 236);
}
.logo{
text-align: center;
 /* margin:0 auto; */
}
</style>
