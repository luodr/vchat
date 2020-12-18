<template>
  <div>
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
        getFriends().then(res=>{
     
        this.$store.state.friendlist=res

       });
        this.$router.push("/chat")
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
</style>
