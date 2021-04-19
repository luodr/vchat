<template>
   <div id="login">
     <div id="login_ner">
      <br>
      <br>
      <br>
     <div class="logo">

             <img style="width:80%;height:80%;  " src="@/assets/icon-imgs/logo_white.png" class="icon-img" />
     </div>
       <br>
      <br>
      <br>
    <div>
      
      <el-input placeholder="" v-model="phone">
        <template slot="prepend"
          ><div style="width: 40px">手机号</div></template
        >
      </el-input>
    </div>
    <div style="margin-top: 15px">
      <el-input placeholder="" v-model="password" type="password">
        <template slot="prepend"
          ><div style="width: 40px">新密码</div></template
        >
      </el-input>
    </div>
    <div style="margin-top: 15px">
      <el-input placeholder="请输入验证码" v-model="code">
        <template slot="append">
          <el-button type="primary" id="reg_codeSend" round @click="sendCode">{{
            codoBtn
          }}</el-button></template
        >
      </el-input>
    </div>
    <div id="register_but_div">
      <el-button type="primary" round id="register_but" @click="toRegister"
        >修改密码</el-button>  

     <p style=" text-align:right">
          <router-link to="/login">

      <span>返回登录</span>
 </router-link>
     </p>
    </div>
     </div>
  </div>
</template>
<script>
import { register, code,forget }  from "@/api/user";
export default {
  name: "register",
  components: {},
  props: {},
  data() {
    return {
      phone: "",
      password: "",
      nowTime: new Date().getTime(),
      jg: 1000 * 60,
      sondF: true,
      codoBtn: "发送验证码",
      code: ""
    };
  },
  created() {},
  mounted() {},
  computed: {},
  watch: {},

  methods: {
    // 发送验证码
     sendCode() {
      if (!this.sondF) {
        return;
      }
     code({phone:this.phone}).then(res=>{
        this.nowTime=new Date().getTime();
     if (res) {
        const interval = setInterval(() => {
          let v = this.nowTime + this.jg - Date.now();
          if (!this.sondF) {
            clearInterval(interval);
          }
          if (v <= 0) {
            //  $('#phoneNum').text("获取验证码");
            this.codoBtn = "发送验证码";
            this.sondF = false;
          } else {
            //  $('#phoneNum').text(Math.ceil(v/1000)+ "秒后再试");
            this.codoBtn = Math.ceil(v / 1000) + "秒后再试";
          }
        }, 1000);
      }

     });
     
    },
    //去注册
     toRegister() {
     forget({
        phone: this.phone,
        password: this.password,
        code: this.code
      }).then(res=>{
        console.log(res,'???')
      //  if(res.data){
         this.$message({
          message: res.message,
         
          duration: 2000
        });
          setTimeout(()=>{
          this.$router.push("/login")
          },500)
      //  }
      })
      
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
#register_but_div {
  text-align: center;
  margin: 15px auto;
}
#register_but {
  width: 200px;
}
#register_but {
  width: 200px;
}
#reg_codeSend {
  color: rgb(9, 114, 189);
}
#login{
 
  width: 860px
}
#login_ner{
 width: 400px;
 margin:0 auto;
}
.logo{
text-align: center;
 /* margin:0 auto; */
}
.title{
  font-size: 50px;
  text-align: center;
  color:rgb(67, 120, 236);
}
</style>
