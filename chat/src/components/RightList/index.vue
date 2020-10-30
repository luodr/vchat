<template>
  <div class="wrapper">
    <div class="notice" style="resolve">
      <VChat-empty class="empty" v-if="!info.notice" size="8rem" />
      <P style="padding-left:0.2rem">{{info.tip}}</P>
      <p style="font-size:0.8rem;margin-top:0.5rem;padding:0 .2rem">{{info.notice}}</p>
    </div>
    <div class="userList">
      <div>{{info.listTip}} ({{info.list.length}})</div>
      <ul>
        <li v-for="(item,k) in info.list" :key="k">
          <VChat-item size="25" :config="item" @click="bindClick" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'VChat-rightbox',
  props: {
    config: Object
  },
  computed: {
    info () {
      const config = this.config
      let tip = '群公告:'
      let notice = ''
      let listTip = '组成员'
      let list = []
      if (this.config) {
        config.tip && (tip = config.tip)
        config.notice && (notice = config.notice)
        config.listTip && (listTip = config.listTip)
        config.list && (list = config.list)
      }
      return {
        tip,
        notice,
        listTip,
        list
      }
    }
  },
  methods: {
    bindClick (type) {
      this.$emit('click', type)
    }
  }
}
</script>
<style scoped lang="scss">
.wrapper {
  padding: 0px;
  height: 100%;
  max-width: 100%;
  width: 0;
  min-width: 220px;
  margin: 0 auto;
}
li {
  list-style: none;
}
.notice {
  height: 30%;
  text-align: left;
  position: relative;
  .empty {
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
  }
}
.userList {
  height: 70%;
  text-align: left;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
.userList li {
  height: 2rem;
  line-height: 2rem;
  padding: 0.2rem;
}
</style>