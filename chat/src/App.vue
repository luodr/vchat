<template>
  <div id="app" class="wrapper">

    <itemTalk />
    <!-- <VChat-index
      :config="config"
      :taleList="list"
      @enter="bindEnter"
      v-model="inputMsg"
      :toolConfig="tool"
      :showRightBox='false'
      scrollType="norell"
      @clickTalk="talkEvent"
    >

    </VChat-index> -->
    
   
  </div>
</template>

<script>
import itemTalk from '@/components/Chat/itemTalk'

export default {
  components: { itemTalk },
  data () {
    return {
      num: 5000,
      inputMsg: '',
      list: [],
      tool: {
        // show: ['file', 'history', 'img', ['文件1', '', '美图']],
        // showEmoji: false,
        callback: this.toolEvent
      },
      rightConfig: {
        listTip: '当前在线',
        // notice: '【公告】这是一款高度自由的聊天组件，基于AVue、Vue、Element-ui开发。点个赞再走吧 ',
        list: [
          {
            name: 'VChat',
            "img": "image/three.jpeg"
          },
          {
            name: 'VChat',
            "img": "image/three.jpeg"
          },
          {
            name: '留恋人间不羡仙',
            "img": "image/one.jpeg"
          },
          {
            name: '只盼流星不盼雨',
            "img": "image/two.jpeg"
          }
        ]
      },
      quickConfig: {
        nav: ['快捷回复', '超级回复'],
        showAdd: true,
        maxlength: 200,
        showHeader: true,
        showDeleteBtn: true,
      },
      talk: [
        '快捷回复1',
        '快捷回复2',
        '快捷回复3',
        '快捷回复4',
        '快捷回复5',
        '快捷回复6',
      ],
      config: {
        img: '/image/cover.png',
        name: 'VChat',
        dept: '最简单、最便捷',
        callback: this.bindCover,
        historyConfig:{
          tip: '加载更多',
          callback: this.bindLoadHistory,
        }
      },
    }
  },
  methods: {
    bindLoadHistory () {
      const history = new Array(3).fill().map((i, j) => {
        return {
          "date": "2020/05/20 23:19:07",
          "text": { "text": j + new Date() },
          "mine": false,
          "name": "VChat",
          "img": "image/three.jpeg"
        }
      })
      let list = history.concat(this.list)
      this.list = list
      console.log('加载历史', list, history)
    },
    change () {
      this.num++
    },
    talkEvent (play) {
      console.log(play)
    },
    bindEnter () {
      const msg = this.inputMsg
      if (!msg) return;
      const msgObj = {
        "date": "2020/05/20 23:19:07",
        "text": { "text": msg },
        "mine": true,
        "name": "VChat",
        "img": "image/three.jpeg"
      }
      this.list.push(msgObj)
    },
    toolEvent (type, plyload) {
      console.log('tools', type, plyload)
    },
    bindCover (type) {
      console.log('header', type)
    },
    rightClick (type) {
      console.log('rigth', type)
    },
    bindTalk (play) {
      console.log('talk', play)
    }
  },
  mounted () {
    this.list = [
      {
        "date": "2020/04/25 21:19:07",
        "text": { "text": "起床不" },
        "mine": false,
        "name": "留恋人间不羡仙",
        "img": "/image/one.jpeg"
      },
      {
        "date": "2020/04/25 21:19:07",
        "text": { "text": "<audio data-src='https://www.w3school.com.cn/i/horse.mp3'/>" },
        "mine": false,
        "name": "只盼流星不盼雨",
        "img": "/image/two.jpeg"
      },
      {
        "date": "2020/04/25 21:19:07",
        "text": { "text": "<img data-src='/image/three.jpeg'/>" },
        "mine": false,
        "name": "只盼流星不盼雨",
        "img": "/image/two.jpeg"
      },
      {
        "date": "2020/04/16 21:19:07",
        "text": { "text": "<video data-src='https://www.w3school.com.cn/i/movie.mp4' controls='controls' />" },
        "mine": true,
        "name": "VChat",
        "img": "/image/three.jpeg"
      },
    ]
  }
}
</script>
<style scoped>
.rightSlot {
  width: 100%;
  height: 100%;
  overflow: hidden;
  flex-direction: column;
}
</style>
<style>
.active {
  color: red;
}
* {
  padding: 0;
  margin: 0;
}
body {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  background-image: linear-gradient(to bottom right, #7a88ff, #7affaf);
}
#app {
  height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
