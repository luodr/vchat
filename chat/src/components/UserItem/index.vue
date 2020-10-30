<template>
  <div class="item">
    <img
      v-if="config.img"
      :src="config.img"
      :style="coverSize"
      class="cover"
      @click="bindClick({ key:'cover', value: config })"
    />
    <div class="info">
      <p class="name" @click="bindClick({ key:'name', value: config })">{{config.name}}</p>
      <p class="dept" @click="bindClick({ key:'dept', value: config })">{{config.dept}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VChat-item',
  props: {
    config: {
      type: Object,
      default: () => ({
        img: '',
        name: 'VChat',
        dept: '',
      }),
      required: true
    },
    size: {
      default: 35
    }
  },
  computed: {

    coverSize () {
      let size = this.size
      if (`${size}`.match(/\d$/)) {
        size += 'px'
      }
      return {
        width: size,
        height: size,
      }
    }
  },
  methods: {
    bindClick (type) {
      const target = type
      this.$emit('click', target)
    }
  },
}
</script>
<style scoped>
.item {
  display: flex;
  align-items: center;
  position: relative;
}
.item .cover {
  border-radius: 50%;
  margin-right: 12px;
  box-shadow: 0 3px 3px 0 rgba(0, 0, 0, 0.1);
  cursor: pointer;
}
.item .info {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.item .info p {
  margin: 0;
  margin: 0;
  padding: 0;
  width: 175px;
  text-overflow: ellipsis;
  overflow: hidden;
  text-align: left;
  white-space: nowrap;
  font-size: 13px;
  cursor: pointer;
}

.item .info p:hover {
  opacity: 0.8;
}
.item .info p:last-child {
  font-size: 12px;
}
</style>