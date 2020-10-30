
// 导入组件，组件必须声明 name
import Chat_cover from './index.vue'

// 为组件添加 install 方法，用于按需引入
Chat_cover.install = function (Vue) {
  Vue.component(Chat_cover.name, Chat_cover)
}

export default Chat_cover