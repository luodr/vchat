<template>
  <span class="wrapper">
    
    <span class="web__msg--audio" v-if="getTag === 'span'" v-html="parseText()" />
    <img
      class="web__msg--img"
      v-if="getTag === 'img'"
      :src="getData.src"
      alt
      @click="showDialog({tag:'img'})"
    />
    <video
      class="web__msg--video"
      v-if="getTag === 'video'"
      :src="getData.src"
      controls="controls"
      @click="showDialog({tag:'video'})"
    />
    <audio
      class="web__msg--audio"
      v-if="getTag === 'audio'"
      style="width:20rem;height:20px;"
      :src="getData.src"
      controls="controls"
    />

    <el-dialog
      :visible.sync="show"
      width="40%"
      append-to-body
      :before-close="handleClose"
      class="web__dialog"
    >
      <img :src="imgSrc" v-if="imgSrc" style="width:100%;object-fit: cover;" />
      <video
        :src="videoSrc"
        v-if="videoSrc"
        style="width:100%;object-fit: cover;"
        controls="controls"
      ></video>
      <audio
        :src="audioSrc"
        v-if="audioSrc"
        style="width:100%;object-fit: cover;"
        controls="controls"
      ></audio>
    </el-dialog>
  </span>
</template>

<script>
import emojiParser from 'wechat-emoji-parser'
export default {
  props: {
    text: String,
  },
  data () {
    return {
      tags: ['img', 'video', 'audio'],
      /* 查看文件详情 */
      show: false,
      imgSrc: '',
      videoSrc: '',
      audioSrc: '',
    }
  },
  computed: {
    getTag () {
      const str = this.text || ""
      let tag = 'span'
      let type = ''

      const isTag = str.match(/(?!^<)[A-Z|a-z]+/)
      isTag && (type = isTag[0])
      if (this.tags.includes(type)) {
        tag = type
      }
      return tag
    },
    getData () {
      const str = this.text || ""
      const dom = document.createElement('div')
      dom.innerHTML = str
      const target = dom.firstChild

      const src = target.getAttribute('data-src')
      const controls = target.getAttribute('controls') || false
      return {
        src,
        controls
      }
    },
  },
  methods: {
    handleClose (done) {
      this.imgSrc = undefined;
      this.videoSrc = undefined;
      this.audioSrc = undefined;
      done();
    },
    showDialog ({ tag }) {
      const { src } = this.getData
      const callback = () => {
        if (tag === 'img') {
          this.imgSrc = src;
          this.show = true;
        } else if (tag === 'video') {
          this.videoSrc = src;
          this.show = true;
        } else if (tag === 'audio') {
          this.audioSrc = src;
          this.show = true;
        } else if (tag === 'FILE') {
          window.open(src)
        }
      }
      if (typeof this.beforeOpen === 'function') {
        this.beforeOpen({ tag, src }, callback)
      } else {
        callback();
      }
    },
    parseText () {
      let { text } = this
      if (!text) return
      text = text.replace(/\n/g,'<br/>')
      const html = emojiParser(text).replace(/<img src/g, '<img data-class="iconBox" src')
      return html
    },
    //处理排版
    /*  handleDetail (html = '') {
       let tag = 'span'
       let options = {}
       if (this.isObject(html)) {
         const { type, content } = html
         tag = type
         options = content
       } else {
         tag = this.getTag(html)
       }
 
       this.createdElement(tag, content)
 
       let result = html;
       result = emojiParser(result).replace(/(<img src)/g, '<img data-class="iconBox" data-src')
       setTimeout(() => {
         const list = this.$refs.content;
         list.forEach(ele => {
           for (let i = 0; i < ele.children.length; i++) {
             const child = ele.children[i];
             child.onload = this.childnodeLoad
             // console.log(child.parentNode)
             if (child.getAttribute('data-flag') != 0) {
               child.setAttribute('data-flag', 0)
               child.onclick = () => {
                 this.handleEvent(child)
               };
               if (child.tagName === 'IMG') {
                 child.className = 'web__msg--img'
                 const icon = child.getAttribute('data-class')
                 if (icon !== 'iconBox') child.type = "IMG"
                 child.src = child.getAttribute('data-src')
               } else if (child.tagName === 'VIDEO') {
                 child.type = "VIDEO"
                 child.className = 'web__msg--video'
                 child.src = child.getAttribute('data-src')
               } else if (child.tagName === 'AUDIO') {
                 child.type = "AUDIO"
                 child.className = 'web__msg--audio'
                 child.controls = 'controls';
                 child.src = child.getAttribute('data-src')
               } else if (child.tagName === 'FILE') {
                 child.type = "FILE"
                 child.className = 'web__msg--file'
                 child.innerHTML = `<h2>File</h2><span>${child.getAttribute('data-name')}</span>`
               } else if (child.tagName === 'MAP') {
                 child.type = "MAP"
                 child.className = 'web__msg--file web__msg--map'
                 child.innerHTML = `<h2>Map</h2><span>${child.getAttribute('data-longitude')} , ${child.getAttribute('data-latitude')}<br />${child.getAttribute('data-address')}</span>`
               }
             }
           }
         });
         console.log('n')
       }, 200)
       return result;
     }, */
  }
}
</script>
<style lange="scss" scoped>
.wrapper {
  padding: 0px;
  margin: 0px;
}
</style>