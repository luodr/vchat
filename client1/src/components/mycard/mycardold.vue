<!-- 最左边的选择框 -->
<template>
  <div class="mycard">
    <header @click.right.prevent="showOnline">
      <img :src="user.img" class="avatar" @click="lookUserinfo" />
      <span
        :class="user.onLineStatus ===1 ? 'onlines' : user.onLineStatus ===2 ? 'leaves' : user.onLineStatus ===3 ? 'busys' : 'appears'"
        class="dot"
      ></span>
      <!-- 更换头像 -->
      <!-- <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img :src="user.img" class="avatar" />
          <span
            :class="user.onLineStatus ===1 ? 'onlines' : user.onLineStatus ===2 ? 'leaves' : user.onLineStatus ===3 ? 'busys' : 'appears'"
            class="dot"
          ></span>
      </el-upload>-->
    </header>
    <!-- 用户资料 -->
    <div class="user-info" v-show="showUser" @click="handleShowUser">
      <el-card class="box-card">
        <div slot="header" class="user-header u-f u-f-sbc">
          <div class="u-left u-f-c u-f-jsb">
            <div style="font-size:15px;margin-top:8px" class="u-f u-f-ac">
              <span>李一桐</span>
              <img
                src="@/assets/icon-imgs/person-icon.png"
                alt=""
                style="height:16px;width:16px;margin-left:10px;"
              />
            </div>
            <p style="font-size:13px;color:#666;margin-top:8px;">手机号： +86 1325272888</p>
            <p style="font-size:13px;color:#666;margin-top:8px;">昵称：微笑天使女孩</p>
          </div>
          <img :src="user.img" class="u-right" />
        </div>
        <p class="u-mark">设置备注和标签</p>
        <el-divider></el-divider>
        <div class="u-area u-f">
          <span style="color:#333;margin-right:15px;">地区</span>
          <span>山东 济南市</span>
        </div>
        <el-divider></el-divider>
        <div class="u-footer u-f u-f-end">
          <img src="@/assets/icon-imgs/send-card.png" alt="" class="u-icon"/>
          <img src="@/assets/icon-imgs/send-msg.png" alt="" class="u-icon"/>
        </div>
      </el-card>
    </div>

    <div class="navbar" @click="clearSearch">
      <router-link to="/chat" class="icon iconfont icon-msg"></router-link>
      <router-link to="/friend" class="icon iconfont icon-friend"></router-link>
      <router-link to="/my" class="icon iconfont icon-collection"></router-link>
      <router-link to="/myfile" class="icon1 icons">
        <!-- <img src="@/assets/icon-imgs/file-icon.png" alt class="icons" /> -->
      </router-link>
    </div>
    <footer>
      <img src="@/assets/icon-imgs/xcx-icon.png" class="icons2" />
      <div class="red-box">
        <span class="dot-red"></span>
        <i class="icon iconfont icon-more" style="margin-top:0;"></i>
      </div>
    </footer>
    <!-- 蒙版 -->
    <div class="online-card" v-show="ishow" @click="handleShow">
      <!-- 在线状态显示列表 -->
      <el-card class="box-card">
        <div
          v-for="item in onlineStatusList"
          :key="item.id"
          class="text-item"
          @click.stop="changeOnlineStatus(item.id)"
        >
          <span
            :class="item.id ===1 ? 'onlines' : item.id ===2 ? 'leaves' : item.id ===3 ? 'busys' : 'appears'"
            class="dot"
          ></span>
          <span>{{ item.name }}</span>
        </div>
        <el-divider></el-divider>
        <div class="h-list">
          <div style="text-align:center;">
            <el-upload
              class="avatar-uploader"
              action="https://jsonplaceholder.typicode.com/posts/"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <p>更换头像</p>
            </el-upload>
          </div>
          <p>关闭桌面通知</p>
          <p>关闭提醒声音</p>
          <p>退出</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  data() {
    return {
      imageUrl: "",
      ishow: false,
      showUser:false
    };
  },
  computed: {
    ...mapState(["user", "onlineStatusList"])
  },
  methods: {
    // 查看用户资料
    lookUserinfo() {
       this.showUser = !this.showUser;
    },

    // 关闭蒙版
    handleShow() {
      this.ishow = false;
    },

    // 关闭用户资料蒙版
    handleShowUser(){
      this.showUser = false;
    },

    // 选择离线还是在线
    changeOnlineStatus(id) {
      this.$store.commit("changeStatus", id);
      this.handleShow();
    },

    // 显示在线状态
    showOnline() {
      this.ishow = !this.ishow;
    },

    clearSearch() {
      this.$store.dispatch("search", "");
    },

    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      //   console.log("新头像：", this.imageUrl);
      this.$store.commit("changeFace", this.imageUrl);
    },
    beforeAvatarUpload(file) {
      const typeArr = ["image/png", "image/jpeg", "image/jpg"];
      const isJPG = typeArr.includes(file.type);
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG|PNG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    }
  }
};
</script>

