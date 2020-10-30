<template>
  <div class="login-container" :style="boxStyle">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="config.rules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <el-form-item prop="username">
        <span class="svg-container">
          <i class="el-icon-user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="账号"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <i class="el-icon-lock" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <VChat-icon :type="passwordType === 'password'?'icon-zyj_unlooked':'icon-look'" />
        </span>
      </el-form-item>

      <el-button type="primary" style="width:100%;" @click.native.prevent="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'VChat-login',
  props: {
    width: {
      type: String,
      default: '100vw'
    },
    config: {
      type: Object,
      default: function () {
        return {
          rules: {
            username: [{
              required: true, trigger: 'blur', validator: function (rule, value, callback) {
                if (!value) {
                  callback(new Error('请输入账号'))
                } else {
                  callback()
                }
              }
            }],
            password: [{
              required: true, trigger: 'blur', validator: function (rule, value, callback) {
                if (!value) {
                  callback(new Error('请输入密码'))
                } else {
                  callback()
                }
              }
            }]
          }
        }
      }
    }
  },
  data () {
    return {
      loginForm: {},
      passwordType: 'password',
    }
  },
  computed: {
    boxStyle () {
      let width = this.width
      if (`${width}`.match(/\d$/)) {
        width += 'px'
      }
      return {
        width
      }
    }
  },
  methods: {
    showPwd () {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin () {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$emit('submit', this.loginForm)
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    /deep/ input {
      background: transparent !important;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}

$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  background: $bg;
  margin: 0 auto;
  padding: 1rem 0;
  position: relative;
  width: 100%;
  height: 100%;
  border-radius: 0.5rem;
  .login-form {
    position: absolute;
    width: 520px;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
