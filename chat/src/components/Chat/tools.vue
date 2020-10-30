<template>
  <div class="toolsBox">
    <div class="web__tools">
      <dl v-if="showEmoji()">
        <el-popover placement="top-start" trigger="click" ref="popover">
          <div class="emjioBox">
            <ul class="emjio">
              <li v-for="item in Object.keys(emoji)" :key="item" @click="selectEmit(item)">
                <a v-if="emoji[item]&&emoji[item].position" :style="emojiStyle(item)"></a>
                <a v-else-if="emoji[item].length<5">{{emoji[item]}}</a>
                <img v-else :src="emoji[item]" />
              </li>
            </ul>
          </div>
          <VChat-icon slot="reference" type="icon-xiaolian" title="表情" />
        </el-popover>
      </dl>
      <template v-for="(item,k) in showkeys()">
        <span
          v-if="toolConfig[item]"
          :key="item"
          :style="item=='history'&&'flex-grow: 100;text-align: right;'"
        >
          <span @click="bindButton(item)">
            <VChat-icon :type="toolConfig[item].icon" :title="setTitle(item,k)" />
          </span>
        </span>
        <i :key="item" v-else :class="item" @click="bindButton(item)"></i>
      </template>
      <slot name="tools" />
    </div>
  </div>
</template>

<script>
import emoji from '@/utils/emoji'
export default {
  name: "VChat-tools",
  props: {
    tools: {
      type: Object,
      default: () => {
        return {
          show: [],
          showEmoji: true,
          callback: () => { }
        }
      }
    }
  },
  data () {
    return {
      emoji,
      toolConfig: {
        'file': { icon: 'icon-wenjian', title: '文件' },
        'video': { icon: 'icon-shipin', title: '视频' },
        'img': { icon: 'icon-xiangce', title: '图片' },
        'hongbao': { icon: 'icon-hongbao', title: '红包' },
        'more': { icon: 'icon-gengduo', title: '更多' },
        'history': { icon: 'icon-lishi', title: '历史' },
      },
      newTitle: null
    }
  },
  methods: {
    showEmoji () {
      const { showEmoji = true } = this.tools || {}
      return showEmoji
    },
    showkeys () {
      let keys = Object.keys(this.toolConfig)
      const { show = [] } = this.tools || {}
      if (show.length > 0) {
        let _key = []
        let h = false
        show.forEach(i => {
          if (this.isArray(i)) {
            this.newTitle = i
            return
          }
          if (i === 'history') {
            h = true
          } else {
            _key.push(i)
          }
        })
        if (h) _key.push('history')
        keys = _key
      }
      return keys
    },
    setTitle (key, index) {
      let title = ''
      if (this.newTitle) {
        title = this.newTitle[index] || ''
      }
      if (!title) {
        title = this.toolConfig[key].title
      }
      return title
    },
    isArray (target) {
      return Object.prototype.toString.call(target) === '[object Array]';
    },
    selectEmit (type) {
      this.$emit('emoji', type)
      this.$refs.popover.doClose()
    },
    bindButton (type) {
      if (!this.tools.callback) return console.warn('callback not find')
      if (type === 'file') {
        this.openFile(type, this.tools.callback)
      } else {
        this.tools.callback(type)
      }
    },
    openFile (type, callback) {
      var input = document.createElement("input")
      input.type = "file"
      input.multiple = 'multiple'
      input.click();
      input.onchange = function () {
        var file = input.files
        callback(type, file)
      }
    },
    emojiStyle (item) {
      const emojiitem = this.emoji[item]
      if (!emojiitem) return {}
      return {
        display: 'inline-block',
        background: `url('https://res.wx.qq.com/a/wx_fed/webwx/res/static/img/6AfH8-r.png')  no-repeat`,
        width: `28px`,
        height: `28px`,
        'background-position': emojiitem.position,
      }
    },
  },
}
</script>

<style  scoped>
.toolsBox {
  position: relative;
}
.web__tools {
  text-align: left;
  padding-left: 8px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  height: 30px;
}
i {
  margin-right: 12px;
  font-size: 20px;
  color: #888a91;
}
i:hover {
  color: #76b1f9;
}
.emjioBox {
  background: #fff;
  height: 150px;
  width: 300px;
  overflow: auto;
  text-align: left;
}
.emjioBox .emjio {
  padding: 0;
}
.emjioBox li {
  display: inline-block;
  width: 28px;
  height: 28px;
  line-height: 28px;
  text-align: center;
  cursor: pointer;
}
</style>