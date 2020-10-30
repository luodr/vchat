
// 导入组件，组件必须声明 name
import Chat from './index.vue'

// 为组件添加 install 方法，用于按需引入
Chat.install = function (Vue) {
  Vue.component(Chat.name, Chat)
}

export default Chat