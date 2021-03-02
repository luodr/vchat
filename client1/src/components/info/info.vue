<!-- 好友信息 -->
<template>
  <div class="Info-wrapper">
    <div class="newfriend" v-show="selectedFriend.id === 0">
      <div class="nickname">新的好友</div>
    </div>
    <div class="friendInfo" v-if="selectedFriend.id > 0">
      <!-- 好友 -->
     <div v-if="selectedFriend.myFriend">

        <div class="esInfo">
        <div class="left">
          <div class="people">
            <div class="nickname">{{selectedFriend.myFriend.name}}</div>
            <div :class="[selectedFriend.sex===1?'gender-male':'gender-female']"></div>
          </div>
          <div class="signature">{{selectedFriend.signature}}</div>
        </div>
        <div class="right">
          <img class="avatar" width="60" height="60" :src="selectedFriend.myFriend.img" />
        </div>
      </div>
      <div class="detInfo">
        <div class="remark">
          <span>备&nbsp;&nbsp;&nbsp;注</span>
          <!-- {{selectedFriend.remark||selectedFriend.myFriend.name}} -->
          <input v-model="selectedFriend.remark" placeholder="添加添加备注"  @blur="blur(selectedFriend)" />
          <!-- <i class="el-icon-edit"></i> -->
          <!-- <el-input v-model="input" placeholder="请输入内容"></el-input> -->
        </div>
        <div class="area">
          <span>地&nbsp;&nbsp;&nbsp;区</span>
          {{selectedFriend.myFriend.city}}
        </div>
        <div class="wxid">
          <span>微信号</span>
          {{selectedFriend.myFriend.phone}}
        </div>
      </div>
     </div>
          <!-- 群聊 -->
      <div v-else>
    
      <div class="left">
        <div class="esInfo">
          <div class="people">
            <div class="nickname">{{selectedFriend.name}}</div>
          </div>
        
        </div>
      
         <div>
            <span v-for="item in selectedFriend.users.slice(0,20)" :key="item.id"  style="  position: relative;" >
             <el-avatar     :src="item.img"></el-avatar>
          </span>
         </div>    
       
        </div>


     </div>
      <div class="send" @click="send">
        <span>发消息</span>
      </div>
    </div>
    <div v-else class="my-new-friends" >
      <div class="friend-list">
        <div class="friend-item u-f u-f-sbc" v-for="item in newFriendList" :key="item.id">
          <div class="f-left u-f u-f-ac">
            <img :src="item.user.img" alt="头像" class="img-face" />
            <div class="u-f-c">
              <span class="txt1">{{item.user.name}}</span>
              <span class="txt2">{{item.user.signature}}</span>
            </div>
          </div>
          <div>
            <template v-if="item.state=='send'">
              <span class="staus-txt txt3" @click.stop="receiveFriend(item)">接受</span>
              <span class="staus-txt txt4" @click.stop="rejectFriend(item)">拒绝</span>
            </template>
            <span class="staus-txt text5" v-else-if="item.state=='agree'">已添加好友</span>
            <span class="staus-txt text5" v-else-if="item.state=='refuse'">已拒绝</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import router from "../../router";
import { mapGetters, mapState, mapMutations } from "vuex";
import {agreeFriend,refuseFriend,updateRemark} from "@/api/friend";
export default {
  computed: {
    ...mapGetters(["selectedFriend"]),
    ...mapState(["backImg2", "newFriendList"])
  },
  methods: {
    ...mapMutations(["addNewFriendToList"]),
    // 发送信息
    send() {
      this.$store.dispatch("send");
      this.$store.dispatch("search", "");
    },
      blur(item) {

    updateRemark({friendId:item.id,remark:item.remark}).then(date=>{
   this.$notify({
          title: '修改好友备注',
          message: '修改备注成功',
          position: 'bottom-right'
        });
    })
    },
    // 接受
    receiveFriend(target) {
        console.log(target,'target');
      agreeFriend({friendId:target.send_user_id}).then(data=>{

        target.state = 'agree';
      })

    },
    // 拒绝
    rejectFriend(target) {
      refuseFriend({friendId:target.send_user_id}).then(data=>{
          target.state = 'refuse';
      })
 
    }
  }
};
</script>

<style lang="stylus" scoped>
.newfriend {
  height: 60px;
  padding: 28px 0 0 30px;
  box-sizing: border-box;
  border-bottom: 1px solid #e7e7e7;

  .nickname {
    font-size: 18px;
  }
}

.friendInfo {
  padding: 0 90px;
}

.esInfo {
  display: flex;
  align-items: center;
  padding: 100px 0 45px 0;

  .left {
    flex: 1;

    .people {
      .nickname {
        display: inline-block;
        font-size: 20px;
        margin-bottom: 16px;
      }

      .gender-male, .gender-female {
        display: inline-block;
        width: 18px;
        height: 18px;
        vertical-align: top;
        margin-top: 2px;
      }

      .gender-male {
        background-image: url('man.png');
        background-size: cover;
      }

      .gender-female {
        background-image: url('woman.png');
        background-size: cover;
      }
    }

    .signature {
      font-size: 14px;
      color: rgba(153, 153, 153, 0.8);
    }
  }

  .right {
    .avatar {
      border-radius: 3px;
    }
  }
}

.detInfo {
  padding: 40px 0;
  border-top: 1px solid #e7e7e7;
  border-bottom: 1px solid #e7e7e7;

  .remark, .area, .wxid {
    font-size: 14px;
    margin-top: 20px;

    span {
      font-size: 14px;
      color: rgba(153, 153, 153, 0.8);
      margin-right: 40px;
    }
  }

  .remark {
    margin-top: 0;
  }
}

.send {
  position: relative;

  text-align: center;
  width: 140px;
  height: 36px;
  left: 115px;
  top: 50px;
  line-height: 36px;
  font-size: 14px;
  color: #fff;
  background-color: #1aad19;
  cursor: pointer;
  border-radius: 2px;

  &:hover {
    background: rgb(18, 150, 17);
  }
}

.my-new-friends {
  width: 550px;
  height: 540px;
  overflow-y: scroll;
  background-repeat: no-repeat;
  background-size: 100% 100%;

  .friend-list {
    padding: 0px 30px 20px;

    .friend-item {
      padding-top: 30px;
      padding-bottom: 20px;
      border-bottom: 1px solid rgba(238, 238, 238, 0.5);

      &:hover {
        background-color: rgba(220, 220, 220, 0.38);
      }

      .img-face {
        height: 50px;
        width: 50px;
        margin-right: 12px;
		margin-left:10px;
        border-radius: 8px;
      }

      .staus-txt {
        width: 100px;
        padding: 4px 10px;
        border-radius: 4px;
        color: white;
        cursor: pointer;
      }

      .txt1 {
        font-size: 14px;
        color: #444;
        margin: 6px 0;
      }

      .txt2 {
        font-size: 12px;
        color: #666;
        margin: 6px 0;
      }

      .txt3 {
        margin-right: 12px;
        background-color: #45c00c;
      }

      .txt4 {
        background-color: #f54f63;
      }

      .text5 {
        color: #757272;
      }
    }
  }
}
</style>

