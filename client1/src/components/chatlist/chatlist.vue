<!-- 聊天列表 -->
<template>
  <div style="position:relative;">
    <div class="msglist">
      <ul>
        <li
          v-for="(item,index) in searchedChatlist"
          class="sessionlist"
          :class="{ active: item.id === selectId }"
          @click="selectSession(item.id)"
          @click.right.prevent="userChoose(item.id,index)"
          @mousedown.prevent="mousedown"
          :key="item.id"
        >
          <div class="list-left">
            <img class="avatar" width="42" height="42" :alt="item.user.name" :src="item.user.img" />
          </div>
          <div class="list-right">
            <p class="name">{{item.user.name}}</p>
            <span class="time">{{item.messages[item.messages.length-1].date | time}}</span>
            <p class="lastmsg">{{item.messages[item.messages.length-1].content}}</p>
          </div>
          <template v-if="user.msgCount>0">
            <span class="msg-count" v-if="index===2">{{user.msgCount}}</span>
          </template>
        </li>
      </ul>
    </div>
    <!-- 用户资料蒙版 -->
    <div class="user-info" v-show="showList" @click="handleshowList">
      <el-card class="box-card" :style="{top: top+'px' }">
        <div class="u-list u-f-c u-f-jsb">
          <p>置顶</p>
          <p>消息免打扰</p>
          <p>设置为星标朋友</p>
          <el-divider></el-divider>
          <p @click.stop="deleteChat">删除聊天</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from "vuex";
export default {
  computed: {
    ...mapState(["selectId", "searchText",'user']),
    ...mapGetters(["searchedChatlist"])
  },
  data() {
    return {
      showList: false,
      id:'',
      top:''
    };
  },
  methods: {
    ...mapActions(["selectSession"]),
    // 右击
    userChoose(id,index) {
      this.selectSession(id);
      this.showList = true
      this.id = id
    },
    // 删除聊天
    deleteChat(){
        // console.log(this.id)
        let deleteIndex = this.searchedChatlist.findIndex(item=>{
            return item.id === this.id
        })
        // console.log('删除索引',deleteIndex)
        this.showList = false;
        this.$store.commit('deleteChatItem',deleteIndex)
    },
    // 关闭蒙版
    handleshowList() {
      this.showList = false;
    },
    mousedown(e){
        let clientY = e.clientY-150
        // console.log('Y坐标距离',clientY)
        this.top = clientY-60
        if(this.top > 400){
            this.top = 360
        }
    }
  },
  mounted() {
    // console.log(this.searchedChatlist);
  },
  filters: {
    // 将日期过滤为 hour:minutes
    time(date) {
      if (typeof date === "string") {
        date = new Date(date);
      }
      if (date.getMinutes() < 10) {
        return date.getHours() + ":0" + date.getMinutes();
      } else {
        return date.getHours() + ":" + date.getMinutes();
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
.msglist {
  position: relative;
  height: 540px;
  overflow-y: auto;

  .sessionlist {
    position: relative;
    display: flex;
    padding: 12px;
    transition: background-color 0.1s;
    font-size: 0;

    &:hover {
      background-color: rgb(220, 220, 220);
    }

    &.active {
      background-color: #c4c4c4;
    }

    .avatar {
      border-radius: 2px;
      margin-right: 12px;
    }

    .list-right {
      position: relative;
      flex: 1;
      margin-top: 4px;
    }

    .name {
      display: inline-block;
      vertical-align: top;
      font-size: 14px;
    }

    .time {
      float: right;
      color: #999;
      font-size: 10px;
      vertical-align: top;
    }

    .lastmsg {
      position: absolute;
      font-size: 12px;
      width: 130px;
      height: 15px;
      line-height: 15px;
      color: #999;
      bottom: 4px;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
    .msg-count{
      text-align:center;
      font-size:12px;
      min-width:20px;
      min-height:20px;
      line-height :20px;
      transform:scale(0.9)
      border-radius:100%;
      background-color:#f54f63;
      color:rgba(255,255,255,0.95);
      position:absolute;
      top:2px;
      left:40px;
    }
  }

  .user-info {
    position: absolute;
    left: 0;
    top: 0px;
    z-index: 99;
    width: $width;
    height: $height;

    .box-card {
      position: absolute;
      left: 60px;
    //   top: 20px;
      width: 140px;
      height: 130px;
      background: rgba(255, 255, 255, 0.9);
      padding-bottom: 10px;
      z-index: 6;

      >>> .el-card__header {
        padding: 10px;
      }

      >>> .el-card__body {
        padding: 10px;
      }

      >>> .el-divider--horizontal {
        margin: 4px 0;
      }

      .u-list {
        padding-left: 10px;
        cursor: pointer;

        p {
          font-size: 14px;
          margin: 7px 0;
        }
      }
    }
  }
}
</style>
