<template>
  <div id="app">
    <div class="imui-center">
      <router-view :key="$route.fullPath"></router-view>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  name: "app",
  data() {
    return {};
  },
  mounted() {},
  methods: {}
};
</script>

<style lang="stylus">
body
  background #3d495c !important
.link
  padding 15px 0
  a
    display inline-block
    font-size 16px
    color #ccd3dc
    text-decoration none
    border-radius 5px
    margin-right 15px
    &:hover
      color #85acda
.action
  margin-top 30px
  button
    margin-right 10px
.imui-center
  position absolute
  top 50%
  left 50%
  transform translate(-50%,-50%)
.drawer-content
  padding 15px
.more
  font-size 32px
  line-height 18px
  height 32px
  position absolute
  top 6px
  right 14px
  cursor pointer
  user-select none
  color #999
  &:active
    color #000
.bar
  text-align center
  line-height 30px
  background #fff
  margin 15px
  color #666
  user-select none
  font-size 12px
.cover
  text-align center
  user-select none
  position absolute
  top 50%
  left 50%
  transform translate(-50%,-50%)
  i
    font-size 84px
    color #e6e6e6
  p
    font-size 18px
    color #ddd
    line-height 50px
.article-item
  line-height 34px
  cursor pointer
  &:hover
    text-decoration underline
    color #318efd
</style>
