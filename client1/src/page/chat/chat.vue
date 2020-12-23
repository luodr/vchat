<!--
 * @Description: 
 * @Version: 1.0
 * @Autor: 
 * @Date: 2020-07-06 09:56:05
 * @LastEditors: 
 * @LastEditTime: 2020-07-21 10:07:13
--> 
<template>
  <div class="content">
    <div class="msglist">
      <search></search>
      <chatlist></chatlist>
    </div>
    <div class="chatbox"  :style="{backgroundImage: 'url(' + backImg + ')',backgroundSize:'cover'}">
      <message></message>
      <v-text></v-text>
    </div>
  </div>
</template>

<script>

import search from "../../components/search/search";
import chatlist from "../../components/chatlist/chatlist";
import message from "../../components/message/message";
import vText from "../../components/text/text";
import {getFriends} from "@/api/friend";
import {getInfo} from "@/api/user";
import longSock from "@/socket/socket";
import {mapState} from 'vuex'

export default {
  components: {
    search,
    chatlist,
    message,
    vText
  },
  mounted(){
if(localStorage.token){
  if(!this.$store.state.user.phone){
    getInfo().then(res=>{
         this.$store.state.user=res
    })
  }
     getFriends().then(res=>{
        this.$store.state.friendlist=res
        if(res&&res.length>0)
          this.$store.state. selectId=res[0].id
      });
  longSock("ws://127.0.0.1:8888/webSocket/"+localStorage.token,(evt, ws)=>{
    console.log(evt.data,'evt.data');
});
}
  },
  computed: {
   ...mapState(['backImg'])
   

  }
};
</script>

<style lang="stylus" scoped>
.content {
  display: flex;
  // width: 800px;

  .msglist {
    width: 250px;
    background: rgb(230, 230, 230);
  }

  .chatbox {
    flex: 1;
  }
}
</style>
