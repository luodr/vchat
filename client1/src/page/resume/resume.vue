<template>
  <div class="resume">
    <div class="about">
      <h1 class="name"></h1>
      <div class="intention">
        求职意向：
        <span>web前端工程师</span>
      </div>
      <div class="info">
        <div class="info-title">个人信息：</div>
        <div class="info-item">
          <i class="icon school"></i>
          <span>苏州托普技术学院</span>
        </div>
        <div class="info-item">
          <i class="icon major"></i>
          <span>软件工程</span>
        </div>
        <div class="info-item">
          <i class="icon phone"></i>
          <span>18112646774</span>
        </div>
        <div class="info-item">
          <i class="icon email"></i>
          <span>2845189781@qq.com</span>
        </div>
        <div style="margin-top:30px">
          <p style="margin-bottom:10px;">github地址</p>
          <span style="word-break: break-word;line-height:1.6;">https://github.com/wangchenyi1996</span>
        </div>
      </div>
    </div>
    <div class="power">
      <div class="power-item">
        <div class="title">
          <i class="icon technology"></i>
          <span class="name">技术栈</span>
        </div>
        <div class="technology-item">
          熟悉掌握 JavaScript、Vue全家桶、React、webpack、微信小程序开发、HTML5、SCSS、CSS3。
          掌握Flutter框架，对页面布局熟悉，独立做过小项目，React、Redux
          熟悉运用Uni-APP制作H5、APP、微信小程序。
          能够运用Node.js、MongoDB、PHP、MySQL、ThinkPHP等技术。
          熟悉运用VS Code、IDEA、Sublime Tset、Hbuilder等开发工具。
        </div>
      </div>
      <div class="power-item">
        <div class="title">
          <i class="icon evaluation"></i>
          <span class="name">个人评价</span>
        </div>
        <div class="myevaluation">
          为人比较真诚，做事细心，有时性格也比较急躁为了赶进度，执行力强，在工作中敢于面对困难与挑战，有团队合作精神、专业知识较为扎实，
          熟悉并能掌握使用JavaScript、Vue 技术栈、React、jQuery、PHP等编程语言及常用框架，熟悉并能使用各种编程语言的开发工具。
          对uni-app开发APP、H5、微信小程序较有心得，能够利用Uni-App独立制作APP、H5应用，喜欢自学新知识并做些小的练习项目。
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="stylus" scoped>
.resume {
  display: flex;
  height: 100%;

  .about {
    width: 250px;
    background-color: rgb(36, 70, 100);
    padding: 10px;
    box-sizing: border-box;
    color: #fff;

    .name {
      text-align: center;
      margin: 20px auto 40px;
      font-size: 28px;
      font-weight: 600;
    }

    .intention {
      font-size: 18px;
      margin-bottom: 20px;

      span {
        text-align: right;
        font-size: 16px;
        line-height: 18px;
        margin: 20px 0;
        letter-spacing: 1px;
      }
    }

    .info {
      .info-title {
        font-size: 18px;
      }

      .info-item {
        display: flex;
        margin: 20px 0 20px 0;
        height: 40px;
        line-height: 40px;

        .icon {
          width: 40px;
          height: 40px;
          background-color: #fff;
          border-radius: 50%;
          margin-right: 20px;
          background-position: center;
          background-repeat: no-repeat;
          background-size: 26px;

          &.school {
            background-image: url('school.png');
          }

          &.major {
            background-image: url('major.png');
          }

          &.phone {
            background-image: url('phone.png');
            background-size: 32px;
          }

          &.email {
            background-image: url('email.png');
          }
        }
      }
    }
  }

  .power {
    flex: 1;
    overflow-y: auto;
    padding: 70px 50px 30px 50px;
    background-color: #fff;

    .power-item {
      color: rgb(36, 70, 100);

      .title {
        display: flex;
        margin-bottom: 20px;

        .icon {
          display: inline-block;
          width: 50px;
          height: 50px;
          background-color: rgb(36, 70, 100);
          border-radius: 50%;
          margin-right: 10px;
          background-position: center;
          background-repeat: no-repeat;
          background-size: 30px;

          &.technology {
            background-size: 35px;
            background-image: url('technology.png');
          }

          &.evaluation {
            background-image: url('evaluation.png');
          }
        }

        .name {
          flex: 1;
          display: inline-block;
          height: 50px;
          font-size: 24px;
          font-weight: 600;
          line-height: 50px;
          box-sizing: border-box;
          border-bottom: 2px solid rgb(36, 70, 100);
        }
      }

      .technology-item {
        margin: 20px auto;
        font-size: 14px;
        line-height: 25px;
        display: flex;

        span {
          display: inline-block;
          font-size: 18px;
        }
      }
    }
  }
}
</style>