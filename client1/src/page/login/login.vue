<template>
  <div class="login-contain" v-if="type==1">
    <h2 class="lg-header">用户登录</h2>
    <div class="lg-main">
      <div class="u-f u-f-ajc lg-input">
        <el-input placeholder="请输入邮箱" v-model.trim="email" clearable style="width:64%;">
          <i slot="prefix" class="el-input__icon el-icon-user"></i>
        </el-input>
        <span class="send-email" @click="sendEmail">{{codeTime ? `(${codeTime}s)秒后重发` : '获取验证码'}}</span>
      </div>
      <div class="u-f u-f-ajc lg-input">
        <el-input placeholder="请输入邮箱验证码" v-model="msgcode" clearable>
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </div>
      <div class="u-f u-f-ajc lg-input" @keyup.enter="handleLogin">
        <el-input placeholder="请输入密码" v-model="pwd" type="password" clearable>
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </div>
      <div class="lgcbx u-f u-f-sbc">
        <div class="u-f u-f-ac">
          <el-checkbox v-model="checked"></el-checkbox>
          <span style="font-size:12px;color:#666;margin-left:10px;">下次自动登录</span>
        </div>
        <p style="font-size:12px;color:#666;margin-left:10px;cursor: pointer;">忘记密码？</p>
      </div>
      <el-button type="danger" size="medium" @click="handleLogin" :loading="isloading">登录</el-button>
      <div
        class="u-f u-f-ajc"
        style="text-align: center;margin-bottom: 15px;font-size: 13px;cursor: pointer;"
      >
        <span style="color: darkgray;">还没账号？</span>
        <span style="color: orangered;" @click="goReg(2)">立即注册</span>
      </div>

      <div class="other-lg u-f u-f-asc">
        <p style="font-size:14px;color:#666;">第三方登录</p>
        <div class="u-f u-f-ac">
          <img src="@/assets/icon-imgs/weixin.png" alt class="icon-img" />
          <img src="@/assets/icon-imgs/qq.png" alt class="icon-img" />
          <img src="@/assets/icon-imgs/xinlang.png" alt class="icon-img" />
        </div>
      </div>
    </div>
  </div>
  <div class="login-contain" v-else>
    <h2 class="lg-header">用户注册</h2>
    <div class="lg-main">
      <div class="u-f u-f-ajc lg-input">
        <el-input placeholder="请输入用户名/手机号码" v-model.trim="username" clearable>
          <i slot="prefix" class="el-input__icon el-icon-user"></i>
        </el-input>
      </div>
      <div class="u-f u-f-ajc lg-input">
        <el-input placeholder="请输入密码" v-model="pwd" type="password" clearable>
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </div>
      <div class="u-f u-f-ajc lg-input">
        <el-input placeholder="请输入邮箱" v-model="email" clearable>
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </div>
      <el-button type="danger" size="medium" @click="handleRegister" :loading="isloading">注册</el-button>
      <div
        class="u-f u-f-ajc"
        style="text-align: center;margin-bottom: 15px;font-size: 13px;cursor: pointer;"
      >
        <span style="color: darkgray;">已有账号？</span>
        <span style="color: orangered;" @click="goReg(1)">去登陆</span>
      </div>
    </div>
  </div>
</template>

