<template>
  <div class="VChat-count" :style="style">
    <span class="title" v-if="getTitle">{{getTitle}}</span>
    <div class="number" :style="numberStyle">{{count}}</div>
    <div v-if="getUnit" :style="unitStyle">{{getUnit}}</div>
  </div>
</template>

<script>
export default {
  size: Number,
  name: 'VChat-count',
  props: {
    num: Number,
    config: {
      regulator: Number,
      time: Number,
      color: Array,
      unit: String,
      title: String,
      width: Array,
      height: String,
    }
  },
  data () {
    return {
      count: 0
    }
  },
  computed: {
    style () {
      const { color = [""], size = ["2rem"], width = "", height='' } = this.config || {}
      return {
        color: color[0],
        fontSize: size[0],
        width,
        height,
        lineHeight: height,
      }
    },
    getUnit () {
      let { unit = "" } = this.config || {}
      return unit
    },
    numberStyle () {
      const { color = [""], size = [""] } = this.config || {}
      const obj = {
        textAlign: 'center'
      }
      if (color[1]) obj.color = color[1]
      if (size[1]) obj.fontSize = size[1]
      return obj
    },
    unitStyle () {
      const { color = [""], size = [""] } = this.config || {}
      const obj = {}
      if (color[2]) obj.color = color[2]
      if (size[2]) obj.fontSize = size[2]
      return obj
    },
    getTitle () {
      const { title = "" } = this.config || {}
      return title
    },
  },
  watch: {
    num () {
      this.NumAutoPlusAnimation()
    }
  },
  methods: {
    NumAutoPlusAnimation () {
      const that = this
      const time = this.config && this.config.time || 1500; //总时间--毫秒为单位
      const finalNum = this.num; //要显示的真实数值
      const regulator = this.config && this.config.regulator || 100 //调速器，改变regulator的数值可以调节数字改变的速度

      const step = finalNum / (time / regulator)/*每30ms增加的数值--*/
      let count = 0 //计数器
      let initial = 0
      var timer = setInterval(function () {
        count = count + step;

        if (count >= finalNum) {
          clearInterval(timer);
          count = finalNum;
        }
        //t未发生改变的话就直接返回
        //避免调用text函数，提高DOM性能
        var t = Math.floor(count);
        if (t == initial) return;

        initial = t;
        that.count = initial
      }, 30);
    }
  },
  created () {
    this.NumAutoPlusAnimation()
  }
}
</script>
<style lang="scss" scoped>
.VChat-count {
  padding: 0px;
  margin: 0px;
  position: relative;
  overflow: hidden;
  display: flex;
  justify-items: center;
  align-items: center;
  margin: 0 auto;

  .title {
    margin-right: 0.2rem;
    text-align: center;
  }
}
</style>
