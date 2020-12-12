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
    <!-- tab 对应内容 -->
    <div class="main-tab">
      <div class="tab1" v-if="currentIndex===1">
        <div class="t-item">
          <div class="u-f">
            <img src="@/static/images/q.png" alt="头像" class="face" />
            <div class="t-content">
              <p>李一桐</p>
              <div class="f-cont">纤云弄巧，飞星传恨，银汉迢迢暗度。金风玉露一相逢，便胜却人间无数。柔情似水，佳期如梦，忍顾鹊桥归路！两情若是久长时，又岂在朝朝暮暮。</div>
              <div class="img-list">
                <img src="@/static/images/h.png" alt />
                <img src="@/static/images/Guai.jpg" alt />
                <img src="@/static/images/n.png" alt />
              </div>
              <div class="u-f u-f-sbc" style="color:#666;font-size:14px;margin-top:12px;">
                <span class="times">1小时前</span>
                <div class="u-f u-f-ajc">
                  <div class="u-f u-f-ac" @click="handleActive">
                    <img
                      :src="isActive ? require('@/assets/icon-imgs/p-support-active.png') : require('@/assets/icon-imgs/p-support.png') "
                      class="c-img"
                    />
                    <span>点赞</span>
                  </div>
                  <div class="u-f u-f-ac">
                    <img src="@/assets/icon-imgs/p-msg.png" alt class="c-img" />
                    <span>评论</span>
                  </div>
                  <img src="@/assets/icon-imgs/p-more.png" alt class="c-img" />
                </div>
              </div>
            </div>
          </div>
          <div class="comments">
            <div class="u-f u-f-ac">
              <i class="el-icon-star-off" style="color:#60729A;"></i>
              <template v-if="persons.length>14">
                <span v-for="person in persons" :key="person.id" class="person-t">{{person.name}}</span>
              </template>
              <template v-else>
                <span v-for="person in persons" :key="person.id" class="person-t">{{person.name}}</span>
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
                <p>大家好，我是周星驰，喜剧之王...,  希望大家多支持支持</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="tab2" v-else>写内容</div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentIndex: 1,
      isActive: true,
      persons: [
        { id: 1, name: "周星驰" },
        { id: 2, name: "刘德华" },
        { id: 3, name: "周润发" },
        { id: 4, name: "张学友" },
        { id: 5, name: "关之琳" },
        { id: 6, name: "李嘉欣" },
        { id: 7, name: "邱淑贞" },
        { id: 8, name: "蓝洁瑛" },
        { id: 9, name: "陈德容" },
        { id: 10, name: "李连杰" },
        { id: 11, name: "黎姿" },
        { id: 12, name: "王祖贤" },
        { id: 13, name: "成龙" }
      ]
    };
  },
  methods: {
    choose(index) {
      this.currentIndex = index;
    },
    // 点赞
    handleActive() {
      this.isActive = !this.isActive;
    }
  }
};
</script>

<style lang="stylus" scoped>
.my-friends {
  height: 100%;
  padding-bottom: 12px;

  .tabs {
    margin: 0px auto;
    background-color: #f2f2f2;
    width: 100%;
    color: #f1931f;
    position: sticky;
    top: 0px;
    z-index: 9999;
    padding: 8px 0;

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

    .tab1 {
      .t-item {
        padding-bottom: 20px;
        margin-top: 20px;
        border-bottom: 1px solid #e8e8e8;

        .face {
          height: 45px;
          width: 45px;
          border-radius: 100%;
          margin-right: 10px;
          flex-shrink: 0;
        }

        .t-content {
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
                margin: 0;
              }
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
        background-color: #E9E9E9;
        border-radius: 8px;
        padding: 10px;
        box-sizing: border-box;

        .person-t {
          font-size: 12px;
          color: #60729A;
          margin-left: 8px;
        }
        .com-list{
          margin-top:10px;
          font-size:13px;
          line-height:1.8;
          .com-item{
            span{
              color:#60729A;
            }
            p{
              color:#567;
            }
          }
        }
      }
    }
  }
}
</style>