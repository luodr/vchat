<template>
  <div class="wrapper">
    <el-menu
      v-if="showHeader"
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item v-for="(item,key) in showNav" :index="`${key+1}`" :key="item">{{item}}</el-menu-item>
      <i
        :style="showAddBtn"
        class="addBtn el-icon-circle-plus-outline"
        @click="visible=true"
        title="新增"
      />
    </el-menu>
    <VChat-empty v-if="!Talelist.length" />
    <ul>
      <li v-for="(i,k) in Talelist" :key="i">
        <el-row>
          <el-col :span="(showDelete==false?19+2:19)">
            <p>{{i}}</p>
          </el-col>
          <el-col :span="(showDelete==false?5-2:5)" style="text-algin:right;">
            <i class="el-icon-circle-check" title="发送" @click="emit({key:'select',value: i})" />
            <i
              class="el-icon-circle-close"
              title="删除"
              v-if="showDelete"
              @click="emit({key:'delIndex',value: k})"
            />
          </el-col>
        </el-row>
      </li>
    </ul>

    <el-dialog title="新增快捷回复" :visible.sync="visible" width="36%" :modal-append-to-body="false">
      <el-input
        :rows="3"
        show-word-limit
        :maxlength="maxlength"
        placeholder="请输入快捷回复语"
        v-model="itemQuick"
        type="textarea"
      />
      <div style="text-align: right; margin: 0;margin-top:10px">
        <el-button size="mini" type="text" @click="visible = false">取消</el-button>
        <el-button type="primary" size="mini" @click="AddQuickFn">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'VChat-talk',
  props: {
    Talelist: Array,
    config: Object
  },
  data () {
    const { maxlength = 300 } = this.config
    return {
      activeIndex: '1',
      visible: false,
      itemQuick: '',
      maxlength
    }
  },
  computed: {
    showAddBtn () {
      let showBtn = true
      let visible = 'visible'
      const { showAdd = true } = this.config
      showBtn = showAdd
      if (!showBtn) {
        visible = 'hidden'
      }
      return {
        visibility: visible
      }
    },
    showNav () {
      let navList = ['快捷回复']
      const { nav } = this.config
      if (nav) {
        const [a, b] = nav
        navList = [a, b]
      }
      return navList
    },
    showDelete () {
      let show = true
      const { showDeleteBtn } = this.config || {}
      if (showDeleteBtn === false) {
        show = false
      }
      return show
    },
  },
  methods: {
    showHeader () {
      let heder = true;
      const { showHeader = true } = this.config || {}
      if (showHeader === false) {
        heder = false
      }
      return heder
    },
    handleSelect (index) {
      this.activeIndex = index
      this.emit({ key: 'navIndex', value: index })
    },
    AddQuickFn () {
      this.emit({ key: 'addTalk', value: this.itemQuick })
      this.visible = false
      this.$nextTick(() => {
        this.itemQuick = ""
      })
    },
    emit (play) {
      this.$emit('event', play)
    }
  }
}
</script>
<style scoped>
.el-menu-demo {
  width: 100%;
  height: 42px;
  line-height: 40px;
  display: flex;
  position: relative;
}
.el-menu-demo > * {
  height: unset;
  line-height: unset;
  width: 40%;
  padding: 0;
  text-align: center;
  box-shadow: none;
}
.addBtn {
  width: 20%;
  position: absolute;
  right: 0;
}
.addBtn:focus {
  outline: none;
  text-align: center;
}
.wrapper {
  padding: 0;
  height: 100%;
  width: 0;
  max-width: 100%;
  min-width: 220px;
  margin: 0 auto;
  text-align: left;
  position: relative;
}
ul {
  padding: 0;
  list-style: none;
  height: 84%;
  overflow: auto;
}
li {
  padding: 0.5rem;
  padding-right: 0;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  margin: 0.2rem 0;
}
li p {
  margin: 0;
  /* width: 95%; */
}
li:hover i {
  display: inline-block;
}
li i {
  /* display: none; */
  margin: 0 0.05rem;
}
i:hover {
  color: #409eff;
  cursor: pointer;
}
</style>