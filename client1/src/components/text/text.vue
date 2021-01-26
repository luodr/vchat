<!-- 文本输入框 -->
<template>
  <div class="text">
    <div class="emoji">
      <div class="u-f u-f-ac" style="padding-top:10px;">
        <img src="@/assets/icon-imgs/emoij.png" class="icon-img" @click="showEmoji=!showEmoji" />
    <el-upload
  action="api/file/upload/one"
  :on-success="sendFile"
  list-type="text"
  :show-file-list=false
  >
 
      <img src="@/assets/icon-imgs/f-icon.png" class="icon-img test"  />
  </el-upload>
   <!-- <mt-popup v-model="popupVisible2" position="bottom" class="popup2"> -->
      <img  src="@/assets/icon-imgs/voice-icon.png" class="icon-img" alt="" @click="translationStart" @mouseleave="translationEnd">
      <!-- <span @click="popupShow2">×</span> -->
<!-- </mt-popup> -->
      
        <img src="@/assets/icon-imgs/send-msg.png" class="icon-img" />
        <div style="margin-left:auto;" class="u-f u-f-ac">
          <!-- <img src="@/assets/icon-imgs/voice-icon.png" class="icon-img" />
          <img src="@/assets/icon-imgs/video-icon.png" class="icon-img" /> -->
        </div>
      </div>
      <transition name="showbox">
        <div class="emojiBox" v-show="showEmoji">
          <li v-for="(item, index) in emojis" :key="index">
            <img
              :src="require('@/static/emoji/'+item.file)"
              :data="item.code"
              @click="content +=item.code"
            />
          </li>
        </div>
      </transition>
    </div>
    <textarea ref="text" v-model="content" @keyup="onKeyup" @click="showEmoji=false"></textarea>
    <div class="send" @click="send">
      <span>发送(S)</span>
    </div>
    <transition name="appear">
      <div class="warn" v-show="warn">
        <div class="description">不能发送空白信息</div>
      </div>
    </transition>
  </div>
</template>

<script>
import Recorderx, { ENCODE_TYPE } from 'recorderx';
import { robotChat } from "../../utils/network/user";
import {sendPrivateChat,sendGroupChat,uploadOne,uploadBlobOne} from "@/api/message";
import { mapGetters, mapState } from "vuex";
export default {
  data() {
    return {
      content: "",
      reply: "未找到",
      frequency: 0,
      warn: false,
      showEmoji: false,
            popupVisible2:''
    };
  },
  computed: {
    ...mapState(["selectId", "emojis"]),
    ...mapGetters(["selectedChat"])
  },
  methods: {

    translationStart(){
      console.log("开始！");
            let that = this
            that.rc = new Recorderx()
            that.$nextTick(()=>{
                that.rc.start()
                .then(() => {
                    console.log("start recording");
                }).catch(error => {
                    console.log("Recording failed.", error);
                });
            })
        },

        //录音结束
        translationEnd(){
          if(!this.rc)return
            this.rc.pause()
            var wav = this.rc.getRecord({
                encodeTo: ENCODE_TYPE.WAV,
            });
              var data = new FormData();
               data.append('file',wav);
               data.append('suffix','wav');
              uploadBlobOne(data).then(res=>{
               this. sendMessage(res,'voice')
            })
        
        },
    // 按回车发送信息
    onKeyup(e) {
      if (e.keyCode === 13) {
        this.send();
      }
    },
    // 点击发送按钮发送信息
     send() {
      if(this.content){
        this.sendMessage(this.content,'text')
      }
     },        
      sendFile(response, file, fileList) {
        console.log(response, file, fileList);
      if(response.data){
             let imgs= ['jpg','png','gif']
            if(imgs.includes( response.data.substring( response.data.lastIndexOf('.')+1)))
           {
                this.sendMessage(response.data,'image')
           }else{
                this.sendMessage(response.data,'file')
           }
      }
    },

    sendMessage(content,type){  
 if(this.$store.state.selectItem.myFriend)
       {
        sendPrivateChat({
        target_id:this.$store.state.selectId,
        contentType:'PrivateChat',
        content:content,
        messageType:type
      }).then(data=>{
      if(data.id){
        this.$store.state. selectItem.messages.push(data)
         this.content="";
        
      
      }
      })
    }else{
         sendGroupChat({
        target_id:this.$store.state.selectId,
        contentType:'GroupChat',
        content:content,
        messageType:type
      }).then(data=>{
      if(data.id){
           this.$store.state. selectItem.messages.push(data)
         this.content="";
      
      }
      })
    }
    
  },
  },

  // 在进入的时候 聚焦输入框
  mounted() {
    this.$refs.text.focus();
  },
  watch: {
    // 在选择其它对话的时候 聚焦输入框
    selectId() {
      setTimeout(() => {
        this.$refs.text.focus();
      }, 0);
    },
    // 当输入框中的值为空时 弹出提示  并在一秒后消失
    content() {
      if (this.content === "") {
        if (this.frequency === 0) {
          this.warn = true;
          this.frequency++;
          setTimeout(() => {
            this.warn = false;
          }, 1000);
        }
      }
    }
  }
};
</script>

<style lang="stylus" scoped>
.text {
  position: relative;
  height: 150px;
  background: rgba(255, 255, 255, 0.8);

  .emoji {
    position: relative;
    width: 100%;
    height: 40px;
    line-height: 40px;
    font-size: 12px;
    padding: 0 20px;
    box-sizing: border-box;
    color: #7c7c7c;

    .icon-img {
      cursor: pointer;
      width: 20px;
      height: 20px;
      margin: 0 3px;

      &:hover {
        color: #1aad19;
      }
    }

    .emojiBox {
      position: absolute;
      display: flex;
      flex-wrap: wrap;
      top: -210px;
      left: -100px;
      width: 300px;
      height: 200px;
      padding: 5px;
      background-color: #fff;
      border: 1px solid #d1d1d1;
      border-radius: 2px;
      box-shadow: 0 1px 2px 1px #d1d1d1;

      &.showbox-enter-active, &.showbox-leave-active {
        transition: all 0.5s;
      }

      &.showbox-enter, &.showbox-leave-active {
        opacity: 0;
      }
    }
  }

  textarea {
    box-sizing: border-box;
    padding: 0 30px;
    height: 110px;
    width: 100%;
    border: none;
    outline: none;
    font-family: 'Micrsofot Yahei';
    resize: none;
    background-color: rgba(255, 255, 255, 0);
  }

  .send {
    position: absolute;
    bottom: 10px;
    right: 30px;
    width: 75px;
    height: 28px;
    line-height: 28px;
    box-sizing: border-box;
    text-align: center;
    border: 1px solid #e5e5e5;
    border-radius: 3px;
    background: #f5f5f5;
    font-size: 14px;
    color: #7c7c7c;
    cursor: pointer;

    &:hover {
      background: rgb(18, 150, 17);
      color: #fff;
    }
  }
.test{
   position: relative;
  top :5px;
}
  .warn {
    position: absolute;
    bottom: 50px;
    right: 10px;
    width: 110px;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    text-align: center;
    border: 1px solid #bdbdbd;
    border-radius: 4px;
    box-shadow: 0 1px 5px 1px #bdbdbd;

    &.appear-enter-active, &.appear-leave-active {
      transition: all 1s;
    }

    &.appear-enter, &.appear-leave-active {
      opacity: 0;
    }

    &:before {
      content: ' ';
      position: absolute;
      top: 100%;
      right: 20px;
      border: 7px solid transparent;
      border-top-color: #fff;
      filter: drop-shadow(1px 3px 2px #bdbdbd);
    }
  }
}
</style>
