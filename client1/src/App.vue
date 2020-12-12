<!--
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:19:13
 * @LastEditors: 
 * @LastEditTime: 2020-07-21 15:26:30
--> 
<template>
  <div id="app" v-if="$route.meta.isShowAside">
    <div class="sidebar">
      <mycard></mycard>
    </div>
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
  <div id="app" v-else>
    <router-view></router-view>
  </div>
</template>

<script>
import mycard from "./components/mycard/mycard";
import { mapActions } from "vuex";
export default {
  components: {
    mycard
  },
  created() {
    this.$store.dispatch("initData");
  },
  mounted() {
    this.socket.on('getUserSocketID',(socketid)=>{
      console.log('%c https://github.com/wangchenyi1996' , 'font-size: 18px;color: red;text-shadow: 1px 1px 0 orange');
      this.$store.commit('getSocketID',socketid)
    })
  }
};
</script>

<style lang="stylus" scoped>
#app {
  display: flex;
  border-radius: 10px;
  margin: 150px auto;
  width: $width;
  height: $height;
  // background-color: #fff;

  .sidebar {
    width: 60px;
    height: 600px;
    background: #2b2c2f;
  }

  .main {
    flex: 1;
    height: 600px;
    background: #f2f2f2;
    // overflow-y: scroll;
  }
}
</style>
