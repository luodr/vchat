<!-- 消息框 -->
<template>
 <div>
    <div class="message" v-if="selectedChat&&selectedChat.myFriend">
    <header class="header u-f u-f-sbc" v-if="selectedChat">
      <div class="friendname">{{(selectedChat.remark||selectedChat.myFriend.name)}}</div>
      <img src="@/assets/icon-imgs/c-gb.png" alt="" style="margin-left:auto;"/>
      <img src="@/assets/icon-imgs/inform.png" alt="" />
      <img src="@/assets/icon-imgs/more.png" alt="" />
    </header>
    <div class="message-wrapper" ref="list">
      <ul v-if="selectedChat">
        <li v-for="(item,index) in selectedChat.messages" class="message-item" :key="item.id" >
      <div v-if="item.context&&item.id">
            <div class="time">
            <span>{{item.updateAt | time}}</span>
          </div>
          <div class="main" :class="{ self: isSelf(item)}"  @mousedown.prevent="mousedown" @click="handleshowList"  @click.right.prevent="userChoose(item,index)">
            <img
              class="avatar"
              width="36"
              height="36"
              :src=" isSelf(item) ? userImg: selectedChat.myFriend.img"
            />
            <div class="content">
              <div class="text" v-html="replaceFace(item.context,item.type,item)"></div>
               <div  class="text" v-if="item.speech"> <p style="text-indent:5px" >{{item.speech}}</p></div>
              <div  class="text" v-if="item.translate"> <p style="text-indent:5px" >翻译:{{item.translate}}</p></div>
                  
            </div>
          </div>
      </div>
        </li>
      </ul>
    </div>
  </div>

  <!--群聊-->
   <div class="message" v-if="selectedChat&&!selectedChat.myFriend">
    <header class="header u-f u-f-sbc" v-if="selectedChat">
      <div class="friendname">{{(selectedChat.remark||selectedChat.name)}}</div>
      <img src="@/assets/icon-imgs/c-gb.png" alt="" style="margin-left:auto;"/>
      <img src="@/assets/icon-imgs/inform.png" alt="" />
 <img src="@/assets/icon-imgs/more.png" alt="" />
    </header>
    <div class="message-wrapper" ref="list">
      <ul v-if="selectedChat">
        <li v-for="item in selectedChat.messages" class="message-item" :key="item.id"  >
      <div v-if="item.context">
            <div class="time">
            <span>{{item.updateAt | time}}</span>
          </div>
          <div class="main" :class="{ self:isSelf(item)}"  @mousedown.prevent="mousedown" @click="handleshowList"  @click.right.prevent="userChoose(item,index)">
            <img
              class="avatar"
              width="36"
              height="36"
              :src="getGroupUser(item.send_user_id).img"
            />
            <div class="content">
            
                <div class="text" v-html="replaceFace(item.context,item.type)"> </div>
                  
             
             <div  class="text" v-if="item.speech"> <p style="text-indent:5px" >{{item.speech}}</p></div>
              <div  class="text" v-if="item.translate"> <p style="text-indent:5px" >翻译:{{item.translate}}</p></div>
            </div>
          </div>
      </div>
        </li>
      </ul>
    </div>
  </div>

   <!-- 用户资料蒙版 -->
    <div class="user-info" v-show="showList" @click="handleshowList">
      <el-card class="box-card" :style="{top: top+'px',left:left+'px' }">
        <div class="u-list u-f-c u-f-jsb">
          <p v-if="this.item.type=='voice'"  @click="speech">语音转文字</p>
          <a  v-if="this.item.type=='image'" :href=" this.item.context" :download="this.item.context">保存图片</a>
         <p v-if="this.item.type=='image'"  @click="imageToText">图片文字识别</p>
          <p v-if="this.item.type=='text'"  @click="translate('zh')">翻译成中文</p>
        <p v-if="this.item.type=='text'"  @click="translate('en')">翻译成英文</p>
          <p >取消</p>
        </div>
      </el-card>
    </div>




    <el-dialog
  title="转换成功"
  :visible.sync="dialogVisible"
  width="30%"
  :before-close="handleClose">
  <div v-html="imageText"></div>
  <span slot="footer" class="dialog-footer">
    <!-- <el-button @click="dialogVisible = false">取 消</el-button> -->
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
   </el-dialog>
 </div>

