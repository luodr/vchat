<template>
  <div>
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
          ><div style="width: 40px">密码</div></template
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
        >注册</el-button
      >    <router-link to="/login">
      <span>已有账号?去登录</span>
 </router-link>
    </div>
  </div>
</template>
<script>
import { register, code }  from "@/api/user";
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
     register({
        phone: this.phone,
        password: this.password,
        code: this.code
      }).then(res=>{
        console.log("注册", res);
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
</style>
