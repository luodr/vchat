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

import {mapState} from 'vuex'

export default {
  components: {
    search,
    chatlist,
    message,
    vText
  },
  mounted(){

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