<style lang="stylus" scoped>
.mycard {
  position: relative;
  width: 100%;
  height: 100%;

  header {
    .dot {
      display: inline-block;
      height: 10px;
      width: 10px;
      border-radius: 100%;
      margin-right: 10px;
      position: absolute;
      left: 10px;
      top: 16px;
      cursor: pointer;
    }

    .onlines {
      background-color: #45C00C;
    }

    .leaves {
      background-color: #FFBA00;
    }

    .busys {
      background-color: #F54F63;
    }

    .appears {
      background-color: #D9D9D9;
    }
  }

  .avatar {
    width: 36px;
    height: 36px;
    margin: 20px 12px 0 12px;
    border-radius: 2px;
    position: relative;
    cursor: pointer;
  }

  .navbar {
    width: 100%;
    text-align: center;
  }

  .icons {
    background-image: url('../../assets/icon-imgs/file-icon.png');
    width: 26px;
    height: 26px;
    margin-top: 22px;
    background-repeat: no-repeat;
    background-size: 100% 100%;

    &.active {
      width: 26px;
      height: 26px;
      background-image: url('../../assets/icon-imgs/active-file.png');
      background-repeat: no-repeat;
      background-size: 100% 100%;
    }
  }

  .icon1 {
    display: inline-block;
    opacity: 0.8;
    cursor: pointer;
  }

  .icon {
    display: inline-block;
    font-size: 26px;
    margin-top: 28px;
    padding: 0 16px;
    box-sizing: border-box;
    color: rgb(173, 174, 175);
    opacity: 0.8;
    cursor: pointer;

    &.active {
      color: rgb(0, 220, 65);
    }

    &:hover {
      opacity: 1;
    }
  }

  .icon-msg, .icon-more {
    font-size: 22px;
  }

  .icon-msg {
    padding: 0 19px;
  }
}

footer {
  position: absolute;
  bottom: 20px;
  width: 100%;
  text-align: center;

  .red-box {
    position: relative;
    margin-top: 20px;

    .dot-red {
      position: absolute;
      right: 15px;
      display: inline-block;
      width: 8px;
      height: 8px;
      border-radius: 100%;
      background-color: #FA5151;
    }
  }

  .icons2 {
    width: 26px;
    height: 26px;
    cursor: pointer;
  }
}

.online-card {
  position: absolute;
  left: 0;
  top: 0;
  z-index: 9;
  width: $width;
  height: $height;

  >>> .box-card {
    position: absolute;
    left: 52px;
    top: 20px;
    width: 100px;
    background: rgba(255, 255, 255, 0.9);
    padding-bottom: 10px;
  }

  >>> .el-card__body {
    padding: 0;
  }

  >>> .el-divider--horizontal {
    margin: 10px 0;
  }

  .text-item {
    padding-left: 12px;
    font-size: 13px;
    margin: 16px 0;
    cursor: pointer;

    .dot {
      display: inline-block;
      height: 10px;
      width: 10px;
      border-radius: 100%;
      margin-right: 10px;
    }

    .onlines {
      background-color: #45C00C;
    }

    .leaves {
      background-color: #FFBA00;
    }

    .busys {
      background-color: #F54F63;
    }

    .appears {
      background-color: #D9D9D9;
    }
  }

  .h-list {
    margin-top: 10px;

    p {
      line-height: 2;
      font-size: 13px;
      text-align: center;
    }
  }
}

.user-info {
   position: absolute;
  left: 0;
  top: 0;
  z-index: 9;
  width: $width;
  height: $height;

  .box-card {
    position: absolute;
    left: 52px;
    top: 20px;
    width: 270px;
    height: 300px;
    background: rgba(255, 255, 255, 0.9);
    padding-bottom: 10px;
    z-index: 6;

    >>> .el-card__header {
      padding: 10px;
    }

    >>> .el-card__body {
      padding: 10px;
    }

    .user-header {
      margin: 10px;

      .u-right {
        width: 60px;
        height: 60px;
        border-radius: 4px;
      }
    }

    .u-mark {
      font-size: 15px;
      padding: 10px;
      cursor: pointer;
    }

    >>> .el-divider--horizontal {
      margin: 5px 0;
    }

    >>> .el-divider {
      background-color: #EBEEF5;
    }

    .u-area {
      padding: 10px;
      font-size: 15px;
      color: #666;
    }
    .u-footer{
      .u-icon{
        height:26px;
        width:26px;
        margin:12px;
        cursor: pointer;
      }
    }

  }
}
</style>
