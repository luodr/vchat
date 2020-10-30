// 导入组件
import Chat from './Chat'
import ChatPage from './ChatInterface'
import Icon from './Icon'
import UserItem from './UserItem'
import Empty from './Empty'
import RightList from './RightList'
import QuickTalk from './QuickTalk'
import Count from './Count'
import UserCard from './UserCard'
import LoginBox from './LoginBox'

// 以数组结构保存组件，便于遍历
const components = [
  Chat,
  ChatPage,
  Icon,
  UserItem,
  Empty,
  RightList,
  QuickTalk,
  Count,
  UserCard,
  LoginBox,
]

// 定义 install 方法
const install = function (Vue) {
  if (install.installed) return
  install.installed = true

  // 遍历并注册全局组件
  components.map(component => {
    Vue.component(component.name, component)
  })
}

if (typeof window !== 'undefined' && window.Vue) {
  install(window.Vue)
}

export default {
  // 导出的对象必须具备一个 install 方法
  install,
  // 组件列表
  ...components
}