<script>
import { userLogin, userRegister,sendEmailinfo } from "../../utils/network/user";
export default {
  data() {
    return {
      username: "",
      pwd: "",
      email: "",
      checked: false,
      type: 1,
      isloading: false,
      codeTime: 0, //倒计时
      intervalId: null,
      msgcode: ''
    };
  },
  created() {
    // 监听 用户登录 socket事件
    // this.getUser()
  },
  methods: {
    // 获取链接socket后的用户信息
    // getUser(){
    //   this.socket.on('getUsers',(result)=>{
    //     // console.log(result)
    //     // 更改用户信息
    //     this.$store.commit('getUserInfo',result.user)
    //     this.$message({
    //       message:result.msg,
    //       type: "success",
    //       duration: 1000
    //     });
    //     this.$router.replace('/chat')
    //   })
    // },

    //定时器
    countDown() {
      // 先清除定时器
      if (this.intervalId) {
        clearInterval(this.intervalId);
      }
      if (!this.codeTime) {
        // 启动倒计时
        this.codeTime = 60;
        this.intervalId = setInterval(() => {
          this.codeTime--;
          if (this.codeTime <= 0) {
            // 停止计时
            clearInterval(this.intervalId);
          }
        }, 1000);
      }
    },
    // 点击发送验证码
    sendEmail() {
      if (this.codeTime > 0) {
        this.$message({
          message: "验证码正在发送中",
          type: "error",
          duration: 500,
        });
        return;
      }
      // 发送
      this.sendMsgCode();
    },
    async sendMsgCode() {
      this.countDown()
      let result = await sendEmailinfo({email:this.email});
      if (result.code === 200) {
        // console.log(result)
        this.$message({
          message: result.msg,
          type: "success",
          duration: 800,
        });
      } else {
        this.$message({
          message: result.msg,
          type: "error",
          duration: 800,
        });
      }
    },

    goReg(type) {
      this.isloading = false;
      this.type = type;
    },
    // 登录
    async handleLogin() {
      // 先判断过滤
      if (!this.email) {
        this.$message({
          message: "邮箱不能为空",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (!this.pwd) {
        this.$message({
          message: "密码不能为空",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(this.email)) {
        this.$message({
          message: "邮箱格式不正确",
          type: "error",
          duration: 1500,
        });
        return;
      }
      this.isloading = true;

      let users = {
        pwd: this.pwd,
        email: this.email,
      };
      let result = await userLogin(users);
      if (result.code === 200) {
        this.isloading = false;
        // 发送socket
        // this.socket.emit('userlogins',result)
        this.$store.commit("getUserInfo", result.user);
        setTimeout(() => {
          this.$message({
            message: result.msg,
            type: "success",
            duration: 1000,
          });
          this.$router.replace("/chat");
        }, 1000);
      } else {
        this.isloading = false;
        this.$message({
          message: result.msg,
          type: "error",
          duration: 1500,
        });
      }
    },
    // 注册
    async handleRegister() {
      // 先判断过滤
      if (!this.username) {
        this.$message({
          message: "用户名不能为空",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (!this.pwd) {
        this.$message({
          message: "密码不能为空",
          type: "error",
          duration: 1500,
        });
        return;
      }
      if (!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(this.email)) {
        this.$message({
          message: "邮箱格式不正确",
          type: "error",
          duration: 1500,
        });
        return;
      }
      this.isloading = true;

      let users = {
        username: this.username,
        pwd: this.pwd,
        email: this.email,
        loginTime: new Date(),
      };
      let result = await userRegister(users);
      if (result.code === 200) {
        // 更改用户信息
        this.$store.commit("getUserInfo", result.user);
        this.isloading = false;
        this.$message({
          message: result.msg,
          type: "success",
          duration: 1500,
        });
        this.$router.replace("/chat");
      } else {
        this.isloading = false;
        this.$message({
          message: result.msg,
          type: "error",
          duration: 1500,
        });
      }
    },
  },
};
</script>

<style lang="stylus" scoped>
.send-email {
  font-size: 12px;
  color: #456;
  cursor: pointer;
  margin-left: 4px;
  background-color: #f1f1f1;
  height: 38px;
  line-height: 38px;
  border-radius: 4px;
  width: 90px;
  text-align: center;
}

.send-email:hover {
  background-color: #eee;
}

.el-input__icon {
  font-size: 20px;
  border-right: 1px solid #ccc;
  width: 30px;
}

.el-input {
  width: 88%;
}

>>> .el-input__inner {
  padding-left: 44px;
}

>>> .el-input__inner:focus {
  border-color: rgba(0, 0, 0, 0);
  outline: 0;
  box-shadow: 0px 2px 15px 0px rgba(255, 69, 0, 0.25);
}

>>> .el-checkbox__input.is-checked .el-checkbox__inner {
  background-color: #ff4500;
  border-color: #ff4500;
}

>>> .el-checkbox__input.is-focus .el-checkbox__inner {
  border-color: #ff4500;
}

.el-button--danger {
  color: #FFF;
  background-color: #ff4500;
  border-color: #ff4500;
  width: 84%;
  margin: 20px auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-contain {
  width: 400px;
  min-height:400px;
  max-height: 450px;
  background-color: white;
  border-radius: 5px;
  box-shadow: -10px 20px 100px #ccc;
  position: absolute;
  top: 50%;
  left: 50%;
  margin-left: -200px;
  margin-top: -200px;

  .lg-header {
    width: 100%;
    height: 64px;
    background-color: orangered;
    border-radius: 5px 5px 0 0;
    line-height: 64px;
    color: white;
    text-align: center;
    margin-bottom: 10px;
    cursor: move;
    font-size: 20px;
    font-weight: bold;
  }

  .lg-main {
    margin-top: 20px;

    .lg-input {
      margin-top: 14px;
    }

    .lgcbx {
      width: 84%;
      margin: 14px auto;
    }

    .other-lg {
      // margin-top: 20px;

      .icon-img {
        width: 28px;
        height: 28px;
        margin: 0 14px;
        cursor: pointer;
      }
    }
  }
}
</style>