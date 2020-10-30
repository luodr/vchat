<template>
  <div class="empty" :style="sizeBox">
    <VChat-icon type="icon-empty" :style="sizeStyle" />
  </div>
</template>

<script>
export default {
  name: 'VChat-empty',
  props: {
    size: {
      default: '5rem',
    }
  },
  computed: {
    sizeBox () {
      let size = this.size
      if (`${size}`.match(/\d$/)) {
        size += 'px'
      }
      return {
        width: size,
      }
    },
    sizeStyle () {
      let size = this.size
      if (`${size}`.match(/\d$/)) {
        size += 'px'
      }
      return {
        fontSize: `calc(${size} / 2)`,
      }
    }
  },
}
</script>
<style scoped  lang="scss">
.empty {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}
</style>