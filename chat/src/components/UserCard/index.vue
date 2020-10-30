<template>
  <div class="userCard" :style="boxStyle">
    <img v-if="cover" :src="cover" :style="coverStyle" class="cover" alt />
    <div class="item" v-for="(item,k) in data" :key="k">
      <span class="icon">
        <i v-if="icon" :class="item.icon||icon" />
      </span>
      <span class="label">{{item.label}}</span>
      <span class="content">{{item.value}}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VChat-card',
  props: {
    config: {
      type: Object,
    },
    data: Array
  },
  data () {
    return {
      icon: 'el-icon-star-off',
      cover: '/image/two.jpeg'
    }
  },
  watch: {
    config (newValu) {
      const { icon = false, cover = false } = newValu || {}
      this.icon = icon
      this.cover = cover
    }
  },
  computed: {
    boxStyle () {
      let width = "25vw";
      let height = '60vh';
      const { width: w, height: h } = this.config || {}
      if (w && `${w}`.match(/\d$/)) {
        width = w + 'vw'
      }
      if (h && `${h}`.match(/\d$/)) {
        height = h + 'vh'
      }
      return {
        width,
        height
      }
    },
    coverStyle () {
      let width = '150px'
      const { coverStyle = {} } = this.config || {}
      return Object.assign({ width }, coverStyle)
    }
  }
}
</script>
<style  scoped>
.userCard {
  padding: 0.5rem 0.8rem;
  margin: 0px;
  background: #fff;
  margin: 0 auto;
  border-radius: 0.7rem;
  text-align: left;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
}
.userCard .item {
  width: 100%;
  line-height: 1.6rem;
  padding: 0.1rem 0;
  display: flex;
}
.userCard .item > * {
  display: inline-block;
}
.cover {
  width: 50px;
}
.icon {
  width: 1.2rem;
}
.label {
  width: 5rem;
  text-align: left;
  opacity: 0.7;
}
.content {
  padding-left: 0.4rem;
}
</style>