</template>

<script>
import { mapGetters, mapState } from "vuex";
import { speech ,translate,imageToText} from "@/api/message";



export default {
  
   data() {
    return {
        showList: false,
      userImg:this.$store.state.user.img,
       top:50,
      left:50,
      item:{},
      imageText:"",
      dialogVisible:false
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
imageToText(){
   if(this.item.type=='image'){
     let path=this.item.context.split('/upload/')[1];
       imageToText({path}).then(data=>{
            let obj= JSON.parse(data)
         console.log(obj,"图片文字识别")
           this.imageText="";
          if(obj.TextDetections){
            let Y=0;
           obj.TextDetections.forEach(item=>{
           
              if(Y!=item.ItemPolygon.Y){
                 this.imageText+='</br>'
                //  alert("换行")
              }
              this.imageText+=item.DetectedText
              Y=item.ItemPolygon.Y;
            })
          }
       this.dialogVisible=true;
       })
   }
},
translate(target){
if(this.item.type=='text'){
  translate({text:this.item.context,target,source:'auto'}).then(data=>{
     let obj= JSON.parse(data)
         console.log(obj,"翻译")
          //"{"TargetText":"Happy New year\n","Source":"zh","Target":"en","RequestId":"08721d10-0792-4f27-9835-f46bb6b886b6"}"
          this.item.translate=obj.TargetText
            this.userChoose(this.item)
           this.userChoose(this.item)
  })
}
},

    speech(){

      if(this.item.type=='voice')
     {
            
        let path=this.item.context.split('/upload/')[1];
       speech({path}).then(data=>{
        let obj= JSON.parse(data)
        if(obj.Result){ //腾讯的
            this.item.speech=obj.Result
        }
        if(obj.result){//百度的
           this.item.speech=""
          obj.result.forEach(item=>{
          this.item.speech+=item;
          })
   
        }
               this.userChoose(this.item)
           this.userChoose(this.item)
      })
     }
    },
      userChoose(item) {
      this.item=item;

      this.showList = ! this.showList
 
    },
       mousedown(e){
        let clientY = e.clientY-150
        let clientX = e.clientX
        this.top = clientY;
        this.left=clientX;
        
        },
    handleshowList(){  this.showList = false},
    isSelf(item){
      return item.send_user_id===this.$store.state.user.id;
    },
   getGroupUser(userId){
     const user=  this.selectedChat.users.find(item=>item.id===userId)
   
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
          con =  `<img src='${con}'  style="max-width:200px;max-hight:200px" />
          `
        return con;
      }
      if(type==='voice'){
        //  
//          con =  `
//          <div id="msg" onclick="play()">
//   <audio id="audio" src="https://interactive-examples.mdn.mozilla.net/media/cc0-audio/t-rex-roar.mp3"></audio>
//   <div id="icon"></div>
//   <span class="loading" id="time"></span>
// </div>           
//                  `
                 con=`<audio src="${con}"  controls  style='width:300px;height: 54px;   display: block;'>
                  </audio> `
        return con;
      }
       if(type==='file'){
         con =  `<a href="${con}"  controls  style='width:200px;height: 54px; download="${con.substring(con.lastIndexOf('/')+1)}'>
          <div style='width:30px;height: 30px; display: block; float:left; '> 
          <img src="${require("@/assets/icon-imgs/file.png")}" alt="" style='width:30px;height: 30px;'/>
          
          </div>
          <span style='color:black'>${con.substring(con.lastIndexOf('/')+1)}</span>
          </br>
          点击下载
          </a>`
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


  .user-info {
    position: absolute;
    left: 0;
    top: 0px;
    z-index: 99;
    width: $width;
    height: $height;
    width: 200px;

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


   
</style>
