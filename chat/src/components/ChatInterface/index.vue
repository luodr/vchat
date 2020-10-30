<template>
  <div class="ChatPage" :style="faceSize">
    <div class="header">
      <VChat-item :config="config" @click="bindClick" />
      <slot name="header" />
    </div>
    <div class="main">
      <div class="chatBox">
        <VChat
          :taleList="taleList"
          @enter="enter"
          v-model="msg"
          :toolConfig="toolConfig"
          :scrollType="scrollType"
          :width="chatWidth"
          :height="chatHeight"
          :config="chatConfig"
          @clickTalk="$emit('clickTalk',$event)"
        >
          <slot name="tools" slot="tools" />
        </VChat>
      </div>
      <div class="rightBox" v-if="showRightBox">
        <slot />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "VChat-index",
  props: {
    config: {
      type: Object,
      default: () => ({
        img: 'image/cover.png',
        name: 'VChat',
        dept: '最简单、最便捷',
        callback: () => { }
      })
    },
    showRightBox: {
      type: Boolean,
      default: true
    },
    taleList: {
      type: Array,
      default: () => {
        return []
      }
    },
    height: {
      type: String,
      default: "570"
    },
    width: {
      default: "750"
    },
    value: {
      default: ''
    },
    toolConfig: {
      type: Object
    },
    scrollType: {
      default: "noroll"
    },
  },
  data () {
    return {
      chatHeight: '',
      chatWidth: "",
      msg: ''
    }
  },
  computed: {
    faceSize () {
      let height = this.height
      let width = this.width
      if (height.match(/\d$/)) {
        height += 'px'
      }
      if (width.match(/\d$/)) {
        width += 'px'
      }
      const style = { height, width }
      return style
    },
    chatConfig () {
      const { historyConfig = {} } = this.config || {}
      return { historyConfig }
    },
  },
  watch: {
    height: {
      handler () {
        this.chatHeight = this.height - 60 + ''
      },
      immediate: true
    },
    width: {
      handler () {
        const width = this.showRightBox ? this.width * .7:this.width
        this.chatWidth = width + ''
      },
      immediate: true
    },
    value: {
      handler () {
        this.msg = this.value;
      },
      immediate: true
    },
    msg: {
      handler () {
        this.$emit('input', this.msg);
      },
      immediate: true
    }
  },
  methods: {
    bindClick (type) {
      const { callback } = this.config || {}
      if (callback) {
        callback(type)
      }
    },
    enter (msg) {
      this.$emit('enter', msg)
    }
  }
}
</script>

<style  scoped>
.ChatPage {
  margin: 0 auto;
  background: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.ChatPage .header {
  background-color: #409eff;
  display: flex;
  margin: 0 auto;
  padding-left: 12px;
  align-items: center;
  height: 60px;
  position: relative;
  color: #fff;
}

.ChatPage .main {
  display: flex;
  height: calc(100% - 60px);
}
.ChatPage .main .rightBox {
  box-shadow: 0 -3px 3px 0 rgba(0, 0, 0, 0.1);
  width: 45%;
  overflow: auto;
}
</style>
