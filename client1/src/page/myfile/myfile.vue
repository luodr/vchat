<template>
  <div class="my-friends">
    <!-- tab 导航 -->
    <div class="tabs u-f u-f-ajc">
      <ul class="u-f u-f-ajc">
        <li
          class="tab"
          @click="choose(1)"
          :class="{'t-current': currentIndex===1}"
          style="width:40%;"
        >
          <i class="el-icon-star-off"></i> 推荐
        </li>
        <li
          class="tab"
          @click="choose(2)"
          :class="{'t-current': currentIndex===2}"
          style="width:60%;"
        >
          <i class="el-icon-plus"></i> 记录美好生活
        </li>
      </ul>
    </div>
    <!-- 
      tab 对应内容
      需要加一个key值，否则不显示，在移动端并不会出现这个问题
      不加key就使用v-show
    -->
    <div class="main-tab">
      <div class="tab1" v-if="currentIndex==1" :key="currentIndex">
        <p class="pyq">朋友圈</p>
        <div class="t-item" v-for="(item,index) in wechatMoments" :key="item.id">
          <div class="u-f">
            <img :src="item.face" alt="头像" class="face" />
            <div class="t-content">
              <p>{{item.username}}</p>
              <div class="f-cont">{{item.content}}</div>
              <!-- 图片列表 -->
              <div class="img-list">
                <!-- <img :src="img.url" v-for="img in item.imgList" :key="img.id" /> -->
                <viewer :images="item.imgList">
                  <img v-for="(src,index) in item.imgList" :src="src.url" :key="index" />
                </viewer>
              </div>
              <!-- 视频列表 -->
              <template v-if="item.video&&item.video.length>0">
                <div class="video-list u-f u-f-ac">
                  <video
                    style="width:48%;margin:0 10px;"
                    controls
                    v-for="(video,index) in item.video"
                    :key="index"
                  >
                    <source :src="video" type="video/mp4" />您的浏览器不支持Video标签。
                  </video>
                </div>
              </template>

              <div class="u-f u-f-sbc" style="color:#666;font-size:14px;margin-top:12px;">
                <span class="times">{{item.time}}</span>
                <div class="u-f u-f-ajc" style="cursor:pointer;">
                  <div class="u-f u-f-ac" @click="handleActive(index)">
                    <img
                      :src="item.active ? require('@/assets/icon-imgs/p-support-active.png') : require('@/assets/icon-imgs/p-support.png') "
                      class="c-img"
                    />
                    <span>点赞</span>
                  </div>
                  <div class="u-f u-f-ac">
                    <img src="@/assets/icon-imgs/p-msg.png" alt class="c-img" />
                    <span>评论</span>
                  </div>

                  <el-popconfirm title="确定删除此条数据吗？" @onConfirm="deleteOne(item.id)">
                    <img
                      slot="reference"
                      src="@/assets/icon-imgs/p-more.png"
                      alt
                      class="c-img"
                    />
                  </el-popconfirm>
                </div>
              </div>
            </div>
          </div>
          <div class="comments">
            <div class="u-f u-f-ac ellipsis">
              <i class="el-icon-star-off" style="color:#60729A;"></i>
              <template v-if="item.persons.length>14">
                <span
                  v-for="person in item.persons"
                  :key="person.id"
                  class="person-t"
                >{{person.name}}</span>
              </template>
              <template v-else>
                <span
                  v-for="person in item.persons"
                  :key="person.id"
                  class="person-t"
                >{{person.name}}</span>
                <i class="el-icon-more" style="color:#60729A;margin-left:10px;"></i>
              </template>
            </div>
            <div class="com-list">
              <div class="com-item u-f u-f-ac">
                <span>张学友：</span>
                <p>高端大气上档次</p>
              </div>
              <div class="com-item u-f u-f-ac">
                <span>郭富城：</span>
                <p>大家好，我是郭富城</p>
              </div>
              <div class="com-item u-f u-f-ac">
                <span>周星驰：</span>
                <p>大家好，我是周星驰，喜剧之王..., 希望大家多支持支持</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="tab2" v-if="currentIndex==2" :key="currentIndex">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm">
          <el-form-item prop="expressText">
            <el-input
              type="textarea"
              v-model="ruleForm.expressText"
              :rows="5"
              placeholder="这一刻的想法..."
            ></el-input>
          </el-form-item>
        </el-form>
        <!-- 照片组 -->
        <div class="photo-group">
          <!-- :http-request="doUpload" 覆盖默认上传的方式 -->
          <el-upload
            ref="upload"
            multiple
            action
            name="pic"
            :limit="5"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :before-upload="beforeAvatarUpload"
            :http-request="doUpload"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt />
          </el-dialog>
        </div>
        <!-- 选择位置 -->
        <div @click="dialog = true" class="u-f u-f-ac" style="cursor:pointer;margin-bottom:30px;">
          <i class="el-icon-location-information" style="font-size:20px;color:#456;"></i>
          <span style="margin-left:10px;color:#555;font-size:14px;">{{gpsWhere ? gpsWhere : '所在位置'}}</span>
        </div>
        <!-- 选择 -->
        <el-radio-group v-model="radio" style="display:block;margin-bottom:30px;">
          <el-radio :label="1">公开</el-radio>
          <el-radio :label="2">私密</el-radio>
          <el-radio :label="3">部分可见</el-radio>
          <el-radio :label="4">不给谁看</el-radio>
        </el-radio-group>
        <!--  -->
        <el-button
          @click="resetForm('ruleForm')"
          type="info"
          style="width:100px;margin-right:20px;"
        >
          取消
          <i class="el-icon-close" style="margin-left:4px;font-size:16px;"></i>
        </el-button>
        <el-button @click="submitForm('ruleForm')" type="primary" style="width:100px;">
          发布
          <i class="el-icon-upload el-icon--right" style="margin-left:4px;font-size:16px;"></i>
        </el-button>

        <el-dialog title="选择所在位置" :visible.sync="dialog" width="70%" :before-close="handleClose">
          <div class="amap-wrapper">
            <el-amap
              ref="map"
              :vid="'amapDemo'"
              :center="center"
              :zoom="zoom"
              :plugin="plugin"
              :events="events"
              class="amap-demo"
            >
              <el-amap-marker v-for="(u,index) in markers" :position="u.position" :key="index"></el-amap-marker>
              <el-amap-marker :position="[121.5273285, 31.21515044]" :icon="icon"></el-amap-marker>
            </el-amap>
          </div>
          <!-- <div style="margin-top:15px;">
            <el-button @click="dialog = false">取 消</el-button>
            <el-button type="primary" @click="dialog = false">确 定</el-button>
          </div>-->
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
// import wechatMoments from "@/assets/data/wechatMonents.js";
import { timeFrom } from "@/utils/timeFunc.js";
import axios from "axios";
import { mutiUploadFile } from "@/utils/network/user.js";
import Config from "@/utils/config.js";
import { mapState } from "vuex";
import { setStore, getStore } from "@/utils/timeFunc.js";

