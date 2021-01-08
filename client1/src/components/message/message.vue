<!-- 消息框 -->
<template>
 <div>
    <div class="message" v-if="selectedChat&&selectedChat.myFriend">
    <header class="header u-f u-f-sbc" v-if="selectedChat">
      <div class="friendname">{{(selectedChat.remark||selectedChat.myFriend.name)}}</div>
      <img src="@/assets/icon-imgs/c-gb.png" alt="" style="margin-left:auto;"/>
      <img src="@/assets/icon-imgs/inform.png" alt="" />
      <img src="@/assets/icon-imgs/c-more.png" alt="" />
    </header>
    <div class="message-wrapper" ref="list">
      <ul v-if="selectedChat">
        <li v-for="item in selectedChat.messages" class="message-item" :key="item.id" >
      <div v-if="item.context">
            <div class="time">
            <span>{{item.updateAt | time}}</span>
          </div>
          <div class="main" :class="{ self: item.self }">
            <img
              class="avatar"
              width="36"
              height="36"
              
              :src="item.self ? userImg: selectedChat.myFriend.img"
            />
            <div class="content">
              <div class="text" v-html="replaceFace(item.context,item.type)"></div>
            </div>
          </div>
      </div>
        </li>
      </ul>
    </div>
  </div>
   <div class="message" v-if="selectedChat&&!selectedChat.myFriend">
    <header class="header u-f u-f-sbc" v-if="selectedChat">
      <div class="friendname">{{(selectedChat.remark||selectedChat.name)}}</div>
      <img src="@/assets/icon-imgs/c-gb.png" alt="" style="margin-left:auto;"/>
      <img src="@/assets/icon-imgs/inform.png" alt="" />
      <img src="@/assets/icon-imgs/c-more.png" alt="" />
    </header>
    <div class="message-wrapper" ref="list">
      <ul v-if="selectedChat">
        <li v-for="item in selectedChat.messages" class="message-item" :key="item.id">
      <div v-if="item.context">
            <div class="time">
            <span>{{item.updateAt | time}}</span>
          </div>
          <div class="main" :class="{ self:isSelf(item)}">
            <img
              class="avatar"
              width="36"
              height="36"
              
              :src="getGroupUser(item.send_user_id).img"
            />
            <div class="content">
              <div class="text" v-html="replaceFace(item.context,item.type)"></div>
            </div>
          </div>
      </div>
        </li>
      </ul>
    </div>
  </div>
 </div>

</template>

<script>
import { mapGetters, mapState } from "vuex";
export default {
  
   data() {
    return {
      userImg:this.$store.state.user.img
    };
  },
  computed: {
    ...mapGetters(["selectedChat", "messages"]),
    ...mapState(["user", "emojis"])

  },
  mounted() {
        
    //  在页面加载时让信息滚动到最下面
    setTimeout(
      () => (this.$refs.list.scrollTop = this.$refs.list.scrollHeight),
      0
    );
  },
  watch: {
    // 发送信息后,让信息滚动到最下面
    messages() {
      setTimeout(
        () => {this.$refs.list.scrollTop = this.$refs.list.scrollHeight ;  console.log("回到底部")},
        0
      );
    }
  },
  methods: {
    isSelf(item){
      return item.send_user_id===this.$store.state.user.id;
    },
   getGroupUser(userId){
     const user=  this.selectedChat.users.find(item=>item.id===userId)
     console.log(user,'user',userId);
       return  user
     },
    //  在发送信息之后，将输入的内容中属于表情的部分替换成emoji图片标签
    //  再经过v-html 渲染成真正的图片
    replaceFace(con,type) {
     if(type==='text'){
        if (con.includes("/:")) {
        var emojis = this.emojis;
        for (var i = 0; i < emojis.length; i++) {
          con = con.replace(
            emojis[i].reg,
            "<img src=" + require("@/static/emoji/" + emojis[i].file) + " />"
          );
        }
        return con;
      }
      }
          if(type==='image'){
          con =  `<img src='${con}'  style="max-width:200px;max-hight:200px" />`
        return con;
      
      }
      
      return con;
    }
  },
  filters: {
    // 将日期过滤为 hour:minutes
    time(date) {
      if(!date) return
      let now=new Date();
      if (typeof date === "string") {
        date = new Date(date);
      }
       if(now.getFullYear()!=date.getFullYear()||now.getMonth()!=date.getMonth()||now.getDate()!=date.getDate())
       {
          return date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日  "+date.getHours() + ":" + date.getMinutes();
       }
       return date.getHours() + ":" + date.getMinutes();
    }
  }
};
</script>

<style lang="stylus" scoped>
.message {
  width: 100%;
  height: 450px;

  .header {
    height: 60px;
    padding: 0 10px;
    box-sizing: border-box;
    border-bottom: 1px solid #e7e7e7;
 

    .friendname {
      font-size: 18px;
     
    }
    img{
      width: 24px;
      height: 24px;
      margin:0 6px;
      cursor: pointer;
    }
  }

  .message-wrapper {
    min-height: 390px;
    max-height: 390px;
    padding: 10px 15px;
    box-sizing: border-box;
    overflow-y: auto;
    border-bottom: 1px solid #e7e7e7;

    .message {
      margin-bottom: 15px;
    }

    .time {
      width: 100%;
      font-size: 12px;
      margin: 7px auto;
      text-align: center;

      span {
        display: inline-block;
        padding: 4px 6px;
        color: #fff;
        border-radius: 3px;
        background-color: #dcdcdc;
      }
    }

    .main {
      .avatar {
        float: left;
        margin-left: 15px;
        border-radius: 3px;
      }

      .content {
        display: inline-block;
        margin-left: 10px;
        position: relative;
        padding: 6px 10px;
        max-width: 330px;
        min-height: 36px;
        line-height: 24px;
        box-sizing: border-box;
        font-size: 14px;
        text-align: left;
        word-break: break-all;
        background-color: #fafafa;
        border-radius: 4px;

        &:before {
          content: ' ';
          position: absolute;
          top: 12px;
          right: 100%;
          border: 6px solid transparent;
          border-right-color: #fafafa;
        }
      }
    }

    .self {
      text-align: right;

      .avatar {
        float: right;
        margin: 0 15px;
      }

      .content {
        background-color: #b2e281;

        &:before {
          right: -12px;
          vertical-align: middle;
          border-right-color: transparent;
          border-left-color: #b2e281;
        }
      }
    }
  }
}
</style>
