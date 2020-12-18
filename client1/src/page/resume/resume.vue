<template>
  <div >
    
 
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