export default {
  data() {
    return {
      currentIndex: 1,
      isActive: true,
      wechatMoments: [],
      ruleForm: {
        expressText: "" // 文本框
      },
      rules: {
        expressText: [
          { required: true, message: "说点什么吧!", trigger: "blur" }
        ]
      },
      dialogImageUrl: "",
      dialogVisible: false,
      radio: 1,
      dialog: false,
      gpsWhere: "",
      // 地图相关数据
      center: [121.5273285, 31.21515044],
      zoom: 12,
      position: [121.5273285, 31.21515044],
      events: {
        init(o) {
          console.log(o.getCenter());
        },
        zoomchange: e => {
          console.log(e);
        },
        zoomend: e => {
          //获取当前缩放zoom值
          console.log(this.$refs.map.$$getInstance().getZoom());
          console.log(e);
        },
        click: e => {
          console.log(e.lnglat);
          let lng = e.lnglat.O;
          let lat = e.lnglat.P;
          axios
            .get("https://restapi.amap.com/v3/geocode/regeo?", {
              params: {
                key: "74976c5462c5d65770f8e09772189af6",
                location: `${lng},${lat}`
              }
            })
            .then(response => {
              // console.log(response.data.regeocode);
              this.gpsWhere = response.data.regeocode.formatted_address;
              this.dialog = false;
            })
            .catch(function(error) {
              console.log(error);
            });
        }
      },
      markers: [
        {
          position: [121.5273285, 31.41515044]
        },
        {
          position: [121.5273286, 31.31515045]
        }
      ],
      //使用其他组件
      plugin: [
        {
          pName: "Scale",
          events: {
            init(instance) {
              console.log(instance);
            }
          }
        },
        {
          pName: "ToolBar",
          events: {
            init(instance) {
              console.log(instance);
            }
          }
        }
      ],
      icon: require("@/assets/icon-imgs/gps.png"),
      mutiImgVideo: [], // formData
      pathArr: [] // 后台返回的文件路径
    };
  },
  computed: {
    ...mapState(["user"])
  },
  created() {
    this.wechatMoments = JSON.parse(getStore("moments"));
  },
  mounted() {
    this.wechatMoments.forEach(item => {
      if (typeof item.time === "number") {
        item.time = timeFrom(item.time);
      }
    });
    this.getAddress();
  },
  methods: {
    // 删除某条评论
    deleteOne(id) {
      this.wechatMoments =this.wechatMoments.filter(item=>{
        return item.id !== id
      })
      setStore("moments", this.wechatMoments);
    },
    // 上传文件
    async doUpload(file) {
      this.mutiImgVideo.push(file.file);
    },
    // 点击按钮手动上传文件
    async handleMtuiFile() {
      // 文件对象
      let form = new FormData();
      this.mutiImgVideo.forEach(item => {
        form.append("pic", item);
      });
      let result = await mutiUploadFile(form);
      if (result.code === 200) {
        // this.pathArr.length === 1 ? this.pathArr : []
        if (result.path.length > 1) {
          this.pathArr = result.path.map((item, index) => {
            return {
              id: index + 100,
              url: Config.upload + item
            };
          });
        } else {
          this.pathArr = result.path.map(item => {
            return (item = Config.upload + item);
          });
        }

        console.log("文件路径：", this.pathArr);
        let contents = {
          id: Date.now(),
          username: this.user.name,
          face: this.user.img,
          type: 1,
          content: this.ruleForm.expressText,
          imgList: this.pathArr.length === 1 ? [] : this.pathArr,
          video: this.pathArr.length === 1 ? this.pathArr : [],
          time: timeFrom(Date.now()),
          active: false,
          persons: [
            { id: 4, name: "张学友" },
            { id: 5, name: "关之琳" },
            { id: 6, name: "李嘉欣" },
            { id: 7, name: "邱淑贞" },
            { id: 8, name: "蓝洁瑛" },
            { id: 9, name: "陈德容" },
            { id: 10, name: "李连杰" },
            { id: this.user.id, name: this.user.name }
          ]
        };
        console.log(contents, 123);
        // 插入到原来数据，后面会通过接口把数据存储到数据库
        this.wechatMoments.unshift(contents);
        setStore("moments", this.wechatMoments);
        // 清空数据
        this.ruleForm.expressText = "";
        this.$refs.upload.clearFiles();
        this.pathArr = [];
      } else {
        this.$message({
          message: "上传失败~~",
          type: "error",
          duration: 500
        });
      }
    },
    // 根据ip获取位置
    // https://restapi.amap.com/v3/ip?key=74976c5462c5d65770f8e09772189af6&ip=58.213.133.150
    getAddress() {
      axios
        .get("https://restapi.amap.com/v3/ip?", {
          params: {
            key: "74976c5462c5d65770f8e09772189af6",
            ip: "58.213.133.150"
          }
        })
        .then(response => {
          let posi = response.data.rectangle.split(";");
          console.log(posi[0]);
          this.position = posi[0].split(",");
          this.center = posi[0].split(",");
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    choose(index) {
      this.currentIndex = index;
    },
    // 点赞
    handleActive(index) {
      this.wechatMoments[index].active = !this.wechatMoments[index].active;
      if (this.wechatMoments[index].active) {
        this.$message({
          message: "点赞成功",
          type: "success",
          duration: 600
        });
      } else {
        this.$message({
          message: "取消点赞",
          type: "error",
          duration: 600
        });
      }
    },
    // 上传照片相关
    handleRemove(file, fileList) {
      console.log(file, fileList);
      let id = file.uid;
      this.mutiImgVideo = this.mutiImgVideo.filter(item => {
        return item.uid !== id;
      });
      console.log(this.mutiImgVideo, 123);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // 发布 取消
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.handleMtuiFile();
          this.$message({
            message: "发布朋友圈成功",
            type: "success",
            duration: 600,
            onClose: () => {
              this.currentIndex = 1;
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$message({
        message: "取消成功",
        type: "error",
        duration: 600
      });
    },
    handleClose() {
      this.dialog = false;
    },
    // 上传前检测
    beforeAvatarUpload(file) {
      // console.log(file,'检测')
      const typeArr = [
        "image/png",
        "image/jpeg",
        "image/jpg",
        "video/mp4",
        "video/mp3"
      ];
      const isJPG = typeArr.includes(file.type);
      const isLt2M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error("不支持此格式!");
        return false;
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 10MB!");
        return false;
      }
      return isJPG && isLt2M;
    }
  }
};
</script>

<style>
.el-popconfirm__main {
  margin-bottom: 10px;
}
</style>
<style lang="stylus" scoped>
::-webkit-scrollbar {
  width: 0px;
}

.amap-demo {
  height: 510px;
}
.my-friends {
  background-image: url('../../static/images/bg/bg4.jpg');
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 100%;
  overflow-y: scroll;

  .tabs {
    margin: 0px auto;
    // background-color: #f2f2f2;
    background-color: rgba(242, 242, 242, 0.8);
    width: 100%;
    color: #f1931f;
    position: sticky;
    top: 0px;
    padding: 8px 0;
    cursor: pointer;

    ul {
      background-color: #FDF6EC;
      width: 30%;
      height: 40px;
      line-height: 40px;

      .tab {
        text-align: center;

        i {
          color: #f1931f;
        }
      }

      .t-current {
        background-color: #409EFF;
        color: #fff;

        i {
          color: #fff;
        }
      }
    }
  }

  .main-tab {
    margin: 0 20px;

    .pyq {
      font-size: 16px;
      color: #345;
      // border-bottom: 1px solid #e8e8e8;
      border-bottom: 1px solid rgba(233, 233, 233, 0.6);
      padding: 14px 0;
    }

    .tab1 {
      .t-item {
        padding-bottom: 20px;
        margin-top: 20px;
        // border-bottom: 1px solid #e8e8e8;
        border-bottom: 1px solid rgba(244, 244, 244, 0.9);

        .face {
          height: 45px;
          width: 45px;
          border-radius: 100%;
          margin-right: 10px;
          flex-shrink: 0;
        }

        .t-content {
          width: 100%;

          p {
            font-size: 14px;
            color: #409EFF;
          }

          .f-cont {
            margin: 8px 0;
            line-height: 1.6;
            font-size: 13px;
            color: #666;
          }

          .img-list {
            img {
              width: 90px;
              height: 90px;
              border-radius: 4px;
              margin: 0 10px;

              &:nth-of-type(1) {
                margin-left: 0;
              }
            }
          }

          .video-list {
            video:nth-of-type(1) {
              margin-left: 0 !important;
            }
          }

          .c-img {
            width: 16px;
            height: 16px;
            margin: 0 10px;
          }

        }
      }

      .comments {
        margin-left: 55px;
        margin-top: 10px;
        // background-color: #E9E9E9;
        background-color: rgba(233, 233, 233, 0.55);
        border-radius: 8px;
        padding: 15px;
        box-sizing: border-box;

        .person-t {
          font-size: 12px;
          color: #60729A;
          margin-left: 8px;
        }

        .com-list {
          margin-top: 10px;
          font-size: 13px;
          line-height: 1.8;

          .com-item {
            span {
              color: #60729A;
            }

            p {
              color: #567;
            }
          }
        }
      }
    }

    .tab2 {
      padding-top: 15px;

      .demo-ruleForm {
        >>> .el-textarea__inner {
          border: none;
        }

        >>> .el-textarea__inner:focus {
          border-color: rgba(0, 0, 0, 0);
          outline: 0;
          box-shadow: 0px 2px 15px 0px rgba(255, 69, 0, 0.25);
        }
      }

      .photo-group {
        margin-top: 15px;
        margin-bottom: 30px;
      }

      >>> .el-dialog {
        height: 600px;
      }

      >>> .el-dialog__title {
        line-height: 24px;
        font-size: 16px;
        color: #456;
        text-align: center;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
      }
    }
  }
}
</style>